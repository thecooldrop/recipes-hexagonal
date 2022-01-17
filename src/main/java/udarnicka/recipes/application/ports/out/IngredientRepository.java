package udarnicka.recipes.application.ports.out;

import udarnicka.recipes.domain.Ingredient;
import udarnicka.recipes.domain.IngredientId;
import udarnicka.recipes.application.CreateIngredient;

import java.util.Optional;

public interface IngredientRepository {
    Ingredient create(CreateIngredient ingredient);
    Optional<Ingredient> deleteById(IngredientId id);
    Optional<Ingredient> readById(IngredientId id);
}
