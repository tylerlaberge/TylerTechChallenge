package com.tylerlaberge.tasks;

import com.tylerlaberge.domain.Cart;
import com.tylerlaberge.domain.Shopper;
import com.tylerlaberge.exceptions.FailedToSolveException;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;


/**
 * A Concrete Task implementation for solving task 1 problems.
 *
 * Task 1 is described as follows.
 *
 * For a given budget, calculate the maximum number of items that can be purchased without going over budget.
 */
public class TaskOne extends Task {

    /**
     * Create a new TaskOne instance.
     *
     * @param constraints           A mapping of the constraints for the given task (i.e budget).
     * @param inventory_details     The details of all the food items for the given task.
     *                              Each item in the details list should have a mapping of food item constraints
     *                              (i.e name, food group, stock, price, weight, volume).
     */
    public TaskOne(HashMap<String, String> constraints, List<HashMap<String, String>> inventory_details) {
        super(constraints, inventory_details);
    }

    /**
     * Build the Shopper to be used during this task.
     * For this task weight limit and volume limit do not apply so the Shopper is built without using those constraints.
     * This effectively gives the Shopper infinite weight and volume limits.
     *
     * @param constraints   The constraints of the Shopper.
     * @return              The Shopper that is built from the given constraints.
     */
    @Override
    protected Shopper buildShopper(HashMap<String, String> constraints) {
        return new Shopper(Double.parseDouble(constraints.get("budget")), new Cart());
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
     * The only constraint for this task is budget.
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
