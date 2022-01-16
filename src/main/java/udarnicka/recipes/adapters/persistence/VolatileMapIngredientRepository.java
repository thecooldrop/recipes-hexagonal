package udarnicka.recipes.adapters.persistence;

import udarnicka.recipes.application.CreateIngredient;
import udarnicka.recipes.application.ports.out.IngredientRepository;
import udarnicka.recipes.domain.Ingredient;
import udarnicka.recipes.domain.IngredientId;

import java.util.Optional;

public class VolatileMapIngredientRepository implements IngredientRepository {
    @Override
    public Ingredient save(CreateIngredient ingredient) {
        return null;
    }

    @Override
    public Optional<Ingredient> deleteById(IngredientId id) {
        return Optional.empty();
    }

    @Override
    public Optional<Ingredient> getById(IngredientId id) {
        return Optional.empty();
    }
}
