package udarnicka.ingredients.crud.domain.ports;

import java.util.Optional;

public class IngredientCrudService {

    private final IngredientRepository ingredientRepository;

    IngredientCrudService(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    public Ingredient createIngredient(CreateIngredient createIngredient) {
        return ingredientRepository.create(createIngredient);
    }

    public Optional<Ingredient> deleteIngredient(IngredientId ingredientId) {
        return ingredientRepository.deleteById(ingredientId);
    }

    public Optional<Ingredient> readIngredient(IngredientId ingredientId) {
        return ingredientRepository.readById(ingredientId);
    }
}
