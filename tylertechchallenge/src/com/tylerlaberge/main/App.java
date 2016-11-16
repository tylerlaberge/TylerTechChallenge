package com.tylerlaberge.main;

import com.tylerlaberge.tasks.Task;
import com.tylerlaberge.tasks.TaskOne;
import com.tylerlaberge.tasks.TaskTwo;

import java.io.*;
import java.util.*;

public class App {

    public static void main(String[] args) throws IOException {
        String input_file_path = args[0];
        String output_file_path = args[1];

        BufferedReader reader = new BufferedReader(new FileReader(input_file_path));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(output_file_path), "utf-8"));

        String first_line = reader.readLine();

        HashMap<String, String> constraints = App.parseConstraints(first_line);
        List<HashMap<String, String>> food_item_details = new ArrayList<>();

        String food_item_line = reader.readLine();
        while (food_item_line != null) {
            food_item_details.add(App.parseFoodItemDetails(food_item_line));
            food_item_line = reader.readLine();
        }
        reader.close();

        Task task = null;
        if (Integer.parseInt(constraints.get("task")) == 1) {
            task = new TaskOne();
        }
        else if (Integer.parseInt(constraints.get("task")) == 2) {
            task = new TaskTwo();
        }
        if (task != null) {
            String optimal_cart = task.solve(constraints, food_item_details);
            writer.write(optimal_cart);
        }
        writer.close();
    }

    private static HashMap<String, String> parseConstraints(String line) {
        HashMap<String, String> constraints_map = new HashMap<>();
        String[] constraints = line.substring(0, line.length() - 1).split(",");

        constraints_map.put("task", constraints[0].trim());
        constraints_map.put("budget", constraints[1].trim());
        constraints_map.put("weight_limit", constraints[2].trim());
        constraints_map.put("volume_limit", constraints[3].trim());

        return constraints_map;
    }

    private static HashMap<String, String> parseFoodItemDetails(String line) {
        HashMap<String, String> food_item_map = new HashMap<>();

        String[] food_item_line = line.substring(0, line.length() - 1).split(",");

        food_item_map.put("name", food_item_line[0].trim());
        food_item_map.put("stock", food_item_line[1].trim());
        food_item_map.put("price", food_item_line[2].trim());
        food_item_map.put("weight", food_item_line[3].trim());
        food_item_map.put("volume", food_item_line[4].trim());
        food_item_map.put("food_group", food_item_line[5].trim());

        return food_item_map;
    }
}
