package udarnicka.recipes.crud.domain.ports;

import jdk.jfr.Description;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class CreateRecipesTest {

    @Test
    @Description("Recipe name can not be null")
    void recipeNameCanNotBeNull() {
        assertThatThrownBy(() -> new CreateRecipe(null)).isInstanceOf(NullPointerException.class);
    }

    @Test
    @Description("Recipe name can not be empty")
    void recipeNameCanNotBeEmpty() {
        assertThatThrownBy(() -> new CreateRecipe("")).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @Description("Recipe name can not be blank")
    void recipeNameCanNotBeBlank() {
        assertThatThrownBy(() -> new CreateRecipe("  \n\t")).isInstanceOf(IllegalArgumentException.class);
    }
}
