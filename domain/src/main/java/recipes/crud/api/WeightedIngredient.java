package recipes.crud.api;

import ingredients.crud.api.IngredientId;
import lombok.NonNull;

import javax.measure.Quantity;
import javax.measure.quantity.Mass;

public class WeightedIngredient {

    private final IngredientId id;
    private final Quantity<Mass> massQuantity;

    public WeightedIngredient(@NonNull IngredientId id, @NonNull Quantity<Mass> massQuantity) {
        this.id = id;
        this.massQuantity = massQuantity;
    }
}
