package udarnicka.common;

import jdk.jfr.Description;
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
}
