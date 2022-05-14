package plans.grocerylist.api;

import lombok.NonNull;
import recipes.crud.api.RecipeId;

import java.util.Collection;

public class GroceryListProvider {

    private final GroceryListRepository repository;

    public GroceryListProvider(@NonNull GroceryListRepository repository) {
        this.repository = repository;
    }

    public Groceries groceries(@NonNull Collection<RecipeId> recipes) {
        return new Groceries(repository.readGroceryList(recipes));
    }

}
