package udarnicka.ingredients.crud.domain.ports;

import lombok.NonNull;
import lombok.Value;
import udarnicka.common.CanonicalName;

@Value
public class IngredientName {
    @NonNull
    CanonicalName name;
}
