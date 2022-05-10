package plans.crud.api;

import lombok.EqualsAndHashCode;
import lombok.NonNull;

import java.util.List;

@EqualsAndHashCode
public class Plan {

    private final PlanId id;
    private final List<PlanEntryId> planEntries;

    public Plan(@NonNull PlanId id, @NonNull List<PlanEntryId> planEntries) {
        this.id = id;
        this.planEntries = planEntries;
    }

}
