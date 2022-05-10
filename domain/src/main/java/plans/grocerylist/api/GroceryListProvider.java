package plans.grocerylist.api;

import lombok.NonNull;

public class GroceryListProvider {

    private final GroceryListRepository repository;

    public GroceryListProvider(@NonNull GroceryListRepository repository) {
        this.repository = repository;
    }
}
