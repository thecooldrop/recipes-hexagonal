package udarnicka.recipes.ingredients.domain.api;

public interface IngredientCrud {

    Ingredient createIngredient(Ingredient input);

    Ingredient deleteIngredient(IngredientId id);
}
