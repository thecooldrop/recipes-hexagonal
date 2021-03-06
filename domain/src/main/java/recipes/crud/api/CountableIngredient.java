package recipes.crud.api;

import common.PositiveInteger;
import ingredients.crud.api.IngredientId;
import lombok.EqualsAndHashCode;
import lombok.NonNull;

/**
 * This is an ingredient which is usually described by a count of instances in which it is included in recipe.
 * For example for cakes we typically say "One vanilla stick".
 */
@EqualsAndHashCode
public class CountableIngredient {

    private final PositiveInteger count;
    private final IngredientId ingredient;

    public CountableIngredient(@NonNull PositiveInteger count, @NonNull IngredientId ingredient) {
        this.count = count;
        this.ingredient = ingredient;
    }
}
