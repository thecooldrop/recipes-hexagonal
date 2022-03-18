package udarnicka.recipes.crud.domain.ports;

import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.ToString;

@EqualsAndHashCode
@ToString
public final class RecipeStep {
    private final String recipeStep;
    public RecipeStep(@NonNull String recipeStep) {
        if(recipeStep.isBlank()) {
            throw new IllegalArgumentException("The recipe step can not be constructed from a blank or empty string");
        }
        this.recipeStep = recipeStep;
    }
}
