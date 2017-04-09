package baz.semantics;

import static baz.semantics.Type.*;

import java.util.*;

import baz.interpreter.*;
import baz.semantics.*;
import baz.syntax.analysis.*;
import baz.syntax.node.*;

public class ClosureInfo {
	
	private Frame freeVariables;
	private LambdaValue lambdaExp;
	
	public ClosureInfo(LambdaValue node, Frame freeVariables){
        this.lambdaExp = node;
        this.freeVariables = freeVariables;
	}
	
	public LambdaValue getLambdaExp(){
		return this.lambdaExp;
	}
}