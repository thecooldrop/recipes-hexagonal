package udarnicka.recipes.crud.domain.ports;

import jdk.jfr.Description;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.data.repository.CrudRepository;
import udarnicka.common.CanonicalName;
import udarnicka.common.HasCanonicalName;
import udarnicka.common.HasId;
import udarnicka.common.SerialInteger;
import udarnicka.ingredients.crud.domain.ports.DuplicateIngredientException;

import static org.assertj.core.api.Assertions.*;

public abstract class RecipeRepositoryAbstractTest {

    @Nested
    @Description("If there is a database")
    public abstract class GivenADatabase {

        @Nested
        @Description("which contains no recipes")
        public abstract class WhichIsEmpty<Entity extends HasId<?> & HasCanonicalName, DataSource extends CrudRepository<Entity, ?>> {

            public abstract RecipeRepository getTestedRepository();
            public abstract DataSource getDataSource();

            @Test
            @Description("then new Recipes can be saved into the database")
            void thenNewRecipesCanBeSavedIntoTheDatabase() {
                CreateRecipe createRecipeCommand = new CreateRecipe("Pizza Margarita");
                getTestedRepository().create(createRecipeCommand);

                int recipeCount = 0;
                for(Entity recipeInDb : getDataSource().findAll()) {
                    assertThat(recipeInDb.getId()).isNotNull();
                    assertThat(recipeInDb.getCanonicalName()).isEqualTo(new CanonicalName("Pizza Margarita"));
                    assertThat(recipeInDb.getCanonicalName().getOriginal()).isEqualTo("Pizza Margarita");
                    recipeCount++;
                }

                assertThat(recipeCount).isEqualTo(1);
            }

            @Test
            @Description("then recipe with same canonical name can not be saved into database twice")
            void recipeWithSameNameCanNotBeCreatedTwice() {
                CreateRecipe createRecipe = new CreateRecipe("Pizza Margarita");
                getTestedRepository().create(createRecipe);
                assertThatThrownBy(() -> getTestedRepository().create(new CreateRecipe("pizza margarita")))
                        .isInstanceOf(DuplicateRecipeException.class);
            }

            @Test
            @Description("then deleting any recipe returns an empty Optional")
            void deletingAnyRecipeReturnsEmtpyOptional() {
                assertThat(getTestedRepository().deleteById(new RecipeId(new SerialInteger(1)))).isEmpty();
            }

            @Test
            @Description("then reading any ingredient returns an empty Optional")
            void readingANonexistentRecipeReturnsAnEmptyOptional() {
                assertThat(getTestedRepository().readById(new RecipeId(new SerialInteger(1)))).isEmpty();
            }
        }

        @Nested
        public abstract class WhichContainsSomeRecipes {

        }
    }
}
