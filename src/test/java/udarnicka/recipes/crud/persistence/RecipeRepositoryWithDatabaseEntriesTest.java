package udarnicka.recipes.crud.persistence;

import jdk.jfr.Description;
import org.junit.jupiter.api.Test;
import org.springframework.transaction.annotation.Transactional;
import udarnicka.recipes.crud.domain.ports.Recipe;
import udarnicka.recipes.crud.domain.ports.RecipeId;
import udarnicka.recipes.crud.domain.ports.RecipeRepository;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@Transactional
// TODO: I need to implement this interface
public interface RecipeRepositoryWithDatabaseEntriesTest {

    RecipeRepository getRecipeRepository();
    boolean isRecipeWithIdInDatabase(RecipeId id);
    Recipe recipeToDelete();
    Recipe recipeToRead();
    Long countRecipesInDatabase();


    @Test
    @Description("then deleting a recipe removes it from the database")
    default void deletingTheRecipeRemovesItFromTheDatabase() {
        Recipe recipeToDelete = recipeToDelete();
        long numberOfRecipesInDbBeforeDeletion = countRecipesInDatabase();
        getRecipeRepository().deleteById(recipeToDelete.getId());
        long numberOfRecipesInDbAfterDeletion = countRecipesInDatabase();

        assertThat(numberOfRecipesInDbBeforeDeletion).isGreaterThan(numberOfRecipesInDbAfterDeletion);
        assertThat(isRecipeWithIdInDatabase(recipeToDelete.getId())).isFalse();
    }


    @Test
    @Description("then the existing recipe can be read")
    default void existingRecipeCanBeRead() {
         Recipe recipeToRead = recipeToRead();
         Optional<Recipe> recipeInDb = getRecipeRepository().readById(recipeToRead.getId());
         assertThat(recipeInDb).isEqualTo(Optional.of(recipeToRead));
    }
}
