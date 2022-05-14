package plans.grocerylist.api;

import ingredients.crud.api.IngredientId;
import recipes.crud.api.RecipeId;

import java.util.Collection;
import java.util.Set;

public interface GroceryListRepository {
    Set<IngredientId> readGroceryList(Collection<RecipeId> recipeIds);
}
