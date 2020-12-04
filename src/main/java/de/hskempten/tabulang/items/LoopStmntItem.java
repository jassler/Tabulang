package de.hskempten.tabulang.items;

public class LoopStmntItem implements LanguageItem {
    private StatementItem myStatement;
    private SetStmntItem mySetStmnt;
    private GroupStmntItem myGroupStmnt;
    private MarkStmntItem myMarkStmnt; //';'

    private LanguageItemType itemType;

    public LoopStmntItem(StatementItem myStatement) {
        this.setMyStatement(myStatement);
        this.itemType = LanguageItemType.LOOP_STMT_STATEMENT;
    }

    public LoopStmntItem(SetStmntItem mySetStmnt) {
        this.setMySetStmnt(mySetStmnt);
        this.itemType = LanguageItemType.LOOP_STMT_SET;
    }

    public LoopStmntItem(GroupStmntItem myGroupStmnt) {
        this.setMyGroupStmnt(myGroupStmnt);
        this.itemType = LanguageItemType.LOOP_STMT_GROUP;
    }

    public LoopStmntItem(MarkStmntItem myMarkStmnt) {
        this.setMyMarkStmnt(myMarkStmnt);
        this.itemType = LanguageItemType.LOOP_STMT_MARK;
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

    @Override
    public LanguageItemType getLanguageItemType() {
        return itemType;
    }
}