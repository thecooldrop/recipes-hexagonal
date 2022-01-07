package udarnicka.recipes.ingredients.domain;

import org.junit.jupiter.api.Test;

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
    void canCreateIngredientFromNonblankIngredientWithNonNullId() {
        assertTrue(Ingredient.tryFrom("Flour", new IngredientId(1)).isPresent());
    }

    @Test
    void ingredientsNameCaseDoesNotMatterForEquality() {
        Ingredient flour = Ingredient.tryFrom("Flour", new IngredientId(1)).get();
        Ingredient flourTwo = Ingredient.tryFrom("flour", new IngredientId(1)).get();
        assertEquals(flour, flourTwo);
        "abc".intern()
    }

    @Test
    void ingredientsWithSameNameButDifferentIdAreNotTheSame() {
        Ingredient flour = Ingredient.tryFrom("Flour", new IngredientId(1)).get();
        Ingredient flourTwo = Ingredient.tryFrom("Flour", new IngredientId(2)).get();
        assertNotEquals(flour, flourTwo);
    }
}