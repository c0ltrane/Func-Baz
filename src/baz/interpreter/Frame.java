
package baz.interpreter;

import java.util.*;

import baz.semantics.*;
import baz.syntax.node.*;

public class Frame {

    private Frame parent;

    private FunctionInfo functionInfo;

    private HashMap<String, Value> variables = new HashMap<>();

    private Value returnValue = VoidValue.getInstance();

    private TId callLocation;

    public Frame(
            Frame parent,
            FunctionInfo functionInfo) {

        this.parent = parent;
        this.functionInfo = functionInfo;
    }

    public void setVariable(
            String name,
            Value value) {

        this.variables.put(name, value);
    }

    public Value getVariable(
            TId id) {

        // vérifie si la variable existe
        String name = id.getText();
        
        if (variableExists(name)) {
            return this.variables.get(name);

        }
        if(parent.variableExists(name)){
            return this.parent.getVariables().get(name);
        }
        else{
        	throw new InterpreterException("undefined variable '" + name + "'",
                    id, this);
        }
        /*
        if (!this.variables.containsKey(name)) {
            throw new InterpreterException("undefined variable '" + name + "'",
                    id, this);
        }
*/
    }
    
    public boolean variableExists(
            String name) {

        if (this.variables.containsKey(name)) {
            return true;
        }

        return false;
    }
    
    public HashMap<String,Value> getVariables(){
    	return variables;
    }
    
    public void setReturnValue(
            Value value) {

        this.returnValue = value;
    }

    public Value getReturnValue(
            Token location) {

        return this.returnValue;
    }

    public void setCallLocation(
            TId callLocation) {

        this.callLocation = callLocation;
    }

    public TId getCallLocation() {

        return this.callLocation;
    }

    public Frame getParent() {

        return this.parent;
    }

    public FunctionInfo getFunctionInfo() {

        return this.functionInfo;
    }
}
