package udarnicka.recipes.crud.domain.ports;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.ToString;
import udarnicka.ingredients.crud.domain.ports.Ingredient;

import javax.measure.Quantity;
import javax.measure.quantity.Mass;

@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class WeightedIngredient {

    @NonNull
    private final Quantity<Mass> mass;

    @NonNull
    private final Ingredient ingredient;
}
