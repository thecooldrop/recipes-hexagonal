package plans.crud.api;

import recipes.crud.api.RecipeId;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface PlanRepository {

    Plan create();
    List<Plan> read();
    Optional<Plan> readById(PlanId id);
    void delete(PlanId id);

    /**
     * @throws RecipeNotFoundException - If we try create an entry with recipe which does not exist, this happens
     * @throws PlanNotFoundException - If we try to add an entry to plan which does not exist
     */
    PlanEntry addEntry(PlanId id, LocalDate date, RecipeId recipeId);

    void deleteEntry(PlanId planId, PlanEntryId entryId);

    /**
     * @throws RecipeNotFoundException - If we try to update an entry with nonexistent recipe
     * @throws EntryNotFoundException - If we try to update a nonexistent entry
     * @throws PlanNotFoundException - If we try to update an entry for nonexistent plan
     */
    PlanEntry updateEntry(PlanId planId, PlanEntryId entryId, LocalDate date, RecipeId recipeId);
}
