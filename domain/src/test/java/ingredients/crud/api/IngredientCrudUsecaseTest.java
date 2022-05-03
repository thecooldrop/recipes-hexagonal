package ingredients.crud.api;

import common.PositiveInteger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Optional;

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
            // TODO: Finish writing this test
            Optional<Ingredient> maybeIngredient = componentUnderTest.readById(new IngredientId(new PositiveInteger(1)));
        }

        // TODO: Writing the ingredient enables us to read it via repository again
    }

    @Nested
    class WithIngredientsAvailable {

        @BeforeEach
        void setup() {
            // TODO: Implement saving of ingredients for setup
        }

        // TODO: Existing ingredient can be read
        // TODO: Writing the existing ingredient throws an exception indicating that it already exists
        // TODO: Deleting an existing ingredient works by meaning that we can not read it anymore
    }

}
