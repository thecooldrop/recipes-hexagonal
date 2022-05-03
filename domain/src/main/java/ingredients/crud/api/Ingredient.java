package ingredients.crud.api;

public class Ingredient {
    private final IngredientId id;
    private final IngredientName name;

    public Ingredient(IngredientId id, IngredientName name) {
        this.id = id;
        this.name = name;
    }
}
