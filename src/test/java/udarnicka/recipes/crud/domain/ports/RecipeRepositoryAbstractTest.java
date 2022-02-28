package udarnicka.recipes.crud.domain.ports;

import jdk.jfr.Description;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.data.repository.CrudRepository;
import udarnicka.common.CanonicalName;
import udarnicka.common.HasCanonicalName;
import udarnicka.common.HasId;
import udarnicka.common.SerialInteger;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.util.*;

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
        public abstract class WhichContainsSomeRecipes<Entity extends HasId<?> & HasCanonicalName, DataSource extends CrudRepository<Entity, ?>> {

            @PersistenceContext
            private EntityManager em;

            private List<Recipe> recipesInDb;

            public abstract DataSource getDataSource();
            public abstract RecipeRepository getTestedRepository();

            private void initializeData() {
                recipesInDb = new ArrayList<>();
                CreateRecipe pizzaMargharita = new CreateRecipe("Pizza Margarita");
                CreateRecipe friedChickenBreast = new CreateRecipe("Fried Chicken Breast");
                CreateRecipe frenchFries = new CreateRecipe("French Fries");
                CreateRecipe lasagnaBolognese = new CreateRecipe("Lasagna Bolognese");
                CreateRecipe spaghettiCarbonara = new CreateRecipe("Spaghetti Carbonara");

                for(CreateRecipe command : Arrays.asList(pizzaMargharita, friedChickenBreast, frenchFries, lasagnaBolognese, spaghettiCarbonara)) {
                    Recipe recipe = getTestedRepository().create(command);
                    recipesInDb.add(recipe);
                }

            }

            @Test
            @Description("then deleting a recipe removes it from the database")
            void deletingTheRecipeRemovesItFromTheDatabase() {
                initializeData();
                Recipe randomRecipe = recipesInDb.get(new Random().nextInt(recipesInDb.size()));
                long numOfRecipesInDbBeforeDeletion = getDataSource().count();
                Optional<Recipe> randomRecipeFromDb = getTestedRepository().readById(randomRecipe.getId());
                Optional<Recipe> removedRecipe = getTestedRepository().deleteById(randomRecipe.getId());
                long numOfRecipesInDatabaseAfterDeletion = getDataSource().count();
                em.flush();
                em.clear();

                Optional<Recipe> shouldBeEmptyRecipe = getTestedRepository().readById(randomRecipe.getId());
                assertThat(randomRecipeFromDb).isEqualTo(Optional.of(randomRecipe));
                assertThat(removedRecipe).isEqualTo(randomRecipeFromDb);
                assertThat(numOfRecipesInDatabaseAfterDeletion).isGreaterThan(numOfRecipesInDbBeforeDeletion);
                assertThat(shouldBeEmptyRecipe).isEqualTo(Optional.empty());
            }

            @Test
            @Description("then deleting a recipe returns the recipe")
            void deletingTheRecipeReturnsDeletedRecipe() {
                Recipe randomRecipe = recipesInDb.get(new Random().nextInt(recipesInDb.size()));
                Optional<Recipe> recipeInDb = getTestedRepository().deleteById(randomRecipe.getId());
                assertThat(recipeInDb).isEqualTo(Optional.of(randomRecipe));
            }

            @Test
            @Description("then the existing recipe can be read")
            void existingRecipeCanBeRead() {
                Recipe randomRecipe = recipesInDb.get(new Random().nextInt(recipesInDb.size()));
                Optional<Recipe> recipeInDb = getTestedRepository().readById(randomRecipe.getId());
                assertThat(recipeInDb).isEqualTo(Optional.of(randomRecipe));
            }
        }
    }
}
