package recipes.crud.api;

import ingredients.crud.api.IngredientId;
import lombok.NonNull;

import javax.measure.Quantity;
import javax.measure.quantity.Volume;

public class VolumetricIngredient {

    private final IngredientId id;
    private final Quantity<Volume> volumeQuantity;

    public VolumetricIngredient(@NonNull IngredientId id, @NonNull Quantity<Volume> volumeQuantity) {
        this.id = id;
        this.volumeQuantity = volumeQuantity;
    }
}
