package ingredients.crud;

import common.PositiveInteger;
import ingredients.crud.api.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class InMemoryIngredientRepository implements IngredientRepository {

    private final Map<IngredientId, Ingredient> ingredients = new HashMap<>();
    private Integer sequence = 1;

    @Override
    public Optional<Ingredient> readById(IngredientId id) {
        return Optional.ofNullable(ingredients.get(id));
    }

    @Override
    public Ingredient save(IngredientName name) {
        checkIfIngredientWithNameExists(name);

        IngredientId id = new IngredientId(new PositiveInteger(sequence));
        Ingredient newIngredient = new Ingredient(id, name);
        ingredients.put(id, newIngredient);
        sequence++;
        return newIngredient;
    }

    @Override
    public List<Ingredient> read() {
        return ingredients.values().stream().toList();
    }

    @Override
    public void delete(IngredientId id) {
        ingredients.remove(id);
    }

    private void checkIfIngredientWithNameExists(IngredientName name) {
        ingredients.values()
                .stream()
                .filter(elem -> elem.name.equals(name))
                .findFirst()
                .ifPresent(elem -> {
                    throw new DuplicateIngredientException(elem);
                });
    }

}
