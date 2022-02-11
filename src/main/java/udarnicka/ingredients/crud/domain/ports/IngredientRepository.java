package udarnicka.ingredients.crud.domain.ports;

import java.util.Optional;

public interface IngredientRepository {

    /**
     *
     * @param ingredient - a command which describes the Ingredient to be created
     * @throws DuplicateIngredientException - thrown in case of attempt to create an ingredient which already
     *                                        exists in the database
     * @return the created Ingredient
     */
    Ingredient create(CreateIngredient ingredient);
    Optional<Ingredient> deleteById(IngredientId id);
    Optional<Ingredient> readById(IngredientId id);
}
