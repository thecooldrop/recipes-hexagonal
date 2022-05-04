package ingredients.crud.api;

import common.PositiveInteger;
import lombok.EqualsAndHashCode;

import java.util.Objects;

@EqualsAndHashCode
public class IngredientId {

    private final PositiveInteger internal;

    public IngredientId(PositiveInteger positiveInteger) {
        if(positiveInteger == null) {
            throw new NullPointerException("The IngredientId can not be initialized from null");
        }
        this.internal = positiveInteger;
    }

    public boolean equals(Object o) {
        if(this == o) {
            return true;
        }
        if(o == null) {
            return false;
        }
        if(!(o instanceof IngredientId)) {
            return false;
        }
        return Objects.equals(this.internal, ((IngredientId) o).internal);
    }
}
