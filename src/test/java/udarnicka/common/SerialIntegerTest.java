package udarnicka.common;

import jdk.jfr.Description;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class SerialIntegerTest {

    @Test
    @Description("Test that serial integer can not be created from values less than 1")
    void mustBeGreaterThanZero() {
        assertThatThrownBy(() -> new SerialInteger(0)).isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> new SerialInteger(-1)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @Description("The inner integer can be retrieved. Basically can be casted to the inner type")
    void canBeCastToInteger() {
        SerialInteger value = new SerialInteger(1);
        assertThat(value.toInteger()).isEqualTo(1);
    }
}
