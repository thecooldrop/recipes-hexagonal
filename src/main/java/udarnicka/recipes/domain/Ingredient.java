package udarnicka.recipes.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

import java.util.Optional;

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
