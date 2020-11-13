package de.hskempten.tabulang.exampleTest.nonTerminal;

import de.hskempten.tabulang.arithmetic.*;
import de.hskempten.tabulang.exampleTest.Interpretation;
import de.hskempten.tabulang.exampleTest.terminal.OperatorItem;
import de.hskempten.tabulang.helper.CreateArithmeticItem;
import de.hskempten.tabulang.helper.OperatorPrecedence;
import de.hskempten.tabulang.items.MarkStmntItem;
import de.hskempten.tabulang.items.PredItem;
import de.hskempten.tabulang.items.TupelItem;

import java.util.ArrayList;

public class TermRItem implements NonTerminalItem {
    private ArrayList<PredItem> myPreds;
    private TermRItem myTermR;
    private TermItem myTerm;
    private OperatorItem myOperator;
    private MarkStmntItem myMarkStmnt;
    private TupelItem myTupel;

    private String myString; //"filter", "intersect", "unite", "."

    public TermRItem(ArrayList<PredItem> myPreds, TermRItem myTermR, String myString) {
        this.myPreds = myPreds;
        this.myTermR = myTermR;
        this.setMyString(myString);
    }

    public TermRItem(TermRItem myTermR, TermItem myTerm, String myString) {
        this.myTermR = myTermR;
        this.myTerm = myTerm;
        this.setMyString(myString);
    }

    public TermRItem(ArrayList<PredItem> myPreds, TermRItem myTermR) {
        this.setMyPreds(myPreds);
        this.myTermR = myTermR;
    }

    public TermRItem(TermRItem myTermR, TermItem myTerm) {
        this.setMyTermR(myTermR);
        this.setMyTerm(myTerm);
    }

    public TermRItem(TermRItem myTermR, TermItem myTerm, OperatorItem myOperator) {
        this.setMyTermR(myTermR);
        this.setMyTerm(myTerm);
        this.setMyOperator(myOperator);
    }

    public TermRItem(TermRItem myTermR, MarkStmntItem myMarkStmnt) {
        this.setMyTermR(myTermR);
        this.setMyMarkStmnt(myMarkStmnt);
    }

    public TermRItem(TermRItem myTermR, TupelItem myTupel) {
        this.setMyTermR(myTermR);
        this.setMyTupel(myTupel);
    }

    public TermRItem() {
    }

    public TermRItem getMyTermR() {
        return myTermR;
    }

    public void setMyTermR(TermRItem myTermR) {
        this.myTermR = myTermR;
    }

    public TermItem getMyTerm() {
        return myTerm;
    }

    public void setMyTerm(TermItem myTerm) {
        this.myTerm = myTerm;
    }

    public OperatorItem getMyOperator() {
        return myOperator;
    }

    public void setMyOperator(OperatorItem myOperator) {
        this.myOperator = myOperator;
    }

    public MarkStmntItem getMyMarkStmnt() {
        return myMarkStmnt;
    }

    public void setMyMarkStmnt(MarkStmntItem myMarkStmnt) {
        this.myMarkStmnt = myMarkStmnt;
    }

    public TupelItem getMyTupel() {
        return myTupel;
    }

    public void setMyTupel(TupelItem myTupel) {
        this.myTupel = myTupel;
    }

    public ArrayList<PredItem> getMyPreds() {
        return myPreds;
    }

    public void setMyPreds(ArrayList<PredItem> myPreds) {
        this.myPreds = myPreds;
    }

    public String getMyString() {
        return myString;
    }

    public void setMyString(String myString) {
        this.myString = myString;
    }


    @Override
    public void traverse(Interpretation i) {
        if (myPreds == null && myTermR == null && myMarkStmnt == null && myTerm == null && myOperator == null && myTupel == null) {
            //Epsilon
        } else if (myOperator != null) {
            if (!i.getOperatorStack().empty()) {
                String topOfStack = i.getOperatorStack().peek();
                if (OperatorPrecedence.getInstance().precedence(myOperator.getMyString()) <= OperatorPrecedence.getInstance().precedence(topOfStack)) {
                    CreateArithmeticItem.getInstance().createCorrespondingItem(i);
                    myOperator.addToStack(i);
                    myTerm.traverse(i);
                    myTermR.traverse(i);
                } else {
                    myOperator.addToStack(i);
                    myTerm.traverse(i);
                    myTermR.traverse(i);
                    //CreateArithmeticItem.getInstance().createCorrespondingItem(i);
                }
            } else {
                myOperator.addToStack(i);
                myTerm.traverse(i);
                myTermR.traverse(i);
                CreateArithmeticItem.getInstance().createCorrespondingItem(i);
            }
        }
    }
}
