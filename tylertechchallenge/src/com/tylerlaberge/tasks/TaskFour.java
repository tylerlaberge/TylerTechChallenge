package com.tylerlaberge.tasks;

import com.tylerlaberge.domain.Cart;
import com.tylerlaberge.domain.FoodItem;
import com.tylerlaberge.domain.Shopper;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;


public class TaskFour extends Task {

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
    protected String solve(Shopper shopper, List<FoodItem> inventory) {
        double price_weight = shopper.getBudgetWeight();
        double weight_weight = shopper.getWeightLimitWeight();
        double volume_weight = shopper.getVolumeLimitWeight();

        this.setFoodItemValues(inventory, price_weight, weight_weight, volume_weight);
        Collections.sort(inventory, FoodItem.mostOptimalComparator());

        for (FoodItem food_item : inventory) {
            int quantity = (int)Math.min(
                    shopper.getRemainingBudget() / food_item.getPrice(),
                    Math.min(
                            shopper.getCart().getRemainingWeight() / food_item.getWeight(),
                            shopper.getCart().getRemainingVolume() / food_item.getVolume()
                    )
            );
            if (quantity >= food_item.getStock()) {
                shopper.addToCart(food_item, food_item.getStock());
            }
            else if (quantity >= 1) {
                shopper.addToCart(food_item, quantity);
            }
        }
        return shopper.getCart().toString();
    }
    private void setFoodItemValues(List<FoodItem> inventory, double price_weight, double weight_weight, double volume_weight) {
        double raw_food_item_values_sum = 0;
        for (FoodItem food_item : inventory){
            double raw_food_item_value = food_item.getPrice()*price_weight + food_item.getWeight()*weight_weight + food_item.getVolume()*volume_weight;
            food_item.setValue(raw_food_item_value);
            raw_food_item_values_sum += raw_food_item_value;
        }
        for (FoodItem food_item : inventory) {
            food_item.setValue(food_item.getValue()/raw_food_item_values_sum);
        }
    }
}


