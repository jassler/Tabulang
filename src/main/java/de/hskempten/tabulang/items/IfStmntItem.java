package de.hskempten.tabulang.items;

import de.hskempten.tabulang.tokenizer.TextPosition;

public class IfStmntItem implements LanguageItem {
    //'if'
    private PredItem myPred;
    private AnyStatementItem myAnyStatement;
    //'else'
    private AnyStatementItem myOptionalAnyStatement;

    private LanguageItemType itemType;
    private TextPosition myTextPosition;

    public IfStmntItem(PredItem myPred, AnyStatementItem myAnyStatement) {
        this.setMyPred(myPred);
        this.setMyAnyStatement(myAnyStatement);
        this.itemType = LanguageItemType.IF_WITHOUTELSE;
    }

    public IfStmntItem(PredItem myPred, AnyStatementItem myAnyStatement, AnyStatementItem myOptionalAnyStatement) {
        this.setMyPred(myPred);
        this.setMyAnyStatement(myAnyStatement);
        this.setMyOptionalAnyStatement(myOptionalAnyStatement);
        this.itemType = LanguageItemType.IF_WITHELSE;
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

    @Override
    public TextPosition getTextPosition() {
        return myTextPosition;
    }

    @Override
    public void setTextPosition(TextPosition textPosition) {
        this.myTextPosition = textPosition;
    }

    @Override
    public LanguageItemType getLanguageItemType() {
        return itemType;
    }
}
