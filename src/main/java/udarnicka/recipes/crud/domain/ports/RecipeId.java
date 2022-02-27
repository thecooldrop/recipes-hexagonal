package udarnicka.recipes.crud.domain.ports;

import lombok.NonNull;
import udarnicka.common.SerialInteger;

public record RecipeId(@NonNull SerialInteger id) {
}
