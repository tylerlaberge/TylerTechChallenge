package com.tylerlaberge.tasks;

import com.tylerlaberge.domain.FoodItem;
import com.tylerlaberge.domain.Shopper;
import com.tylerlaberge.exceptions.FailedToSolveException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * A Template for solving tasks described by the TylerTech challenge.
 */
public abstract class Task {

    protected Shopper shopper;
    protected List<FoodItem> inventory;

    /**
     * Create a new Task instance. Concrete Task's should call this in their own constructors.
     *
     * @param constraints           A mapping of the constraints for the given task (i.e budget, weight limit, volume limit").
     * @param inventory_details     The details of all the food items for the given task.
     *                              Each item in the details list should have a mapping of food item constraints
     *                              (i.e name, food group, stock, price, weight, volume).
     */
    protected Task(HashMap<String, String> constraints, List<HashMap<String, String>> inventory_details) {
        this.shopper = this.buildShopper(constraints);
        this.inventory = this.buildInventory(inventory_details);
    }

    /**
     * An abstract method for solving a given task.
     * Concrete Tasks must override this method to contain the logic/algorithm for solving their specific task.
     *
     * @return                          Concrete implementations should return a String which is the solution to their given task.
     * @throws FailedToSolveException   Concrete implementations should throw this exception if they fail to solve their given task.
     */
    public abstract String solve() throws FailedToSolveException;

    /**
     * An abstract method for building the Shopper that is to be used in solving the given task.
     * Concrete Tasks must override this method to contain the logic for building the Shopper with the correct constraints.
     * (i.e budget, weight limit, volume limit)
     *
     * @param constraints   The constraints of the Shopper.
     * @return              The Shopper that is built with the given constraints.
     */
    abstract Shopper buildShopper(HashMap<String, String> constraints);

    /**
     * Build the inventory of FoodItems that are part of the task.
     *
     * @param food_item_details     The details of each food item. (i.e. name, food group, price, weight, volume, stock)
     * @return                      The inventory of FoodItems that are to be used during this task.
     */
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
}
