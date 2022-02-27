package udarnicka.common;

import lombok.Getter;
import lombok.NonNull;

import java.util.Locale;

/**
 * This class is a wrapper around strings which represents the canonical representation of the string. This
 * class enables us to have a canonical representation of names of objects and to compare them consistently.
 *
 * In this class the strings "Pizza Margharita" and "pizza margharita" and "pizza Margharita" are all equal objects.
 *
 * <pre>{@code
 *      CanonicalName first = new CanonicalName("One Two");
 *      CanonicalName second = new CanonicalName("one Two");
 *      assert first.equals(second);
 *      assert first.toString().equals("one Two");
 *      assert second.toString().equals("One Two");
 *      assert !first.toString().equals(second.toString());
 *
 *      // Throws illegal argument exception
 *      CanonicalName third = new CanonicalName(null);
 *
 * }
 * </pre>
 */
@Getter
public final class CanonicalName {

    private final String original;
    private final String canonical;

    public CanonicalName(@NonNull String original) {
        this.original = original;
        this.canonical = original.toLowerCase();
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(o instanceof CanonicalName) {
            return this.canonical.equalsIgnoreCase(((CanonicalName) o).canonical);
        } else {
            return false;
        }
    }
}
