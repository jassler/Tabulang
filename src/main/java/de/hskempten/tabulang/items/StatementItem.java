package de.hskempten.tabulang.items;

public class StatementItem implements LanguageItem {
    private LoopItem myLoop;
    private IfStmntItem myIfStmnt;
    private VarDefItem myVarDef;
    private BodyItem myBody;

    public StatementItem(LoopItem myLoop) {
        this.setMyLoop(myLoop);
    }

    public StatementItem(IfStmntItem myIfStmnt) {
        this.setMyIfStmnt(myIfStmnt);
    }

    public StatementItem(VarDefItem myVarDef) {
        this.setMyVarDef(myVarDef);
    }

    public StatementItem(BodyItem myBody) {
        this.setMyBody(myBody);
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
}
