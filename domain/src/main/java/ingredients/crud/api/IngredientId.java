package ingredients.crud.api;

import common.PositiveInteger;
import lombok.EqualsAndHashCode;
import lombok.NonNull;

import java.util.Objects;

@EqualsAndHashCode
public class IngredientId {

    private final PositiveInteger internal;

    public IngredientId(@NonNull PositiveInteger positiveInteger) {
        this.internal = positiveInteger;
    }
}
