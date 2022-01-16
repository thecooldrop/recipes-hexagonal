package udarnicka.recipes.domain;

import lombok.NonNull;
import lombok.Value;

@Value
public record IngredientId(@NonNull Integer id) {
}
