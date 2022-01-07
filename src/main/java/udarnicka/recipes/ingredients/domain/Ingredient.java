package udarnicka.recipes.ingredients.domain;

import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.Optional;

@ToString
@EqualsAndHashCode
public class Ingredient {

    private String name;
    private IngredientId id;

    private Ingredient(String name, IngredientId id) {
        this.name = name;
        this.id = id;
    }

    public static Optional<Ingredient> tryFrom(String ingredient,
                                               IngredientId id) {
        if(ingredient == null || id == null) {
            return Optional.empty();
        }
        if(!ingredient.isBlank()) {
            return Optional.of(new Ingredient(ingredient, id));
        }
        return Optional.empty();
    }
}
