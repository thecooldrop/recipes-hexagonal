package udarnicka.recipes.ingredients.domain.api;

import udarnicka.recipes.ingredients.domain.Ingredient;
import udarnicka.recipes.ingredients.domain.IngredientId;

public interface IngredientCrud {

    Ingredient createIngredient(Ingredient input);

    Ingredient deleteIngredient(IngredientId id);
}
