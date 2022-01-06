package udarnicka.recipes.ingredients.domain.api;

import udarnicka.recipes.ingredients.domain.Ingredient;
import udarnicka.recipes.ingredients.domain.IngredientId;

import java.util.Optional;

public interface IngredientCrudUsecase {

    Optional<Ingredient> createIngredient(CreateIngredient createIngredient);
    Optional<Ingredient> deleteIngredient(IngredientId ingredientId);
    Optional<Ingredient> readIngredient(IngredientId ingredientId);
}
