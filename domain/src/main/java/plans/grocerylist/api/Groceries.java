package plans.grocerylist.api;

import ingredients.crud.api.IngredientId;
import lombok.NonNull;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class Groceries {

    private final Set<IngredientId> ingredients = new HashSet<>();

    public Groceries(@NonNull Collection<IngredientId> ingredients ) {
        ingredients = new HashSet<>(ingredients);
    }
}
