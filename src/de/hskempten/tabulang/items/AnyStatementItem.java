package de.hskempten.tabulang.items;

public class AnyStatementItem implements LanguageItem {
    private StatementItem myStatement;
    private ReturnStmntItem myReturnStmnt;
    private SetStmntItem mySetStmnt;
    private GroupStmntItem myGroupStmnt;

    public AnyStatementItem(StatementItem myStatement) {
        this.setMyStatement(myStatement);
    }

    public AnyStatementItem(ReturnStmntItem myReturnStmnt) {
        this.setMyReturnStmnt(myReturnStmnt);
    }

    public AnyStatementItem(SetStmntItem mySetStmnt) {
        this.setMySetStmnt(mySetStmnt);
    }

    public AnyStatementItem(GroupStmntItem myGroupStmnt) {
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
