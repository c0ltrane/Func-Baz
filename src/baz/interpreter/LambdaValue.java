
package baz.interpreter;

import baz.syntax.node.*;


public class LambdaValue
        extends Value {

    private ALambdaTerm definition;
    
    LambdaValue(
            ALambdaTerm node) {
        this.definition = node;
    }
    
    public ALambdaTerm getValue() {

        return this.definition;
    }

    @Override
    public boolean equals(
            Object obj) {

        return ((ALambdaTerm) obj) == this.definition;
    }
    
}
