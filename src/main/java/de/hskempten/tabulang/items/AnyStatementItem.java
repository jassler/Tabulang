package de.hskempten.tabulang.items;

import static de.hskempten.tabulang.items.LanguageItemType.*;

public class AnyStatementItem extends StatementAnyItem {
    private StatementItem myStatement;
    private ReturnStmntItem myReturnStmnt;
    private SetStmntItem mySetStmnt;
    private GroupStmntItem myGroupStmnt;

    public AnyStatementItem(StatementItem myStatement) {
        super(ANYSTATEMENT_STATEMENT);
        this.setMyStatement(myStatement);
    }

    public AnyStatementItem(ReturnStmntItem myReturnStmnt) {
        super(ANYSTATEMENT_RETURN);
        this.setMyReturnStmnt(myReturnStmnt);
    }

    public AnyStatementItem(SetStmntItem mySetStmnt) {
        super(ANYSTATEMENT_SET);
        this.setMySetStmnt(mySetStmnt);
    }

    public AnyStatementItem(GroupStmntItem myGroupStmnt) {
        super(ANYSTATEMENT_GROUP);
        this.setMyGroupStmnt(myGroupStmnt);
    }

    public StatementItem getMyStatement() {
        return myStatement;
    }

    public void setMyStatement(StatementItem myStatement) {
        this.myStatement = myStatement;
    }

    public ReturnStmntItem getMyReturnStmnt() {
        return myReturnStmnt;
    }

    public void setMyReturnStmnt(ReturnStmntItem myReturnStmnt) {
        this.myReturnStmnt = myReturnStmnt;
    }

    public SetStmntItem getMySetStmnt() {
        return mySetStmnt;
    }

    public void setMySetStmnt(SetStmntItem mySetStmnt) {
        this.mySetStmnt = mySetStmnt;
    }

    public GroupStmntItem getMyGroupStmnt() {
        return myGroupStmnt;
    }

    public void setMyGroupStmnt(GroupStmntItem myGroupStmnt) {
        this.myGroupStmnt = myGroupStmnt;
    }
}
