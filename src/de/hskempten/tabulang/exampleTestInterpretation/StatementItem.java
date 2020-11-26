package de.hskempten.tabulang.exampleTestInterpretation;

import de.hskempten.tabulang.items.BodyItem;
import de.hskempten.tabulang.items.IfStmntItem;
import de.hskempten.tabulang.items.LoopItem;


public class StatementItem {
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

    public Interpretation eval(Interpretation i){
        if(myVarDef != null) {
            myVarDef.eval(i);
            System.out.println("Gerade evaluiert: " + this.getClass().getSimpleName());
            return i;
        }
        System.out.println(this.getClass().getSimpleName() + "hmmmm");
        return i;
    }
}
