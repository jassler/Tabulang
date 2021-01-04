package de.hskempten.tabulang.items;

import de.hskempten.tabulang.tokenizer.TextPosition;

public class BinBoolItem implements LanguageItem {
    //"and"/"or"/"xor"/"iff"/"impl"
    private String myString;

    private LanguageItemType itemType;
    private TextPosition myTextPositon;

    public BinBoolItem(String myString) {
        this.setMyString(myString);
        this.itemType = switch (myString) {
            case "and" -> LanguageItemType.BINBOOL_AND;
            case "or" -> LanguageItemType.BINBOOL_OR;
            case "xor" -> LanguageItemType.BINBOOL_XOR;
            case "iff" -> LanguageItemType.BINBOOL_IFF;
            case "impl" -> LanguageItemType.BINBOOL_IMPL;
            default -> LanguageItemType.BINBOOL_NULL;
        };
    }

    public String getMyString() {
        return myString;
    }

    public void setMyString(String myString) {
        this.myString = myString;
    }

    @Override
    public TextPosition getTextPosition() {
        return myTextPositon;
    }

    @Override
    public void setTextPosition(TextPosition textPosition) {
        this.myTextPositon = textPosition;
    }

    @Override
    public LanguageItemType getLanguageItemType() {
        return itemType;
    }
}
