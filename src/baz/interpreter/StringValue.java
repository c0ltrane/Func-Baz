
package baz.interpreter;

// *** À COMPRENDRE ***

public class StringValue
        extends Value {

    private String value;

    StringValue(
            String value) {
        this.value = value;
    }

    public String getValue() {

        return this.value;
    }

    @Override
    public boolean equals(
            Object obj) {

        return ((StringValue) obj).value.equals(this.value);
    }

    @Override
    public String toString() {

        return "" + this.value;
    }
}
