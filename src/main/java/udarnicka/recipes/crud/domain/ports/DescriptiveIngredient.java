package udarnicka.recipes.crud.domain.ports;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.ToString;
import udarnicka.ingredients.crud.domain.ports.Ingredient;


/**
 * A class for representing ingredients which are descriptive and not weighted or volumetric. Examples of ingredients
 * which may be descriptive ingredients would be:
 *
 * - Pinch of salt
 * - Lemon zest to taste
 * - Teaspoon of sugar
 */
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class DescriptiveIngredient {

    @NonNull
    private final IngredientMeasureDescription description;

    @NonNull
    private final Ingredient ingredient;
}
