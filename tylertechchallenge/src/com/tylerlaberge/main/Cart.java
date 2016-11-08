package com.tylerlaberge.main;

import jdk.nashorn.internal.runtime.regexp.joni.exception.ValueException;

import java.util.HashMap;

public class Cart {

    private final double WEIGHT_LIMIT;
    private final double VOLUME_LIMIT;
    private HashMap<String, Integer> food_items;
    private double current_weight;
    private double current_volume;

    public Cart(double weight_limit, double volume_limit) {
        this.WEIGHT_LIMIT = weight_limit;
        this.VOLUME_LIMIT = volume_limit;
        this.food_items = new HashMap<String, Integer>();
        this.current_weight = 0;
        this.current_volume = 0;
    }
    public void addFoodItem(FoodItem food_item) throws ValueException {
        if (food_item.getStock() > 0) {
            String food_item_name = food_item.getName();
            if (this.current_weight + food_item.getWeight() <= this.WEIGHT_LIMIT
                    && this.current_volume + food_item.getVolume() <= this.VOLUME_LIMIT){
                if (this.food_items.containsKey(food_item_name)) {
                    int amount = this.food_items.get(food_item_name);
                    this.food_items.put(food_item_name, amount + 1);
                }
                else {
                    this.food_items.put(food_item_name, 1);
                }
                food_item.setStock(food_item.getStock() - 1);
            }
            else {
                throw new ValueException("Food item surpasses cart weight/volume limit");
            }
        }
        else {
            throw new ValueException("Food item out of stock");
        }
    }
    public double getWeight_limit() {
        return WEIGHT_LIMIT;
    }
    public double getVolume_limit() {
        return VOLUME_LIMIT;
    }
    public double getCurrent_weight() {
        return current_weight;
    }

    public double getCurrent_volume() {
        return current_volume;
    }
    public HashMap<String, Integer> getFood_items() {
        return food_items;
    }
}
