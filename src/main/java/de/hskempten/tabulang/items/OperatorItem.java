package de.hskempten.tabulang.items;

import de.hskempten.tabulang.tokenizer.TextPosition;

public class OperatorItem implements LanguageItem {
    //+ - * / div mod ^
    private String myString;

    LanguageItemType itemType;
    private TextPosition myTextPositon;

    public OperatorItem(String myString) {
        this.setMyString(myString);
        this.itemType = switch (myString) {
            case "+" -> LanguageItemType.OPERATOR_ADD;
            case "-" -> LanguageItemType.OPERATOR_SUBTRACT;
            case "*" -> LanguageItemType.OPERATOR_MULTIPLY;
            case "/" -> LanguageItemType.OPERATOR_DIVIDE;
            case "div" -> LanguageItemType.OPERATOR_DIV;
            case "mod" -> LanguageItemType.OPERATOR_MOD;
            case "^" -> LanguageItemType.OPERATOR_POWER;
            default -> LanguageItemType.NULL;
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
