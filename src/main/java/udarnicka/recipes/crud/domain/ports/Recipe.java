package udarnicka.recipes.crud.domain.ports;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

import java.util.List;

@Getter
@ToString
@EqualsAndHashCode
public class Recipe {

    private final String name;
    private final RecipeId id;
    private final List<RecipeStep> recipeSteps;

    public Recipe(@NonNull String name, @NonNull RecipeId id, @NonNull List<RecipeStep> recipeSteps) {
        if(name.isBlank()) {
            throw new IllegalArgumentException("The name of the recipe may not be empty or blank");
        }
        this.name = name;
        this.id = id;
        this.recipeSteps = recipeSteps;
    }
}
