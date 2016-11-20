package tests;

import com.tylerlaberge.domain.Cart;
import com.tylerlaberge.domain.FoodItem;

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
        this.cart = new Cart(20.0, 65.0);
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void addFoodItem() throws Exception {
        this.cart.addFoodItem(this.food_item, this.food_item.getStock()/2);

        int amount  = this.cart.getFoodItems().get(this.food_item);

        assertEquals(15, this.food_item.getStock());
        assertEquals(7.5, this.cart.getCurrentWeight(), 0.0);
        assertEquals(60, this.cart.getCurrentVolume(), 0.0);
        assertEquals(15, amount);
    }

    @Test
    public void testRemoveFoodItem() throws Exception {
        this.cart.addFoodItem(this.food_item, 5);
        this.cart.removeFoodItem(this.food_item, 5);

        assertEquals(30, this.food_item.getStock());
        assertEquals(0, this.cart.getCurrentWeight(), 0.0);
        assertEquals(0, this.cart.getCurrentVolume(), 0.0);
        assertFalse(this.cart.getFoodItems().containsKey("bread"));
    }

    @Test(expected=IllegalArgumentException.class)
    public void testRemoveOverQuantity() {
        this.cart.addFoodItem(this.food_item, 5);
        this.cart.removeFoodItem(this.food_item, 6);
    }

    @Test(expected=IllegalArgumentException.class)
    public void testRemoveMissingItem() {
        this.cart.removeFoodItem(this.food_item, 1);
    }

    @Test
    public void getWeight_limit() throws Exception {
        assertEquals(20.0, this.cart.getWeightLimit(), 0.0);
    }

    @Test
    public void getVolume_limit() throws Exception {
        assertEquals(65.0, this.cart.getVolumeLimit(), 0.0);
    }

    @Test
    public void getCurrent_weight() throws Exception {
        assertEquals(0.0, this.cart.getCurrentWeight(), 0.0);
    }

    @Test
    public void getCurrent_volume() throws Exception {
        assertEquals(0.0, this.cart.getCurrentVolume(), 0.0);
    }

    @Test
    public void getFood_items() throws Exception {
        assertTrue(this.cart.getFoodItems().isEmpty());
    }
}