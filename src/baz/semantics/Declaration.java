
package baz.semantics;

import static baz.semantics.Type.*;

import baz.syntax.node.*;

public class Declaration {

    private String name;

    private Type type;

    private Token location;

    Declaration(
            String name,
            Type type,
            Token location) {

        if (type == VOID) {
            throw new SemanticException("void is not a valid declaration type",
                    location);
        }
        this.name = name;
        this.type = type;
        this.location = location;
    }

    public String getName() {

        return this.name;
    }

    public Type getType() {

        return this.type;
    }

    public Token getLocation() {

        return this.location;
    }
}
