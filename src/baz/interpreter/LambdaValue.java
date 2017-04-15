
package baz.interpreter;


import baz.syntax.node.*;


public class LambdaValue
        extends Value {

	private ALambdaTerm definition;
	
	private Frame environment;
	
    LambdaValue(
            ALambdaTerm node, Frame outerFunction) {
        this.definition = node;
        this.environment = outerFunction;
    }
    
    public ALambdaTerm getLambda() {

        return this.definition;
    }
   
    public Frame getEnvironment(){
    	return environment;
    }
    
    
    @Override
    public boolean equals(
            Object obj) {

        return ((ALambdaTerm) obj) == this.definition;
    }
    
}
