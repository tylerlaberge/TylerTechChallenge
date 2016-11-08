package com.tylerlaberge.main;

public class Shopper {

    private double budget;
    private Cart cart;

    public Shopper(double budget, Cart cart) {
        this.budget = budget;
        this.cart = cart;
    }
    public void addToCart(FoodItem food_item) {
        this.cart.addFoodItem(food_item);
    }
    public double getBudget() {
        return budget;
    }
    public Cart getCart() {
        return cart;
    }
}
