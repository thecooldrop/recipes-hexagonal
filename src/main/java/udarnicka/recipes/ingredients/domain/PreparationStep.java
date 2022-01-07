package udarnicka.recipes.ingredients.domain;

import lombok.NonNull;
import lombok.Value;

@Value
public class PreparationStep {
    @NonNull
    String step;
}
