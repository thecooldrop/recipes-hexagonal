package udarnicka.recipes.crud.domain.ports;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

@Getter
@ToString
@EqualsAndHashCode
public class Recipe {

    private final String name;
    private final RecipeId id;

    public Recipe(@NonNull String name, @NonNull RecipeId id) {
        if(name.isBlank()) {
            throw new IllegalArgumentException("The name of the recipe may not be empty or blank");
        }
        this.name = name;
        this.id = id;
    }
}
