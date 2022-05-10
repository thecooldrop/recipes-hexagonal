package plans.crud.api;

import lombok.EqualsAndHashCode;
import lombok.NonNull;
import recipes.crud.api.RecipeId;

import java.time.LocalDate;

@EqualsAndHashCode
public class PlanEntry {

    private PlanEntryId id;
    private LocalDate date;
    private RecipeId recipeId;

    public PlanEntry(@NonNull PlanEntryId id, @NonNull LocalDate date, @NonNull RecipeId recipeId) {
        this.id = id;
        this.date = date;
        this.recipeId = recipeId;
    }
}
