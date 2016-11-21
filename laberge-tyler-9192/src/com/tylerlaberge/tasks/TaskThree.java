package com.tylerlaberge.tasks;

import com.tylerlaberge.domain.Cart;
import com.tylerlaberge.domain.FoodItem;
import com.tylerlaberge.domain.Shopper;
import com.tylerlaberge.exceptions.FailedToSolveException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;


/**
 * A Concrete Task implementation for solving task 3 problems.
 *
 * Task 3 is described as follows.
 *
 * For a given budget and weight, calculate the maximum number of items that can be purchased
 * without going over budget and exceeding the weight limit.
 * At the same time keep the distribution of the four food groups to a balanced 25% +/- 5%.
 *
 * The four food groups are grains, meat, dairy, veggies.
 */
public class TaskThree extends Task {

    /**
     * Create a new TaskThree instance.
     *
     * @param constraints           A mapping of the constraints for the given task (i.e budget, weight limit).
     * @param inventory_details     The details of all the food items for the given task.
     *                              Each item in the details list should have a mapping of food item constraints
     *                              (i.e name, food group, stock, price, weight, volume).
     */
    public TaskThree(HashMap<String, String> constraints, List<HashMap<String, String>> inventory_details) {
        super(constraints, inventory_details);
    }

    /**
     * Build the Shopper to be used during this task.
     * For this task volume limit does not apply so the Shopper is built without using the volume constraint.
     * This effectively gives the Shopper an infinite volume limit.
     *
     * @param constraints   The constraints of the Shopper.
     * @return              The Shopper that is built from the given constraints.
     */
    @Override
    protected Shopper buildShopper(HashMap<String, String> constraints) {
        return new Shopper(
                Double.parseDouble(constraints.get("budget")),
                new Cart(Double.parseDouble(constraints.get("weight_limit")))
        );
    }

    /**
     * Solve this task.
     *
     * The algorithm being used for this task is as follows.
     *
     * Sort the inventory of FoodItems pessimistically by the number of each you can purchase.
     * That is, FoodItem 'Fa' comes before FoodItem 'Fb' if the Shopper can purchase more of 'Fb' than 'Fa' without violating constraints.
     * The constraints for this task being budget, and weight limit.
     *
     * Next, for each item in the pessimistically sorted list, buy the maximum amount the item as possible.
     *
     * Then,
     *
     * Define the list of items the Shopper can purchase to be a copy of the inventory.
     * While the cart is not balanced and the cart is not empty:
     *      If the list of items the Shopper can purchase is empty, re-copy the inventory into the list.
     *      Get the most distributed FoodItem of the most distributed food group.
     *      Remove the most distributed FoodItem from the Cart with the quantity to be removed being 1.
     *      If the shoppers cart is empty,
     *          Break, there is no way to balance the given food items.
     *      Else,
     *          Remove the most distributed FoodItem from the list of food items that can be purchased by the Shopper.
     *          For each food item in the list of food items the Shopper can purchase, buy the maximum amount of the item as possible.
     *
     *
     * Essentially the algorithm fills the cart in the worst way possible to begin with, making the worse items be the most distributed items.
     * Then the algorithm starts chipping away at the amount of each of these bad items in the cart and replaces them with better items.
     * The reason we chip away from worse items is because it is guaranteed that a better item can be added to the cart after a worse item is removed.
     * At this point it is likely that the most distributed items are actually better items because by definition you
     * can purchase more of a better item than a worse item, and so if you remove one bad item then it is likely you can add at least two good items.
     * Now that the good items are the most distributed you start chipping away at those instead.
     * Basically this will eventually balance out to have the least amount of bad items while still maintaining the most
     * amount of items that can purchased.
     *
     *
     * @return                          The solution to this task, detailing the optimal food items for the Shopper to purchase.
     * @throws FailedToSolveException   If this Task unexpectedly failed to be solved.
     */
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