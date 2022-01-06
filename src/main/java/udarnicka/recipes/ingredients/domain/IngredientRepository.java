package udarnicka.recipes.ingredients.domain;

import udarnicka.recipes.ingredients.domain.api.CreateIngredient;

interface IngredientRepository {

    Ingredient save(CreateIngredient ingredient);

}
