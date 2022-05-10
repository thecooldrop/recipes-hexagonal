package recipes.crud.api;

import java.util.Optional;

public interface RecipeRepository {

    Optional<Recipe> readById(RecipeId id);
    Recipe save(CreateRecipeCommand command);
    void delete(RecipeId id);
}
