package de.hskempten.tabulang.exampleTest.nonTerminal;

import de.hskempten.tabulang.exampleTest.Interpretation;
import de.hskempten.tabulang.exampleTest.terminal.IdentifierItem;
import de.hskempten.tabulang.items.*;
import de.hskempten.tabulang.symbols.CloseParenthesis;
import de.hskempten.tabulang.symbols.OpenParenthesis;


public class TermItem implements NonTerminalItem {
    private OpenParenthesis myOpenParenthesis;
    private TermItem myTerm;
    private CloseParenthesis myCloseParenthesis;
    private TermRItem myTermR;
    private IdentifierItem myIdentifier;
    private LoopItem myLoop;
    private FlipTItem myFlipT;
    private de.hskempten.tabulang.exampleTest.nonTerminal.OrdinalItem myOrdinal;
    private DirectionalTermItem myDirectionalTerm;
    private FunDefItem myFunDef;
    private AggregationTItem myAggregationT;
    private DistinctTItem myDistinctT;

    public TermItem(OpenParenthesis myOpenParenthesis, TermItem myTerm, CloseParenthesis myCloseParenthesis, TermRItem myTermR) {
        this.setMyOpenParenthesis(myOpenParenthesis);
        this.myTerm = myTerm;
        this.setMyCloseParenthesis(myCloseParenthesis);
        this.myTermR = myTermR;
    }

    public TermItem(TermRItem myTermR, de.hskempten.tabulang.exampleTest.nonTerminal.OrdinalItem myOrdinal) {
        this.setMyTermR(myTermR);
        this.setMyOrdinal(myOrdinal);
    }

    public TermItem(TermRItem myTermR, IdentifierItem myIdentifier) {
        this.myTermR = myTermR;
        this.myIdentifier = myIdentifier;
    }

    public TermItem getMyTerm() {
        return myTerm;
    }

    public void setMyTerm(TermItem myTerm) {
        this.myTerm = myTerm;
    }

    public TermRItem getMyTermR() {
        return myTermR;
    }

    public void setMyTermR(TermRItem myTermR) {
        this.myTermR = myTermR;
    }

    public LoopItem getMyLoop() {
        return myLoop;
    }

    public void setMyLoop(LoopItem myLoop) {
        this.myLoop = myLoop;
    }

    public FlipTItem getMyFlipT() {
        return myFlipT;
    }

    public void setMyFlipT(FlipTItem myFlipT) {
        this.myFlipT = myFlipT;
    }

    public de.hskempten.tabulang.exampleTest.nonTerminal.OrdinalItem getMyOrdinal() {
        return myOrdinal;
    }

    public void setMyOrdinal(OrdinalItem myOrdinal) {
        this.myOrdinal = myOrdinal;
    }

    public DirectionalTermItem getMyDirectionalTerm() {
        return myDirectionalTerm;
    }

    public void setMyDirectionalTerm(DirectionalTermItem myDirectionalTerm) {
        this.myDirectionalTerm = myDirectionalTerm;
    }

    public FunDefItem getMyFunDef() {
        return myFunDef;
    }

    public void setMyFunDef(FunDefItem myFunDef) {
        this.myFunDef = myFunDef;
    }

    public AggregationTItem getMyAggregationT() {
        return myAggregationT;
    }

    public void setMyAggregationT(AggregationTItem myAggregationT) {
        this.myAggregationT = myAggregationT;
    }

    public DistinctTItem getMyDistinctT() {
        return myDistinctT;
    }

    public void setMyDistinctT(DistinctTItem myDistinctT) {
        this.myDistinctT = myDistinctT;
    }

    public de.hskempten.tabulang.exampleTest.terminal.IdentifierItem getMyIdentifier() {
        return myIdentifier;
    }

    public void setMyIdentifier(de.hskempten.tabulang.exampleTest.terminal.IdentifierItem myIdentifier) {
        this.myIdentifier = myIdentifier;
    }

    public OpenParenthesis getMyOpenParenthesis() {
        return myOpenParenthesis;
    }

    public void setMyOpenParenthesis(OpenParenthesis myOpenParenthesis) {
        this.myOpenParenthesis = myOpenParenthesis;
    }

    public CloseParenthesis getMyCloseParenthesis() {
        return myCloseParenthesis;
    }

    public void setMyCloseParenthesis(CloseParenthesis myCloseParenthesis) {
        this.myCloseParenthesis = myCloseParenthesis;
    }

    @Override
    public void traverse(Interpretation i){
        if(myOrdinal != null){
            myOrdinal.traverse(i);
        }
        else if(myOpenParenthesis != null && myTerm != null && myCloseParenthesis != null && myTermR != null){
            myOpenParenthesis.addToStack(i);
            myTerm.traverse(i);
            myCloseParenthesis.addToStack(i);
            myTermR.traverse(i);
        }
        else if(myTermR != null && myIdentifier != null){
            myIdentifier.addToStack(i);
            myTermR.traverse(i);
        }
    }
}
