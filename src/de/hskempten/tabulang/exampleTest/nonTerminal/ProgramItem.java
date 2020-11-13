package de.hskempten.tabulang.exampleTest.nonTerminal;



import de.hskempten.tabulang.exampleTest.Interpretation;

import java.util.ArrayList;

public class ProgramItem implements NonTerminalItem {
    private ArrayList<StatementItem> myStatements;

    public ProgramItem(ArrayList<StatementItem> myStatements) {
        this.myStatements = myStatements;
    }

    public ArrayList<StatementItem> getMyStatements() {
        return myStatements;
    }

    public void setMyStatements(ArrayList<StatementItem> myStatements) {
        this.myStatements = myStatements;
    }

    @Override
    public void traverse(Interpretation i) {
        for(StatementItem si : myStatements){
            System.out.println("-------------------");
            System.out.println("Neues Statement: ");
            si.traverse(i);
        }
    }
}
