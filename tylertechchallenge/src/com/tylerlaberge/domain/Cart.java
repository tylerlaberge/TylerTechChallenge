package com.tylerlaberge.domain;

import jdk.nashorn.internal.runtime.regexp.joni.exception.ValueException;

import java.util.*;

public class Cart {

    private final double WEIGHT_LIMIT;
    private final double VOLUME_LIMIT;
    private HashMap<FoodItem, Integer> food_items = new HashMap<>();
    private double current_weight = 0;
    private double current_volume = 0;

    public Cart() {
        this.WEIGHT_LIMIT = Double.POSITIVE_INFINITY;
        this.VOLUME_LIMIT = Double.POSITIVE_INFINITY;
    }

    public Cart(double weight_limit) {
        this.WEIGHT_LIMIT = weight_limit;
        this.VOLUME_LIMIT = Double.POSITIVE_INFINITY;
    }

    public Cart(double weight_limit, double volume_limit) {
        this.WEIGHT_LIMIT = weight_limit;
        this.VOLUME_LIMIT = volume_limit;
    }

    public void addFoodItem(FoodItem food_item, int quantity) throws ValueException {
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
                    throw new ValueException("Surpassed cart weight/volume limit");
                }
            } else {
                throw new ValueException("Not enough stock");
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
        return this.food_items.containsKey(food_item);
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

    public double getRemainingWeight() { return this.WEIGHT_LIMIT - this.current_weight; }

    public double getRemainingVolume() { return this.VOLUME_LIMIT - this.current_volume; }

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
