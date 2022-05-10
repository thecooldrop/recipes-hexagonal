package plans.crud.api;

import lombok.NonNull;
import recipes.crud.api.RecipeId;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class PlanCrud {

    private final PlanRepository repository;

    public PlanCrud(@NonNull PlanRepository repository) {
        this.repository = repository;
    }

    Plan create() {
        return repository.create();
    }

    Optional<Plan> read(@NonNull PlanId id) {
        return repository.readById(id);
    }

    List<Plan> readAll() {
        return repository.read();
    }

    void delete(@NonNull PlanId id) {
        repository.delete(id);
    }

    // TODO: What if the referenced recipe does not exist?
    /**
     * @throws RecipeNotFoundException - If we try create an entry with recipe which does not exist, this happens
     * @throws PlanNotFoundException - If we try to add an entry to plan which does not exist
     */
    PlanEntry addEntry(@NonNull PlanId id, @NonNull LocalDate date, @NonNull RecipeId recipeId) {
        return repository.addEntry(id, date, recipeId);
    }

    void deleteEntry(@NonNull PlanId planId, @NonNull PlanEntryId entryId) {
        repository.deleteEntry(planId, entryId);
    }

    /**
     * @throws RecipeNotFoundException - If we try to update an entry with nonexistent recipe
     * @throws EntryNotFoundException - If we try to update a nonexistent entry
     * @throws PlanNotFoundException - If we try to update an entry for nonexistent plan
     */
    PlanEntry updateEntry(@NonNull PlanId planId, @NonNull PlanEntryId entryId, @NonNull LocalDate date, @NonNull RecipeId recipeId) {
        return repository.updateEntry(planId, entryId, date, recipeId);
    }
}
