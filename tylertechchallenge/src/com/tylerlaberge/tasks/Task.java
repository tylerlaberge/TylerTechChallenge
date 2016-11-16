package com.tylerlaberge.tasks;

import com.tylerlaberge.domain.FoodItem;
import com.tylerlaberge.domain.Shopper;

import java.util.HashMap;
import java.util.List;

public abstract class Task {

    abstract Shopper buildShopper(HashMap<String, String> constraints);

    abstract List<FoodItem> buildInventory(List<HashMap<String, String>> inventory_details);

    abstract String solve(Shopper shopper, List<FoodItem> inventory);

    public String solve(HashMap<String, String> constraints, List<HashMap<String, String>> inventory_details) {
        Shopper shopper = this.buildShopper(constraints);
        List<FoodItem> inventory = this.buildInventory(inventory_details);

        return this.solve(shopper, inventory);
    }
}
