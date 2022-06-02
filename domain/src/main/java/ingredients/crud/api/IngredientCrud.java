package ingredients.crud.api;

import java.util.Optional;

public class IngredientCrud {
    private final IngredientRepository ingredientRepository;

    public IngredientCrud(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    final Optional<Ingredient> readById(IngredientId id) {
        return ingredientRepository.readById(id);
    }

    final Ingredient create(IngredientName ingredientToCreate) {
        return ingredientRepository.save(ingredientToCreate);
    }

    final void delete(IngredientId id) {
        ingredientRepository.delete(id);
    }
}
