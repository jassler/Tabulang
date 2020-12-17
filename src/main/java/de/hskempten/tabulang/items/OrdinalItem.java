package de.hskempten.tabulang.items;

public class OrdinalItem implements LanguageItem {
    //"null"
    private String myString;
    private NumberItem myNumber;
    private QuotedStringItem myQuotedString;
    private TupelItem myTupel;

    LanguageItemType itemType;

    public OrdinalItem(String myString) {
        this.setMyString(myString);
        this.itemType = LanguageItemType.ORDINAL_NULL;
    }

    public OrdinalItem(NumberItem myNumber) {
        this.setMyNumber(myNumber);
        this.itemType = LanguageItemType.ORDINAL_NUMBER;
    }

    public OrdinalItem(QuotedStringItem myQuotedString) {
        this.setMyQuotedString(myQuotedString);
        this.itemType = LanguageItemType.ORDINAL_QUOTEDSTRING;
    }

    public OrdinalItem(TupelItem myTupel) {
        this.setMyTupel(myTupel);
        this.itemType = LanguageItemType.ORDINAL_TUPEL;
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

    @Override
    public LanguageItemType getLanguageItemType() {
        return itemType;
    }
}
