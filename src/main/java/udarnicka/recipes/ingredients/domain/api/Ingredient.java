package udarnicka.recipes.ingredients.domain.api;

import lombok.NonNull;
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
    public Optional<Ingredient> tryFrom(@NonNull String ingredient,
                                        @NonNull IngredientId id) {
        if(!ingredient.isBlank()) {
            return Optional.of(new Ingredient(name, id));
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
