package com.tylerlaberge.tasks;

import com.tylerlaberge.domain.Cart;
import com.tylerlaberge.domain.Shopper;

import java.util.*;


public class TaskOne extends Task {

    public TaskOne(HashMap<String, String> constraints, List<HashMap<String, String>> inventory_details) {
        super(constraints, inventory_details);
    }
    @Override
    protected Shopper buildShopper(HashMap<String, String> constraints) {
        return new Shopper(Double.parseDouble(constraints.get("budget")), new Cart());
    }

    @Override
    public String solve() {
        Collections.sort(this.inventory, this.shopper.mostOptimalFoodItemComparator());
        this.shopper.fillCart(this.inventory);
        return this.shopper.getCart().toString();
    }
}
