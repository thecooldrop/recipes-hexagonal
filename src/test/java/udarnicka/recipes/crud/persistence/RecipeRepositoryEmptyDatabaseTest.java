package udarnicka.recipes.crud.persistence;

import jdk.jfr.Description;
import org.junit.jupiter.api.Test;
import udarnicka.common.SerialInteger;
import udarnicka.recipes.crud.domain.ports.CreateRecipe;
import udarnicka.recipes.crud.domain.ports.DuplicateRecipeException;
import udarnicka.recipes.crud.domain.ports.RecipeId;
import udarnicka.recipes.crud.domain.ports.RecipeRepository;

import javax.transaction.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@Transactional
public interface RecipeRepositoryEmptyDatabaseTest {

    RecipeRepository getRepository();

    /**
     * The database content checker is in charge of performing checks that the content of the database is correct.
     * It is expected that this runnable checks that created elements actually existing the backing database
     * technology.
     *
     * It is necessary to have this generally overridable since both the types used for representing the elements
     * in the backing database technology, as well as class which is used to access the undelying database technology
     * are wildly different.
     *
     * In general this runnable should check that:
     * - There is only one element in the backing storage technology
     * - The single element in backing storage corresponds to that which has been created
     */
    Runnable databaseContentChecker();


    @Test
    @Description("then new Recipes can be saved into the database")
    default void thenNewRecipesCanBeSavedIntoTheDatabase() {
        CreateRecipe createRecipeCommand = new CreateRecipe("Pizza Margarita");
        getRepository().create(createRecipeCommand);
        databaseContentChecker().run();
    }

    @Test
    @Description("then recipe with same canonical name can not be saved into database twice")
    default void recipeWithSameNameCanNotBeCreatedTwice() {
        CreateRecipe createRecipe = new CreateRecipe("Pizza Margarita");
        getRepository().create(createRecipe);
        assertThatThrownBy(() -> getRepository().create(new CreateRecipe("pizza margarita")))
                .isInstanceOf(DuplicateRecipeException.class);
    }

    @Test
    @Description("then reading any ingredient returns an empty Optional")
    default void readingANonexistentRecipeReturnsAnEmptyOptional() {
        assertThat(getRepository().readById(new RecipeId(new SerialInteger(1)))).isEmpty();
    }

}
