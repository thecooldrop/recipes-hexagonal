package udarnicka.recipes.domain;

import org.junit.jupiter.api.Test;
import udarnicka.recipes.domain.Ingredient;
import udarnicka.recipes.domain.IngredientId;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class IngredientTest {

    @Test
    void canNotCreateIngredientFromNullValues() {
        assertEquals(Optional.empty(), Ingredient.tryFrom(null, null));
    }

    @Test
    void canNotCreateIngredientFromBlankIngredientName() {
        assertEquals(Optional.empty(), Ingredient.tryFrom("", new IngredientId(1)));
        assertEquals(Optional.empty(), Ingredient.tryFrom("  \n", new IngredientId(1)));
    }

    @Test
    void canCreateIngredientFromNonBlankIngredientWithNonNullId() {
        assertTrue(Ingredient.tryFrom("Flour", new IngredientId(1)).isPresent());
    }
}