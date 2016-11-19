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
        Collections.sort(inventory, shopper.foodItemComparator());
        shopper.fillCart(inventory);
        return shopper.getCart().toString();
    }
}
