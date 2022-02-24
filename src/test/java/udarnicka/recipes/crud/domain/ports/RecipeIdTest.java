package udarnicka.recipes.crud.domain.ports;

import jdk.jfr.Description;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class RecipeIdTest {

    @Test
    @Description("The integer representing the recipe identifier may not be null")
    void recipeIdentifierMayNotBeNull() {
        assertThatThrownBy(() -> new RecipeId((Integer) null)).isInstanceOf(NullPointerException.class);
    }
}
