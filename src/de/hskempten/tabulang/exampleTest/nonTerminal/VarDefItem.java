package de.hskempten.tabulang.exampleTest.nonTerminal;


import de.hskempten.tabulang.exampleTest.Interpretable;
import de.hskempten.tabulang.exampleTest.Interpretation;
import de.hskempten.tabulang.exampleTest.terminal.IdentifierItem;
import de.hskempten.tabulang.items.ProceduralFItem;
import de.hskempten.tabulang.symbols.ColonEquals;

import java.util.Iterator;


public class VarDefItem implements NonTerminalItem, Interpretable {
    private IdentifierItem myIdentifier;
    private ColonEquals myColonEquals;
    private TermItem myTerm;
    //';'
    private ProceduralFItem myProceduralF;

    public VarDefItem(IdentifierItem myIdentifier, TermItem myTerm) {
        this.setMyIdentifier(myIdentifier);
        this.setMyTerm(myTerm);
    }

    public VarDefItem(IdentifierItem myIdentifier, ColonEquals myColonEquals, TermItem myTerm) {
        this.myIdentifier = myIdentifier;
        this.setMyColonEquals(myColonEquals);
        this.myTerm = myTerm;
    }

    public VarDefItem(ProceduralFItem myProceduralF) {
        this.setMyProceduralF(myProceduralF);
    }

    public IdentifierItem getMyIdentifier() {
        return myIdentifier;
    }

    public void setMyIdentifier(IdentifierItem myIdentifier) {
        this.myIdentifier = myIdentifier;
    }

    public TermItem getMyTerm() {
        return myTerm;
    }

    public void setMyTerm(TermItem myTerm) {
        this.myTerm = myTerm;
    }

    public ProceduralFItem getMyProceduralF() {
        return myProceduralF;
    }

    public void setMyProceduralF(ProceduralFItem myProceduralF) {
        this.myProceduralF = myProceduralF;
    }

    public ColonEquals getMyColonEquals() {
        return myColonEquals;
    }

    public void setMyColonEquals(ColonEquals myColonEquals) {
        this.myColonEquals = myColonEquals;
    }

    @Override
    public void traverse(Interpretation i){
        if(myIdentifier != null && myTerm != null){

            //Identifier := Term;
            //Zuweisung von rechts evaluieren, deshalb zuerst myTerm (rechte Seite) durchlaufen
            myTerm.traverse(i);
            myIdentifier.addToStack(i);

            /*myIdentifier.addToStack(i);
            myColonEquals.addToStack(i);
            myTerm.traverse(i);*/


            Iterator itOperand = i.getOperandStack().iterator();
            Iterator itType = i.getTypeStack().iterator();
            while (itOperand.hasNext() && itType.hasNext()) {
                System.out.println(itOperand.next() + "   " + itType.next());
                //it.remove(); // avoids a ConcurrentModificationException
            }
            //System.out.println("------------");
            interpret(i);
        }
    }

    @Override
    public void interpret(Interpretation i) {
        //AssignmentItem: Identifier := Term
        i.getEnvironment().put(i.getOperandStack().pop(), i.getOperandStack().pop());
        i.getTypeStack().pop();
        i.getTypeStack().pop();
    }
}
