
package baz.interpreter;

import java.util.*;

import baz.semantics.*;
import baz.syntax.analysis.*;
import baz.syntax.node.*;

public class Interpreter
        extends DepthFirstAdapter {

    private FunctionTable functionTable;

    private Value result;

    private Frame currentFrame;

    public static void interpret(
            Node tree,
            FunctionTable functionTable) {

        new Interpreter(functionTable).visit(tree);
    }

    // constructeur
    private Interpreter(
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

    /** evaluate an expression and return its result */
    private Value eval(
            Node node) {

        // pour s'assurer qu'on n'a pas précédemment oublié d'utiliser une
        // valeur
        assert this.result == null;

        // visite du noeud pour évaluer l'expression
        visit(node);

        // pour s'assurer qu'une valeur a effectivement été calculée
        assert this.result != null;

        // retourne la valeur en remettant this.result à null
        Value result = this.result;
        this.result = null;
        return result;
    }

    @Override
    public void caseAProgram(
            AProgram node) {

        // mettre en place un Frame pour le programme principal
        this.currentFrame = new Frame(null, (FunctionInfo)null);

        // ne visiter que les instructions du programme principal
        visit(node.getBlock());

        this.currentFrame = null;
    }

    @Override
    public void caseADeclarationInst(
            ADeclarationInst node) {

        // évalue l'expression
        Value value = eval(node.getExp());

        // assigne la variable
        String name = node.getId().getText();

        this.currentFrame.addVariable(name, value);
    }

    @Override
    public void caseAExpInst(
            AExpInst node) {

        // évalue l'expression
        eval(node.getExp());

        // ne fait rien avec le résultat
    }

    @Override
    public void caseAIfInst(
            AIfInst node) {

        // évalue l'expression
        BoolValue value = (BoolValue) eval(node.getExp());

        if (value.getValue()) {
            // si vrai, visite le bloc THEN
            visit(node.getBlock());
        }
        else {
            // sinon, visite la partie ELSE
            visit(node.getElsePart());
        }
    }

    @Override
    public void caseAWhileInst(
            AWhileInst node) {

        while (true) {

            // évalue l'expression
            BoolValue value = (BoolValue) eval(node.getExp());

            if (!value.getValue()) {
                // si faux, sort de la boucle
                break;
            }

            visit(node.getBlock());
        }
    }

    @Override
    public void caseAPrintInst(
            APrintInst node) {

        if (node.getExp() == null) {
            System.out.println();
        }
        else {
            Value value = eval(node.getExp());
            System.out.print(value.toString());
        }
    }

    @Override
    public void caseAReturnInst(
            AReturnInst node) {

        if (node.getExp() != null) {
            // évalue l'expression
            Value value = eval(node.getExp());
            this.currentFrame.setReturnValue(value);
        }

        throw new ReturnException();
    }

    @Override
    public void caseAAssignExp(
            AAssignExp node) {

        // évalue l'expression
        Value value = eval(node.getExp());

        // assigne la variable
        String name = node.getId().getText();
        this.currentFrame.setVariable(name, value);

        // retourne la valeur
        this.result = value;
    }

    @Override
    public void caseAOrChoice(
            AOrChoice node) {

        // évalue l'expression à gauche
        BoolValue left = (BoolValue) eval(node.getLeft());

        // évalue l'expression à droite
        BoolValue right = (BoolValue) eval(node.getRight());

        this.result = new BoolValue(left.getValue() || right.getValue());
    }

    @Override
    public void caseAAndConjunction(
            AAndConjunction node) {

        // évalue l'expression à gauche
        BoolValue left = (BoolValue) eval(node.getLeft());

        // évalue l'expression à droite
        BoolValue right = (BoolValue) eval(node.getRight());

        this.result = new BoolValue(left.getValue() && right.getValue());
    }

    @Override
    public void caseAEqEquality(
            AEqEquality node) {

        // évalue l'expression à gauche
        Value left = eval(node.getLeft());

        // évalue l'expression à droite
        Value right = eval(node.getRight());

        this.result = new BoolValue(left.equals(right));
    }

    @Override
    public void caseANeqEquality(
            ANeqEquality node) {

        // évalue l'expression à gauche
        Value left = eval(node.getLeft());

        // évalue l'expression à droite
        Value right = eval(node.getRight());

        this.result = new BoolValue(!left.equals(right));
    }

    @Override
    public void caseALtRelative(
            ALtRelative node) {

        // évalue l'expression à gauche
        IntValue left = (IntValue) eval(node.getLeft());

        // évalue l'expression à droite
        IntValue right = (IntValue) eval(node.getRight());

        this.result = new BoolValue(left.getValue() < right.getValue());
    }

    @Override
    public void caseALteqRelative(
            ALteqRelative node) {

        // évalue l'expression à gauche
        IntValue left = (IntValue) eval(node.getLeft());

        // évalue l'expression à droite
        IntValue right = (IntValue) eval(node.getRight());

        this.result = new BoolValue(left.getValue() <= right.getValue());
    }

    @Override
    public void caseAGtRelative(
            AGtRelative node) {

        // évalue l'expression à gauche
        IntValue left = (IntValue) eval(node.getLeft());

        // évalue l'expression à droite
        IntValue right = (IntValue) eval(node.getRight());

        this.result = new BoolValue(left.getValue() > right.getValue());
    }

    @Override
    public void caseAGteqRelative(
            AGteqRelative node) {

        // évalue l'expression à gauche
        IntValue left = (IntValue) eval(node.getLeft());

        // évalue l'expression à droite
        IntValue right = (IntValue) eval(node.getRight());

        this.result = new BoolValue(left.getValue() >= right.getValue());
    }

    @Override
    public void caseAAddAdditive(
            AAddAdditive node) {

        // évalue l'expression à gauche
        Value left = eval(node.getLeft());

        // évalue l'expression à droite
        Value right = eval(node.getRight());

        if (left instanceof IntValue && right instanceof IntValue) {
            this.result = new IntValue(((IntValue) left).getValue()
                    + ((IntValue) right).getValue());
        }
        else {
            this.result = new StringValue(left.toString() + right.toString());
        }
    }

    @Override
    public void caseASubAdditive(
            ASubAdditive node) {

        // évalue l'expression à gauche
        IntValue left = (IntValue) eval(node.getLeft());

        // évalue l'expression à droite
        IntValue right = (IntValue) eval(node.getRight());

        this.result = new IntValue(left.getValue() - right.getValue());
    }

    @Override
    public void caseAMulFactor(
            AMulFactor node) {

        // évalue l'expression à gauche
        IntValue left = (IntValue) eval(node.getLeft());

        // évalue l'expression à droite
        IntValue right = (IntValue) eval(node.getRight());

        this.result = new IntValue(left.getValue() * right.getValue());
    }

    @Override
    public void caseADivFactor(
            ADivFactor node) {

        // évalue l'expression à gauche
        IntValue left = (IntValue) eval(node.getLeft());

        // évalue l'expression à droite
        IntValue right = (IntValue) eval(node.getRight());

        try {
            this.result = new IntValue(left.getValue() / right.getValue());
        }
        catch (ArithmeticException e) {
            throw new InterpreterException("divide by zero", node.getSlash(),
                    this.currentFrame);
        }
    }

    @Override
    public void caseAModFactor(
            AModFactor node) {

        // évalue l'expression à gauche
        IntValue left = (IntValue) eval(node.getLeft());

        // évalue l'expression à droite
        IntValue right = (IntValue) eval(node.getRight());

        try {
            this.result = new IntValue(left.getValue() % right.getValue());
        }
        catch (ArithmeticException e) {
            throw new InterpreterException("divide by zero", node.getPercent(),
                    this.currentFrame);
        }
    }

    @Override
    public void caseAPosUnary(
            APosUnary node) {

        // évalue l'expression
        IntValue value = (IntValue) eval(node.getUnary());

        this.result = new IntValue(+value.getValue());
    }

    @Override
    public void caseANegUnary(
            ANegUnary node) {

        // évalue l'expression
        IntValue value = (IntValue) eval(node.getUnary());

        this.result = new IntValue(-value.getValue());
    }

    @Override
    public void caseANotUnary(
            ANotUnary node) {

        // évalue l'expression
        BoolValue value = (BoolValue) eval(node.getUnary());

        this.result = new BoolValue(!value.getValue());
    }

    @Override
    public void caseAVarTerm(
            AVarTerm node) {

        this.result = this.currentFrame.getVariable(node.getId());
    }

    @Override
    public void caseANumTerm(
            ANumTerm node) {

        this.result = new IntValue(
                Integer.parseInt(node.getNumber().getText()));
    }

    @Override
    public void caseAStringTerm(
            AStringTerm node) {

        String s = node.getStringLiteral().getText();
        int length = s.length();
        s = s.substring(1, length - 1);

        this.result = new StringValue(s);
    }

    @Override
    public void caseATrueTerm(
            ATrueTerm node) {

        this.result = new BoolValue(true);
    }

    @Override
    public void caseAFalseTerm(
            AFalseTerm node) {

        this.result = new BoolValue(false);
    }
    
    @Override
    public void caseALambdaTerm(
            ALambdaTerm node) {

    	LambdaValue lambda = new LambdaValue(node, this.currentFrame);
    	this.result = lambda;
    	
    }

    
    @Override
    public void caseACallTerm(
            ACallTerm node) {

    	Frame oldFrame = this.currentFrame;
        
        Frame newFrame;
        
        oldFrame.setCallLocation(node.getId());

        LambdaInfo lambdaInfo = null;
        
        // évalue les arguments
        LinkedList<Value> args = new LinkedList<>();
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

        FunctionInfo functionInfo = this.functionTable
                .getFunctionInfo(node.getId().getText());
        
        boolean isLambdaExp = functionInfo == null;

        if(isLambdaExp){
        	Value lambdaExp = this.currentFrame.getVariable(node.getId());
        	Frame lexicalEnv = ((LambdaValue) lambdaExp).getEnvironment();
        	
        	lambdaInfo = new LambdaInfo((LambdaValue)lambdaExp);
        	newFrame = new Frame(oldFrame,lexicalEnv, lambdaInfo);
        	lambdaInfo.setParams(args, newFrame);

        }
        else{
        	newFrame = new Frame(oldFrame, functionInfo);
            functionInfo.setParams(args, newFrame);

        }
        

        // exécute la fonction

        this.currentFrame = newFrame;
        
        try {
        	if(isLambdaExp){
        		visit(lambdaInfo.getBlock());
        	}

        	else{
        		//System.out.println("visit function");
        		visit(functionInfo.getBlock());
        	}
        }
        catch (ReturnException e) {
        }
        
        // remet l'ancien Frame
        this.currentFrame = oldFrame;

        // oublie l'enplacement
        oldFrame.setCallLocation(null);

        // retourne la valeur
        this.result = newFrame.getReturnValue(node.getId());
    }
}