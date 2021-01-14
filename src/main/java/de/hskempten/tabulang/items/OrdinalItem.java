package de.hskempten.tabulang.items;

import static de.hskempten.tabulang.items.LanguageItemType.*;

public class OrdinalItem extends LanguageItemAbstract implements LanguageItem {
    //"null"
    private String myString;
    private NumberItem myNumber;
    private QuotedStringItem myQuotedString;
    private TupelItem myTupel;

    public OrdinalItem(String myString) {
        super(ORDINAL_NULL);
        this.setMyString(myString);
    }

    public OrdinalItem(NumberItem myNumber) {
        super(ORDINAL_NUMBER);
        this.setMyNumber(myNumber);
    }

    public OrdinalItem(QuotedStringItem myQuotedString) {
        super(ORDINAL_QUOTEDSTRING);
        this.setMyQuotedString(myQuotedString);
    }

    public OrdinalItem(TupelItem myTupel) {
        super(ORDINAL_TUPEL);
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
}
