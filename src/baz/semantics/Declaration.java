
package baz.semantics;

import static baz.semantics.Type.*;

import baz.syntax.analysis.DepthFirstAdapter;
import baz.syntax.node.*;
import java.util.*;

public class Declaration {

    private String name;

    private Type type;

    private Token location;
    
    private LinkedList<Type> anonParameters = new LinkedList<>();
    
    private Type anonReturnType;
    
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
    
    // constructor for lambda variable
    Declaration(
            String name,
            Type type,
            Token location, ALambdaTerm lambda) {

       this(name,type,location);
       this.lambdaDefinition = lambda;
    }
    
    // constructor for anonymous function passed as function parameter
    Declaration(
            String name,
            Type type,
            Token location, PType paramType) {

       this(name,type,location);
       
       if(type == ANON){
          	addAnonParams(((AAnonType)paramType).getParamsAnon());
          	if (((AAnonType)paramType).getReturnTypeAnon() != null) {

          		((AAnonType)paramType).getReturnTypeAnon().apply(new DepthFirstAdapter() {

                    @Override
                    public void caseAReturnTypeAnon(
                            AReturnTypeAnon node) {

                        Declaration.this.anonReturnType = Type.get(node.getType());
                    }
                });
            }
       }
       
    }
    
    private void addAnonParams(PParamsAnon params){
    	//PParams params = node.getParams();
        if (params != null) {
            params.apply(new DepthFirstAdapter() {

                @Override
                public void caseAParamAnon(
                        AParamAnon node) {
                    Declaration.this.anonParameters.add(Type.get(node.getType()));
                    
                }
            });
        }
        
    }
    
    
    public ALambdaTerm getLambda(){
    	return lambdaDefinition;
    }
    
    public LinkedList<Type> getAnonParameters(){
    	return anonParameters;
    }
    
    public Type getAnonReturnType(){
    	return anonReturnType;
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
