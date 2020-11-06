package de.hskempten.tabulang.items;

public class IfStmntItem {
    //'if'
    private PredItem myPred;
    private AnyStatementItem myAnyStatement;
    //'else'
    private AnyStatementItem myOptionalAnyStatement;

    public IfStmntItem(PredItem myPred, AnyStatementItem myAnyStatement) {
        this.setMyPred(myPred);
        this.setMyAnyStatement(myAnyStatement);
    }

    public IfStmntItem(PredItem myPred, AnyStatementItem myAnyStatement, AnyStatementItem myOptionalAnyStatement) {
        this.setMyPred(myPred);
        this.setMyAnyStatement(myAnyStatement);
        this.setMyOptionalAnyStatement(myOptionalAnyStatement);
    }

    public PredItem getMyPred() {
        return myPred;
    }

    public void setMyPred(PredItem myPred) {
        this.myPred = myPred;
    }

    public AnyStatementItem getMyAnyStatement() {
        return myAnyStatement;
    }

    public void setMyAnyStatement(AnyStatementItem myAnyStatement) {
        this.myAnyStatement = myAnyStatement;
    }

    public AnyStatementItem getMyOptionalAnyStatement() {
        return myOptionalAnyStatement;
    }

    public void setMyOptionalAnyStatement(AnyStatementItem myOptionalAnyStatement) {
        this.myOptionalAnyStatement = myOptionalAnyStatement;
    }
}
