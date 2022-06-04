package common;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class PositiveInteger {

    private final Integer internal;
    public PositiveInteger(Integer number) {
        if(number == null) {
            throw new NullPointerException("The PositiveInteger can only be initialized with non-null values");
        }
        if(number <= 0) {
            throw new IllegalArgumentException("The positive integer must be greater than zero");
        }
        this.internal = number;
    }

    public Integer asInteger() {
        return internal;
    }
}
