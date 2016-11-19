package tests;

import com.tylerlaberge.tasks.Task;
import com.tylerlaberge.tasks.TaskThree;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.*;


public class TaskThreeTest {
    private HashMap<String, String> constraints = new HashMap<>();
    private List<HashMap<String, String>> inventory_details = new ArrayList<>();
    private Task task_two = new TaskThree();

    @Before
    public void setUp() throws Exception {
        this.constraints.put("task", "3");
        this.constraints.put("budget", "100");
        this.constraints.put("weight_limit", "20");
        this.constraints.put("volume_limit", "12");

        HashMap<String, String> bread_details = new HashMap<>();
        bread_details.put("name", "bread");
        bread_details.put("stock", "30");
        bread_details.put("price", "3.00");
        bread_details.put("weight", "0.5");
        bread_details.put("volume", "4.00");
        bread_details.put("food_group", "grains");

        HashMap<String, String> milk_details = new HashMap<>();
        milk_details.put("name", "milk");
        milk_details.put("stock", "20");
        milk_details.put("price", "4.00");
        milk_details.put("weight", "3.5");
        milk_details.put("volume", "3.8");
        milk_details.put("food_group", "dairy");

        HashMap<String, String> watermelon_details = new HashMap<>();
        watermelon_details.put("name", "watermelon");
        watermelon_details.put("stock", "5");
        watermelon_details.put("price", "1.00");
        watermelon_details.put("weight", "10");
        watermelon_details.put("volume", "2");
        watermelon_details.put("food_group", "veggies");

        HashMap<String, String> chicken_details = new HashMap<>();
        chicken_details.put("name", "chicken");
        chicken_details.put("stock", "5");
        chicken_details.put("price", "5.00");
        chicken_details.put("weight", "1.3");
        chicken_details.put("volume", "1.36");
        chicken_details.put("food_group", "meat");

        this.inventory_details.add(bread_details);
        this.inventory_details.add(milk_details);
        this.inventory_details.add(watermelon_details);
        this.inventory_details.add(chicken_details);
    }

    @Test
    public void testSolve() throws Exception {
        String expected_solution = "bread, 1;\nchicken, 1;\nmilk, 1;\nwatermelon, 1;";
        String actual_solution = this.task_two.solve(this.constraints, this.inventory_details);

        assertEquals(expected_solution, actual_solution);
    }
}