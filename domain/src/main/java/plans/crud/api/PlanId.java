package plans.crud.api;

import common.PositiveInteger;
import lombok.EqualsAndHashCode;
import lombok.NonNull;

@EqualsAndHashCode
public class PlanId {

    public final PositiveInteger id;

    public PlanId(@NonNull PositiveInteger id) {
        this.id = id;
    }
}
