package ingredients.crud.api;

import java.util.List;
import java.util.Optional;

public interface IngredientRepository {

    Optional<Ingredient> readById(IngredientId id);
    Ingredient save(IngredientName name);
    void delete(IngredientId id);
    List<Ingredient> read();
}
