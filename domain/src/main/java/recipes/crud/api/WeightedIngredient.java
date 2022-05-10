package recipes.crud.api;

import ingredients.crud.api.IngredientId;
import lombok.EqualsAndHashCode;
import lombok.NonNull;

import javax.measure.Quantity;
import javax.measure.quantity.Mass;

@EqualsAndHashCode
public class WeightedIngredient {
    private final IngredientId id;
    private final Quantity<Mass> massQuantity;

    public WeightedIngredient(@NonNull Quantity<Mass> massQuantity, @NonNull IngredientId id) {
        this.id = id;
        this.massQuantity = massQuantity;
    }
}
