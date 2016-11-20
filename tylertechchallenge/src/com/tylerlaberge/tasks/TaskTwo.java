package com.tylerlaberge.tasks;

import com.tylerlaberge.domain.Cart;
import com.tylerlaberge.domain.Shopper;
import com.tylerlaberge.exceptions.FailedToSolveException;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;


public class TaskTwo extends Task {

    public TaskTwo(HashMap<String, String> constraints, List<HashMap<String, String>> inventory_details) {
        super(constraints, inventory_details);
    }

    @Override
    protected Shopper buildShopper(HashMap<String, String> constraints) {
        return new Shopper(
                Double.parseDouble(constraints.get("budget")),
                new Cart(Double.parseDouble(constraints.get("weight_limit")))
        );
    }

    @Override
    public String solve() throws FailedToSolveException {
        Collections.sort(this.inventory, this.shopper.mostOptimalFoodItemComparator());
        try {
            this.shopper.fillCart(this.inventory);
        } catch (IllegalArgumentException e) {
            throw new FailedToSolveException("Failed to solve the given input.");
        }
        return this.shopper.getCart().toString();
    }
}

