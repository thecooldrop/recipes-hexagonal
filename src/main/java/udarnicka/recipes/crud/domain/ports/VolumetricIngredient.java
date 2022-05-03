package udarnicka.recipes.crud.domain.ports;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.ToString;
import udarnicka.ingredients.crud.domain.ports.Ingredient;

import javax.measure.Quantity;
import javax.measure.quantity.Volume;

/**
 * Ingredients which are measured by volume. Example of such ingredients would be:
 *
 * - 500ml of water
 * - 1L of chicken stock
 * - 200ml of milk
 */
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class VolumetricIngredient {

    @NonNull
    private final Quantity<Volume> volume;

    @NonNull
    private final Ingredient ingredient;
}
