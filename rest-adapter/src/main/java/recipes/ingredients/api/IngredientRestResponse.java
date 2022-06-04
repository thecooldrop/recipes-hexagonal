package recipes.ingredients.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

@Data
@AllArgsConstructor
public class IngredientRestResponse {
    @NonNull
    public Integer id;
    @NonNull
    public String name;
}
