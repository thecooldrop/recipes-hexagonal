package udarnicka.ingredients.crud.domain.ports;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

@ToString
@EqualsAndHashCode
@Getter
public class Ingredient {

    private final IngredientName name;
    private final IngredientId id;

    public Ingredient(@NonNull IngredientName name, @NonNull IngredientId id) {
        this.name = name;
        this.id = id;
    }
}
