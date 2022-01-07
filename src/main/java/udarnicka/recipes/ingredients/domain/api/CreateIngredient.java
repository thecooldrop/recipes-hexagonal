package udarnicka.recipes.ingredients.domain.api;

import java.util.Objects;

public record CreateIngredient(String name) {
    public CreateIngredient {
        Objects.requireNonNull(name);
        if(name.isBlank()) throw new IllegalArgumentException("Name of the ingredient may not be blank");
    }
}
