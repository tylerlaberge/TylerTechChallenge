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
        Collections.sort(inventory, (FoodItem food_item_one, FoodItem food_item_two) -> {
            if (food_item_one.getPrice() == food_item_two.getPrice())
                return 0;
            else {
                return food_item_one.getPrice() < food_item_two.getPrice() ? -1 : 1;
            }
        });

        for (FoodItem food_item : inventory) {
            int quantity = (int) shopper.getRemainingBudget() / (int) food_item.getPrice();
            if (quantity < 1) {
                break;
            } else if (quantity >= food_item.getStock()) {
                shopper.addToCart(food_item, food_item.getStock());
            } else {
                shopper.addToCart(food_item, quantity);
            }
        }
        return shopper.getCart().toString();
    }
}
