package udarnicka.recipes.crud.domain.ports;

import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.ToString;

/**
 * A class used for representing the description of an ingredient measure. Examples of ingredient measure descriptions
 * are "pinch", "a teaspoon" and "to taste".
 */
@EqualsAndHashCode
@ToString
public class IngredientMeasureDescription {
    private final String description;

    public IngredientMeasureDescription(@NonNull String description) {
        if(description.isBlank()) {
            throw new IllegalArgumentException("The description can not be null");
        }
        this.description = description;
    }
}
