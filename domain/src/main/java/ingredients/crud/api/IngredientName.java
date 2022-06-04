package ingredients.crud.api;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class IngredientName {

    // Note to self: This class does not need canonical internal representation for equality check purposes, because
    // only a single ingredient with specific canonical name can be saved into the database. This means that
    // for any canonical name, there is only one corresponding user-input name, thus it is also unique in the system.
    private final String name;

    public IngredientName(String name) {
        if(name == null) {
            throw new NullPointerException("The ingredient name can not be null");
        }
        if(name.isBlank()) {
            throw new IllegalArgumentException("The ingredient name can not be blank string");
        }
        this.name = name;
    }

    public String asString() {
        return name;
    }
}
