package com.tylerlaberge.tasks;

import com.tylerlaberge.domain.Cart;
import com.tylerlaberge.domain.FoodItem;
import com.tylerlaberge.domain.Shopper;

import java.util.*;


public class TaskThree extends Task {

    public TaskThree(HashMap<String, String> constraints, List<HashMap<String, String>> inventory_details) {
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
    public String solve() {
        Collections.sort(this.inventory, this.shopper.leastOptimalFoodItemComparator());
        List<FoodItem> purchasable_list = new ArrayList<>(this.inventory);

        this.shopper.fillCart(purchasable_list);

        while(!this.shopper.getCart().isBalanced() && !this.shopper.getCart().isEmpty()){
            if (purchasable_list.isEmpty()) {
                purchasable_list = new ArrayList<>(this.inventory);
            }

            String most_distributed_food_group = this.shopper.getCart().getMostDistributedFoodGroup();
            FoodItem most_distributed_food_item = this.shopper.getCart().getMostDistributedFoodItemByFoodGroup(most_distributed_food_group);

            this.shopper.removeFromCart(most_distributed_food_item, 1);
            if (this.shopper.getCart().isEmpty()) {
                break;
            }
            else {
                purchasable_list.remove(most_distributed_food_item);
                this.shopper.fillCart(purchasable_list);
            }
        }
        return this.shopper.getCart().toString();
    }
}