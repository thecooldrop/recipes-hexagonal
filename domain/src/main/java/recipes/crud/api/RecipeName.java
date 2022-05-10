package recipes.crud.api;

import lombok.EqualsAndHashCode;
import lombok.NonNull;

@EqualsAndHashCode
public class RecipeName {

    private final String name;

    public RecipeName(@NonNull String name) {
        if(name.isBlank()) {
            throw new IllegalArgumentException("The recipe name can not be a blank string");
        }
        this.name = name;
    }
}
