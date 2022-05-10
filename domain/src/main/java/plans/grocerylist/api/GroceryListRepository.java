package plans.grocerylist.api;

import ingredients.crud.api.IngredientId;
import recipes.crud.api.RecipeId;

import java.util.List;

public interface GroceryListRepository {
    List<IngredientId> readGroceryList(List<RecipeId> recipeIds);
}
