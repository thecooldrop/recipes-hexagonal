package udarnicka.ingredients.crud.domain.ports;

import org.junit.jupiter.api.Test;
import udarnicka.common.SerialInteger;
import udarnicka.ingredients.crud.domain.ports.Ingredient;
import udarnicka.ingredients.crud.domain.ports.IngredientId;

import static org.junit.jupiter.api.Assertions.*;

class IngredientTest {

    @Test
    void canNotCreateIngredientFromNullValues() {
        assertThrows(NullPointerException.class, () -> new Ingredient(null, null));
    }

    @Test
    void canNotCreateIngredientFromBlankIngredientName() {
        assertThrows(IllegalArgumentException.class, () -> new Ingredient("", new IngredientId(new SerialInteger(1))));
        assertThrows(IllegalArgumentException.class, () -> new Ingredient(" \n", new IngredientId(new SerialInteger(1))));
    }

    @Test
    void canCreateIngredientFromNonBlankIngredientWithNonNullId() {
        new Ingredient("Flour", new IngredientId(new SerialInteger(1)));
    }
}