package de.hskempten.tabulang.exampleTestInterpretation;



import java.util.ArrayList;

public class ProgramItem {
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

    public Interpretation eval(Interpretation i){
        for(StatementItem si : myStatements){
            si.eval(i);
            System.out.println("Gerade evaluiert: " + this.getClass().getSimpleName());
            return i;
        }
        System.out.println(this.getClass().getSimpleName() + "hmmmm");
        return i;
    }
}
