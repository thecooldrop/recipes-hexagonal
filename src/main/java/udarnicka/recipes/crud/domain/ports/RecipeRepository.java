package udarnicka.recipes.crud.domain.ports;

import java.util.Optional;

public interface RecipeRepository {

    Recipe create(CreateRecipe recipe);
    void deleteById(RecipeId id);
    Optional<Recipe> readById(RecipeId id);
}
