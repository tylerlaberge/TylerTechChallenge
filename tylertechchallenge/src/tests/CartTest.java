package tests;

import com.tylerlaberge.main.Cart;
import com.tylerlaberge.main.FoodItem;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CartTest {
    private FoodItem food_item;
    private Cart cart;

    @Before
    public void setUp() throws Exception {
        this.food_item = new FoodItem("bread", "grains", 30, 3.00, 0.5, 4.00);
        this.cart = new Cart(20.0, 12.0);
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void addFoodItem() throws Exception {
        this.cart.addFoodItem(this.food_item);
        this.cart.addFoodItem(this.food_item);

        int amount  = this.cart.getFood_items().get("bread");

        assertEquals(28, this.food_item.getStock());
        assertEquals(1.0, this.cart.getCurrent_weight(), 0.0);
        assertEquals(8.00, this.cart.getCurrent_volume(), 0.0);
        assertEquals(2, amount);
    }

    @Test
    public void getWeight_limit() throws Exception {
        assertEquals(20.0, this.cart.getWeight_limit(), 0.0);
    }

    @Test
    public void getVolume_limit() throws Exception {
        assertEquals(12.0, this.cart.getVolume_limit(), 0.0);
    }

    @Test
    public void getCurrent_weight() throws Exception {
        assertEquals(0.0, this.cart.getCurrent_weight(), 0.0);
    }

    @Test
    public void getCurrent_volume() throws Exception {
        assertEquals(0.0, this.cart.getCurrent_volume(), 0.0);
    }

    @Test
    public void getFood_items() throws Exception {
        assertTrue(this.cart.getFood_items().isEmpty());
    }
}