package com.tylerlaberge.domain;

import java.util.*;

/**
 * A class for maintaining a collection of FoodItems without going over weight or volume constraints..
 */
public class Cart {

    private final double WEIGHT_LIMIT;
    private final double VOLUME_LIMIT;
    private HashMap<FoodItem, Integer> food_items = new HashMap<>();
    private HashMap<String, Double> food_group_distribution = new HashMap<>();

    private double current_weight = 0;
    private double current_volume = 0;

    /**
     * Create a new Cart instance.
     */
    public Cart() {
        this.WEIGHT_LIMIT = Double.POSITIVE_INFINITY;
        this.VOLUME_LIMIT = Double.POSITIVE_INFINITY;

        this.food_group_distribution.put("grains", 0.0);
        this.food_group_distribution.put("dairy", 0.0);
        this.food_group_distribution.put("veggies", 0.0);
        this.food_group_distribution.put("meat", 0.0);
    }

    /**
     * Create a new Cart instance.
     *
     * @param weight_limit  A weight limit constraint for the Cart.
     */
    public Cart(double weight_limit) {
        this.WEIGHT_LIMIT = weight_limit;
        this.VOLUME_LIMIT = Double.POSITIVE_INFINITY;

        this.food_group_distribution.put("grains", 0.0);
        this.food_group_distribution.put("dairy", 0.0);
        this.food_group_distribution.put("veggies", 0.0);
        this.food_group_distribution.put("meat", 0.0);
    }

    /**
     * Create a new Cart instance.
     *
     * @param weight_limit  A weight limit constraint for the Cart.
     * @param volume_limit  A volume limit constraint for the Cart.
     */
    public Cart(double weight_limit, double volume_limit) {
        this.WEIGHT_LIMIT = weight_limit;
        this.VOLUME_LIMIT = volume_limit;

        this.food_group_distribution.put("grains", 0.0);
        this.food_group_distribution.put("dairy", 0.0);
        this.food_group_distribution.put("veggies", 0.0);
        this.food_group_distribution.put("meat", 0.0);
    }

    /**
     * Add a FoodItem to this Cart.
     *
     * @param food_item The FoodItem to add to this Cart.
     * @param quantity  The number of this FoodItem to add to this Cart.
     * @throws IllegalArgumentException If this Cart's weight or volume limit is surpassed
     *                                  or if there is not enough of the FoodItem in stock.
     */
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
                } else {
                    throw new IllegalArgumentException("Surpassed cart weight/volume limit.");
                }
            } else {
                throw new IllegalArgumentException("Not enough stock for the food item.");
            }
        }
    }

    /**
     * Remove a FoodItem from this Cart.
     *
     * @param food_item The FoodItem to remove from this Cart.
     * @param quantity  The number of this FoodItem to remove from this Cart.
     * @throws IllegalArgumentException If the quantity to remove is more than the number of this FoodItem in this Cart,
     *                                  or the FoodItem is not in the Cart.
     */
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
        } else {
            throw new IllegalArgumentException("No such food item in the cart");
        }
    }

    /**
     * Updates the food group distribution of the FoodItems in this Cart.
     */
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

    /**
     * Get whether or not this Cart contains any FoodItems.
     *
     * @return  Whether or not this Cart contains any FoodItems.
     */
    public boolean isEmpty() {
        return this.food_items.isEmpty();
    }

    /**
     * Get whether or not the food group distribution of this Cart is balanced.
     *
     * @return  Whether or not the food group distribution of the Cart is balanced.
     */
    public boolean isBalanced() {
        this.updateFoodGroupDistribution();
        double num_food_groups = this.food_group_distribution.size();
        for (double distribution : this.food_group_distribution.values()) {
            if (distribution < 1 / num_food_groups - 0.05 || distribution > 1 / num_food_groups + 0.05) {
                return false;
            }
        }
        return true;
    }

    /**
     * Get the most distributed food group of this Cart.
     *
     * @return  The most distributed food group of this Cart.
     */
    public String getMostDistributedFoodGroup() {
        this.updateFoodGroupDistribution();
        String max_distributed_food_group = null;
        for (String food_group : this.food_group_distribution.keySet()) {
            if (max_distributed_food_group == null ||
                    this.food_group_distribution.get(food_group) > this.food_group_distribution.get(max_distributed_food_group)) {
                max_distributed_food_group = food_group;
            }
        }
        return max_distributed_food_group;
    }

    /**
     * Get the most distributed food item of this Cart, filtered by a food group.
     *
     * @param food_group    The food group to get the most distributed food item for.
     * @return              The most distributed food item in this Cart that is part of the given food group.
     */
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

    /**
     * Get the weight limit of this Cart.
     *
     * @return The weight limit of this Cart.
     */
    public double getWeightLimit() {
        return WEIGHT_LIMIT;
    }

    /**
     * Get the volume limit of this Cart.
     *
     * @return The volume limit of this Cart.
     */
    public double getVolumeLimit() {
        return VOLUME_LIMIT;
    }

    /**
     * Get the current weight of this Cart being taken up by FoodItems.
     *
     * @return The current weight of this Cart.
     */
    public double getCurrentWeight() {
        return current_weight;
    }

    /**
     * Get the current volume of this Cart being take up by FoodItems.
     *
     * @return The current volume of this Cart.
     */
    public double getCurrentVolume() {
        return current_volume;
    }

    /**
     * Get the remaining unused weight of this Cart.
     *
     * @return The remaining weight of this Cart.
     */
    public double getRemainingWeight() {
        return this.WEIGHT_LIMIT - this.current_weight;
    }

    /**
     * Get the remaining unused volume of this Cart.
     *
     * @return The remaining volume of this Cart.
     */
    public double getRemainingVolume() {
        return this.VOLUME_LIMIT - this.current_volume;
    }

    /**
     * Get the FoodItems and their amounts that are in this Cart.
     *
     * @return A Map of the FoodItems in this Cart mapped to how many are in this Cart.
     */
    public HashMap<FoodItem, Integer> getFoodItems() {
        return food_items;
    }

    /**
     * Get a String representation of this Cart.
     *
     * @return The string representation of this Cart.
     */
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