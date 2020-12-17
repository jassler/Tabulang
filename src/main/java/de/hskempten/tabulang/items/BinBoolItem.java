package de.hskempten.tabulang.items;

public class BinBoolItem implements LanguageItem {
    //"and"/"or"/"xor"/"iff"/"impl"
    private String myString;

    private LanguageItemType itemType;

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
    public LanguageItemType getLanguageItemType() {
        return itemType;
    }
}
