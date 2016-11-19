package com.tylerlaberge.tasks;

import com.tylerlaberge.domain.Cart;
import com.tylerlaberge.domain.FoodItem;
import com.tylerlaberge.domain.Shopper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;


public class TaskThree extends Task {

    @Override
    protected Shopper buildShopper(HashMap<String, String> constraints) {
        return new Shopper(
                Double.parseDouble(constraints.get("budget")),
                new Cart(Double.parseDouble(constraints.get("weight_limit")))
        );
    }
    @Override
    protected String solve(Shopper shopper, List<FoodItem> inventory) {
        double price_weight = shopper.getBudgetWeight();
        double weight_weight = shopper.getWeightLimitWeight();
        this.setFoodItemValues(inventory, price_weight, weight_weight);

        HashMap<String, List<FoodItem>> food_group_map = this.getFoodGroupMap(inventory);
        List<FoodItem> optimal_food_group_items_list = this.getOptimalFoodGroupItemsList(food_group_map);
        List<FoodItem> purchasable_list = new ArrayList<>(optimal_food_group_items_list);
        HashMap<String, Double> food_group_distribution = shopper.getCart().getFoodGroupDistribution(new ArrayList<>(food_group_map.keySet()));

        shopper.fillCart(purchasable_list);
        while(!shopper.getCart().foodGroupDistributionBalanced(new ArrayList<>(food_group_map.keySet()))){
            if (purchasable_list.isEmpty()) {
                purchasable_list = new ArrayList<>(optimal_food_group_items_list);
            }
            List<FoodItem> max_distributed_food_items = this.getMaxDistributedFoodItems(food_group_distribution, optimal_food_group_items_list);

            int index = -1;
            for (FoodItem food_item : max_distributed_food_items) {
                int optimal_list_index = optimal_food_group_items_list.indexOf(food_item);
                if (index == -1 || optimal_list_index < index) {
                    index = optimal_list_index;
                }
            }

            FoodItem max_distributed_food_item = optimal_food_group_items_list.get(index);
            int max_distributed_food_item_amount = shopper.getCart().getFoodItems().get(max_distributed_food_item);

            if (max_distributed_food_item_amount > 1) {
                shopper.removeFromCart(max_distributed_food_item, 1);
            }
            purchasable_list.remove(max_distributed_food_item);
            shopper.fillCart(purchasable_list);

            food_group_distribution = shopper.getCart().getFoodGroupDistribution(new ArrayList<>(food_group_map.keySet()));
        }
        return shopper.getCart().toString();
    }
    private void setFoodItemValues(List<FoodItem> inventory, double price_weight, double weight_weight) {
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
    private List<FoodItem> getOptimalFoodGroupItemsList(HashMap<String, List<FoodItem>> food_group_map) {
        List<FoodItem> optimal_food_group_items_list = new ArrayList<>();
        for (List<FoodItem> food_list : food_group_map.values()) {
            Collections.sort(food_list, FoodItem.mostOptimalComparator());
            optimal_food_group_items_list.add(food_list.get(0));
        }
        Collections.sort(optimal_food_group_items_list, FoodItem.leastOptimalComparator());

        return optimal_food_group_items_list;
    }
    private List<FoodItem> getMaxDistributedFoodItems(HashMap<String, Double> food_group_distribution, List<FoodItem> food_items){
        List<String> max_distributed_food_groups = new ArrayList<>();
        for(String food_group : food_group_distribution.keySet()) {
            if (max_distributed_food_groups.isEmpty()) {
                max_distributed_food_groups.add(food_group);
            }
            else if (food_group_distribution.get(food_group) > food_group_distribution.get(max_distributed_food_groups.get(0))){
                max_distributed_food_groups.clear();
                max_distributed_food_groups.add(food_group);
            }
            else if (food_group_distribution.get(food_group).equals(food_group_distribution.get(max_distributed_food_groups.get(0)))) {
                max_distributed_food_groups.add(food_group);
            }
        }
        List<FoodItem> max_distributed_food_items = new ArrayList<>();
        for (FoodItem food_item : food_items) {
            if (max_distributed_food_groups.contains(food_item.getFood_group())) {
                max_distributed_food_items.add(food_item);
            }
        }
        return max_distributed_food_items;
    }
    private HashMap<String, List<FoodItem>> getFoodGroupMap (List<FoodItem> food_items) {
        HashMap<String, List<FoodItem>> food_group_map = new HashMap<>();
        for (FoodItem food_item : food_items) {
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
}