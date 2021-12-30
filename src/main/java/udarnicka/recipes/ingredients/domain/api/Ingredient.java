package udarnicka.recipes.ingredients.domain.api;

import lombok.ToString;

import java.util.Optional;

@ToString
public class Ingredient {

    private String name;

    private Ingredient(String name) {
        this.name = name;
    }

    // TODO add tests
    public Optional<Ingredient> tryFrom(String ingredient) {
        if(ingredient != null && !ingredient.isBlank()) {
            return Optional.of(new Ingredient(name));
        }
        return Optional.empty();
    }

    @Override
    public int hashCode() {
        return this.name.hashCode();
    }

    @Override
    // TODO add test
    public boolean equals(Object other) {
        if(other instanceof Ingredient) {
            Ingredient otherIngredient = (Ingredient) other;
            return this.name.equalsIgnoreCase(otherIngredient.name);
        }
        return false;
    }
}
