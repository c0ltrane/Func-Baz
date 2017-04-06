
package baz.semantics;

import java.util.*;

import baz.syntax.node.*;
import baz.interpreter.*;

public class Scope {

    private Scope parent;

    private LinkedHashMap<String, Declaration> variables = new LinkedHashMap<>();
   // private LinkedHashMap<String , LambdaValue> anonymousFunctions = new LinkedHashMap<>();

    public Scope(
            Scope parent) {
        this.parent = parent;
    }

    public Scope getParent() {

        return this.parent;
    }

    public void addVariable(
            Declaration declaration) {

        String name = declaration.getName();

        if (variableExists(name)) {
            throw new SemanticException(
                    "variable " + name + " is already declared",
                    declaration.getLocation());
        }

        this.variables.put(name, declaration);
    }
    

    public boolean variableExists(
            String name) {

        if (this.variables.containsKey(name)) {
            return true;
        }

        if (this.parent != null) {
            return this.parent.variableExists(name);
        }

        return false;
    }

    public Declaration getVariable(
            TId id) {

        String name = id.getText();
        if (!this.variables.containsKey(name)) {
            if (this.parent != null) {
                return this.parent.getVariable(id);
            }
            else {
                throw new SemanticException("undefined variable " + name, id);
            }
        }

        return this.variables.get(name);
    }
}
