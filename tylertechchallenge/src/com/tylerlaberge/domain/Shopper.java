package com.tylerlaberge.domain;

import jdk.nashorn.internal.runtime.regexp.joni.exception.ValueException;

public class Shopper {

    private double budget;
    private double remaining_budget;
    private Cart cart;

    public Shopper(double budget, Cart cart) {
        this.budget = budget;
        this.remaining_budget = budget;
        this.cart = cart;
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

    public double getBudget() {
        return budget;
    }

    public double getRemainingBudget() {
        return remaining_budget;
    }

    public Cart getCart() {
        return cart;
    }

    public String toString() {
        return String.format("<Shopper budget: %f, cart: %s>", this.budget, this.cart.toString());
    }
}
