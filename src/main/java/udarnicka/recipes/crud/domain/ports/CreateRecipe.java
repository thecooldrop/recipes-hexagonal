package udarnicka.recipes.crud.domain.ports;

import lombok.NonNull;

import java.util.Objects;

public record CreateRecipe(@NonNull String name) {
    public CreateRecipe {
        if(name.isBlank()) throw new IllegalArgumentException("The name of the recipe may not be null");
    }
}
