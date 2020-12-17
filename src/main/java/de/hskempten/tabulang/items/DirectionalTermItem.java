package de.hskempten.tabulang.items;

public class DirectionalTermItem implements LanguageItem {
    private String myString;
    private TermItem myTerm;

    private LanguageItemType itemType;

    public DirectionalTermItem(String myString, TermItem myTerm) {
        this.setMyString(myString);
        this.setMyTerm(myTerm);
        this.setLanguageItemType(switch (myString) {
            case "horizontal" -> LanguageItemType.TERM_DIRECTIONAL_H;
            case "vertical" -> LanguageItemType.TERM_DIRECTIONAL_V;
            default -> throw new IllegalStateException("Unexpected value: " + myString);
        });
    }

    public DirectionalTermItem(TermItem myTerm) {
        this.setMyTerm(myTerm);
    }

    public String getMyString() {
        return myString;
    }

    public void setMyString(String myString) {
        this.myString = myString;
    }

    public TermItem getMyTerm() {
        return myTerm;
    }

    public void setMyTerm(TermItem myTerm) {
        this.myTerm = myTerm;
    }

    public void setLanguageItemType(LanguageItemType itemType) {
        this.itemType = itemType;
    }

    @Override
    public LanguageItemType getLanguageItemType() {
        return itemType;
    }
}
