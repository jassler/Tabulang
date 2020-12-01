package de.hskempten.tabulang.items;

public class OperatorItem implements LanguageItem {
    //+ - * / div mod ^
    private String myString;

    public OperatorItem(String myString) {
        this.setMyString(myString);
    }

    public String getMyString() {
        return myString;
    }

    public void setMyString(String myString) {
        this.myString = myString;
    }

    public int getPrecedence() {
        switch (myString) {
            case "+":
                return 0;
            case "-":
                return 1;
            case "*":
                return 2;
            case "mod":
                return 3;
            case "div":
                return 4;
            case "/":
                return 5;
            case "^":
                return 6;
            default:
                return -1;

        }
    }
}
