package udarnicka.recipes.crud.persistence;

import jdk.jfr.Description;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.data.repository.CrudRepository;
import udarnicka.common.CanonicalName;
import udarnicka.common.HasCanonicalName;
import udarnicka.common.HasId;
import udarnicka.common.SerialInteger;
import udarnicka.recipes.crud.domain.ports.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.util.*;

import static org.assertj.core.api.Assertions.*;

public abstract class RecipeRepositoryAbstractTest {

    @Nested
    @Description("If there is a database")
    public class GivenADatabase {

        @Nested
        @Description("which contains no recipes")
        public class WhichIsEmpty<Entity extends HasId<?> & HasCanonicalName, DataSource extends CrudRepository<Entity, ?>> {

            protected RecipeRepository recipeRepository;
            protected DataSource dataSource;

            @BeforeEach
            protected void setup() {

            }

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
            protected void deletingAnyRecipeReturnsEmtpyOptional() {
                assertThat(recipeRepository.deleteById(new RecipeId(new SerialInteger(1)))).isEmpty();
            }

            @Test
            @Description("then reading any ingredient returns an empty Optional")
            protected void readingANonexistentRecipeReturnsAnEmptyOptional() {
                assertThat(recipeRepository.readById(new RecipeId(new SerialInteger(1)))).isEmpty();
            }
        }

        @Nested
        @Description("which contains some recipes")
        public class WhichContainsSomeRecipes<Entity extends HasId<?> & HasCanonicalName, DataSource extends CrudRepository<Entity, ?>> {

            @PersistenceContext
            private EntityManager em;

            private List<Recipe> recipesInDb;

            protected DataSource dataSource;
            protected RecipeRepository recipeRepository;

            protected void initializeData() {
                recipesInDb = new ArrayList<>();
                CreateRecipe pizzaMargharita = new CreateRecipe("Pizza Margarita");
                CreateRecipe friedChickenBreast = new CreateRecipe("Fried Chicken Breast");
                CreateRecipe frenchFries = new CreateRecipe("French Fries");
                CreateRecipe lasagnaBolognese = new CreateRecipe("Lasagna Bolognese");
                CreateRecipe spaghettiCarbonara = new CreateRecipe("Spaghetti Carbonara");

                for(CreateRecipe command : Arrays.asList(pizzaMargharita, friedChickenBreast, frenchFries, lasagnaBolognese, spaghettiCarbonara)) {
                    Recipe recipe = recipeRepository.create(command);
                    recipesInDb.add(recipe);
                }

            }

            @Test
            @Description("then deleting a recipe removes it from the database")
            protected void deletingTheRecipeRemovesItFromTheDatabase() {
                initializeData();
                Recipe randomRecipe = recipesInDb.get(new Random().nextInt(recipesInDb.size()));
                long numOfRecipesInDbBeforeDeletion = dataSource.count();
                Optional<Recipe> randomRecipeFromDb = recipeRepository.readById(randomRecipe.getId());
                Optional<Recipe> removedRecipe = recipeRepository.deleteById(randomRecipe.getId());
                long numOfRecipesInDatabaseAfterDeletion = dataSource.count();
                em.flush();
                em.clear();

                Optional<Recipe> shouldBeEmptyRecipe = recipeRepository.readById(randomRecipe.getId());
                assertThat(randomRecipeFromDb).isEqualTo(Optional.of(randomRecipe));
                assertThat(removedRecipe).isEqualTo(randomRecipeFromDb);
                assertThat(numOfRecipesInDatabaseAfterDeletion).isGreaterThan(numOfRecipesInDbBeforeDeletion);
                assertThat(shouldBeEmptyRecipe).isEqualTo(Optional.empty());
            }

            @Test
            @Description("then deleting a recipe returns the recipe")
            protected void deletingTheRecipeReturnsDeletedRecipe() {
                initializeData();
                Recipe randomRecipe = recipesInDb.get(new Random().nextInt(recipesInDb.size()));
                Optional<Recipe> recipeInDb = recipeRepository.deleteById(randomRecipe.getId());
                assertThat(recipeInDb).isEqualTo(Optional.of(randomRecipe));
            }

            @Test
            @Description("then the existing recipe can be read")
            protected void existingRecipeCanBeRead() {
                initializeData();
                Recipe randomRecipe = recipesInDb.get(new Random().nextInt(recipesInDb.size()));
                Optional<Recipe> recipeInDb = recipeRepository.readById(randomRecipe.getId());
                assertThat(recipeInDb).isEqualTo(Optional.of(randomRecipe));
            }
        }
    }
}
