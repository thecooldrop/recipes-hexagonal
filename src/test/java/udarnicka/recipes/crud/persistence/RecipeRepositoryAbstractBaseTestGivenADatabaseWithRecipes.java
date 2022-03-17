package udarnicka.recipes.crud.persistence;

import jdk.jfr.Description;
import org.junit.jupiter.api.Test;
import org.springframework.data.repository.CrudRepository;
import udarnicka.common.HasCanonicalName;
import udarnicka.common.HasId;
import udarnicka.recipes.crud.domain.ports.CreateRecipe;
import udarnicka.recipes.crud.domain.ports.Recipe;
import udarnicka.recipes.crud.domain.ports.RecipeRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

@Transactional
public class RecipeRepositoryAbstractBaseTestGivenADatabaseWithRecipes<Entity extends HasId<?> & HasCanonicalName, DataSource extends CrudRepository<Entity, ?>> {

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
