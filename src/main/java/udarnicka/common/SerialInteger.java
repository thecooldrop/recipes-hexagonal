package udarnicka.common;

/**
 * A class which represents integers which are greater than or equal to one
 */
public final class SerialInteger {

    private int inner;

    public SerialInteger(int value) {
        if(value < 1) {
            throw new IllegalArgumentException("The serial integer must be greater than or equals to one");
        }
        inner = value;
    }

    public SerialInteger(SerialInteger other) {
        this.inner = other.inner;
    }

    public int toInteger() {
        return inner;
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(o instanceof SerialInteger) {
            return this.inner == ((SerialInteger) o).inner;
        } else {
            return false;
        }
    }

}
