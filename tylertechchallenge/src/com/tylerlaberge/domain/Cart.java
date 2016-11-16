package com.tylerlaberge.domain;

import jdk.nashorn.internal.runtime.regexp.joni.exception.ValueException;

import java.util.HashMap;
import java.util.StringJoiner;

public class Cart {

    private final double WEIGHT_LIMIT;
    private final double VOLUME_LIMIT;
    private HashMap<String, Integer> food_items = new HashMap<>();
    private double current_weight = 0;
    private double current_volume = 0;

    public Cart() {
        this.WEIGHT_LIMIT = Double.POSITIVE_INFINITY;
        this.VOLUME_LIMIT = Double.POSITIVE_INFINITY;
    }

    public Cart(double weight_limit, double volume_limit) {
        this.WEIGHT_LIMIT = weight_limit;
        this.VOLUME_LIMIT = volume_limit;
    }

    public void addFoodItem(FoodItem food_item, int quantity) throws ValueException {
        if (quantity > 0) {
            if (food_item.getStock() >= quantity) {
                String food_item_name = food_item.getName();
                if (this.current_weight + food_item.getWeight() * quantity <= this.WEIGHT_LIMIT
                        && this.current_volume + food_item.getVolume() * quantity <= this.VOLUME_LIMIT) {
                    if (this.food_items.containsKey(food_item_name)) {
                        int amount = this.food_items.get(food_item_name);
                        this.food_items.put(food_item_name, amount + quantity);
                    } else {
                        this.food_items.put(food_item_name, quantity);
                    }
                    food_item.setStock(food_item.getStock() - quantity);
                    this.current_weight += food_item.getWeight() * quantity;
                    this.current_volume += food_item.getVolume() * quantity;
                } else {
                    throw new ValueException("Surpassed cart weight/volume limit");
                }
            } else {
                throw new ValueException("Not enough stock");
            }
        }
    }

    public void removeFoodItem(FoodItem food_item, int quantity) {
        if (this.food_items.containsKey(food_item.getName())) {
            int current_amount = this.food_items.get(food_item.getName());
            int after_amount = current_amount - quantity;
            if (after_amount > 0) {
                this.food_items.put(food_item.getName(), after_amount);
            } else if (after_amount == 0) {
                this.food_items.remove(food_item.getName());
            } else {
                throw new ValueException("Cannot remove more food items than there are in the cart.");
            }
            this.current_weight -= food_item.getWeight() * quantity;
            this.current_volume -= food_item.getVolume() * quantity;
            food_item.setStock(food_item.getStock() + quantity);
        } else {
            throw new ValueException("No such food item in the cart");
        }
    }

    public boolean containsFoodItem(FoodItem food_item) {
        return this.food_items.containsKey(food_item.getName());
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

    public double getCurrentVolume() {
        return current_volume;
    }

    public HashMap<String, Integer> getFoodItems() {
        return food_items;
    }

    public String toString() {
        StringJoiner joiner = new StringJoiner("\n");
        for (String item_name : this.food_items.keySet()) {
            joiner.add(String.format("%s, %d;", item_name, this.food_items.get(item_name)));
        }
        return joiner.toString();
    }
}
