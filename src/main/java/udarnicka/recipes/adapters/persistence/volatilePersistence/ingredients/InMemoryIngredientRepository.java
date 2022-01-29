package udarnicka.recipes.adapters.persistence.volatilePersistence.ingredients;

import lombok.NonNull;
import org.springframework.stereotype.Repository;
import udarnicka.recipes.application.ingredients.api.CreateIngredient;
import udarnicka.recipes.application.ingredients.api.IngredientRepository;
import udarnicka.recipes.application.ingredients.api.Ingredient;
import udarnicka.recipes.application.ingredients.api.IngredientId;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
class InMemoryIngredientRepository implements IngredientRepository {

    private final Map<IngredientId, Ingredient> savedIngredients = new HashMap<>();
    private Integer ingredientCounter = 0;

    @Override
    public Ingredient create(@NonNull CreateIngredient createIngredient) {
        IngredientId id = new IngredientId(ingredientCounter++);
        Ingredient ingredient = new Ingredient(createIngredient.name(), id);
        savedIngredients.put(id, ingredient);
        return ingredient;
    }

    @Override
    public Optional<Ingredient> deleteById(IngredientId id) {
        Ingredient ingredientById = savedIngredients.get(id);
        if(ingredientById == null) {
            return Optional.empty();
        } else {
            savedIngredients.remove(id);
            return Optional.of(ingredientById);
        }
    }

    @Override
    public Optional<Ingredient> readById(IngredientId id) {
        return Optional.ofNullable(savedIngredients.get(id));
    }
}
