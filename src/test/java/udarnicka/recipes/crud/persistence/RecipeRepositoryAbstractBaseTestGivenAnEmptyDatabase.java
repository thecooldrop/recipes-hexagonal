package udarnicka.recipes.crud.persistence;

import jdk.jfr.Description;
import org.junit.jupiter.api.Test;
import org.springframework.data.repository.CrudRepository;
import udarnicka.common.CanonicalName;
import udarnicka.common.HasCanonicalName;
import udarnicka.common.HasId;
import udarnicka.common.SerialInteger;
import udarnicka.recipes.crud.domain.ports.CreateRecipe;
import udarnicka.recipes.crud.domain.ports.DuplicateRecipeException;
import udarnicka.recipes.crud.domain.ports.RecipeId;
import udarnicka.recipes.crud.domain.ports.RecipeRepository;

import javax.transaction.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@Transactional
public abstract class RecipeRepositoryAbstractBaseTestGivenAnEmptyDatabase<Entity extends HasId<?> & HasCanonicalName, DataSource extends CrudRepository<Entity, ?>> {

    protected RecipeRepository recipeRepository;
    protected DataSource dataSource;

    @Test
    @Description("then new Recipes can be saved into the database")
    protected void thenNewRecipesCanBeSavedIntoTheDatabase() {
        CreateRecipe createRecipeCommand = new CreateRecipe("Pizza Margarita");
        recipeRepository.create(createRecipeCommand);

        int recipeCount = 0;
        for(Entity recipeInDb : dataSource.findAll()) {
            assertThat(recipeInDb.getId()).isNotNull();
            assertThat(recipeInDb.getCanonicalName()).isEqualTo(new CanonicalName("Pizza Margarita"));
            assertThat(recipeInDb.getCanonicalName().getOriginal()).isEqualTo("Pizza Margarita");
            recipeCount++;
        }

        assertThat(recipeCount).isEqualTo(1);
    }

    @Test
    @Description("then recipe with same canonical name can not be saved into database twice")
    protected void recipeWithSameNameCanNotBeCreatedTwice() {
        CreateRecipe createRecipe = new CreateRecipe("Pizza Margarita");
        recipeRepository.create(createRecipe);
        assertThatThrownBy(() -> recipeRepository.create(new CreateRecipe("pizza margarita")))
                .isInstanceOf(DuplicateRecipeException.class);
    }

    @Test
    @Description("then deleting any recipe returns an empty Optional")
    protected void deletingAnyRecipeReturnsEmptyOptional() {
        assertThat(recipeRepository.deleteById(new RecipeId(new SerialInteger(1)))).isEmpty();
    }

    @Test
    @Description("then reading any ingredient returns an empty Optional")
    protected void readingANonexistentRecipeReturnsAnEmptyOptional() {
        assertThat(recipeRepository.readById(new RecipeId(new SerialInteger(1)))).isEmpty();
    }
}
