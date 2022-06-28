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

    private final IngredientRepository inMemoryIngredientRepository = new InMemoryIngredientRepository();
    private final IngredientCrud componentUnderTest = new IngredientCrud(inMemoryIngredientRepository);

    @Nested
    class NoIngredientsSaved {

        @Test
        void readingTheIngredientByIdReturnsNoIngredient() {
            Optional<Ingredient> maybeIngredient = componentUnderTest.readById(new IngredientId(new PositiveInteger(1)));
            assertEquals(Optional.empty(), maybeIngredient);
        }


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

        @Test
        void exisitingIngredientCanBeRead() {
            assertEquals(Optional.of(savedIngredient), componentUnderTest.readById(savedIngredient.id));
        }

        @Test
        void tryingToSaveAnExistingIngredientResultsInAnException() {
            assertThrows(DuplicateIngredientException.class, () -> componentUnderTest.create(savedIngredient.name));
        }


        @Test
        void deletingAnIngredientMakesItUnreadable() {
            componentUnderTest.delete(savedIngredient.id);
            assertEquals(Optional.empty(), inMemoryIngredientRepository.readById(savedIngredient.id));
        }
    }

}
