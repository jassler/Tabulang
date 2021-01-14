package de.hskempten.tabulang.items;

import static de.hskempten.tabulang.items.LanguageItemType.*;

public class BinBoolItem extends LanguageItemAbstract implements LanguageItem {
    //"and"/"or"/"xor"/"iff"/"impl"
    private String myString;

    public BinBoolItem(String myString) {
        super(switch (myString) {
            case "and" -> BINBOOL_AND;
            case "or" -> BINBOOL_OR;
            case "xor" -> BINBOOL_XOR;
            case "iff" -> BINBOOL_IFF;
            case "impl" -> BINBOOL_IMPL;
            default -> BINBOOL_NULL;
        });
        this.setMyString(myString);
    }

    public String getMyString() {
        return myString;
    }

    public void setMyString(String myString) {
        this.myString = myString;
    }
}
