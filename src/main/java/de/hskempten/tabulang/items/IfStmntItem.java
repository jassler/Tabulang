package de.hskempten.tabulang.items;

import static de.hskempten.tabulang.items.LanguageItemType.IF_WITHELSE;
import static de.hskempten.tabulang.items.LanguageItemType.IF_WITHOUTELSE;

public class IfStmntItem extends LanguageItemAbstract {
    //'if'
    private PredItem myPred;
    private AnyStatementItem myAnyStatement;
    //'else'
    private AnyStatementItem myOptionalAnyStatement;

    public IfStmntItem(PredItem myPred, AnyStatementItem myAnyStatement) {
        super(IF_WITHOUTELSE);
        this.setMyPred(myPred);
        this.setMyAnyStatement(myAnyStatement);
    }

    public IfStmntItem(PredItem myPred, AnyStatementItem myAnyStatement, AnyStatementItem myOptionalAnyStatement) {
        super(IF_WITHELSE);
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
