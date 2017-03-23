
package baz.interpreter;

// *** À COMPRENDRE ***

public class VoidValue
        extends Value {

    private static VoidValue instance = new VoidValue();

    private VoidValue() {
    }

    public static VoidValue getInstance() {

        return instance;
    }
}
