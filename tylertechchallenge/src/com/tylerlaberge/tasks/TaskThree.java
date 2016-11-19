package com.tylerlaberge.tasks;

import com.tylerlaberge.domain.Cart;
import com.tylerlaberge.domain.FoodItem;
import com.tylerlaberge.domain.Shopper;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.jar.Pack200;


public class TaskThree extends Task {

    @Override
    protected Shopper buildShopper(HashMap<String, String> constraints) {
        return new Shopper(
                Double.parseDouble(constraints.get("budget")),
                new Cart(Double.parseDouble(constraints.get("weight_limit")))
        );
    }

    protected Double calculatePriceWeight(Shopper shopper) {
        return  1/shopper.getBudget()/(1/shopper.getBudget() + 1/shopper.getCart().getWeightLimit());
    }
    protected Double calculateWeightWeight(Shopper shopper) {
        return  1/shopper.getCart().getWeightLimit()/(1/shopper.getBudget() + 1/shopper.getCart().getWeightLimit());
    }
    protected void setFoodItemValues(List<FoodItem> inventory, double price_weight, double weight_weight) {
        double raw_food_item_values_sum = 0;
        for (FoodItem food_item : inventory){
            double raw_food_item_value = food_item.getPrice()*price_weight + food_item.getWeight()*weight_weight;
            food_item.setValue(raw_food_item_value);
            raw_food_item_values_sum += raw_food_item_value;
        }
        for (FoodItem food_item : inventory) {
            food_item.setValue(food_item.getValue()/raw_food_item_values_sum);
        }
    }
    protected HashMap<String, List<FoodItem>> createFoodGroupMap (List<FoodItem> inventory) {
        HashMap<String, List<FoodItem>> food_group_map = new HashMap<>();
        for (FoodItem food_item : inventory) {
            String food_group = food_item.getFood_group();
            List<FoodItem> food_list;
            if (food_group_map.containsKey(food_group)) {
                food_list = food_group_map.get(food_group);
            }
            else {
                food_list = new ArrayList<>();
            }
            food_list.add(food_item);
            food_group_map.put(food_group, food_list);
        }
        return food_group_map;
    }
    protected void sortByMostOptimal (List<FoodItem> food_item_list) {
        Collections.sort(food_item_list, (FoodItem food_item_one, FoodItem food_item_two) -> {
            if (food_item_one.getValue() == food_item_two.getValue()) {
                return 0;
            }
            else {
                return food_item_one.getValue() < food_item_two.getValue() ? -1 : 1;
            }
        });
    }
    protected void sortByLeastOptimal (List<FoodItem> food_item_list) {
        Collections.sort(food_item_list, (FoodItem food_item_one, FoodItem food_item_two) -> {
            if (food_item_one.getValue() == food_item_two.getValue()) {
                return 0;
            }
            else {
                return food_item_one.getValue() < food_item_two.getValue() ? 1 : -1;
            }
        });
    }
    protected void fillCart(Shopper shopper, List<FoodItem> food_item_list) {
        for (FoodItem food_item : food_item_list) {
            int quantity = (int)Math.min(
                    shopper.getRemainingBudget() / food_item.getPrice(),
                    shopper.getCart().getRemainingWeight() / food_item.getWeight()
            );
            if (quantity >= food_item.getStock()) {
                shopper.addToCart(food_item, food_item.getStock());
            }
            else if (quantity > 1) {
                shopper.addToCart(food_item, quantity);
            }
        }
    }
    protected HashMap<String, Double> getFoodGroupDistribution(List<String> food_groups, Cart cart) {
        HashMap<String, Double> food_group_distribution = new HashMap<>();
        for (String food_group : food_groups) {
            food_group_distribution.put(food_group, 0.0);
        }

        HashMap<String, List<FoodItem>> cart_food_groups = new HashMap<>();
        int quantity_sum = 0;
        for (FoodItem food_item : cart.getFoodItems().keySet()) {
            String food_group = food_item.getFood_group();
            List<FoodItem> food_group_list;
            if (cart_food_groups.containsKey(food_group)) {
                food_group_list = cart_food_groups.get(food_group);
            }
            else {
                food_group_list = new ArrayList<>();
            }
            food_group_list.add(food_item);
            cart_food_groups.put(food_group, food_group_list);
            quantity_sum += cart.getFoodItems().get(food_item);
        }

        for (String food_group : cart_food_groups.keySet()) {
            double amount = 0;
            for (FoodItem food_item : cart_food_groups.get(food_group)) {
                amount += cart.getFoodItems().get(food_item);
            }
            double distribution = amount/quantity_sum;
            food_group_distribution.put(food_group, distribution);
        }
        return food_group_distribution;
    }
    protected FoodItem getMaxDistributedFoodItem(HashMap<String, Double> food_group_distribution, List<FoodItem> food_items){
        String max_distributed_food_group = null;
        for(String food_group : food_group_distribution.keySet()) {
            if (max_distributed_food_group == null) {
                max_distributed_food_group = food_group;
            }
            else if (food_group_distribution.get(food_group) > food_group_distribution.get(max_distributed_food_group)){
                max_distributed_food_group = food_group;
            }
        }
        for (FoodItem food_item : food_items) {
            if (food_item.getFood_group().equals(max_distributed_food_group)) {
                return food_item;
            }
        }
        return null;
    }
    protected FoodItem getMinDistributedFoodItem(HashMap<String, Double> food_group_distribution, List<FoodItem> food_items){
        String min_distributed_food_group = null;
        for(String food_group : food_group_distribution.keySet()) {
            if (min_distributed_food_group == null) {
                min_distributed_food_group = food_group;
            }
            else if (food_group_distribution.get(food_group) < food_group_distribution.get(min_distributed_food_group)){
                min_distributed_food_group = food_group;
            }
        }
        for (FoodItem food_item : food_items) {
            if (food_item.getFood_group().equals(min_distributed_food_group)) {
                return food_item;
            }
        }
        return null;
    }
    protected boolean foodGroupDistributionBalanced(HashMap<String, Double> food_group_distribution) {
        double num_food_groups = 4;
        for (double distribution : food_group_distribution.values()) {
            if (distribution < 1/num_food_groups - 0.05 || distribution > 1/num_food_groups + 0.05) {
                return false;
            }
        }
        return true;
    }
    @Override
    protected String solve(Shopper shopper, List<FoodItem> inventory) {
        double price_weight = this.calculatePriceWeight(shopper);
        double weight_weight = this.calculateWeightWeight(shopper);

        this.setFoodItemValues(inventory, price_weight, weight_weight);
        HashMap<String, List<FoodItem>> food_group_map = this.createFoodGroupMap(inventory);

        List<FoodItem> optimal_food_items_list = new ArrayList<>();
        for (List<FoodItem> food_list : food_group_map.values()) {
            this.sortByMostOptimal(food_list);
            optimal_food_items_list.add(food_list.get(0));
        }
        this.sortByLeastOptimal(optimal_food_items_list);
        this.fillCart(shopper, optimal_food_items_list);

        HashMap<String, Double> food_group_distribution = this.getFoodGroupDistribution(new ArrayList<>(food_group_map.keySet()), shopper.getCart());
        while(!this.foodGroupDistributionBalanced(food_group_distribution)){
            FoodItem max_distributed_food_item = getMaxDistributedFoodItem(food_group_distribution, optimal_food_items_list);
            FoodItem min_distributed_food_item = getMinDistributedFoodItem(food_group_distribution, optimal_food_items_list);

            int max_distributed_food_item_amount = shopper.getCart().getFoodItems().get(max_distributed_food_item);

            if (max_distributed_food_item_amount == 1) {
                shopper.addToCart(min_distributed_food_item, 1);
            }
            else {
                shopper.removeFromCart(max_distributed_food_item, max_distributed_food_item_amount/2);
                shopper.addToCart(min_distributed_food_item, max_distributed_food_item_amount/2);
            }

            food_group_distribution = this.getFoodGroupDistribution(new ArrayList<>(food_group_map.keySet()), shopper.getCart());
        }
        return shopper.getCart().toString();
    }
}