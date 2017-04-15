
package baz.semantics;

import static baz.semantics.Type.*;

import baz.syntax.analysis.DepthFirstAdapter;
import baz.syntax.node.*;
import java.util.*;

public class Declaration {

    private String name;

    private Type type;

    private Token location;
        
    private LambdaInfo lambdaInfo;
    
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
    
    // constructor for lambda expression
    Declaration(
            String name,
            Type type,
            Token location,
            LambdaInfo lambdaInfo) {
    	this(name,type,location);
    	this.lambdaInfo = lambdaInfo;
    }
    
    
    // constructor for lambda expression passed as function parameter
    Declaration(
            String name,
            Type type,
            Token location, PType paramType) {

       this(name,type,location);
       this.lambdaInfo = new LambdaInfo(paramType); 
	}
    
    public LambdaInfo getLambda(){
    	return lambdaInfo;
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
