package ingredients.crud.api;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class IngredientNameTest {

    @Test
    void canNotCreateFromNull() {
        assertThrows(NullPointerException.class, () -> new IngredientName(null));
    }

    @Test
    void canNotCreateFromEmptyStringOrBlank() {
        assertThrows(IllegalArgumentException.class, () -> new IngredientName(""));
        assertThrows(IllegalArgumentException.class, () -> new IngredientName("\n\t"));
    }
}
