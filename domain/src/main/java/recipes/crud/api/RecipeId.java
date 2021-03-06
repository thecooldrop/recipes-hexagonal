package recipes.crud.api;

import common.PositiveInteger;
import lombok.EqualsAndHashCode;
import lombok.NonNull;

@EqualsAndHashCode
public class RecipeId {

    public final PositiveInteger id;

    public RecipeId(@NonNull  PositiveInteger id) {
        this.id = id;
    }
}
