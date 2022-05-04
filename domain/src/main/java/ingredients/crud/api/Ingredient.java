package ingredients.crud.api;

import lombok.EqualsAndHashCode;

import java.util.Objects;

@EqualsAndHashCode
public class Ingredient {
    public final IngredientId id;
    public final IngredientName name;

    public Ingredient(IngredientId id, IngredientName name) {
        this.id = id;
        this.name = name;
    }
}
