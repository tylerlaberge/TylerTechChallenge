package com.tylerlaberge.tasks;

import com.tylerlaberge.domain.Cart;
import com.tylerlaberge.domain.Shopper;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;


public class TaskFour extends Task {

    public TaskFour(HashMap<String, String> constraints, List<HashMap<String, String>> inventory_details) {
        super(constraints, inventory_details);
    }
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
    public String solve() {
        Collections.sort(this.inventory, this.shopper.mostOptimalFoodItemComparator());
        this.shopper.fillCart(this.inventory);
        return this.shopper.getCart().toString();
    }
}


