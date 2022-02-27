package udarnicka.recipes.crud.domain.ports;

import java.util.Optional;

public interface RecipeRepository {

    Recipe create(CreateRecipe recipe);
    Optional<Recipe> deleteById(RecipeId id);
    Optional<Recipe> readById(RecipeId id);
}
