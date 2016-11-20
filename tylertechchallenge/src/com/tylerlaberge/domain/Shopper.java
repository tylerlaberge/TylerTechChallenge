package com.tylerlaberge.domain;

import java.util.Comparator;
import java.util.List;

/**
 * A class responsible for maintaining a Cart of FoodItems without going over a budget constraint.
 */
public class Shopper {

    private final double budget;
    private final Cart cart;
    private double remaining_budget;

    /**
     * Create a new Shopper instance.
     *
     * @param budget    The amount of money this Shopper can spend on FoodItems.
     * @param cart      The Cart for this Shopper to add or remove food items from.
     */
    public Shopper(double budget, Cart cart) {
        this.budget = budget;
        this.remaining_budget = budget;
        this.cart = cart;
    }

    /**
     * Get a comparator which will compare FoodItems by how many this Shopper can purchase.
     * The FoodItems which the Shopper can purchase more of are considered more optimal and will come before less optimal FoodItems.
     *
     * @return  The comparator to compare FoodItems by which is more optimal for this Shopper to purchase.
     */
    public Comparator<FoodItem> mostOptimalFoodItemComparator() {
        Shopper shopper = this;
        return new Comparator<FoodItem>() {
            @Override
            public int compare(FoodItem o1, FoodItem o2) {
                int o1_quantity = shopper.getAmountCanBuy(o1);
                int o2_quantity = shopper.getAmountCanBuy(o2);
                if (o1_quantity == o2_quantity) {
                    return 0;
                } else {
                    return o1_quantity > o2_quantity ? -1 : 1;
                }
            }
        };
    }

    /**
     * Get a comparator which will compare FoodItems by how many this Shopper can purchase.
     * The FoodItems which the Shopper can purchase less of are considered less optimal and will come before more optimal FoodItems.
     *
     * @return  The comparator to compare FoodItems by which is less optimal for this Shopper to purchase.
     */
    public Comparator<FoodItem> leastOptimalFoodItemComparator() {
        Shopper shopper = this;
        return new Comparator<FoodItem>() {
            @Override
            public int compare(FoodItem o1, FoodItem o2) {
                int o1_quantity = shopper.getAmountCanBuy(o1);
                int o2_quantity = shopper.getAmountCanBuy(o2);
                if (o1_quantity == o2_quantity) {
                    return 0;
                } else {
                    return o1_quantity < o2_quantity ? -1 : 1;
                }
            }
        };
    }

    /**
     * Fill this Shoppers Cart up as much as possible and in the most optimal way from a list of FoodItems that this Shopper can purchase from.
     *
     * @param food_item_list    The list of FoodItems that this Shopper can purchase from.
     */
    public void fillCart(List<FoodItem> food_item_list) {
        for (FoodItem food_item : food_item_list) {
            int quantity = (int) Math.min(
                    this.getRemainingBudget() / food_item.getPrice(),
                    Math.min(
                            this.getCart().getRemainingWeight() / food_item.getWeight(),
                            this.getCart().getRemainingVolume() / food_item.getVolume()
                    )
            );
            if (quantity >= food_item.getStock()) {
                this.addToCart(food_item, food_item.getStock());
            } else if (quantity >= 1) {
                this.addToCart(food_item, quantity);
            }
        }
    }

    /**
     * Add a FoodItem to this Cart.
     *
     * @param food_item                     The FoodItem to add to this Cart.
     * @param quantity                      The amount of the FoodItem to add to this Cart.
     * @throws IllegalArgumentException     If this Shopper cannot afford the given amount of the FoodItem.
     */
    public void addToCart(FoodItem food_item, int quantity) throws IllegalArgumentException {
        if (this.canAfford(food_item, quantity)) {
            this.cart.addFoodItem(food_item, quantity);
            this.remaining_budget -= food_item.getPrice() * quantity;
        } else {
            throw new IllegalArgumentException("Cant afford food item.");
        }
    }

    /**
     * Remove a FoodItem from this Cart.
     *
     * @param food_item The FoodItem to remove from this Cart.
     * @param quantity  The amount of the FoodItem to remove from this Cart.
     */
    public void removeFromCart(FoodItem food_item, int quantity) {
        this.cart.removeFoodItem(food_item, quantity);
        this.remaining_budget += food_item.getPrice() * quantity;
    }

    /**
     * Get the maximum amount of the given FoodItem that this Shopper can purchase.
     *
     * @param food_item The FoodItem to get the maximum amount that can be purchased.
     * @return          The maximum amount of the given FoodItem that this Shopper can purchase.
     */
    public int getAmountCanBuy(FoodItem food_item) {
        return (int) Math.min(
                this.getRemainingBudget() / food_item.getPrice(),
                Math.min(
                        this.getCart().getRemainingWeight() / food_item.getWeight(),
                        this.getCart().getRemainingVolume() / food_item.getVolume()
                )
        );
    }

    /**
     * Get whether or not this Shopper can afford a given FoodItem and quantity.
     *
     * @param food_item The FoodItem to check if this Shopper can afford.
     * @param quantity  The amount of the FoodItem to check if this Shopper can afford.
     * @return          Whether or not this Shopper can afford the given FoodItem and quantity.
     */
    public boolean canAfford(FoodItem food_item, int quantity) {
        return this.remaining_budget - food_item.getPrice() * quantity >= 0;
    }

    /**
     * Get the budget of this Shopper.
     *
     * @return  The budget of this Shopper.
     */
    public double getBudget() {
        return budget;
    }

    /**
     * Get the remaining budget of this Shopper.
     *
     * @return  The remaining budget of this Shopper.
     */
    public double getRemainingBudget() {
        return remaining_budget;
    }

    /**
     * Get the Cart being used by this Shopper.
     *
     * @return  The Cart being used by this Shopper.
     */
    public Cart getCart() {
        return cart;
    }

    /**
     * Get the String representation of this Shopper.
     *
     * @return  The String representation of this Shopper.
     */
    public String toString() {
        return String.format("<Shopper budget: %f, cart: %s>", this.budget, this.cart.toString());
    }
}
