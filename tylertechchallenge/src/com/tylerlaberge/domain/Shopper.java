package com.tylerlaberge.domain;

import jdk.nashorn.internal.runtime.regexp.joni.Matcher;
import jdk.nashorn.internal.runtime.regexp.joni.exception.ValueException;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Shopper {

    private double budget;
    private double remaining_budget;
    private Cart cart;

    public Shopper(double budget, Cart cart) {
        this.budget = budget;
        this.remaining_budget = budget;
        this.cart = cart;
    }
    public Comparator<FoodItem> foodItemComparator() {
        Shopper shopper = this;
        return new Comparator<FoodItem>() {
            @Override
            public int compare(FoodItem o1, FoodItem o2) {
                int o1_quantity = (int)Math.min(
                        shopper.getRemainingBudget() / o1.getPrice(),
                        Math.min(
                                shopper.getCart().getRemainingWeight() / o1.getWeight(),
                                shopper.getCart().getRemainingVolume() / o1.getVolume()
                        )
                );
                int o2_quantity = (int)Math.min(
                        shopper.getRemainingBudget() / o2.getPrice(),
                        Math.min(
                                shopper.getCart().getRemainingWeight() / o2.getWeight(),
                                shopper.getCart().getRemainingVolume() / o2.getVolume()
                        )
                );
                if (o1_quantity == o2_quantity) {
                    return 0;
                }
                else {
                    return o1_quantity > o2_quantity ? -1 : 1;
                }
            }
        };
    }
    public void fillCart(List<FoodItem> food_item_list) {
        for (FoodItem food_item : food_item_list) {
            int quantity = (int)Math.min(
                    this.getRemainingBudget() / food_item.getPrice(),
                    Math.min(
                            this.getCart().getRemainingWeight() / food_item.getWeight(),
                            this.getCart().getRemainingVolume() / food_item.getVolume()
                    )
            );
            if (quantity >= food_item.getStock()) {
                this.addToCart(food_item, food_item.getStock());
            }
            else if (quantity >= 1) {
                this.addToCart(food_item, quantity);
            }
        }
    }
    public void addToCart(FoodItem food_item, int quantity) {
        if (this.canAfford(food_item, quantity)) {
            this.cart.addFoodItem(food_item, quantity);
            this.remaining_budget -= food_item.getPrice() * quantity;
        } else {
            throw new ValueException("Cant afford food item");
        }
    }

    public void removeFromCart(FoodItem food_item, int quantity) {
        this.cart.removeFoodItem(food_item, quantity);
        this.remaining_budget += food_item.getPrice() * quantity;
    }

    public boolean canAfford(FoodItem food_item, int quantity) {
        return this.remaining_budget - food_item.getPrice() * quantity >= 0;
    }
    public double getBudgetWeight() {
        return  1/this.getBudget()/(1/this.getBudget() + 1/this.getCart().getWeightLimit() + 1/this.getCart().getVolumeLimit());
    }
    public double getWeightLimitWeight() {
        return  1/this.getCart().getWeightLimit()/(1/this.getBudget() + 1/this.getCart().getWeightLimit() + 1/this.getCart().getVolumeLimit());
    }
    public double getVolumeLimitWeight() {
        return 1/this.getCart().getVolumeLimit()/(1/this.getBudget() + 1/this.getCart().getWeightLimit() + 1/this.getCart().getVolumeLimit());
    }
    public double getBudget() {
        return budget;
    }

    public double getRemainingBudget() {
        return remaining_budget;
    }

    public Cart getCart() {return cart;}

    public String toString() {
        return String.format("<Shopper budget: %f, cart: %s>", this.budget, this.cart.toString());
    }
}
