package udarnicka.ingredients.crud.persistence.jpa;

import jdk.jfr.Description;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class IngredientJpaTest {

    @Test
    @Description("Setting the id for JPA Ingredient entity should not allow nulls")
    void jpaIngredientEntityIsNotNull() {
        IngredientJpaEntity ingredient = new IngredientJpaEntity();
        assertThatThrownBy(() -> ingredient.setId(null)).isInstanceOf(NullPointerException.class);
    }

    @Test
    @Description("Setting the name of ingredient sets the canonical name to lowercase of the name")
    void settingTheNameOfIngredientSetsTheCanonicalNameOfTheIngredient() {
        IngredientJpaEntity ingredient = new IngredientJpaEntity();
        ingredient.setName("Flour");
        assertThat(ingredient.getCanonicalName()).isEqualTo("flour");
        ingredient.setName("FLOUR");
        assertThat(ingredient.getCanonicalName()).isEqualTo("flour");
    }

    @Test
    @Description("Name of the ingredient may not be set to blank")
    void nameOfTheIngredientMayNotBeSetToBlank() {
        IngredientJpaEntity ingredient = new IngredientJpaEntity();
        assertThatThrownBy(() -> ingredient.setName("\n\t  ")).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @Description("Name of the ingredient may not be set to empty")
    void nameOfTheIngredientMayNotBeSetToEmpty() {
        IngredientJpaEntity ingredient = new IngredientJpaEntity();
        assertThatThrownBy(() -> ingredient.setName("")).isInstanceOf(IllegalArgumentException.class);
    }
}
