package de.hskempten.tabulang.items;

public class ReturnStmntItem implements StatementAnyItem {
    private TermItem myTerm;
    private LanguageItemType itemType = LanguageItemType.ANYSTATEMENT_RETURN;

    public ReturnStmntItem(TermItem myTerm) {
        this.setMyTerm(myTerm);
    }

    public TermItem getMyTerm() {
        return myTerm;
    }

    public void setMyTerm(TermItem myTerm) {
        this.myTerm = myTerm;
    }

    @Override
    public LanguageItemType getLanguageItemType() {
        return itemType;
    }
}
