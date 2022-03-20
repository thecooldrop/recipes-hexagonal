package udarnicka.recipes.crud.domain.ports;

import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.ToString;
import udarnicka.common.SerialInteger;

@EqualsAndHashCode
@ToString
public class RecipeId {

    private final SerialInteger id;

    public RecipeId(@NonNull SerialInteger id) {
        this.id = id;
    }

    public Integer toInteger() {
        return id.toInteger();
    }
}
