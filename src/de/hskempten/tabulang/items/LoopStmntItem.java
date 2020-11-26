package de.hskempten.tabulang.items;

public class LoopStmntItem implements LanguageItem {
    private StatementItem myStatement;
    private SetStmntItem mySetStmnt;
    private GroupStmntItem myGroupStmnt;
    private MarkStmntItem myMarkStmnt; //';'

    public LoopStmntItem(StatementItem myStatement) {
        this.setMyStatement(myStatement);
    }

    public LoopStmntItem(SetStmntItem mySetStmnt) {
        this.setMySetStmnt(mySetStmnt);
    }

    public LoopStmntItem(GroupStmntItem myGroupStmnt) {
        this.setMyGroupStmnt(myGroupStmnt);
    }

    public LoopStmntItem(MarkStmntItem myMarkStmnt) {
        this.setMyMarkStmnt(myMarkStmnt);
    }

    public StatementItem getMyStatement() {
        return myStatement;
    }

    public void setMyStatement(StatementItem myStatement) {
        this.myStatement = myStatement;
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

    public MarkStmntItem getMyMarkStmnt() {
        return myMarkStmnt;
    }

    public void setMyMarkStmnt(MarkStmntItem myMarkStmnt) {
        this.myMarkStmnt = myMarkStmnt;
    }
}
