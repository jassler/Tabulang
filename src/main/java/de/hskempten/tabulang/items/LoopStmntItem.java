package de.hskempten.tabulang.items;

import static de.hskempten.tabulang.items.LanguageItemType.*;

public class LoopStmntItem extends LanguageItemAbstract implements StatementAnyItem {
    private StatementItem myStatement;
    private SetStmntItem mySetStmnt;
    private GroupStmntItem myGroupStmnt;
    private MarkStmntItem myMarkStmnt; //';'

    public LoopStmntItem(StatementItem myStatement) {
        super(LOOP_STMT_STATEMENT);
        this.setMyStatement(myStatement);
    }

    public LoopStmntItem(SetStmntItem mySetStmnt) {
        super(LOOP_STMT_SET);
        this.setMySetStmnt(mySetStmnt);
    }

    public LoopStmntItem(GroupStmntItem myGroupStmnt) {
        super(LOOP_STMT_GROUP);
        this.setMyGroupStmnt(myGroupStmnt);
    }

    public LoopStmntItem(MarkStmntItem myMarkStmnt) {
        super(LOOP_STMT_MARK);
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
