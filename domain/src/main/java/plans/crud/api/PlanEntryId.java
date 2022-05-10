package plans.crud.api;

import common.PositiveInteger;
import lombok.EqualsAndHashCode;
import lombok.NonNull;

@EqualsAndHashCode
public class PlanEntryId {

    private final PositiveInteger id;

    public PlanEntryId(@NonNull PositiveInteger id) {
        this.id = id;
    }
}
