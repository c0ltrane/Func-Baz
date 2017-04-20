
package baz.semantics;

import static baz.semantics.Type.*;

import java.util.*;

import baz.interpreter.*;
import baz.syntax.analysis.*;
import baz.syntax.node.*;

public class LambdaInfo {

    	
	private Node definition;
	    
    private LinkedHashSet<String> paramNames = new LinkedHashSet<>();

    private LinkedList<Declaration> params = new LinkedList<>();
    
    private Type returnType;

    public LambdaInfo(
            ALambdaTerm node) {

        this.definition = node.getBlock();

        // collectionne les paramètres
        addParams(node.getParams());
        if (node.getReturnType() != null) {

            node.getReturnType().apply(new DepthFirstAdapter() {

                @Override
                public void caseAReturnType(
                        AReturnType node) {

                	LambdaInfo.this.returnType = Type.get(node.getType());
                }
            });
        }
        else {
            this.returnType = VOID;
        }
        
    }
    
    // constructor for lambda expression function parameter definition
    public LambdaInfo(
            PType paramType) {
    	addLambdaParams(((AAnonType)paramType).getParamsAnon());
      	if (((AAnonType)paramType).getReturnTypeAnon() != null) {

      		((AAnonType)paramType).getReturnTypeAnon().apply(new DepthFirstAdapter() {

                @Override
                public void caseAReturnTypeAnon(
                        AReturnTypeAnon node) {

                    LambdaInfo.this.returnType = Type.get(node.getType());
                }
      		});
    
      	}
    }
    
    public LambdaInfo(
            LambdaValue node) {
    	this(node.getLambda());
    }
    
   
    private void addParams(PParams params){
        if (params != null) {
            params.apply(new DepthFirstAdapter() {

                @Override
                public void caseAParam(
                        AParam node) {

                    String name = node.getId().getText();
                    if (LambdaInfo.this.paramNames.contains(name)) {
                        throw new SemanticException("redefinition of " + name,
                                node.getId());
                    }
                    LambdaInfo.this.paramNames.add(name);

                    LambdaInfo.this.params
                            .add(new Declaration(node.getId().getText(),
                                    Type.get(node.getType()), node.getId()));
                }
            });
        }
        
    }
    
    private void addLambdaParams(PParamsAnon params){
    	//PParams params = node.getParams();
        if (params != null) {
            params.apply(new DepthFirstAdapter() {

                @Override
                public void caseAParamAnon(
                        AParamAnon node) {
                    LambdaInfo.this.params.add(new Declaration(null,
                            Type.get(node.getType()), null));
                    
                }
            });
        }
        
    }
    

    public void setParams(
            LinkedList<Value> args,
            Frame frame) {

        Iterator<Value> argIterator = args.iterator();

        for (Declaration param : this.params) {
            frame.addVariable(param.getName(), argIterator.next());
        }
    }

    public Node getBlock() {

        return this.definition;
    }

    public int paramCount() {

        return this.params.size();
    }

    
    public void setReturnType(){
    	
    }

    public Type getReturnType() {

        return this.returnType;
    }
    

    public void verifyArgs(
            LinkedList<Type> args,
            Token location) {

        Iterator<Type> argsIterator = args.iterator();
        int i = 0;
        for (Declaration declaration : this.params) {
            ++i;
            if (!argsIterator.hasNext()) {
                throw new SemanticException("argument #" + i + " is missing",
                        location);
            }

            if (argsIterator.next() != declaration.getType()) {
                throw new SemanticException("argument #" + i + " is not of "
                        + declaration.getType() + " type", location);
            }
        }

        if (argsIterator.hasNext()) {
            throw new SemanticException(
                    "only " + i + " arguments were expected", location);
        }

    }

}
