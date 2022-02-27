package udarnicka.recipes.crud.domain.ports;

public class RecipeCrudService {

    public RecipeCrudService() {

    }

    public Recipe read(RecipeId recipeId) {
        return null;
    }

    /**
     *
     * @param recipe
     * @throws DuplicateRecipeException when the recipe you are trying to create already exists in the database
     * @return
     */
    public Recipe create(CreateRecipe recipe) {
        return null;
    }

    public Recipe delete(RecipeId recipeId) {
        return null;
    }
}
