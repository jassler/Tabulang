package de.hskempten.tabulang.items;

import de.hskempten.tabulang.tokenizer.TextPosition;

public abstract class TermOrRItem extends LanguageItemAbstract {

    public TermRItem getMyTermR() {
        return null;
    }

    public TermOrRItem(LanguageItemType languageItemType) {
        super(languageItemType);
    }

    public TermOrRItem(LanguageItemType languageItemType, TextPosition textPosition) {
        super(languageItemType, textPosition);
    }
}
