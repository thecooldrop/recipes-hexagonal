package udarnicka.recipes.crud.domain.ports;

import jdk.jfr.Description;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RecipeStepTest {

    @Test
    @Description("Can not create a recipe step from a null string")
    void recipeStepCanNotBeANullString() {
        assertThrows(NullPointerException.class, () -> new RecipeStep(null));
    }

    @Test
    @Description("Can not create a recipe step from an empty string")
    void recipeStepCanNotBeAnEmptyString() {
        assertThrows(IllegalArgumentException.class, () -> new RecipeStep(""));
    }

    @Test
    @Description("Can not create a recipe steop from a blank string")
    void recipeStepCanNotBeCreatedFromABlankString() {
        assertThrows(IllegalArgumentException.class, () -> new RecipeStep("\n\t"));
    }
}