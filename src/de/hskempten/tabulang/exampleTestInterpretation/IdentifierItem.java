package de.hskempten.tabulang.exampleTestInterpretation;

public class IdentifierItem {
    private String myString; //[a-zA-Z][0-9a-zA-Z]*

    public IdentifierItem(String myString) {
        this.myString = myString;
    }

    public String getMyString() {
        return myString;
    }

    public void setMyString(String myString) {
        this.myString = myString;
    }

    public String eval(Interpretation i){
        System.out.println("Gerade evaluiert: " + this.getClass().getSimpleName() + ". Interpretation: " + myString);
        return myString;
    }
}
