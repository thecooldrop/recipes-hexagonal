package udarnicka.ingredients.crud.domain.ports;

import org.junit.jupiter.api.Test;
import udarnicka.common.CanonicalName;
import udarnicka.common.SerialInteger;
import static org.junit.jupiter.api.Assertions.*;

class IngredientTest {

    @Test
    void canNotCreateIngredientFromNullValues() {
        assertThrows(NullPointerException.class, () -> new Ingredient(null, null));
    }

    @Test
    void canNotCreateIngredientFromBlankIngredientName() {
        assertThrows(IllegalArgumentException.class, () -> new Ingredient(new IngredientName(new CanonicalName("")), new IngredientId(new SerialInteger(1))));
        assertThrows(IllegalArgumentException.class, () -> new Ingredient(new IngredientName(new CanonicalName("\n\t")), new IngredientId(new SerialInteger(1))));
    }

    @Test
    void canCreateIngredientFromNonBlankIngredientWithNonNullId() {
        new Ingredient(new IngredientName(new CanonicalName("Flour")), new IngredientId(new SerialInteger(1)));
    }
}