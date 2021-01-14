package de.hskempten.tabulang.items;

import de.hskempten.tabulang.tokenizer.TextPosition;

import static de.hskempten.tabulang.items.LanguageItemType.*;

public class StatementItem extends LanguageItemAbstract implements StatementAnyItem {
    private LoopItem myLoop;
    private IfStmntItem myIfStmnt;
    private VarDefItem myVarDef;
    private BodyItem myBody;
    private FunCallItem myFunCall;

    public StatementItem(LoopItem myLoop) {
        super(STATEMENT_LOOP);
        this.setMyLoop(myLoop);
    }

    public StatementItem(IfStmntItem myIfStmnt) {
        super(STATEMENT_IF);
        this.setMyIfStmnt(myIfStmnt);
    }

    public StatementItem(VarDefItem myVarDef) {
        super(STATEMENT_VARDEF);
        this.setMyVarDef(myVarDef);
    }

    public StatementItem(BodyItem myBody) {
        super(STATEMENT_BODY);
        this.setMyBody(myBody);
    }

    public StatementItem(FunCallItem myFunCall) {
        super(STATEMENT_FUNCALL);
        this.setMyFunCall(myFunCall);
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
}
