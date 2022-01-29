package udarnicka.recipes.application.ingredients.api;

import java.util.Optional;

public interface IngredientCrudUsecase {

    Ingredient createIngredient(CreateIngredient createIngredient);
    Optional<Ingredient> deleteIngredient(IngredientId ingredientId);
    Optional<Ingredient> readIngredient(IngredientId ingredientId);
}
