package plans.crud.api;

import lombok.NonNull;

public class PlanNotFoundException extends RuntimeException {

    private final PlanId id;

    public PlanNotFoundException(@NonNull PlanId id) {
        this.id = id;
    }

    public PlanNotFoundException(@NonNull PlanId id, Throwable cause) {
        super(cause);
        this.id = id;
    }
}
