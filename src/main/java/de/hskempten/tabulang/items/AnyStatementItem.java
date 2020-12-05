package de.hskempten.tabulang.items;

public class AnyStatementItem implements StatementAnyItem {
    private StatementItem myStatement;
    private ReturnStmntItem myReturnStmnt;
    private SetStmntItem mySetStmnt;
    private GroupStmntItem myGroupStmnt;
    private LanguageItemType itemType;

    public AnyStatementItem(StatementItem myStatement) {
        this.setMyStatement(myStatement);
        this.itemType = LanguageItemType.ANYSTATEMENT_STATEMENT;
    }

    public AnyStatementItem(ReturnStmntItem myReturnStmnt) {
        this.setMyReturnStmnt(myReturnStmnt);
        this.itemType = LanguageItemType.ANYSTATEMENT_RETURN;
    }

    public AnyStatementItem(SetStmntItem mySetStmnt) {
        this.setMySetStmnt(mySetStmnt);
        this.itemType = LanguageItemType.ANYSTATEMENT_SET;
    }

    public AnyStatementItem(GroupStmntItem myGroupStmnt) {
        this.setMyGroupStmnt(myGroupStmnt);
        this.itemType = LanguageItemType.ANYSTATEMENT_GROUP;
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

    @Override
    public LanguageItemType getLanguageItemType() {
        return itemType;
    }
}
