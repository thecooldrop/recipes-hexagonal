package udarnicka.recipes.crud.domain.ports;

import lombok.Getter;

public class DuplicateRecipeException extends RuntimeException {

    @Getter
    private final Recipe alreadyInDatabase;

    public DuplicateRecipeException(String message, Recipe alreadyInDatabase) {
        super(message);
        this.alreadyInDatabase = alreadyInDatabase;
    }
}
