package udarnicka.recipes.application.ingredients.api;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

@ToString
@EqualsAndHashCode
@Getter
public class Ingredient {

    private final String name;
    private final IngredientId id;

    public Ingredient(@NonNull String ingredient, @NonNull IngredientId id) {
        if(ingredient.isBlank()) {
            throw new IllegalArgumentException("Parameter Ingredient may not be blank");
        }
        this.name = ingredient;
        this.id = id;
    }
}
