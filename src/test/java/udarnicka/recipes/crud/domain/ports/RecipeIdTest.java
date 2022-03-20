package udarnicka.recipes.crud.domain.ports;

import jdk.jfr.Description;
import org.junit.jupiter.api.Test;
import udarnicka.common.SerialInteger;

import static org.assertj.core.api.Assertions.*;

public class RecipeIdTest {

    @Test
    @Description("The integer representing the recipe identifier may not be null")
    void recipeIdentifierMayNotBeNull() {
        assertThatThrownBy(() -> new RecipeId(null)).isInstanceOf(NullPointerException.class);
    }
}
