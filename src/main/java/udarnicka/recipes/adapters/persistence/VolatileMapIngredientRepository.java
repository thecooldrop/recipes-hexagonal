package udarnicka.recipes.adapters.persistence;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import udarnicka.recipes.application.CreateIngredient;
import udarnicka.recipes.application.ports.out.IngredientRepository;
import udarnicka.recipes.domain.Ingredient;
import udarnicka.recipes.domain.IngredientId;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
class VolatileMapIngredientRepository implements IngredientRepository {

    private final Map<IngredientId, Ingredient> savedIngredients = new HashMap<>();
    private Integer ingredientCounter = 0;

    @Override
    public Ingredient create(CreateIngredient createIngredient) {
        if(createIngredient == null) {
            throw new NullPointerException("Creating a new ingredient from null input is not supported. Ingredient argument to VolatileMapIngredientRepository.create(CreateIngredient) method may is null");
        }
        IngredientId id = new IngredientId(ingredientCounter++);
        Ingredient ingredient = Ingredient.tryFrom(createIngredient.name(), id).get();
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
