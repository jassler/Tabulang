package de.hskempten.tabulang.exampleTestInterpretation;


import de.hskempten.tabulang.items.QuotedStringItem;
import de.hskempten.tabulang.items.TupelItem;

public class OrdinalItem {
    private String myString; //"null"
    private NumberItem myNumber;
    private QuotedStringItem myQuotedString;
    private TupelItem myTupel;

    public OrdinalItem(String myString) {
        this.setMyString(myString);
    }

    public OrdinalItem(NumberItem myNumber) {
        this.setMyNumber(myNumber);
    }

    public OrdinalItem(QuotedStringItem myQuotedString) {
        this.setMyQuotedString(myQuotedString);
    }

    public OrdinalItem(TupelItem myTupel) {
        this.setMyTupel(myTupel);
    }

    public String getMyString() {
        return myString;
    }

    public void setMyString(String myString) {
        this.myString = myString;
    }

    public NumberItem getMyNumber() {
        return myNumber;
    }

    public void setMyNumber(NumberItem myNumber) {
        this.myNumber = myNumber;
    }

    public QuotedStringItem getMyQuotedString() {
        return myQuotedString;
    }

    public void setMyQuotedString(QuotedStringItem myQuotedString) {
        this.myQuotedString = myQuotedString;
    }

    public TupelItem getMyTupel() {
        return myTupel;
    }

    public void setMyTupel(TupelItem myTupel) {
        this.myTupel = myTupel;
    }

    public Interpretation eval(Interpretation i){
        if(myNumber != null){
            myNumber.eval(i);
            System.out.println("Gerade evaluiert: " + this.getClass().getSimpleName());
            return i;

        }
        System.out.println(this.getClass().getSimpleName() + " hmmmm");
        return i;
    }
}
