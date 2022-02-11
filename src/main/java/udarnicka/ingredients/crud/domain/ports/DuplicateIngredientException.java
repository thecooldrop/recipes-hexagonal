package udarnicka.ingredients.crud.domain.ports;

import lombok.Getter;

public class DuplicateIngredientException extends RuntimeException {

    @Getter
    private final Ingredient alreadyInDatabase;

    public DuplicateIngredientException(String message, Ingredient alreadyInDatabase) {
        super(message);
        this.alreadyInDatabase = alreadyInDatabase;
    }
}
