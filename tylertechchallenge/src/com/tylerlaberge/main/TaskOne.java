package com.tylerlaberge.main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;


public class TaskOne implements Task {

    @Override
    public String solve(HashMap<String, String> constraints, List<HashMap<String, String>> inventory_details) {
        Shopper shopper = this.buildShopper(constraints);
        List<FoodItem> inventory = this.buildInventory(inventory_details);

        return this.solve(shopper, inventory);
    }

    private Shopper buildShopper(HashMap<String, String> constraints) {
        return new Shopper(Double.parseDouble(constraints.get("budget")), new Cart());
    }

    private List<FoodItem> buildInventory(List<HashMap<String, String>> food_item_details) {
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

    private String solve(Shopper shopper, List<FoodItem> inventory) {
        Collections.sort(inventory);
        for (FoodItem food_item : inventory){
            int quantity = (int)shopper.getRemaining_budget()/(int)food_item.getPrice();
            if (quantity < 1) {
                break;
            }
            else if (quantity >= food_item.getStock()) {
                shopper.addToCart(food_item, food_item.getStock());
            }
            else {
                shopper.addToCart(food_item, quantity);
            }
        }
        return shopper.getCart().toString();
    }
}
