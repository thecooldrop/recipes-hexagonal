package recipes.crud.api;

import common.PositiveInteger;
import ingredients.crud.api.IngredientId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import recipes.crud.InMemoryRecipeRepository;
import tech.units.indriya.quantity.Quantities;
import tech.units.indriya.unit.Units;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class RecipeCrudUsecaseTest {

    private final RecipeRepository inMemoryRepository = new InMemoryRecipeRepository();
    private final RecipeCrud componentUnderTest = new RecipeCrud(inMemoryRepository);

    @Nested
    public class WithNoRecipesPresent {

        private Recipe recipeToCreate;
        private CreateRecipeCommand createCommand;

        @BeforeEach
        void setup() {
            RecipeId id = new RecipeId(new PositiveInteger(1));
            RecipeName name = new RecipeName("Recipe");

            RecipeStep firstStep = new RecipeStep("First");
            RecipeStep secondStep = new RecipeStep("Second");
            RecipeStep thirdStep = new RecipeStep("Third");
            List<RecipeStep> steps = List.of(firstStep, secondStep, thirdStep);

            CountableIngredient vanillaSticks = new CountableIngredient(new PositiveInteger(2), new IngredientId(new PositiveInteger(1)));
            CountableIngredient starsOfAnise = new CountableIngredient(new PositiveInteger(5), new IngredientId(new PositiveInteger(2)));
            List<CountableIngredient> countableIngredients = List.of(vanillaSticks, starsOfAnise);

            VolumetricIngredient water = new VolumetricIngredient(Quantities.getQuantity(250, Units.LITRE).divide(1000), new IngredientId(new PositiveInteger(4)));
            VolumetricIngredient milk = new VolumetricIngredient(Quantities.getQuantity(250, Units.LITRE).divide(1000), new IngredientId(new PositiveInteger(5)));
            List<VolumetricIngredient> volumetricIngredients = List.of(water, milk);

            WeightedIngredient flour = new WeightedIngredient(Quantities.getQuantity(200, Units.GRAM), new IngredientId(new PositiveInteger(6)));
            WeightedIngredient onion = new WeightedIngredient(Quantities.getQuantity(200, Units.GRAM), new IngredientId(new PositiveInteger(7)));
            List<WeightedIngredient> weightedIngredients = List.of(flour, onion);

            recipeToCreate = new Recipe(
                    id,
                    name,
                    steps,
                    countableIngredients,
                    weightedIngredients,
                    volumetricIngredients
            );

            createCommand = new CreateRecipeCommand(name,
                    steps,
                    countableIngredients,
                    weightedIngredients,
                    volumetricIngredients);
        }

        @Test
        void readingARecipeReturnsAnEmptyOptional() {
            assertEquals(Optional.empty(), componentUnderTest.read(new RecipeId(new PositiveInteger(1))));
        }

        @Test
        void writingARecipeMakesItRetrievableByRepository() {
            Recipe createdRecipe = componentUnderTest.create(createCommand);

            assertEquals(recipeToCreate.recipeName, createdRecipe.recipeName);
            assertEquals(recipeToCreate.steps, createdRecipe.steps);
            assertEquals(recipeToCreate.countableIngredients, createdRecipe.countableIngredients);
            assertEquals(recipeToCreate.weightedIngredients, createdRecipe.weightedIngredients);
            assertEquals(recipeToCreate.volumetricIngredients, createdRecipe.volumetricIngredients);

            Optional<Recipe> recipeReadFromRepository = inMemoryRepository.readById(createdRecipe.id);
            assertEquals(Optional.of(recipeToCreate), recipeReadFromRepository);
        }
    }

    @Nested
    public class WithRecipesInDatabase {

        private Recipe firstRecipe;
        private Recipe secondRecipe;

        private CreateRecipeCommand createRecipeCommand;
        private RecipeName expectedRecipeName;
        private List<RecipeStep> expectedRecipeSteps;
        private List<CountableIngredient> expectedCountableIngredients;
        private List<WeightedIngredient> expectedWeightedIngredients;
        private List<VolumetricIngredient> expectedVolumetricIngredients;
        private CreateRecipeCommand createDuplicateRecipeCommand;

        @BeforeEach
        void setup() {
            expectedRecipeName = new RecipeName("Fist");
            RecipeName secondRecipeName = new RecipeName("Second");
            RecipeName thirdRecipeName = new RecipeName("Third");

            expectedRecipeSteps = List.of(new RecipeStep("First step of first recipe"),
                    new RecipeStep("Second step of first recipe"),
                    new RecipeStep("Third step of first recipe"));

            List<RecipeStep> secondRecipeSteps = List.of(new RecipeStep("First step of second recipe"),
                    new RecipeStep("Second step of second recipe"));

            List<RecipeStep> thirdRecipeSteps = List.of(new RecipeStep("First step of third recipe"));

            expectedCountableIngredients = List.of(
                    new CountableIngredient(new PositiveInteger(1), new IngredientId(new PositiveInteger(1))),
                    new CountableIngredient(new PositiveInteger(2), new IngredientId(new PositiveInteger(2))),
                    new CountableIngredient(new PositiveInteger(3), new IngredientId(new PositiveInteger(3)))
            );
            List<CountableIngredient> secondRecipeCountableIngredients = List.of(
                    new CountableIngredient(new PositiveInteger(2), new IngredientId(new PositiveInteger(4)))
            );
            List<CountableIngredient> thirdRecipeCountableIngredients = List.of(
                    new CountableIngredient(new PositiveInteger(7), new IngredientId(new PositiveInteger(2))),
                    new CountableIngredient(new PositiveInteger(1), new IngredientId(new PositiveInteger(5)))
            );

            expectedWeightedIngredients = List.of(
                    new WeightedIngredient(Quantities.getQuantity(100, Units.GRAM), new IngredientId(new PositiveInteger(11))),
                    new WeightedIngredient(Quantities.getQuantity(250, Units.GRAM), new IngredientId(new PositiveInteger(12))),
                    new WeightedIngredient(Quantities.getQuantity(1, Units.KILOGRAM), new IngredientId(new PositiveInteger(13)))
            );
            List<WeightedIngredient> secondRecipeWeightedIngredients = List.of(
                    new WeightedIngredient(Quantities.getQuantity(1, Units.KILOGRAM), new IngredientId(new PositiveInteger(13)))
            );
            List<WeightedIngredient> thirdRecipeWeightedIngredients = List.of();

            expectedVolumetricIngredients = List.of(
                    new VolumetricIngredient(Quantities.getQuantity(1, Units.LITRE), new IngredientId(new PositiveInteger(21)))
            );
            List<VolumetricIngredient> secondRecipeVolumetricIngredients = List.of(
                    new VolumetricIngredient(Quantities.getQuantity(250, Units.LITRE).divide(1000), new IngredientId(new PositiveInteger(22))),
                    new VolumetricIngredient(Quantities.getQuantity(250, Units.LITRE).divide(1000), new IngredientId(new PositiveInteger(23)))
            );
            List<VolumetricIngredient> thirdRecipeVolumetricIngredients = List.of();

            createRecipeCommand = new CreateRecipeCommand(expectedRecipeName, expectedRecipeSteps, expectedCountableIngredients, expectedWeightedIngredients, expectedVolumetricIngredients);
            CreateRecipeCommand createSecondRecipe = new CreateRecipeCommand(secondRecipeName, secondRecipeSteps, secondRecipeCountableIngredients, secondRecipeWeightedIngredients, secondRecipeVolumetricIngredients);
            CreateRecipeCommand createThirdRecipe = new CreateRecipeCommand(thirdRecipeName, thirdRecipeSteps, thirdRecipeCountableIngredients, thirdRecipeWeightedIngredients, thirdRecipeVolumetricIngredients );

            firstRecipe = inMemoryRepository.save(createSecondRecipe);
            secondRecipe = inMemoryRepository.save(createThirdRecipe);
            createDuplicateRecipeCommand = createThirdRecipe;
        }

        @Test
        void canReadExistingRecipes() {
            assertEquals(Optional.of(firstRecipe), componentUnderTest.read(firstRecipe.id));
            assertEquals(Optional.of(secondRecipe), componentUnderTest.read(secondRecipe.id));
        }

        @Test
        void canSaveAdditionalRecipesIntoDatabase() {
            Recipe savedRecipe = componentUnderTest.create(createRecipeCommand);

            assertEquals(expectedRecipeName, savedRecipe.recipeName);
            assertEquals(expectedRecipeSteps, savedRecipe.steps);
            assertEquals(expectedCountableIngredients, savedRecipe.countableIngredients);
            assertEquals(expectedWeightedIngredients, savedRecipe.weightedIngredients);
            assertEquals(expectedVolumetricIngredients, savedRecipe.volumetricIngredients);

            assertEquals(Optional.of(savedRecipe), inMemoryRepository.readById(savedRecipe.id));
        }

        @Test
        void tryingToSaveAnExistingRecipeIntoTheDatabaseResultsInAnException() {
            assertThrows(DuplicateRecipeException.class, () -> componentUnderTest.create(createDuplicateRecipeCommand));
            try {
                componentUnderTest.create(createDuplicateRecipeCommand);
            } catch (DuplicateRecipeException e) {
                assertEquals(secondRecipe, e.duplicateRecipe);
            }
        }

        @Test
        void deletingARecipeMakesItUnreadableViaRepository() {
            componentUnderTest.delete(firstRecipe.id);
            assertEquals(Optional.empty(), inMemoryRepository.readById(firstRecipe.id));
        }

    }

}
