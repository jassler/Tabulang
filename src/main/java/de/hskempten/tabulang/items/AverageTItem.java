package de.hskempten.tabulang.items;

import de.hskempten.tabulang.tokenizer.TextPosition;

public class AverageTItem implements LanguageItem {
    private IdentifierItem myIdentifier;
    private TermItem myTerm;
    private TextPosition myTextPosition;

    public AverageTItem(IdentifierItem myIdentifier, TermItem myTerm) {
        this.setMyIdentifier(myIdentifier);
        this.setMyTerm(myTerm);
    }

    public IdentifierItem getMyIdentifier() {
        return myIdentifier;
    }

    public void setMyIdentifier(IdentifierItem myIdentifier) {
        this.myIdentifier = myIdentifier;
    }

    public TermItem getMyTerm() {
        return myTerm;
    }

    public void setMyTerm(TermItem myTerm) {
        this.myTerm = myTerm;
    }

    @Override
    public TextPosition getTextPosition() {
        return myTextPosition;
    }

    @Override
    public void setTextPosition(TextPosition textPosition) {
        this.myTextPosition = textPosition;
    }

}
