package com.tylerlaberge.main;

import jdk.nashorn.internal.runtime.regexp.joni.exception.ValueException;

import java.io.*;
import java.util.*;

public class Main {

    public static HashMap<String, Double> parseConstraints(String line) {
        String[] constraints = line.substring(0, line.length() - 1).split(",");

        HashMap<String, Double> constraints_map = new HashMap<String, Double>();
        constraints_map.put("task", Double.parseDouble(constraints[0].trim()));
        constraints_map.put("budget", Double.parseDouble(constraints[1].trim()));
        constraints_map.put("weight_limit", Double.parseDouble(constraints[2].trim()));
        constraints_map.put("volume_limit", Double.parseDouble(constraints[3].trim()));

        return constraints_map;
    }
    public static FoodItem parseFoodItem(String line) {
        String[] food_item_details = line.substring(0, line.length() - 1).split(",");

        String name = food_item_details[0].trim();
        int stock = Integer.parseInt(food_item_details[1].trim());
        double price = Double.parseDouble(food_item_details[2].trim());
        double weight = Double.parseDouble(food_item_details[3].trim());
        double volume = Double.parseDouble(food_item_details[4].trim());
        String food_group = food_item_details[5].trim();

        return new FoodItem(name, food_group, stock, price, weight, volume);
    }
    public static void Shop(Shopper shopper, List<FoodItem> food_items) {
        Collections.sort(food_items);
        FoodItem prev_item = null;
        for (FoodItem food_item : food_items){
            int quantity = (int)shopper.getRemaining_budget()/(int)food_item.getPrice();
            if (quantity < 1) {
                if (prev_item != null) {
                    double prev_remaining_budget = shopper.getRemaining_budget();
                    shopper.removeFromCart(prev_item, 1);
                    double new_remaining_budget = shopper.getRemaining_budget() - food_item.getPrice();
                    if (new_remaining_budget < prev_remaining_budget) {
                        shopper.addToCart(food_item, 1);
                    }
                    else {
                        shopper.addToCart(prev_item, 1);
                    }
                }
                break;
            }
            else if (quantity >= food_item.getStock()) {
                shopper.addToCart(food_item, food_item.getStock());
            }
            else {
                shopper.addToCart(food_item, quantity);
            }
            if (prev_item == null || !prev_item.getName().equals(food_item.getName())){
                prev_item = food_item;
            }
        }
    }
    public static void main(String[] args) throws IOException {
        String input_file_path = args[0];
        String output_file_path = args[1];
        try (
                BufferedReader reader = new BufferedReader(new FileReader(input_file_path));
                Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(output_file_path), "utf-8"));
        ) {
            Shopper shopper;

            String line = reader.readLine();
            HashMap<String, Double> constraints = Main.parseConstraints(line);
            if(constraints.get("task") == 1) {
                shopper = new Shopper(constraints.get("budget"), new Cart());
            }
            else {
                throw new ValueException("Invalid task");
            }
            List<FoodItem> food_items = new ArrayList<FoodItem>();
            line = reader.readLine();
            while(line !=null)
            {
                FoodItem food_item = Main.parseFoodItem(line);
                food_items.add(food_item);
                line = reader.readLine();
            }
            Main.Shop(shopper, food_items);
            writer.write(shopper.getCart().toString());
        }
    }
}
