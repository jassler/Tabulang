package de.hskempten.tabulang.items;

import static de.hskempten.tabulang.items.LanguageItemType.*;

public class OperatorItem extends LanguageItemAbstract implements LanguageItem {
    //+ - * / div mod ^
    private String myString;

    public OperatorItem(String myString) {
        super(switch (myString) {
            case "+" -> OPERATOR_ADD;
            case "-" -> OPERATOR_SUBTRACT;
            case "*" -> OPERATOR_MULTIPLY;
            case "/" -> OPERATOR_DIVIDE;
            case "div" -> OPERATOR_DIV;
            case "mod" -> OPERATOR_MOD;
            case "^" -> OPERATOR_POWER;
            default -> NULL;
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
