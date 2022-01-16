package udarnicka.recipes.application.ports.in;

import udarnicka.recipes.domain.Ingredient;
import udarnicka.recipes.domain.IngredientId;
import udarnicka.recipes.application.CreateIngredient;

import java.util.Optional;

public interface IngredientCrudUsecase {

    Ingredient createIngredient(CreateIngredient createIngredient);
    Optional<Ingredient> deleteIngredient(IngredientId ingredientId);
    Optional<Ingredient> readIngredient(IngredientId ingredientId);
}
