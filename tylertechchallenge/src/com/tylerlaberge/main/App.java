package com.tylerlaberge.main;

import com.tylerlaberge.exceptions.FailedToSolveException;
import com.tylerlaberge.tasks.*;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class App {

    public static void main(String[] args) throws IOException {
        String input_file_path = args[0];
        String output_file_path = args[1];

        BufferedReader reader = new BufferedReader(new FileReader(input_file_path));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(output_file_path), "utf-8"));

        String first_line = reader.readLine();

        HashMap<String, String> constraints = new HashMap<>();
        List<HashMap<String, String>> food_item_details_list = new ArrayList<>();
        boolean valid_input = false;
        try {
            constraints = App.parseConstraints(first_line);

            String food_item_line = reader.readLine();
            while (food_item_line != null) {
                HashMap<String, String> food_item_details = App.parseFoodItemDetails(food_item_line);
                food_item_details_list.add(food_item_details);
                food_item_line = reader.readLine();
            }
            reader.close();

            App.validateConstraints(constraints);
            App.validateFoodItemDetails(food_item_details_list);
            valid_input = true;

        } catch (IllegalArgumentException e) {
            System.out.println("Invalid input file format.");
        }
        if (valid_input) {

            int task_number = Integer.parseInt(constraints.get("task"));

            Task task = null;
            if (task_number == 1) {
                task = new TaskOne(constraints, food_item_details_list);
            } else if (task_number == 2) {
                task = new TaskTwo(constraints, food_item_details_list);
            } else if (task_number == 3) {
                task = new TaskThree(constraints, food_item_details_list);
            } else if (task_number == 4) {
                task = new TaskFour(constraints, food_item_details_list);
            }
            if (task != null) {
                try {
                    String optimal_cart = task.solve();
                    writer.write(optimal_cart);
                } catch (FailedToSolveException e) {
                    System.out.println(e.toString());
                }
            }
            writer.close();
        }
    }

    private static HashMap<String, String> parseConstraints(String line) {
        HashMap<String, String> constraints_map = new HashMap<>();

        try {
            if (!line.substring(line.length() - 1).equals(";")) {
                throw new IllegalArgumentException("Invalid line format");
            }
            String[] constraints = line.substring(0, line.length() - 1).split(",");
            constraints_map.put("task", constraints[0].trim());
            constraints_map.put("budget", constraints[1].trim());
            constraints_map.put("weight_limit", constraints[2].trim());
            constraints_map.put("volume_limit", constraints[3].trim());
        } catch (NullPointerException e) {
            throw new IllegalArgumentException("Invalid line format");
        }
        return constraints_map;
    }

    private static HashMap<String, String> parseFoodItemDetails(String line) {
        if (!line.substring(line.length() - 1).equals(";")) {
            throw new IllegalArgumentException("Invalid line format");
        }
        HashMap<String, String> food_item_map = new HashMap<>();
        try {
            String[] food_item_line = line.substring(0, line.length() - 1).split(",");

            food_item_map.put("name", food_item_line[0].trim());
            food_item_map.put("stock", food_item_line[1].trim());
            food_item_map.put("price", food_item_line[2].trim());
            food_item_map.put("weight", food_item_line[3].trim());
            food_item_map.put("volume", food_item_line[4].trim());
            food_item_map.put("food_group", food_item_line[5].trim());
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new IllegalArgumentException("Invalid line format");
        }
        return food_item_map;
    }

    private static void validateConstraints(HashMap<String, String> constraints) {
        if (!constraints.get("task").matches("^[1-4]$")
                || !constraints.get("budget").matches("^(0+|[1-9][0-9]*)?\\.?[0-9]+$")
                || !constraints.get("weight_limit").matches("^(0+|[1-9][0-9]*)?\\.?[0-9]+$")
                || !constraints.get("volume_limit").matches("^(0+|[1-9][0-9]*)?\\.?[0-9]+$")
                ) {
            throw new IllegalArgumentException("Invalid constraints.");
        }
    }

    private static void validateFoodItemDetails(List<HashMap<String, String>> food_item_details_list) {
        if (food_item_details_list.isEmpty()) {
            throw new IllegalArgumentException("Invalid food item details.");
        }
        for (HashMap<String, String> food_item_details : food_item_details_list) {
            if (!food_item_details.get("name").matches("^[a-zA-Z]+$")
                    || !food_item_details.get("stock").matches("^(0|[1-9][0-9]*)$")
                    || !food_item_details.get("price").matches("^(0+|[1-9][0-9]*)?\\.?[0-9]+$")
                    || !food_item_details.get("weight").matches("^(0+|[1-9][0-9]*)?\\.?[0-9]+$")
                    || !food_item_details.get("volume").matches("^(0+|[1-9][0-9]*)?\\.?[0-9]+$")
                    || !food_item_details.get("food_group").matches("^[a-zA-Z]+$")) {
                throw new IllegalArgumentException("Invalid food item details");
            }
        }
    }
}
