package udarnicka.recipes.crud.domain.ports;

import jdk.jfr.Description;
import org.junit.jupiter.api.Test;
import udarnicka.common.SerialInteger;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.*;

public class RecipeTest {

    @Test
    @Description("Inputs to the Recipe class constructor may not be null ( a really technical test )")
    void inputsForRecipeMayNotBeNull() {
        assertThatThrownBy(() -> new Recipe(null, null, null, null, null, null)).isInstanceOf(NullPointerException.class);
        assertThatThrownBy(() -> new Recipe(null, new RecipeId(new SerialInteger(1)), new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>()));
        assertThatThrownBy(() -> new Recipe("Chopping onions MPV", null, new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>())).isInstanceOf(NullPointerException.class);
        assertThatThrownBy(() -> new Recipe("Chopping onions MPV", new RecipeId(new SerialInteger(1)), null, new ArrayList<>(), new ArrayList<>(), new ArrayList<>())).isInstanceOf(NullPointerException.class);
        assertThatThrownBy(() -> new Recipe("Chopping onions MPV", new RecipeId(new SerialInteger(1)), new ArrayList<>(), null, new ArrayList<>(), new ArrayList<>())).isInstanceOf(NullPointerException.class);
        assertThatThrownBy(() -> new Recipe("Chopping onions MPV", new RecipeId(new SerialInteger(1)), new ArrayList<>(), new ArrayList<>(), null, new ArrayList<>())).isInstanceOf(NullPointerException.class);
        assertThatThrownBy(() -> new Recipe("Chopping onions MPV", new RecipeId(new SerialInteger(1)), new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), null)).isInstanceOf(NullPointerException.class);
    }

    @Test
    @Description("The name of the recipe may not be an empty or blank string")
    void recipeNameMayNotBeBlankOrEmpty() {
        assertThatThrownBy(() -> new Recipe("", new RecipeId(new SerialInteger(1)), new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>())).isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> new Recipe("\n \t", new RecipeId(new SerialInteger(1)), new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>())).isInstanceOf(IllegalArgumentException.class);
    }
}

