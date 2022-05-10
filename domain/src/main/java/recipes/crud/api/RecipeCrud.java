package recipes.crud.api;

import java.util.Optional;

public class RecipeCrud {

    private final RecipeRepository recipeRepository;

    public RecipeCrud(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    public Optional<Recipe> read(RecipeId recipeId) {
        return recipeRepository.readById(recipeId);
    }

    public Recipe create(CreateRecipeCommand recipeCommand) {
        return recipeRepository.save(recipeCommand);
    }

    public void delete(RecipeId id) {
        recipeRepository.delete(id);
    }
}
