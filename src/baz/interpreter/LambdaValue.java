
package baz.interpreter;

import baz.semantics.Scope;
import baz.semantics.Declaration;

import baz.syntax.node.*;

import java.util.*;


public class LambdaValue
        extends Value {

	private ALambdaTerm definition;
	
	private Frame environment;
	//private HashMap<String, Value> environment;
	
    LambdaValue(
            ALambdaTerm node) {
        this.definition = node;
    }
    
    public ALambdaTerm getValue() {

        return this.definition;
    }
    /*
    public void setEnvironment(Frame outerFunction){
    	environment = new HashMap<String,Value>(outerFunction.getVariables());
    }
   */
    
    public Frame getEnvironment(){
    	return environment;
    }
    
    public void setEnvironment(Frame outerFunction){
    	this.environment = outerFunction;
    }
    
    @Override
    public boolean equals(
            Object obj) {

        return ((ALambdaTerm) obj) == this.definition;
    }
    
}
