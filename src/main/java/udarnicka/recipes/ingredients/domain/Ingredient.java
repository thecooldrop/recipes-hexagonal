package udarnicka.recipes.ingredients.domain;

import lombok.ToString;

import java.util.Optional;

@ToString
public class Ingredient {

    private String name;
    private IngredientId id;

    private Ingredient(String name, IngredientId id) {
        this.name = name;
        this.id = id;
    }

    // TODO add tests
    public static Optional<Ingredient> tryFrom(String ingredient,
                                               IngredientId id) {
        if(ingredient == null || id == null) {
            return Optional.empty();
        }
        if(!ingredient.isBlank()) {
            return Optional.of(new Ingredient(ingredient, id));
        }
        return Optional.empty();
    }

    @Override
    public int hashCode() {
        return this.name.hashCode();
    }

    @Override
    public boolean equals(Object other) {
        if(other instanceof Ingredient) {
            Ingredient otherIngredient = (Ingredient) other;
            return this.name.equalsIgnoreCase(otherIngredient.name) &&
                    this.id.equals(otherIngredient.id);
        }
        return false;
    }

}
