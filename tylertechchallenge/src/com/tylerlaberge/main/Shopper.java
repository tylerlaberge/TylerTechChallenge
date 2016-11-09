package com.tylerlaberge.main;

public class Shopper {

    private double budget;
    private double remaining_budget;
    private Cart cart;

    public Shopper(double budget, Cart cart) {
        this.budget = budget;
        this.remaining_budget = budget;
        this.cart = cart;
    }
    public void addToCart(FoodItem food_item) {
        this.cart.addFoodItem(food_item);
        this.remaining_budget -= food_item.getPrice();
    }
    public double getBudget() {
        return budget;
    }
    public double getRemaining_budget() { return remaining_budget; }
    public Cart getCart() {
        return cart;
    }
}
