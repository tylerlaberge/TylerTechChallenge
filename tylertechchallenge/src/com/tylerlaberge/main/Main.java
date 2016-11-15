package com.tylerlaberge.main;

import java.io.*;
import java.util.Arrays;
import java.util.function.DoubleBinaryOperator;

public class Main {

    public static void main(String[] args) throws IOException {
        String input_file_path = args[0];
        String output_file_path = args[1];
        try (
                BufferedReader reader = new BufferedReader(new FileReader(input_file_path));
                Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(output_file_path), "utf-8"));
        ) {
            String line = reader.readLine();
            String[] constraints = line.substring(0, line.length() - 1).split(",");
            int task = Integer.parseInt(constraints[0].trim());
            double budget = Double.parseDouble(constraints[1].trim());
            double weight_limit = Double.parseDouble(constraints[2].trim());
            double volume_limit = Double.parseDouble(constraints[3].trim());
            Shopper shopper = new Shopper(budget, new Cart(weight_limit, volume_limit));
            System.out.println(task);
            System.out.println(shopper);
            line = reader.readLine();
            while(line !=null)
            {
                String[] food_item_details = line.substring(0, line.length() - 1).split(",");
                String name = food_item_details[0].trim();
                int stock = Integer.parseInt(food_item_details[1].trim());
                double price = Double.parseDouble(food_item_details[2].trim());
                double weight = Double.parseDouble(food_item_details[3].trim());
                double volume = Double.parseDouble(food_item_details[4].trim());
                String food_group = food_item_details[5].trim();

                FoodItem food_item = new FoodItem(name, food_group, stock, price, weight, volume);
                System.out.println(food_item);
                line = reader.readLine();
            }
            writer.write(shopper.getCart().getFood_items().toString());
        }
    }
}
