package ingredients.crud.api;

import java.util.Optional;

public class IngredientCrud {
    private final IngredientRepository ingredientRepository;

    public IngredientCrud(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    final Optional<Ingredient> readById(IngredientId id) {
        // TODO: Complete the implementation
        return Optional.empty();
    }
}
