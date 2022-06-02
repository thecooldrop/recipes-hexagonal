package recipes.crud.api;

import ingredients.crud.api.IngredientId;
import lombok.EqualsAndHashCode;
import lombok.NonNull;

/**
 * Ingredient, whose amount can only be described in words. Examples of such ingredients would be spices, which
 * are usually described as "to taste", or "a pinch"
 */
@EqualsAndHashCode
public class DescriptiveIngredient {

    private final String description;
    private final IngredientId ingredient;

    public DescriptiveIngredient(@NonNull String description, @NonNull IngredientId ingredient) {
        this.description = description;
        this.ingredient = ingredient;
    }
}
