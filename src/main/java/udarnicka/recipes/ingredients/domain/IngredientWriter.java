package udarnicka.recipes.ingredients.domain;

import udarnicka.recipes.ingredients.domain.api.Ingredient;
import udarnicka.recipes.ingredients.domain.api.IngredientId;

import java.util.Optional;

public interface IngredientWriter {

    Optional<Ingredient> saveIngredient(Ingredient ingredient);
    Optional<Ingredient> deleteIngredient(IngredientId ingredientId);
}
