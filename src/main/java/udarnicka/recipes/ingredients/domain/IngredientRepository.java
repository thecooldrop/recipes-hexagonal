package udarnicka.recipes.ingredients.domain;

import udarnicka.recipes.ingredients.domain.api.CreateIngredient;

import java.util.Optional;

interface IngredientRepository {

    Ingredient save(CreateIngredient ingredient);
    Optional<Ingredient> deleteById(IngredientId id);
    Optional<Ingredient> getById(IngredientId id);
}
