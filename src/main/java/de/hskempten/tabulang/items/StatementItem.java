package de.hskempten.tabulang.items;

public class StatementItem implements AnyStatementAST {

    private StatementAST myStatement;

    public StatementItem(StatementAST statement) {
        myStatement = statement;
    }

    public StatementAST getStatement() {
        return myStatement;
    }

    public void setStatement(StatementAST statement) {
        this.myStatement = myStatement;
    }
}

