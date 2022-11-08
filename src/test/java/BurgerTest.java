import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import praktikum.*;

import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;

@RunWith(Enclosed.class)
public class BurgerTest {

    @RunWith(MockitoJUnitRunner.class)
    public static class BurgerTestWithoutParametrized {

        @Mock
        private Bun bun;
        @Mock
        private Ingredient ingredientMock;
        @Mock
        private List<Ingredient> ingredients;

        @Test
        public void getPriceTest() {
            float price = 400F;
            Mockito.when(bun.getPrice()).thenReturn(200F);
            Burger burger = new Burger();
            burger.setBuns(bun);
            assertThat(price, equalTo(burger.getPrice()));
        }


        @Test
        public void addIngredient() {
            Burger burger = new Burger();
            burger.addIngredient(ingredientMock);
        }

        @Test
        public void removeIngredient() {
            Burger burger = new Burger();
            burger.addIngredient(ingredients.get(1));
            burger.removeIngredient(0);
        }

        @Test
        public void moveIngredient() {
            Burger burger = new Burger();
            burger.addIngredient(ingredients.get(1));
            burger.addIngredient(ingredients.get(2));
            burger.moveIngredient(1,0);
        }


        @Test
        public void setBuns() {
            Database database = new Database();
            List<Bun> buns = database.availableBuns();
            Burger burger = new Burger();
            burger.setBuns(buns.get(0));
            assertEquals(buns, database.availableBuns());

        }
    }
    @RunWith(Parameterized.class)
    public static class BurgerTestParametrized {
        private Bun bun = Mockito.mock(Bun.class);

        private final String bunName;
        private final IngredientType ingredientType;
        private final float bunPrice;
        private final float saucePrice;
        private final String ingredientName;

        public BurgerTestParametrized(String bunName, IngredientType ingredientType, float bunPrice, float saucePrice, String ingredientName) {
            this.bunName = bunName;
            this.ingredientType = ingredientType;
            this.bunPrice = bunPrice;
            this.saucePrice = saucePrice;
            this.ingredientName = ingredientName;
        }

        @Parameterized.Parameters
        public static Object[][] parameters() {
            return new Object[][]{
                    {"black bun", IngredientType.SAUCE, 100F, 200F, "sour cream"},
                    {"white bun", IngredientType.FILLING, 10F, 300.55F, "cutlet"},
                    {"red bun", IngredientType.FILLING, 6.5F, 1000F, "dinosaur"},
            };
        }

        @Test
        public void getReceipt() {
            Mockito.when(bun.getName()).thenReturn(bunName);
            Mockito.when(bun.getPrice()).thenReturn(bunPrice);
            Burger burger = new Burger();
            Ingredient ingredient = new Ingredient(ingredientType, ingredientName, saucePrice);
            burger.setBuns(bun);
            burger.addIngredient(ingredient);

            StringBuilder receipt = new StringBuilder(String.format("(==== %s ====)%n", bunName));
            receipt.append(String.format("= %s %s =%n", ingredientType.toString().toLowerCase(), ingredientName));
            receipt.append(String.format("(==== %s ====)%n", bunName));
            receipt.append(String.format("%nPrice: %f%n", burger.getPrice()));
            assertEquals(receipt.toString(), burger.getReceipt());
        }
    }
}
