package recipes.crud;

import common.PositiveInteger;
import lombok.EqualsAndHashCode;
import lombok.NonNull;

@EqualsAndHashCode
public class RecipeId {

    private final PositiveInteger id;

    public RecipeId(@NonNull  PositiveInteger id) {
        this.id = id;
    }
}
