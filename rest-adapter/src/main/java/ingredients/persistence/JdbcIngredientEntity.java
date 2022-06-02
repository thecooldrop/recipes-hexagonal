package ingredients.persistence;

import lombok.Data;
import lombok.NonNull;

@Data
public class JdbcIngredientEntity {
    @NonNull
    private final Integer id;
    @NonNull
    private final String name;
    @NonNull
    private final String canonical_name;
}
