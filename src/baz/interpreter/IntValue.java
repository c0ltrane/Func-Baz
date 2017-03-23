
package baz.interpreter;

// *** À COMPRENDRE ***

public class IntValue
        extends Value {

    private int value;

    IntValue(
            int value) {
        this.value = value;
    }

    public int getValue() {

        return this.value;
    }

    @Override
    public boolean equals(
            Object obj) {

        return ((IntValue) obj).value == this.value;
    }

    @Override
    public String toString() {

        return "" + this.value;
    }
}
