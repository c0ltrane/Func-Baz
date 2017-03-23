/* This file was generated by SableCC (http://www.sablecc.org/). */

package baz.syntax.analysis;

import java.util.*;
import baz.syntax.node.*;

public class AnalysisAdapter implements Analysis
{
    private Hashtable<Node,Object> in;
    private Hashtable<Node,Object> out;

    @Override
    public Object getIn(Node node)
    {
        if(this.in == null)
        {
            return null;
        }

        return this.in.get(node);
    }

    @Override
    public void setIn(Node node, Object o)
    {
        if(this.in == null)
        {
            this.in = new Hashtable<Node,Object>(1);
        }

        if(o != null)
        {
            this.in.put(node, o);
        }
        else
        {
            this.in.remove(node);
        }
    }

    @Override
    public Object getOut(Node node)
    {
        if(this.out == null)
        {
            return null;
        }

        return this.out.get(node);
    }

    @Override
    public void setOut(Node node, Object o)
    {
        if(this.out == null)
        {
            this.out = new Hashtable<Node,Object>(1);
        }

        if(o != null)
        {
            this.out.put(node, o);
        }
        else
        {
            this.out.remove(node);
        }
    }

    @Override
    public void caseStart(Start node)
    {
        defaultCase(node);
    }

    @Override
    public void caseAProgram(AProgram node)
    {
        defaultCase(node);
    }

    @Override
    public void caseAFunc(AFunc node)
    {
        defaultCase(node);
    }

    @Override
    public void caseAParams(AParams node)
    {
        defaultCase(node);
    }

    @Override
    public void caseAAdditionalParam(AAdditionalParam node)
    {
        defaultCase(node);
    }

    @Override
    public void caseAParam(AParam node)
    {
        defaultCase(node);
    }

    @Override
    public void caseAReturnType(AReturnType node)
    {
        defaultCase(node);
    }

    @Override
    public void caseABoolType(ABoolType node)
    {
        defaultCase(node);
    }

    @Override
    public void caseAIntType(AIntType node)
    {
        defaultCase(node);
    }

    @Override
    public void caseAStringType(AStringType node)
    {
        defaultCase(node);
    }

    @Override
    public void caseABlock(ABlock node)
    {
        defaultCase(node);
    }

    @Override
    public void caseADeclarationInst(ADeclarationInst node)
    {
        defaultCase(node);
    }

    @Override
    public void caseAExpInst(AExpInst node)
    {
        defaultCase(node);
    }

    @Override
    public void caseAIfInst(AIfInst node)
    {
        defaultCase(node);
    }

    @Override
    public void caseAWhileInst(AWhileInst node)
    {
        defaultCase(node);
    }

    @Override
    public void caseAPrintInst(APrintInst node)
    {
        defaultCase(node);
    }

    @Override
    public void caseAReturnInst(AReturnInst node)
    {
        defaultCase(node);
    }

    @Override
    public void caseABlockInst(ABlockInst node)
    {
        defaultCase(node);
    }

    @Override
    public void caseAElsePart(AElsePart node)
    {
        defaultCase(node);
    }

    @Override
    public void caseAAssignExp(AAssignExp node)
    {
        defaultCase(node);
    }

    @Override
    public void caseASimpleExp(ASimpleExp node)
    {
        defaultCase(node);
    }

    @Override
    public void caseAOrChoice(AOrChoice node)
    {
        defaultCase(node);
    }

    @Override
    public void caseASimpleChoice(ASimpleChoice node)
    {
        defaultCase(node);
    }

    @Override
    public void caseAAndConjunction(AAndConjunction node)
    {
        defaultCase(node);
    }

    @Override
    public void caseASimpleConjunction(ASimpleConjunction node)
    {
        defaultCase(node);
    }

    @Override
    public void caseAEqEquality(AEqEquality node)
    {
        defaultCase(node);
    }

    @Override
    public void caseANeqEquality(ANeqEquality node)
    {
        defaultCase(node);
    }

    @Override
    public void caseASimpleEquality(ASimpleEquality node)
    {
        defaultCase(node);
    }

    @Override
    public void caseALtRelative(ALtRelative node)
    {
        defaultCase(node);
    }

    @Override
    public void caseALteqRelative(ALteqRelative node)
    {
        defaultCase(node);
    }

    @Override
    public void caseAGtRelative(AGtRelative node)
    {
        defaultCase(node);
    }

    @Override
    public void caseAGteqRelative(AGteqRelative node)
    {
        defaultCase(node);
    }

    @Override
    public void caseASimpleRelative(ASimpleRelative node)
    {
        defaultCase(node);
    }

    @Override
    public void caseAAddAdditive(AAddAdditive node)
    {
        defaultCase(node);
    }

    @Override
    public void caseASubAdditive(ASubAdditive node)
    {
        defaultCase(node);
    }

    @Override
    public void caseASimpleAdditive(ASimpleAdditive node)
    {
        defaultCase(node);
    }

    @Override
    public void caseAMulFactor(AMulFactor node)
    {
        defaultCase(node);
    }

    @Override
    public void caseADivFactor(ADivFactor node)
    {
        defaultCase(node);
    }

    @Override
    public void caseAModFactor(AModFactor node)
    {
        defaultCase(node);
    }

    @Override
    public void caseASimpleFactor(ASimpleFactor node)
    {
        defaultCase(node);
    }

    @Override
    public void caseAPosUnary(APosUnary node)
    {
        defaultCase(node);
    }

    @Override
    public void caseANegUnary(ANegUnary node)
    {
        defaultCase(node);
    }

    @Override
    public void caseANotUnary(ANotUnary node)
    {
        defaultCase(node);
    }

    @Override
    public void caseASimpleUnary(ASimpleUnary node)
    {
        defaultCase(node);
    }

    @Override
    public void caseAVarTerm(AVarTerm node)
    {
        defaultCase(node);
    }

    @Override
    public void caseANumTerm(ANumTerm node)
    {
        defaultCase(node);
    }

    @Override
    public void caseAStringTerm(AStringTerm node)
    {
        defaultCase(node);
    }

    @Override
    public void caseATrueTerm(ATrueTerm node)
    {
        defaultCase(node);
    }

    @Override
    public void caseAFalseTerm(AFalseTerm node)
    {
        defaultCase(node);
    }

    @Override
    public void caseAParTerm(AParTerm node)
    {
        defaultCase(node);
    }

    @Override
    public void caseACallTerm(ACallTerm node)
    {
        defaultCase(node);
    }

    @Override
    public void caseAArgs(AArgs node)
    {
        defaultCase(node);
    }

    @Override
    public void caseAAdditionalArg(AAdditionalArg node)
    {
        defaultCase(node);
    }

    @Override
    public void caseTSemi(TSemi node)
    {
        defaultCase(node);
    }

    @Override
    public void caseTAssign(TAssign node)
    {
        defaultCase(node);
    }

    @Override
    public void caseTEq(TEq node)
    {
        defaultCase(node);
    }

    @Override
    public void caseTNeq(TNeq node)
    {
        defaultCase(node);
    }

    @Override
    public void caseTLt(TLt node)
    {
        defaultCase(node);
    }

    @Override
    public void caseTGt(TGt node)
    {
        defaultCase(node);
    }

    @Override
    public void caseTLteq(TLteq node)
    {
        defaultCase(node);
    }

    @Override
    public void caseTGteq(TGteq node)
    {
        defaultCase(node);
    }

    @Override
    public void caseTPlus(TPlus node)
    {
        defaultCase(node);
    }

    @Override
    public void caseTMinus(TMinus node)
    {
        defaultCase(node);
    }

    @Override
    public void caseTLBrace(TLBrace node)
    {
        defaultCase(node);
    }

    @Override
    public void caseTRBrace(TRBrace node)
    {
        defaultCase(node);
    }

    @Override
    public void caseTLPar(TLPar node)
    {
        defaultCase(node);
    }

    @Override
    public void caseTRPar(TRPar node)
    {
        defaultCase(node);
    }

    @Override
    public void caseTAnd(TAnd node)
    {
        defaultCase(node);
    }

    @Override
    public void caseTOr(TOr node)
    {
        defaultCase(node);
    }

    @Override
    public void caseTNot(TNot node)
    {
        defaultCase(node);
    }

    @Override
    public void caseTStar(TStar node)
    {
        defaultCase(node);
    }

    @Override
    public void caseTSlash(TSlash node)
    {
        defaultCase(node);
    }

    @Override
    public void caseTPercent(TPercent node)
    {
        defaultCase(node);
    }

    @Override
    public void caseTComma(TComma node)
    {
        defaultCase(node);
    }

    @Override
    public void caseTColon(TColon node)
    {
        defaultCase(node);
    }

    @Override
    public void caseTDo(TDo node)
    {
        defaultCase(node);
    }

    @Override
    public void caseTElse(TElse node)
    {
        defaultCase(node);
    }

    @Override
    public void caseTFun(TFun node)
    {
        defaultCase(node);
    }

    @Override
    public void caseTIf(TIf node)
    {
        defaultCase(node);
    }

    @Override
    public void caseTPrint(TPrint node)
    {
        defaultCase(node);
    }

    @Override
    public void caseTReturn(TReturn node)
    {
        defaultCase(node);
    }

    @Override
    public void caseTThen(TThen node)
    {
        defaultCase(node);
    }

    @Override
    public void caseTWhile(TWhile node)
    {
        defaultCase(node);
    }

    @Override
    public void caseTBool(TBool node)
    {
        defaultCase(node);
    }

    @Override
    public void caseTInt(TInt node)
    {
        defaultCase(node);
    }

    @Override
    public void caseTString(TString node)
    {
        defaultCase(node);
    }

    @Override
    public void caseTTrue(TTrue node)
    {
        defaultCase(node);
    }

    @Override
    public void caseTFalse(TFalse node)
    {
        defaultCase(node);
    }

    @Override
    public void caseTVar(TVar node)
    {
        defaultCase(node);
    }

    @Override
    public void caseTStringLiteral(TStringLiteral node)
    {
        defaultCase(node);
    }

    @Override
    public void caseTId(TId node)
    {
        defaultCase(node);
    }

    @Override
    public void caseTNumber(TNumber node)
    {
        defaultCase(node);
    }

    @Override
    public void caseTBlank(TBlank node)
    {
        defaultCase(node);
    }

    @Override
    public void caseTComment(TComment node)
    {
        defaultCase(node);
    }

    @Override
    public void caseEOF(EOF node)
    {
        defaultCase(node);
    }

    @Override
    public void caseInvalidToken(InvalidToken node)
    {
        defaultCase(node);
    }

    public void defaultCase(@SuppressWarnings("unused") Node node)
    {
        // do nothing
    }
}