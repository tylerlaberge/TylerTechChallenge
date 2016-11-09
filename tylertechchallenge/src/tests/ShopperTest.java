package tests;

import com.tylerlaberge.main.Shopper;
import com.tylerlaberge.main.Cart;
import com.tylerlaberge.main.FoodItem;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ShopperTest {

    private Shopper shopper;
    private Cart cart;
    private FoodItem food_item;

    @Before
    public void setUp() throws Exception {
        this.food_item = new FoodItem("bread", "grains", 30, 3.00, 0.5, 4.00);
        this.cart = new Cart(20.0, 12.0);
        this.shopper = new Shopper(100.0, this.cart);
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void addToCart() throws Exception {

        this.shopper.addToCart(this.food_item);

        int amount = this.cart.getFood_items().get(this.food_item.getName());

        assertEquals(1, amount);
        assertEquals(29, this.food_item.getStock());
        assertEquals(97.0, this.shopper.getRemaining_budget(), 0.0);
    }

    @Test
    public void getBudget() throws Exception {
        assertEquals(100.0, this.shopper.getBudget(), 0.0);
    }

    @Test
    public void getRemainingBudget() throws Exception {
        assertEquals(100.0, this.shopper.getRemaining_budget(), 0.0);
    }

    @Test
    public void getCart() throws Exception {
        assertEquals(this.cart, this.shopper.getCart());
    }
}