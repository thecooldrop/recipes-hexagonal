package udarnicka.recipes.ingredients.domain;

import lombok.EqualsAndHashCode;
import lombok.NonNull;

import javax.measure.Quantity;
import javax.measure.quantity.Mass;
import java.util.Optional;

@EqualsAndHashCode
public class WeightedIngredient {

    private final Ingredient ingredient;
    private final Quantity<Mass> mass;

    private WeightedIngredient(@NonNull Ingredient ingredient, @NonNull Quantity<Mass> mass) {
        this.ingredient = ingredient;
        this.mass = mass;
    }

    public Optional<WeightedIngredient> tryFrom(@NonNull Ingredient ingredient, @NonNull Quantity<Mass> mass) {
        if(mass.getValue().doubleValue() <= 0) {
            return Optional.empty();
        }
        return Optional.of(new WeightedIngredient(ingredient, mass));
    }
}
