package udarnicka.recipes.ingredients.application.ports.in;

import udarnicka.recipes.ingredients.domain.Ingredient;
import udarnicka.recipes.ingredients.domain.IngredientId;
import udarnicka.recipes.ingredients.application.CreateIngredient;

import java.util.Optional;

public interface IngredientCrudUsecase {

    Ingredient createIngredient(CreateIngredient createIngredient);
    Optional<Ingredient> deleteIngredient(IngredientId ingredientId);
    Optional<Ingredient> readIngredient(IngredientId ingredientId);
}
