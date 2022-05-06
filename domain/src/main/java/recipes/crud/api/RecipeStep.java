package recipes.crud.api;

import lombok.NonNull;

public class RecipeStep {

    private final String step;

    public RecipeStep(@NonNull String step) {
        if(step.isBlank()) {
            throw new IllegalArgumentException("The step should not be blank");
        }
        this.step = step;
    }
}
