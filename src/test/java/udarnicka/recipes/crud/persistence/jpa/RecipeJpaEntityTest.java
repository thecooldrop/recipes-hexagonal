package udarnicka.recipes.crud.persistence.jpa;

import jdk.jfr.Description;
import org.junit.jupiter.api.Test;
import udarnicka.common.CanonicalName;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class RecipeJpaEntityTest {

    @Test
    void canNotSetNameToEmptyString() {
        RecipeJpaEntity recipe = new RecipeJpaEntity();
        assertThatThrownBy(() -> recipe.setName("")).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void canNotSetNameToBlankString() {
        RecipeJpaEntity recipe = new RecipeJpaEntity();
        assertThatThrownBy(() -> recipe.setName("\n\t")).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @Description("If the name of the recipe is already set, then we can call the setName only with values, which" +
            "are equal to the already set name. The comparison between new value and old value should be done as defined" +
            "by the class CanonicalName")
    void nameOfTheRecipeCanBeChangedToCanonicallyEqualValue() {
        RecipeJpaEntity recipe = new RecipeJpaEntity();

        CanonicalName oldName = new CanonicalName("Pizza");
        CanonicalName newName = new CanonicalName("PiZZa");
        CanonicalName incompatibleName = new CanonicalName("Pizza Margarita");

        recipe.setName("Pizza");
        assertThat(oldName).isEqualTo(newName);
        assertThat(newName).isNotEqualTo(incompatibleName);


        recipe.setName(newName.getOriginal()); // should work with no exception
        assertThatThrownBy(() -> recipe.setName(incompatibleName.getOriginal())).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @Description("Test that the RecipeJpaEntity can return its canonical name")
    void canReturnItsCanonicalName() {
        RecipeJpaEntity recipe = new RecipeJpaEntity();
        recipe.setName("PIZZA");

        CanonicalName expectedCanonicalName = new CanonicalName("PIZZA");
        assertThat(recipe.getCanonicalName()).isEqualTo(expectedCanonicalName);
    }

    @Test
    @Description("If the name of the recipe is not yet set, then the returned canonical name is null")
    void ifNameIsNotSetCanonicalNameIsNull() {
        RecipeJpaEntity recipe = new RecipeJpaEntity();
        assertThat(recipe.getCanonicalName()).isNull();
    }
}
