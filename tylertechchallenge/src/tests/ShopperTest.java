package tests;

import com.tylerlaberge.domain.Cart;
import com.tylerlaberge.domain.FoodItem;
import com.tylerlaberge.domain.Shopper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ShopperTest {

    private Shopper shopper;
    private Cart cart;
    private FoodItem food_item;

    @Before
    public void setUp() throws Exception {
        this.food_item = new FoodItem("bread", "grains", 30, 3.00, 0.5, 4.00);
        this.cart = new Cart(20.0, 65.0);
        this.shopper = new Shopper(100.0, this.cart);
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void addToCart() throws Exception {

        this.shopper.addToCart(this.food_item, this.food_item.getStock() / 2);
        assertEquals(55.00, this.shopper.getRemainingBudget(), 0.0);
    }

    @Test
    public void testRemoveFromCart() throws Exception {

        this.shopper.addToCart(this.food_item, 5);
        this.shopper.removeFromCart(this.food_item, 5);

        assertEquals(100.0, this.shopper.getRemainingBudget(), 0.0);
    }

    @Test
    public void getBudget() throws Exception {
        assertEquals(100.0, this.shopper.getBudget(), 0.0);
    }

    @Test
    public void getRemainingBudget() throws Exception {
        assertEquals(100.0, this.shopper.getRemainingBudget(), 0.0);
    }

    @Test
    public void getCart() throws Exception {
        assertEquals(this.cart, this.shopper.getCart());
    }
}