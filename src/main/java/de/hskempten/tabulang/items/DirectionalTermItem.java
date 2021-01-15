package de.hskempten.tabulang.items;

import static de.hskempten.tabulang.items.LanguageItemType.TERM_DIRECTIONAL_H;
import static de.hskempten.tabulang.items.LanguageItemType.TERM_DIRECTIONAL_V;

public class DirectionalTermItem extends LanguageItemAbstract {
    private String myString;
    private TermItem myTerm;

    public DirectionalTermItem(String myString, TermItem myTerm) {
        super(switch (myString) {
            case "horizontal" -> TERM_DIRECTIONAL_H;
            case "vertical" -> TERM_DIRECTIONAL_V;
            default -> throw new IllegalStateException("Unexpected value: " + myString);
        });
        this.setMyString(myString);
        this.setMyTerm(myTerm);
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
}
