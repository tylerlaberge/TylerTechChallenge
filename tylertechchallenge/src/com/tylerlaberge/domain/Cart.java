package com.tylerlaberge.domain;

import java.util.*;

public class Cart {

    private final double WEIGHT_LIMIT;
    private final double VOLUME_LIMIT;
    private HashMap<FoodItem, Integer> food_items = new HashMap<>();
    private HashMap<String, Double> food_group_distribution = new HashMap<>();

    private double current_weight = 0;
    private double current_volume = 0;

    public Cart() {
        this.WEIGHT_LIMIT = Double.POSITIVE_INFINITY;
        this.VOLUME_LIMIT = Double.POSITIVE_INFINITY;

        this.food_group_distribution.put("grains", 0.0);
        this.food_group_distribution.put("dairy", 0.0);
        this.food_group_distribution.put("veggies", 0.0);
        this.food_group_distribution.put("meat", 0.0);
    }

    public Cart(double weight_limit) {
        this.WEIGHT_LIMIT = weight_limit;
        this.VOLUME_LIMIT = Double.POSITIVE_INFINITY;

        this.food_group_distribution.put("grains", 0.0);
        this.food_group_distribution.put("dairy", 0.0);
        this.food_group_distribution.put("veggies", 0.0);
        this.food_group_distribution.put("meat", 0.0);
    }

    public Cart(double weight_limit, double volume_limit) {
        this.WEIGHT_LIMIT = weight_limit;
        this.VOLUME_LIMIT = volume_limit;

        this.food_group_distribution.put("grains", 0.0);
        this.food_group_distribution.put("dairy", 0.0);
        this.food_group_distribution.put("veggies", 0.0);
        this.food_group_distribution.put("meat", 0.0);
    }

    public void addFoodItem(FoodItem food_item, int quantity) throws IllegalArgumentException {
        if (quantity > 0) {
            if (food_item.getStock() >= quantity) {
                if (this.current_weight + food_item.getWeight() * quantity <= this.WEIGHT_LIMIT
                        && this.current_volume + food_item.getVolume() * quantity <= this.VOLUME_LIMIT) {
                    if (this.food_items.containsKey(food_item)) {
                        int amount = this.food_items.get(food_item);
                        this.food_items.put(food_item, amount + quantity);
                    } else {
                        this.food_items.put(food_item, quantity);
                    }
                    food_item.setStock(food_item.getStock() - quantity);
                    this.current_weight += food_item.getWeight() * quantity;
                    this.current_volume += food_item.getVolume() * quantity;
                    this.updateFoodGroupDistribution();
                } else {
                    throw new IllegalArgumentException("Surpassed cart weight/volume limit.");
                }
            } else {
                throw new IllegalArgumentException("Not enough stock for the food item.");
            }
        }
    }

    public void removeFoodItem(FoodItem food_item, int quantity) {
        if (this.food_items.containsKey(food_item)) {
            int current_amount = this.food_items.get(food_item);
            int after_amount = current_amount - quantity;
            if (after_amount > 0) {
                this.food_items.put(food_item, after_amount);
            } else if (after_amount == 0) {
                this.food_items.remove(food_item);
            } else {
                throw new IllegalArgumentException("Cannot remove more food items than there are in the cart.");
            }
            this.current_weight -= food_item.getWeight() * quantity;
            this.current_volume -= food_item.getVolume() * quantity;
            food_item.setStock(food_item.getStock() + quantity);
            this.updateFoodGroupDistribution();
        } else {
            throw new IllegalArgumentException("No such food item in the cart");
        }
    }

    private void updateFoodGroupDistribution() {
        HashMap<String, List<FoodItem>> cart_food_groups = new HashMap<>();

        int food_group_quantity_sum = 0;
        for (FoodItem food_item : this.food_items.keySet()) {
            String food_group = food_item.getFoodGroup();
            List<FoodItem> food_group_list;
            if (cart_food_groups.containsKey(food_group)) {
                food_group_list = cart_food_groups.get(food_group);
            } else {
                food_group_list = new ArrayList<>();
            }
            food_group_list.add(food_item);
            cart_food_groups.put(food_group, food_group_list);
            food_group_quantity_sum += this.food_items.get(food_item);
        }
        for (String food_group : this.food_group_distribution.keySet()) {
            double distribution = 0.0;
            if (cart_food_groups.containsKey(food_group)) {
                double amount = 0;
                for (FoodItem food_item : cart_food_groups.get(food_group)) {
                    amount += this.food_items.get(food_item);
                }
                distribution = amount / food_group_quantity_sum;
            }
            this.food_group_distribution.put(food_group, distribution);
        }
    }

    public boolean isEmpty() {
        return this.food_items.isEmpty();
    }

    public boolean isBalanced() {
        double num_food_groups = this.food_group_distribution.size();
        for (double distribution : this.food_group_distribution.values()) {
            if (distribution < 1 / num_food_groups - 0.05 || distribution > 1 / num_food_groups + 0.05) {
                return false;
            }
        }
        return true;
    }

    public String getMostDistributedFoodGroup() {
        String max_distributed_food_group = null;
        for (String food_group : this.food_group_distribution.keySet()) {
            if (max_distributed_food_group == null ||
                    this.food_group_distribution.get(food_group) > this.food_group_distribution.get(max_distributed_food_group)) {
                max_distributed_food_group = food_group;
            }
        }
        return max_distributed_food_group;
    }

    public FoodItem getMostDistributedFoodItemByFoodGroup(String food_group) {

        FoodItem most_distributed_food_item = null;
        for (FoodItem food_item : this.getFoodItems().keySet()) {
            if (food_item.getFoodGroup().equals(food_group) &&
                    (
                            most_distributed_food_item == null ||
                                    this.getFoodItems().get(food_item) > this.getFoodItems().get(most_distributed_food_item)
                    )) {
                most_distributed_food_item = food_item;
            }
        }
        return most_distributed_food_item;
    }

    public boolean containsFoodItem(FoodItem food_item) {
        return this.food_items.containsKey(food_item);
    }

    public HashMap<String, Double> getFoodGroupDistribution() {
        return this.food_group_distribution;
    }

    public double getWeightLimit() {
        return WEIGHT_LIMIT;
    }

    public double getVolumeLimit() {
        return VOLUME_LIMIT;
    }

    public double getCurrentWeight() {
        return current_weight;
    }

    public double getRemainingWeight() {
        return this.WEIGHT_LIMIT - this.current_weight;
    }

    public double getRemainingVolume() {
        return this.VOLUME_LIMIT - this.current_volume;
    }

    public double getCurrentVolume() {
        return current_volume;
    }

    public HashMap<FoodItem, Integer> getFoodItems() {
        return food_items;
    }

    public String toString() {
        StringJoiner joiner = new StringJoiner("\n");
        List<FoodItem> food_item_list = new ArrayList<>(this.food_items.keySet());
        Collections.sort(food_item_list, new Comparator<FoodItem>() {
            @Override
            public int compare(FoodItem o1, FoodItem o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });
        for (FoodItem food_item : food_item_list) {
            joiner.add(String.format("%s, %d;", food_item.getName(), this.food_items.get(food_item)));
        }
        return joiner.toString();
    }
}
