package de.hskempten.tabulang.items;

public class StatementItem implements LanguageItem {
    private LoopItem myLoop;
    private IfStmntItem myIfStmnt;
    private VarDefItem myVarDef;
    private BodyItem myBody;

    private LanguageItemType itemType;

    public StatementItem(LoopItem myLoop) {
        this.setMyLoop(myLoop);
        this.itemType = LanguageItemType.STATEMENT_LOOP;
    }

    public StatementItem(IfStmntItem myIfStmnt) {
        this.setMyIfStmnt(myIfStmnt);
        this.itemType = LanguageItemType.STATEMENT_IF;
    }

    public StatementItem(VarDefItem myVarDef) {
        this.setMyVarDef(myVarDef);
        this.itemType = LanguageItemType.STATEMENT_VARDEF;
    }

    public StatementItem(BodyItem myBody) {
        this.setMyBody(myBody);
        this.itemType = LanguageItemType.STATEMENT_BODY;
    }

    public LoopItem getMyLoop() {
        return myLoop;
    }

    public void setMyLoop(LoopItem myLoop) {
        this.myLoop = myLoop;
    }

    public IfStmntItem getMyIfStmnt() {
        return myIfStmnt;
    }

    public void setMyIfStmnt(IfStmntItem myIfStmnt) {
        this.myIfStmnt = myIfStmnt;
    }

    public VarDefItem getMyVarDef() {
        return myVarDef;
    }

    public void setMyVarDef(VarDefItem myVarDef) {
        this.myVarDef = myVarDef;
    }

    public BodyItem getMyBody() {
        return myBody;
    }

    public void setMyBody(BodyItem myBody) {
        this.myBody = myBody;
    }

    @Override
    public LanguageItemType getLanguageItemType() {
        return itemType;
    }
}
