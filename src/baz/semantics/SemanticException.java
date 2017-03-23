
package baz.semantics;

import baz.syntax.node.*;

public class SemanticException
        extends RuntimeException {

    private final String message;

    private final Token token;

    public SemanticException(
            String message,
            Token token) {

        this.message = message;
        this.token = token;
    }

    @Override
    public String getMessage() {

        return this.message + " at line " + this.token.getLine() + " position "
                + this.token.getPos();
    }

}
