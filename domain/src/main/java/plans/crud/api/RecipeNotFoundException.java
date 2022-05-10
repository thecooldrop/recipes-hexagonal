package plans.crud.api;

import recipes.crud.api.RecipeId;

public class RecipeNotFoundException extends RuntimeException{

    private final RecipeId recipeId;

    public RecipeNotFoundException(RecipeId id) {
        this.recipeId = id;
    }

    public RecipeNotFoundException(RecipeId id, Throwable cause) {
        super(cause);
        this.recipeId = id;
    }
}
