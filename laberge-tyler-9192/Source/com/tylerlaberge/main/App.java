package com.tylerlaberge.main;

import com.tylerlaberge.exceptions.FailedToSolveException;
import com.tylerlaberge.exceptions.ValidationException;
import com.tylerlaberge.tasks.*;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/**
 * The main class of the application.
 *
 * This application solves tasks described by the TylerTech challenge.
 */
public class App {

    private BufferedReader reader;
    private BufferedWriter writer;
    private HashMap<String, String> constraints;
    private List<HashMap<String, String>> food_item_details_list;

    /**
     * Create a new App instance.
     *
     * @param input_file_path   The path to the input file containing the constraints of the task.
     * @param output_file_path  The path to the output file to write the solution too.
     */
    private App(String input_file_path, String output_file_path) {
        try {
            this.reader = new BufferedReader(new FileReader(input_file_path));
            this.writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(output_file_path), "utf-8"));
        } catch (IOException e) {
            System.out.println("Failed to open file.");
            System.exit(1);
        }
        try {
            this.constraints = App.parseConstraints(this.reader.readLine());
            this.food_item_details_list = new ArrayList<>();
            this.buildFoodItemDetailsList();

            App.validateConstraints(this.constraints);
            App.validateFoodItemDetails(this.food_item_details_list);
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid input file format.");
            System.exit(1);
        } catch (ValidationException e) {
            System.out.println("Input file failed validation.");
            System.exit(1);
        } catch (IOException e) {
            System.out.println("Failed to read from file.");
            System.exit(1);
        }
    }

    /**
     * Run the application.
     *
     * Solves the problem described in the given input file path and outputs the solution to the given output file path.
     */
    private void run() {
        int task_number = Integer.parseInt(this.constraints.get("task"));

        Task task = null;
        if (task_number == 1) {
            task = new TaskOne(this.constraints, this.food_item_details_list);
        } else if (task_number == 2) {
            task = new TaskTwo(this.constraints, this.food_item_details_list);
        } else if (task_number == 3) {
            task = new TaskThree(this.constraints, this.food_item_details_list);
        } else if (task_number == 4) {
            task = new TaskFour(this.constraints, this.food_item_details_list);
        }
        if (task != null) {
            try {
                String optimal_cart = task.solve();
                this.writer.write(optimal_cart);
                this.writer.close();
            } catch (FailedToSolveException e) {
                System.out.println(e.toString());
            } catch (IOException e) {
                System.out.println("Failed to write to file.");
            }
        }
    }

    /**
     * Builds the list of details for each food item contained in the file at the given input file path.
     *
     * @throws IOException  If the input file could not be read.
     */
    private void buildFoodItemDetailsList() throws IOException {
        String food_item_line = this.reader.readLine();
        while (food_item_line != null) {
            HashMap<String, String> food_item_details = App.parseFoodItemDetails(food_item_line);
            this.food_item_details_list.add(food_item_details);
            food_item_line = this.reader.readLine();
        }
    }

    /**
     * Parses the constraints out of the constrains line in the given input file.
     *
     * @param line  The line containing the constraints of the problem.
     * @return      A mapping of the constraints names to their values.
     */
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

    /**
     * Parses the food item details out of a food item line in the given input file.
     *
     * @param line  A line containing the details of a food item for the problem.
     * @return      A mapping of the food item detail names to their values.
     */
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

    /**
     * Validate that the constraints are valid values.
     *
     * @param constraints           The constraints to validate.
     * @throws ValidationException  If the constraints fail validation.
     */
    private static void validateConstraints(HashMap<String, String> constraints) throws ValidationException {
        if (!constraints.get("task").matches("^[1-4]$")
                || !constraints.get("budget").matches("^(0+|[1-9][0-9]*)?\\.?[0-9]+$")
                || !constraints.get("weight_limit").matches("^(0+|[1-9][0-9]*)?\\.?[0-9]+$")
                || !constraints.get("volume_limit").matches("^(0+|[1-9][0-9]*)?\\.?[0-9]+$")
                ) {
            throw new ValidationException("Invalid constraints.");
        }
    }

    /**
     * Validate that the list of food item details are valid values.
     *
     * @param food_item_details_list    The list of food item details to validate.
     * @throws ValidationException      If a food item fails validation.
     */
    private static void validateFoodItemDetails(List<HashMap<String, String>> food_item_details_list) throws ValidationException {
        if (food_item_details_list.isEmpty()) {
            throw new ValidationException("Invalid food item details.");
        }
        for (HashMap<String, String> food_item_details : food_item_details_list) {
            if (!food_item_details.get("name").matches("^[a-zA-Z]+$")
                    || !food_item_details.get("stock").matches("^(0|[1-9][0-9]*)$")
                    || !food_item_details.get("price").matches("^(0+|[1-9][0-9]*)?\\.?[0-9]+$")
                    || !food_item_details.get("weight").matches("^(0+|[1-9][0-9]*)?\\.?[0-9]+$")
                    || !food_item_details.get("volume").matches("^(0+|[1-9][0-9]*)?\\.?[0-9]+$")
                    || !food_item_details.get("food_group").matches("^[a-zA-Z]+$")) {
                throw new ValidationException("Invalid food item details");
            }
        }
    }

    /**
     * The main entry point of the application.
     * Runs the application with the given input and output files.
     *
     * @param args  Command line arguments. Should be of the form [input_file_path, output_file_path].
     */
    public static void main(String[] args) {
        String input_file_path = null;
        String output_file_path = null;
        try{
            input_file_path = args[0];
            output_file_path = args[1];
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Must supply input and output file arguments.");
        }
        if (input_file_path != null && output_file_path != null){
            App app = new App(input_file_path, output_file_path);
            app.run();
        }
    }
}
