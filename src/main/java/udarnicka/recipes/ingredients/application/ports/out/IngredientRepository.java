package udarnicka.recipes.ingredients.application.ports.out;

import udarnicka.recipes.ingredients.domain.Ingredient;
import udarnicka.recipes.ingredients.domain.IngredientId;
import udarnicka.recipes.ingredients.application.CreateIngredient;

import java.util.Optional;

public interface IngredientRepository {
    Ingredient save(CreateIngredient ingredient);
    Optional<Ingredient> deleteById(IngredientId id);
    Optional<Ingredient> getById(IngredientId id);
}
