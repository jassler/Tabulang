package de.hskempten.tabulang.items;

public class AnyStatementItem implements LanguageItem {
    private AnyStatementAST myAnyStatement;

    public AnyStatementItem(AnyStatementAST myAnyStatement) {
        this.myAnyStatement = myAnyStatement;
    }

    public AnyStatementAST getAnyStatement() {
        return myAnyStatement;
    }

    public void setMyAnyStatement(AnyStatementAST anyStatement) {
        this.myAnyStatement = anyStatement;
    }
}
