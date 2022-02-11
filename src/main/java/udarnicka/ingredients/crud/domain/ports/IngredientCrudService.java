package udarnicka.ingredients.crud.domain.ports;

import java.util.Optional;

public class IngredientCrudService {

    private final IngredientRepository ingredientRepository;

    public IngredientCrudService(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    /**
     *
     * @param createIngredient - a command containing the description of the ingredient which to save
     * @throws DuplicateIngredientException - thrown in case that the ingredient already exists
     * @return Ingredient created in the database
     */
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
