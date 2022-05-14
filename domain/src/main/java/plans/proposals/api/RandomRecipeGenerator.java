package plans.proposals.api;

import recipes.crud.api.RecipeId;
// TODO: https://stackoverflow.com/questions/5297396/quick-random-row-selection-in-postgres
//       Note for the implementation of sampling of random recipe.
public interface RandomRecipeGenerator {
    RecipeId randomRecipe();
}