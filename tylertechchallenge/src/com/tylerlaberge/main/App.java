package com.tylerlaberge.main;

import jdk.nashorn.internal.runtime.regexp.joni.exception.ValueException;

import java.io.*;
import java.util.*;

public class App {

    private Shopper shopper;
    private List<FoodItem> food_items = new ArrayList<>();
    private BufferedReader reader;
    private BufferedWriter writer;

    private App(String input_file_path, String output_file_path) throws IOException {
        this.reader = new BufferedReader(new FileReader(input_file_path));
        this.writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(output_file_path), "utf-8"));
    }
    private void prepare() throws IOException {
        this.buildShopper();
        this.buildInventory();
        this.reader.close();
    }
    private void finish() throws IOException {
        this.writer.write(this.shopper.getCart().toString());
        this.writer.close();
    }
    private void buildShopper() throws IOException {
        String line = this.reader.readLine();
        HashMap<String, Double> constraints = this.parseConstraints(line);

        if(constraints.get("task") == 1) {
            this.shopper = new Shopper(constraints.get("budget"), new Cart());
        }
        else {
            throw new ValueException("Invalid task");
        }
    }
    private void buildInventory() throws IOException {
        String line = this.reader.readLine();
        while(line !=null)
        {
            FoodItem food_item = this.parseFoodItem(line);
            this.food_items.add(food_item);
            line = this.reader.readLine();
        }
    }
    private HashMap<String, Double> parseConstraints(String line) {
        String[] constraints = line.substring(0, line.length() - 1).split(",");

        HashMap<String, Double> constraints_map = new HashMap<>();
        constraints_map.put("task", Double.parseDouble(constraints[0].trim()));
        constraints_map.put("budget", Double.parseDouble(constraints[1].trim()));
        constraints_map.put("weight_limit", Double.parseDouble(constraints[2].trim()));
        constraints_map.put("volume_limit", Double.parseDouble(constraints[3].trim()));

        return constraints_map;
    }
    private FoodItem parseFoodItem(String line) {
        String[] food_item_details = line.substring(0, line.length() - 1).split(",");

        String name = food_item_details[0].trim();
        int stock = Integer.parseInt(food_item_details[1].trim());
        double price = Double.parseDouble(food_item_details[2].trim());
        double weight = Double.parseDouble(food_item_details[3].trim());
        double volume = Double.parseDouble(food_item_details[4].trim());
        String food_group = food_item_details[5].trim();

        return new FoodItem(name, food_group, stock, price, weight, volume);
    }
    private void run() {
        Collections.sort(this.food_items);
        FoodItem prev_item = null;
        for (FoodItem food_item : this.food_items){
            int quantity = (int)this.shopper.getRemaining_budget()/(int)food_item.getPrice();
            if (quantity < 1) {
                if (prev_item != null) {
                    double prev_remaining_budget = this.shopper.getRemaining_budget();
                    this.shopper.removeFromCart(prev_item, 1);
                    double new_remaining_budget = this.shopper.getRemaining_budget() - food_item.getPrice();
                    if (new_remaining_budget < prev_remaining_budget) {
                        this.shopper.addToCart(food_item, 1);
                    }
                    else {
                        this.shopper.addToCart(prev_item, 1);
                    }
                }
                break;
            }
            else if (quantity >= food_item.getStock()) {
                this.shopper.addToCart(food_item, food_item.getStock());
            }
            else {
                this.shopper.addToCart(food_item, quantity);
            }
            prev_item = food_item;
        }
    }
    public static void main(String[] args) throws IOException {
        String input_file_path = args[0];
        String output_file_path = args[1];

        App app = new App(input_file_path, output_file_path);
        app.prepare();
        app.run();
        app.finish();
    }
}
