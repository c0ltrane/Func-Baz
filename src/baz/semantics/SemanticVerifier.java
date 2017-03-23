
package baz.semantics;

import static baz.semantics.Type.*;

import java.util.*;

import baz.syntax.analysis.*;
import baz.syntax.node.*;

public class SemanticVerifier
        extends DepthFirstAdapter {

    private FunctionTable functionTable;

    private Scope currentScope;

    private FunctionInfo currentFunction;

    private Type result;

    public static void verify(
            Node tree,
            FunctionTable functionTable) {

        new SemanticVerifier(functionTable).visit(tree);
    }

    private SemanticVerifier(
            FunctionTable functionTable) {

        this.functionTable = functionTable;
    }

    /** visit node, if not null */
    public void visit(
            Node node) {

        if (node != null) {
            node.apply(this);
        }
    }

    /** visit node list, if not null */
    public void visit(
            List<? extends Node> nodes) {

        if (nodes != null) {
            for (Node n : nodes) {
                visit(n);
            }
        }
    }

    @Override
    public void caseAProgram(
            AProgram node) {

        // trouve les déclarations de fonction
        visit(node.getFuncs());

        // faire l'interprétation abstraite du code

        // (1) du programme principal
        visit(node.getBlock());

        // (2) du bloc des fonctions
        for (FunctionInfo functionInfo : this.functionTable.getFunctions()) {
            this.currentScope = new Scope(null);
            this.currentFunction = functionInfo;
            functionInfo.populateScope(this.currentScope);
            visit(functionInfo.getBlock());
            this.currentScope = null;
        }

    }

    @Override
    public void caseAFunc(
            AFunc node) {

        this.functionTable.addFunction(node);
    }

    /** evaluate an expression abstractly and return its type */
    private Type eval(
            Node node) {

        // pour s'assurer qu'on n'a pas précédemment oublié d'utiliser une
        // valeur
        assert this.result == null;

        // visite du noeud pour évaluer l'expression
        visit(node);

        // pour s'assurer qu'une valeur a effectivement été calculée
        assert this.result != null;

        // retourne la valeur en remettant this.result à null
        Type result = this.result;
        this.result = null;
        return result;
    }

    @Override
    public void caseABlock(
            ABlock node) {

        Scope oldScope = this.currentScope;
        this.currentScope = new Scope(oldScope);
        visit(node.getInsts());
        this.currentScope = oldScope;
    }

    @Override
    public void caseADeclarationInst(
            ADeclarationInst node) {

        // évalue l'expression
        Type type = eval(node.getExp());

        this.currentScope.addVariable(
                new Declaration(node.getId().getText(), type, node.getId()));
    }

    @Override
    public void caseAExpInst(
            AExpInst node) {

        // évalue l'expression
        eval(node.getExp());
    }

    @Override
    public void caseAIfInst(
            AIfInst node) {

        // évalue l'expression
        Type type = eval(node.getExp());

        if (type != BOOL) {
            throw new SemanticException("contition must be a boolean",
                    node.getIf());
        }

        visit(node.getBlock());
        visit(node.getElsePart());
    }

    @Override
    public void caseAWhileInst(
            AWhileInst node) {

        // évalue l'expression
        Type type = eval(node.getExp());

        if (type != BOOL) {
            throw new SemanticException("condition must be a boolean",
                    node.getWhile());
        }

        visit(node.getBlock());
    }

    @Override
    public void caseAPrintInst(
            APrintInst node) {

        // *** À COMPRENDRE ***

        if (node.getExp() != null) {
            // évalue l'expression
            Type type = eval(node.getExp());
            if (type == VOID) {
                throw new SemanticException("cannot print a void expression",
                        node.getPrint());
            }
        }
    }

    @Override
    public void caseAReturnInst(
            AReturnInst node) {

        // *** À FAIRE ***
    	if (node.getExp() == null) {
            // évalue l'expression
            if(this.currentFunction.getReturnType() != VOID){
            	throw new SemanticException("function must return void", node.getReturn());
            }
    	}
    	else{
    		Type type = eval(node.getExp());
    	
            if(this.currentFunction.getReturnType() != type){
            	throw new SemanticException("bad return type", node.getReturn());
            }
     
        }
    	
    }

    @Override
    public void caseAAssignExp(
            AAssignExp node) {

        // *** À FAIRE ***
    	Type type = eval(node.getExp());
    	Declaration declaration = this.currentScope.getVariable(node.getId());
    	if(type!=declaration.getType()){
    		throw new SemanticException("must assign a value of " + declaration.getType() + "type", node.getAssign());
    	}
    	this.result = type;
    }

    @Override
    public void caseAOrChoice(
            AOrChoice node) {

        // évalue l'expression à gauche
        Type left = eval(node.getLeft());

        // évalue l'expression à droite
        Type right = eval(node.getRight());

        if (left != BOOL) {
            throw new SemanticException("left value must be of bool type",
                    node.getOr());
        }

        if (right != BOOL) {
            throw new SemanticException("right value must be of bool type",
                    node.getOr());
        }

        this.result = BOOL;
    }

    @Override
    public void caseAAndConjunction(
            AAndConjunction node) {

        // évalue l'expression à gauche
        Type left = eval(node.getLeft());

        // évalue l'expression à droite
        Type right = eval(node.getRight());

        if (left != BOOL) {
            throw new SemanticException("left value must be of bool type",
                    node.getAnd());
        }

        if (right != BOOL) {
            throw new SemanticException("right value must be of bool type",
                    node.getAnd());
        }

        this.result = BOOL;
    }

    @Override
    public void caseAEqEquality(
            AEqEquality node) {

        // évalue l'expression à gauche
        Type left = eval(node.getLeft());

        // évalue l'expression à droite
        Type right = eval(node.getRight());

        if (left == VOID) {
            throw new SemanticException("left cannot be of void type",
                    node.getEq());
        }

        if (right != left) {
            throw new SemanticException(
                    "right value must be of same type as left value",
                    node.getEq());
        }

        this.result = BOOL;
    }

    @Override
    public void caseANeqEquality(
            ANeqEquality node) {

        // évalue l'expression à gauche
        Type left = eval(node.getLeft());

        // évalue l'expression à droite
        Type right = eval(node.getRight());

        if (left == VOID) {
            throw new SemanticException("left cannot be of void type",
                    node.getNeq());
        }

        if (right != left) {
            throw new SemanticException(
                    "right value must be of same type as left value",
                    node.getNeq());
        }

        this.result = BOOL;
    }

    @Override
    public void caseALtRelative(
            ALtRelative node) {

        // évalue l'expression à gauche
        Type left = eval(node.getLeft());

        // évalue l'expression à droite
        Type right = eval(node.getRight());

        if (left != INT) {
            throw new SemanticException("left cannot be of " + left + " type",
                    node.getLt());
        }

        if (right != left) {
            throw new SemanticException(
                    "right value must be of same type as left value",
                    node.getLt());
        }

        this.result = BOOL;
    }

    @Override
    public void caseALteqRelative(
            ALteqRelative node) {

        // évalue l'expression à gauche
        Type left = eval(node.getLeft());

        // évalue l'expression à droite
        Type right = eval(node.getRight());

        if (left != INT) {
            throw new SemanticException("left cannot be of " + left + " type",
                    node.getLteq());
        }

        if (right != left) {
            throw new SemanticException(
                    "right value must be of same type as left value",
                    node.getLteq());
        }

        this.result = BOOL;
    }

    @Override
    public void caseAGtRelative(
            AGtRelative node) {

        // évalue l'expression à gauche
        Type left = eval(node.getLeft());

        // évalue l'expression à droite
        Type right = eval(node.getRight());

        if (left != INT) {
            throw new SemanticException("left cannot be of " + left + " type",
                    node.getGt());
        }

        if (right != left) {
            throw new SemanticException(
                    "right value must be of same type as left value",
                    node.getGt());
        }

        this.result = BOOL;
    }

    @Override
    public void caseAGteqRelative(
            AGteqRelative node) {

        // évalue l'expression à gauche
        Type left = eval(node.getLeft());

        // évalue l'expression à droite
        Type right = eval(node.getRight());

        if (left != INT) {
            throw new SemanticException("left cannot be of " + left + " type",
                    node.getGteq());
        }

        if (right != left) {
            throw new SemanticException(
                    "right value must be of same type as left value",
                    node.getGteq());
        }

        this.result = BOOL;
    }

    @Override
    public void caseAAddAdditive(
            AAddAdditive node) {

        // *** À FAIRE ***
        // Note: permettre la concaténation de string avec string, int et bool
        // (en plus de l'addition entre entiers).
    	Type left = eval(node.getLeft());

        // évalue l'expression à droite
        Type right = eval(node.getRight());
        
        /*
        switch(left){
        	case BOOL:
        		
        	case INT:
        	case STRING:
        		if(right != )
        }
        */
    }

    @Override
    public void caseASubAdditive(
            ASubAdditive node) {

        // évalue l'expression à gauche
        Type left = eval(node.getLeft());

        // évalue l'expression à droite
        Type right = eval(node.getRight());

        if (left != INT) {
            throw new SemanticException("left cannot be of " + left + " type",
                    node.getMinus());
        }

        if (right != left) {
            throw new SemanticException(
                    "right value must be of same type as left value",
                    node.getMinus());
        }

        this.result = INT;
    }

    @Override
    public void caseAMulFactor(
            AMulFactor node) {

        // évalue l'expression à gauche
        Type left = eval(node.getLeft());

        // évalue l'expression à droite
        Type right = eval(node.getRight());

        if (left != INT) {
            throw new SemanticException("left cannot be of " + left + " type",
                    node.getStar());
        }

        if (right != left) {
            throw new SemanticException(
                    "right value must be of same type as left value",
                    node.getStar());
        }

        this.result = INT;
    }

    @Override
    public void caseADivFactor(
            ADivFactor node) {

        // évalue l'expression à gauche
        Type left = eval(node.getLeft());

        // évalue l'expression à droite
        Type right = eval(node.getRight());

        if (left != INT) {
            throw new SemanticException("left cannot be of " + left + " type",
                    node.getSlash());
        }

        if (right != left) {
            throw new SemanticException(
                    "right value must be of same type as left value",
                    node.getSlash());
        }

        this.result = INT;
    }

    @Override
    public void caseAModFactor(
            AModFactor node) {

        // évalue l'expression à gauche
        Type left = eval(node.getLeft());

        // évalue l'expression à droite
        Type right = eval(node.getRight());

        if (left != INT) {
            throw new SemanticException("left cannot be of " + left + " type",
                    node.getPercent());
        }

        if (right != left) {
            throw new SemanticException(
                    "right value must be of same type as left value",
                    node.getPercent());
        }

        this.result = INT;
    }

    @Override
    public void caseAPosUnary(
            APosUnary node) {

        // évalue l'expression
        Type type = eval(node.getUnary());

        if (type != INT) {
            throw new SemanticException("value cannot be of " + type + " type",
                    node.getPlus());
        }

        this.result = INT;
    }

    @Override
    public void caseANegUnary(
            ANegUnary node) {

        // évalue l'expression
        Type type = eval(node.getUnary());

        if (type != INT) {
            throw new SemanticException("value cannot be of " + type + " type",
                    node.getMinus());
        }

        this.result = INT;
    }

    @Override
    public void caseANotUnary(
            ANotUnary node) {

        // évalue l'expression
        Type type = eval(node.getUnary());

        if (type != BOOL) {
            throw new SemanticException("value cannot be of " + type + " type",
                    node.getNot());
        }

        this.result = BOOL;
    }

    @Override
    public void caseAVarTerm(
            AVarTerm node) {

        // *** À FAIRE ***
    }

    @Override
    public void caseANumTerm(
            ANumTerm node) {

        // vérifie que la valeur est OK
        try {
            Integer.parseInt(node.getNumber().getText());
        }
        catch (NumberFormatException e) {
            throw new SemanticException(
                    "invalid number '" + node.getNumber().getText() + "'",
                    node.getNumber());
        }

        this.result = INT;
    }

    @Override
    public void caseAStringTerm(
            AStringTerm node) {

        this.result = STRING;
    }

    @Override
    public void caseATrueTerm(
            ATrueTerm node) {

        this.result = BOOL;
    }

    @Override
    public void caseAFalseTerm(
            AFalseTerm node) {

        this.result = BOOL;
    }

    @Override
    public void caseACallTerm(
            ACallTerm node) {

        // *** À COMPRENDRE ***

        FunctionInfo functionInfo = this.functionTable
                .getFunctionInfo(node.getId());

        if (functionInfo == null) {
            throw new SemanticException(
                    "unknown function " + node.getId().getText(), node.getId());
        }

        // évalue les arguments
        LinkedList<Type> args = new LinkedList<>();
        if (node.getArgs() != null) {
            // premier argument
            AArgs aArgs = (AArgs) node.getArgs();
            args.add(eval(aArgs.getExp()));

            // arguments additionnels
            for (PAdditionalArg pAdditionalArg : aArgs.getAdditionalArgs()) {
                AAdditionalArg aAdditionalArg = (AAdditionalArg) pAdditionalArg;

                args.add(eval(aAdditionalArg.getExp()));
            }
        }

        // *** À COMPRENDRE: le corps de FunctionInfo.verifyArgs ***

        // vérifie les arguments
        functionInfo.verifyArgs(args, node.getLPar());

        this.result = functionInfo.getReturnType();
    }
}
