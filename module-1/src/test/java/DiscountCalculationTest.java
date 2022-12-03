import javafx.util.Pair;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(Parameterized.class)
public class DiscountCalculationTest {
    private Pair<Item.Type, Integer> itemParams;
    private int expectedResult;

    public DiscountCalculationTest(Pair<Item.Type, Integer> itemParams, int expectedResult) {
        this.itemParams = itemParams;
        this.expectedResult = expectedResult;
    }

    @Parameterized.Parameters
    public static Collection parameters() {
        return Arrays.asList(new Object[][]{
                {new Pair<>(Item.Type.REGULAR, 1), 0},
                {new Pair<>(Item.Type.REGULAR, 99), 0},
                {new Pair<>(Item.Type.REGULAR, 100), 10},
                {new Pair<>(Item.Type.REGULAR, 101), 10},
                {new Pair<>(Item.Type.REGULAR, 201), 20},
                {new Pair<>(Item.Type.REGULAR, 800), 80},
                {new Pair<>(Item.Type.REGULAR, 1000), 80},
                {new Pair<>(Item.Type.SECOND, 1), 0},
                {new Pair<>(Item.Type.SECOND, 2), 50},
                {new Pair<>(Item.Type.SECOND, 100), 60},
                {new Pair<>(Item.Type.SECOND, 300), 80},
                {new Pair<>(Item.Type.SECOND, 800), 80},
                {new Pair<>(Item.Type.DISCOUNT, 1), 10},
                {new Pair<>(Item.Type.DISCOUNT, 9), 10},
                {new Pair<>(Item.Type.DISCOUNT, 10), 20},
                {new Pair<>(Item.Type.DISCOUNT, 11), 20},
                {new Pair<>(Item.Type.DISCOUNT, 20), 30},
                {new Pair<>(Item.Type.DISCOUNT, 40), 50},
                {new Pair<>(Item.Type.DISCOUNT, 50), 50},
                {new Pair<>(Item.Type.DISCOUNT, 100), 60},
                {new Pair<>(Item.Type.DISCOUNT, 300), 80},
                {new Pair<>(Item.Type.DISCOUNT, 400), 80},
                {new Pair<>(Item.Type.SALE, 1), 80},
                {new Pair<>(Item.Type.SALE, 10), 80},
                {new Pair<>(Item.Type.SALE, 100), 80}
        });
    }

    @Test
    public void calcRegularDiscount() {
        System.out.println("Parameterized number is : " + itemParams);
        assertEquals(expectedResult,
                ShoppingCart.calculateDiscount(new Item("Regular Item", 1, itemParams.getKey(), itemParams.getValue())));
    }
}