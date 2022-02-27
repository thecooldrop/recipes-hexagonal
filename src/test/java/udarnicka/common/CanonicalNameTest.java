package udarnicka.common;

import jdk.jfr.Description;
import org.checkerframework.checker.units.qual.C;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class CanonicalNameTest {

    @Test
    @Description("Canonical name can not be constructed from null")
    void canNotBeConstructedFromNull() {
        assertThatThrownBy(() -> new CanonicalName(null)).isInstanceOf(NullPointerException.class);
    }

    @Test
    @Description("Canonical representation is equal to lowercase of the original")
    void canonicalRepresentationIsLowercaseOfTheOriginal() {
        CanonicalName original = new CanonicalName("Original");
        CanonicalName lowercase = new CanonicalName("original");
        assertThat(original).isEqualTo(lowercase);
        assertThat(original.getCanonical()).isEqualTo("original");
    }

    @Test
    @Description("Original representation can be retrieved from canonical representation")
    void originalRepresentationCanBeRetrievedFromTheCanonicalRepresentation() {
        CanonicalName canonical = new CanonicalName("CanonIcal");
        assertThat(canonical.getOriginal()).isEqualTo("CanonIcal");
    }

    @Test
    @Description("Canonical names can not be constructed from empty or blank strings")
    void canNotBeEmptyOrBlank() {
        assertThatThrownBy(() -> new CanonicalName("")).isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> new CanonicalName("\n\t")).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @Description("Equal objects return the same hashcode")
    void equalObjectsReturnSameHashValues() {
        CanonicalName lowercased = new CanonicalName("lowercased");
        CanonicalName uppercased = new CanonicalName("LOWERCASED");
        assertThat(lowercased).isEqualTo(uppercased);
        assertThat(lowercased.hashCode()).isEqualTo(uppercased.hashCode());
    }
}
