package com.tylerlaberge.tasks;

import com.tylerlaberge.domain.Cart;
import com.tylerlaberge.domain.FoodItem;
import com.tylerlaberge.domain.Shopper;

import java.util.*;


public class TaskOne extends Task {

    @Override
    protected Shopper buildShopper(HashMap<String, String> constraints) {
        return new Shopper(Double.parseDouble(constraints.get("budget")), new Cart());
    }

    @Override
    protected String solve(Shopper shopper, List<FoodItem> inventory) {
        Collections.sort(inventory, FoodItem.priceComparator());

        for (FoodItem food_item : inventory) {
            int quantity = (int)(shopper.getRemainingBudget() / food_item.getPrice());
            if (quantity >= food_item.getStock()) {
                shopper.addToCart(food_item, food_item.getStock());
            }
            else if (quantity >= 1) {
                shopper.addToCart(food_item, quantity);
            }
        }
        return shopper.getCart().toString();
    }
}
