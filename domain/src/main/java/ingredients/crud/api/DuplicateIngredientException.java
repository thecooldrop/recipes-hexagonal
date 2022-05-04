package ingredients.crud.api;

/**
 * Indicates that the ingredient already exists and can not be created
 */
public class DuplicateIngredientException extends RuntimeException {

    public final Ingredient duplicate;

    public DuplicateIngredientException(Ingredient duplicate) {
        this.duplicate = duplicate;
    }
}
