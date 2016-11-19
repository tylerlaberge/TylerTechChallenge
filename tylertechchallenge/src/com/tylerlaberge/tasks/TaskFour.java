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

        Collections.sort(inventory, (FoodItem food_item_one, FoodItem food_item_two) -> {
            double price_weight = 1/shopper.getBudget();
            double weight_weight = 1/shopper.getCart().getWeightLimit();
            double volume_weight = 1/shopper.getCart().getVolumeLimit();
            double sum = price_weight + weight_weight + volume_weight;

            double food_item_one_value = food_item_one.getPrice()*price_weight/sum
                    + food_item_one.getWeight()*weight_weight/sum
                    + food_item_one.getVolume()*volume_weight/sum;
            double food_item_two_value = food_item_two.getPrice()*price_weight/sum
                    + food_item_two.getWeight()*weight_weight/sum
                    + food_item_two.getVolume()*volume_weight/sum;

            if (food_item_one_value == food_item_two_value)
                return 0;
            else {
                return food_item_one_value < food_item_two_value ? -1 : 1;
            }
        });

        for (FoodItem food_item : inventory) {
            int quantity = (int)Math.min(
                    shopper.getRemainingBudget() / food_item.getPrice(),
                    Math.min(
                            shopper.getCart().getRemainingWeight() / food_item.getWeight(),
                            shopper.getCart().getRemainingVolume() / food_item.getVolume()
                    )
            );
            if (quantity < 1) {
                break;
            } else if (quantity >= food_item.getStock()) {
                shopper.addToCart(food_item, food_item.getStock());
            } else {
                shopper.addToCart(food_item, quantity);
            }
        }

        return shopper.getCart().toString();
    }
}


