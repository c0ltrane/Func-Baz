
package baz.interpreter;

// *** À COMPRENDRE ***

public class BoolValue
        extends Value {

    private boolean value;

    BoolValue(
            boolean value) {
        this.value = value;
    }

    public boolean getValue() {

        return this.value;
    }

    @Override
    public boolean equals(
            Object obj) {

        return ((BoolValue) obj).value == this.value;
    }

    @Override
    public String toString() {

        return "" + this.value;
    }
}
