package ingredients.crud.api;

import java.util.Optional;

public interface IngredientRepository {

    // TODO: Create the interface which will actually be needed by the usecase
    Optional<Ingredient> readById(IngredientId id);
    Ingredient save(IngredientName name);

    void delete(IngredientId id);
}