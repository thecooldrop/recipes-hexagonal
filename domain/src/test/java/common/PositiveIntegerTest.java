package common;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class PositiveIntegerTest {

    @Test
    void canNotInitializeFromZero() {
        assertThrows(IllegalArgumentException.class, () -> {
            new PositiveInteger(0);
        });
    }

    @Test
    void canNotInitializeFromNull() {
        assertThrows(NullPointerException.class, () -> {
            new PositiveInteger(null);
        });
    }
}
