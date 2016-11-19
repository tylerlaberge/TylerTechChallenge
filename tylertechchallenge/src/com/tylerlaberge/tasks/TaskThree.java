package com.tylerlaberge.tasks;

import com.tylerlaberge.domain.Cart;
import com.tylerlaberge.domain.FoodItem;
import com.tylerlaberge.domain.Shopper;

import java.util.*;


public class TaskThree extends Task {

    public TaskThree(HashMap<String, String> constraints, List<HashMap<String, String>> inventory_details) {
        super(constraints, inventory_details);
    }
    @Override
    protected Shopper buildShopper(HashMap<String, String> constraints) {
        return new Shopper(
                Double.parseDouble(constraints.get("budget")),
                new Cart(Double.parseDouble(constraints.get("weight_limit")))
        );
    }
    @Override
    public String solve() {
        HashMap<String, List<FoodItem>> food_group_map = this.getFoodGroupMap();
        List<FoodItem> optimal_food_group_items_list = this.getOptimalFoodGroupItemsList(food_group_map);
        List<FoodItem> purchasable_list = new ArrayList<>(optimal_food_group_items_list);
        List<String> food_group_names = new ArrayList<>(food_group_map.keySet());
        HashMap<String, Double> food_group_distribution = this.shopper.getCart().getFoodGroupDistribution(food_group_names);

        this.shopper.fillCart(purchasable_list);
        while(!this.shopper.getCart().foodGroupDistributionBalanced(food_group_names)){
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
            int max_distributed_food_item_amount = this.shopper.getCart().getFoodItems().get(max_distributed_food_item);

            if (max_distributed_food_item_amount > 1) {
                this.shopper.removeFromCart(max_distributed_food_item, 1);
            }
            purchasable_list.remove(max_distributed_food_item);
            this.shopper.fillCart(purchasable_list);

            food_group_distribution = this.shopper.getCart().getFoodGroupDistribution(food_group_names);
        }
        return this.shopper.getCart().toString();
    }
    private List<FoodItem> getOptimalFoodGroupItemsList(HashMap<String, List<FoodItem>> food_group_map) {
        List<FoodItem> optimal_food_group_items_list = new ArrayList<>();
        for (List<FoodItem> food_list : food_group_map.values()) {
            Collections.sort(food_list, this.shopper.mostOptimalFoodItemComparator());
            optimal_food_group_items_list.add(food_list.get(0));
        }
        Collections.sort(optimal_food_group_items_list, this.shopper.leastOptimalFoodItemComparator());

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
    private HashMap<String, List<FoodItem>> getFoodGroupMap () {
        HashMap<String, List<FoodItem>> food_group_map = new HashMap<>();
        for (FoodItem food_item : this.inventory) {
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