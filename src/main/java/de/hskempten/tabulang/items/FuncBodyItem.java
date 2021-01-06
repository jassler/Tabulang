package de.hskempten.tabulang.items;

import de.hskempten.tabulang.tokenizer.TextPosition;

import java.util.LinkedList;

public class FuncBodyItem implements LanguageItem {
    private LinkedList<StatementAnyItem> myStatements;

    private LanguageItemType itemType;
    private TextPosition myTextPosition;


    public FuncBodyItem(LinkedList<StatementAnyItem> myStatements) {
        this.setMyStatements(myStatements);
        this.itemType = LanguageItemType.FUNCBODY_STATEMENTS;
    }


    public LinkedList<StatementAnyItem> getMyStatements() {
        return myStatements;
    }

    public void setMyStatements(LinkedList<StatementAnyItem> myStatements) {
        this.myStatements = myStatements;
    }

    @Override
    public TextPosition getTextPosition() {
        return myTextPosition;
    }

    @Override
    public void setTextPosition(TextPosition textPosition) {
        this.myTextPosition = textPosition;
    }

    @Override
    public LanguageItemType getLanguageItemType() {
        return itemType;
    }
}
