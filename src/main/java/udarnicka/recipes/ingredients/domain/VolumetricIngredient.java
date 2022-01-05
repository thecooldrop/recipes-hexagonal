package udarnicka.recipes.ingredients.domain;

import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.ToString;

import javax.measure.Quantity;
import javax.measure.quantity.Volume;
import java.util.Optional;

@EqualsAndHashCode
@ToString
public class VolumetricIngredient {
    private final Quantity<Volume> volume;
    private final Ingredient ingredient;

    private VolumetricIngredient(Ingredient ingredient, Quantity<Volume> volume) {
        this.ingredient = ingredient;
        this.volume = volume;
    }

    public Optional<VolumetricIngredient> tryFrom(@NonNull Ingredient ingredient, @NonNull Quantity<Volume> volume) {
        if(volume.getValue().doubleValue() <= 0) {
            return Optional.empty();
        }
        return Optional.of(new VolumetricIngredient(Ingredient, volume))
    }
}
