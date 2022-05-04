package ingredients.crud.api;

import common.PositiveInteger;
import ingredients.crud.InMemoryIngredientRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class IngredientCrudUsecaseTest {

    private IngredientCrud componentUnderTest;

    private IngredientRepository inMemoryIngredientRepository;

    @BeforeEach
    private void setupComponentUnderTest() {
        inMemoryIngredientRepository = new InMemoryIngredientRepository();
        this.componentUnderTest = new IngredientCrud(inMemoryIngredientRepository);
    }

    @Nested
    class NoIngredientsSaved {

        @Test
        void readingTheIngredientByIdReturnsNoIngredient() {
            Optional<Ingredient> maybeIngredient = componentUnderTest.readById(new IngredientId(new PositiveInteger(1)));
            assertEquals(Optional.empty(), maybeIngredient);
        }


        // TODO: Writing the ingredient enables us to read it via repository again
        @Test
        void writingTheIngredientMakesItRetrievableByRepository() {
            Ingredient writtenIngredient = componentUnderTest.create(new IngredientName("Pizza"));
            Optional<Ingredient> retrievedIngredient = inMemoryIngredientRepository.readById(writtenIngredient.id);
            assertEquals(Optional.of(writtenIngredient), retrievedIngredient);
        }
    }

    @Nested
    class WithIngredientsAvailable {

        private Ingredient savedIngredient;

        @BeforeEach
        void setup() {
            IngredientName flour = new IngredientName("Flour");
            savedIngredient = inMemoryIngredientRepository.save(flour);
        }

        // TODO: Existing ingredient can be read
        @Test
        void exisitingIngredientCanBeRead() {
            assertEquals(Optional.of(savedIngredient), componentUnderTest.readById(savedIngredient.id));
        }

        // TODO: Write IngredientRepository interface test which ensures that trying to save an existing ingredient
        //       results in an exception
        @Test
        void tryingToSaveAnExistingIngredientResultsInAnException() {
            assertThrows(DuplicateIngredientException.class, () -> componentUnderTest.create(savedIngredient.name));
        }


        // TODO: Write IngredientRepository interface test which ensures that trying to read deleted ingredient
        //       does not work.
        @Test
        void deletingAnIngredientMakesItUnreadable() {
            componentUnderTest.delete(savedIngredient.id);
            assertEquals(Optional.empty(), inMemoryIngredientRepository.readById(savedIngredient.id));
        }
    }

}
