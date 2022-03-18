package udarnicka.recipes.crud.domain.ports;

import lombok.Getter;

public class DuplicateRecipeException extends RuntimeException {

    @Getter
    private final RecipeId alreadyInDatabase;

    public DuplicateRecipeException(String message, RecipeId alreadyInDatabase) {
        super(message);
        this.alreadyInDatabase = alreadyInDatabase;
    }
}
