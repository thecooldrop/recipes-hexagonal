package plans.crud.api;

import lombok.NonNull;

public class EntryNotFoundException extends RuntimeException {

    private final PlanEntryId id;

    public EntryNotFoundException(@NonNull PlanEntryId id) {
        this.id = id;
    }

    public EntryNotFoundException(@NonNull PlanEntryId id, Throwable cause) {
        super(cause);
        this.id = id;
    }
}
