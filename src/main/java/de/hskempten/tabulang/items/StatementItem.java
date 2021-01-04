package de.hskempten.tabulang.items;

public class StatementItem implements StatementAnyItem {
    private LoopItem myLoop;
    private IfStmntItem myIfStmnt;
    private VarDefItem myVarDef;
    private BodyItem myBody;
    private FunCallItem myFunCall;

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

    public StatementItem(FunCallItem myFunCall) {
        this.setMyFunCall(myFunCall);
        this.itemType = LanguageItemType.STATEMENT_FUNCALL;
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

    public FunCallItem getMyFunCall() {
        return myFunCall;
    }

    public void setMyFunCall(FunCallItem myFunCall) {
        this.myFunCall = myFunCall;
    }

    @Override
    public LanguageItemType getLanguageItemType() {
        return itemType;
    }
}
