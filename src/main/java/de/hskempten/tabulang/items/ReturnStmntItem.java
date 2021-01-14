package de.hskempten.tabulang.items;

public class ReturnStmntItem extends LanguageItemAbstract implements StatementAnyItem {
    private TermItem myTerm;

    public ReturnStmntItem(TermItem myTerm) {
        super(LanguageItemType.ANYSTATEMENT_RETURN);
        this.setMyTerm(myTerm);
    }

    public TermItem getMyTerm() {
        return myTerm;
    }

    public void setMyTerm(TermItem myTerm) {
        this.myTerm = myTerm;
    }
}
