
package baz.semantics;

import static baz.semantics.Type.*;

import baz.syntax.node.*;

public class Declaration {

    private String name;

    private Type type;

    private Token location;
    
    private ALambdaTerm lambdaDefinition;

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
    
    Declaration(
            String name,
            Type type,
            Token location, ALambdaTerm lambda) {

       this(name,type,location);
       this.lambdaDefinition = lambda;
    }
    
    public ALambdaTerm getLambda(){
    	return lambdaDefinition;
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
