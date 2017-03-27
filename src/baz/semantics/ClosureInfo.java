package baz.semantics;

import static baz.semantics.Type.*;

import java.util.*;

import baz.interpreter.*;
import baz.semantics.*;
import baz.syntax.analysis.*;
import baz.syntax.node.*;

public class ClosureInfo {
	
	private Scope freeVariables;
	private ALambdaTerm definition;
	
	public ClosureInfo(ALambdaTerm node, Scope scope){
        this.definition = node;
        this.freeVariables = scope;
	}
	
	public ALambdaTerm getClosureDefinition(){
		return this.definition;
	}
}