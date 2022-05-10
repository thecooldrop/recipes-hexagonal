package recipes.crud.api;

import ingredients.crud.api.IngredientId;
import lombok.EqualsAndHashCode;
import lombok.NonNull;

import javax.measure.Quantity;
import javax.measure.quantity.Volume;

@EqualsAndHashCode
public class VolumetricIngredient {
    private final IngredientId id;
    private final Quantity<Volume> volumeQuantity;

    public VolumetricIngredient(@NonNull Quantity<Volume> volumeQuantity, @NonNull IngredientId id) {
        this.id = id;
        this.volumeQuantity = volumeQuantity;
    }
}
