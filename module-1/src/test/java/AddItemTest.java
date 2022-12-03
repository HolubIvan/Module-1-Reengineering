import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AddItemTest {
    @BeforeEach
    void setUp() {

    }

    @Test
    void addItemTestWrongTitle() {
        ShoppingCart cart = new ShoppingCart();
        assertThrows(IllegalArgumentException.class,
                () -> cart.addItem("", 1, 1, Item.Type.REGULAR),
                "Empty title test failed");
        assertThrows(IllegalArgumentException.class,
                () -> cart.addItem(null, 1, 1, Item.Type.REGULAR),
                "Null title test failed");
        assertThrows(IllegalArgumentException.class,
                () -> cart.addItem("Item With 33 Characters Title....", 1, 1, Item.Type.REGULAR),
                "33 characters title test failed");
        assertDoesNotThrow(() -> cart.addItem("1", 1, 1, Item.Type.REGULAR),
                "1 character title test failed");
        assertDoesNotThrow(() -> cart.addItem("Item With 32 Characters Title...", 1, 1, Item.Type.REGULAR),
                "32 characters title test failed");
    }

    @Test
    void addItemTestWrongPrice() {
        ShoppingCart cart = new ShoppingCart();
        assertThrows(IllegalArgumentException.class,
                () -> cart.addItem("Item With 0 Price", 0, 1, Item.Type.REGULAR),
                "0 price item test failed");
        assertThrows(IllegalArgumentException.class,
                () -> cart.addItem("Item With 1000 Price", 1000, 1, Item.Type.REGULAR),
                "1000 price item test failed");
        assertDoesNotThrow(() -> cart.addItem("Item With 1 Price", 1, 1, Item.Type.REGULAR),
                "1 price item test failed");
        assertDoesNotThrow(() -> cart.addItem("Item With 999 Price", 999, 1, Item.Type.REGULAR),
                "999 price item test failed");
    }

    @Test
    void addItemTestWrongQuantity() {
        ShoppingCart cart = new ShoppingCart();
        assertThrows(Exception.class,
                () -> cart.addItem("Item With 0 Quantity", 1, 0, Item.Type.REGULAR),
                "0 quantity item test failed");
        assertThrows(Exception.class,
                () -> cart.addItem("Item With 999 Quantity", 1, 1001, Item.Type.REGULAR),
                "1001 quantity item test failed");
        assertDoesNotThrow(() -> cart.addItem("Item With 1 Quantity", 1, 1, Item.Type.REGULAR),
                "1 quantity item test failed");
        assertDoesNotThrow(() -> cart.addItem("Item With 1000 Quantity", 1, 1000, Item.Type.REGULAR),
                "1000 quantity item test failed");
    }

    @Test
    void addItemTestOneHundredItems() {
        ShoppingCart cart = new ShoppingCart();
        //adding 98 items
        for (int i = 1; i < 99; i++) {
            cart.addItem("Item " + i, 1, 1, Item.Type.REGULAR);
        }
        //adding 99th item
        assertDoesNotThrow(() -> cart.addItem("Item 99", 1, 1, Item.Type.REGULAR),
                "Adding 99 items test failed");
        //adding 100th item
        assertThrows(IndexOutOfBoundsException.class,
                () -> cart.addItem("Item 100", 1, 1, Item.Type.REGULAR),
                "Adding 100 items test failed");
    }

    @Test
    void testAddItemWrongType() {
        // no item type - so can't make a test
    }
}