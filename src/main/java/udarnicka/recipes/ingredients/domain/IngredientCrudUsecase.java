package udarnicka.recipes.ingredients.domain;

import java.util.Optional;

public interface IngredientCrudUsecase {

    Optional<Ingredient> saveIngredient(Ingredient ingredient);
    Optional<Ingredient> deleteIngredient(IngredientId ingredientId);
}
