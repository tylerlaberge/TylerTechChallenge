package com.tylerlaberge.domain;

import jdk.nashorn.internal.runtime.regexp.joni.exception.ValueException;

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
    public void fillCart(List<FoodItem> food_item_list) {
        for (FoodItem food_item : food_item_list) {
            int quantity = (int)Math.min(
                    this.getRemainingBudget() / food_item.getPrice(),
                    this.getCart().getRemainingWeight() / food_item.getWeight()
            );
            if (quantity >= food_item.getStock()) {
                this.addToCart(food_item, food_item.getStock());
            }
            else if (quantity > 1) {
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
        return  1/this.getBudget()/(1/this.getBudget() + 1/this.getCart().getWeightLimit());
    }
    public double getWeightLimitWeight() {
        return  1/this.getCart().getWeightLimit()/(1/this.getBudget() + 1/this.getCart().getWeightLimit());
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
