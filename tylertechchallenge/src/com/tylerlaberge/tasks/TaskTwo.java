package com.tylerlaberge.tasks;

import com.tylerlaberge.domain.Cart;
import com.tylerlaberge.domain.Shopper;
import com.tylerlaberge.exceptions.FailedToSolveException;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;


/**
 * A Concrete Task implementation for solving task 2 problems.
 *
 * Task 2 is described as follows.
 *
 * For a given budget and weight constraint, calculate the maximum number of items that can be purchased without
 * going over budget and without exceeding the weight limit.
 */
public class TaskTwo extends Task {

    /**
     * Create a new TaskTwo instance.
     *
     * @param constraints           A mapping of the constraints for the given task (i.e budget, weight limit).
     * @param inventory_details     The details of all the food items for the given task.
     *                              Each item in the details list should have a mapping of food item constraints
     *                              (i.e name, food group, stock, price, weight, volume).
     */
    public TaskTwo(HashMap<String, String> constraints, List<HashMap<String, String>> inventory_details) {
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
     * Sort the inventory of FoodItems by the number of each you can purchase.
     * That is, FoodItem 'Fa' comes before FoodItem 'Fb' if the Shopper can purchase more of 'Fa' than 'Fb' without violating constraints.
     *
     * Then, for each item in the sorted list buy the maximum amount of the item as possible.
     *
     * The constraints for this task are budget, and weight limit.
     *
     * @return                          The solution to this task, detailing the optimal food items for the Shopper to purchase.
     * @throws FailedToSolveException   If this Task unexpectedly failed to be solved.
     */
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

