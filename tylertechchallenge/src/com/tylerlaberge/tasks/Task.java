package com.tylerlaberge.tasks;

import com.tylerlaberge.domain.FoodItem;
import com.tylerlaberge.domain.Shopper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public abstract class Task {

    abstract String solve(Shopper shopper, List<FoodItem> inventory);

    abstract Shopper buildShopper(HashMap<String, String> constraints);

    protected List<FoodItem> buildInventory(List<HashMap<String, String>> food_item_details) {
        List<FoodItem> food_items = new ArrayList<>();
        for (HashMap<String, String> food_item_map : food_item_details) {
            food_items.add(
                    new FoodItem(
                            food_item_map.get("name"), food_item_map.get("food_group"),
                            Integer.parseInt(food_item_map.get("stock")), Double.parseDouble(food_item_map.get("price")),
                            Double.parseDouble(food_item_map.get("weight")), Double.parseDouble(food_item_map.get("volume"))
                    )
            );
        }
        return food_items;
    }

    public String solve(HashMap<String, String> constraints, List<HashMap<String, String>> inventory_details) {
        Shopper shopper = this.buildShopper(constraints);
        List<FoodItem> inventory = this.buildInventory(inventory_details);

        return this.solve(shopper, inventory);
    }
}
