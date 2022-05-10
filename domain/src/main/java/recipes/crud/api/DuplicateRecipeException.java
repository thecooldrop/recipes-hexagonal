package recipes.crud.api;

public class DuplicateRecipeException extends RuntimeException {

    protected final Recipe duplicateRecipe;

    public DuplicateRecipeException(Recipe duplicateRecipe) {
        this.duplicateRecipe = duplicateRecipe;
    }
}
