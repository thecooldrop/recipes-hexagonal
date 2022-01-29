package udarnicka.recipes.application.ingredients.api;

import java.util.Optional;

public interface IngredientRepository {
    Ingredient create(CreateIngredient ingredient);
    Optional<Ingredient> deleteById(IngredientId id);
    Optional<Ingredient> readById(IngredientId id);
}
