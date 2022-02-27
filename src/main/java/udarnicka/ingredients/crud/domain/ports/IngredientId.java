package udarnicka.ingredients.crud.domain.ports;

import lombok.NonNull;
import udarnicka.common.SerialInteger;

public record IngredientId(@NonNull SerialInteger id) {
}
