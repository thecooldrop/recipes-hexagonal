package recipe.crud.api;

import common.PositiveInteger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import recipes.crud.InMemoryRecipeRepository;
import recipes.crud.api.RecipeCrud;
import recipes.crud.api.RecipeId;
import recipes.crud.api.RecipeRepository;

import java.util.Optional;

public class RecipeCrudUsecaseTest {

    private final RecipeRepository inMemoryRepository = new InMemoryRecipeRepository();
    private final RecipeCrud componentUnderTest = new RecipeCrud();

    @Nested
    public class WithNoRecipesPresent {

        @Test
        void readingARecipeReturnsAnEmptyOptional() {
            assertEquals(Optional.empty(), componentUnderTest.read(new RecipeId(new PositiveInteger(1))));
        }
    }
}
