package com.tylerlaberge.tasks;

import com.tylerlaberge.domain.Cart;
import com.tylerlaberge.domain.FoodItem;
import com.tylerlaberge.domain.Shopper;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;


public class TaskFour extends Task {

    @Override
    protected Shopper buildShopper(HashMap<String, String> constraints) {
        return new Shopper(
                Double.parseDouble(constraints.get("budget")),
                new Cart(
                        Double.parseDouble(constraints.get("weight_limit")),
                        Double.parseDouble(constraints.get("volume_limit"))
                )
        );
    }
    @Override
    protected String solve(Shopper shopper, List<FoodItem> inventory) {
        Collections.sort(inventory, shopper.foodItemComparator());
        shopper.fillCart(inventory);
        return shopper.getCart().toString();
    }
}


