package de.hskempten.tabulang.items;

import java.util.LinkedList;

import static de.hskempten.tabulang.items.LanguageItemType.FUNCBODY_STATEMENTS;

public class FuncBodyItem extends LanguageItemAbstract implements LanguageItem {
    private LinkedList<StatementAnyItem> myStatements;

    public FuncBodyItem(LinkedList<StatementAnyItem> myStatements) {
        super(FUNCBODY_STATEMENTS);
        this.setMyStatements(myStatements);
    }

    public LinkedList<StatementAnyItem> getMyStatements() {
        return myStatements;
    }

    public void setMyStatements(LinkedList<StatementAnyItem> myStatements) {
        this.myStatements = myStatements;
    }
}
