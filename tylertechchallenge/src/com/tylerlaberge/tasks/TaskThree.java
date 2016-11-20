package com.tylerlaberge.tasks;

import com.tylerlaberge.domain.Cart;
import com.tylerlaberge.domain.FoodItem;
import com.tylerlaberge.domain.Shopper;
import com.tylerlaberge.exceptions.FailedToSolveException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;


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
    public String solve() throws FailedToSolveException {
        Collections.sort(this.inventory, this.shopper.leastOptimalFoodItemComparator());
        List<FoodItem> purchasable_list = new ArrayList<>(this.inventory);

        try {
            this.shopper.fillCart(purchasable_list);
        } catch (IllegalArgumentException e) {
            throw new FailedToSolveException("Failed to solve the given input.");
        }

        while (!this.shopper.getCart().isBalanced() && !this.shopper.getCart().isEmpty()) {
            if (purchasable_list.isEmpty()) {
                purchasable_list = new ArrayList<>(this.inventory);
            }

            String most_distributed_food_group = this.shopper.getCart().getMostDistributedFoodGroup();
            FoodItem most_distributed_food_item = this.shopper.getCart().getMostDistributedFoodItemByFoodGroup(most_distributed_food_group);

            try {
                this.shopper.removeFromCart(most_distributed_food_item, 1);
            } catch (IllegalArgumentException e) {
                throw new FailedToSolveException("Failed to solve the given input.");
            }
            if (this.shopper.getCart().isEmpty()) {
                break;
            } else {
                purchasable_list.remove(most_distributed_food_item);
                try {
                    this.shopper.fillCart(purchasable_list);
                } catch (IllegalArgumentException e) {
                    throw new FailedToSolveException("Failed to solve the given input.");
                }
            }
        }
        return this.shopper.getCart().toString();
    }
}