package tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import com.tylerlaberge.domain.FoodItem;

public class FoodItemTest {
    private FoodItem food_item;

    @Before
    public void setUp() throws Exception {
        this.food_item = new FoodItem("bread", "grains", 30, 3.00, 0.5, 4.00);
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void getName() throws Exception {
        assertEquals("bread", this.food_item.getName());
    }

    @Test
    public void getFood_group() throws Exception {
        assertEquals("grains", this.food_item.getFood_group());
    }

    @Test
    public void getStock() throws Exception {
        assertEquals(30, this.food_item.getStock());
    }

    @Test
    public void getPrice() throws Exception {
        assertEquals(3.00, this.food_item.getPrice(), 0.0);
    }

    @Test
    public void getWeight() throws Exception {
        assertEquals(0.5, this.food_item.getWeight(), 0.0);
    }

    @Test
    public void getVolume() throws Exception {
        assertEquals(4.00, this.food_item.getVolume(), 0.0);
    }

    @Test
    public void setStock() throws Exception {
        this.food_item.setStock(45);
        assertEquals(45, this.food_item.getStock());
    }

}