package de.hskempten.tabulang.items;

import de.hskempten.tabulang.tokenizer.TextPosition;

public abstract class StatementAnyItem extends LanguageItemAbstract {

    public StatementAnyItem(LanguageItemType languageItemType) {
        super(languageItemType);
    }

    public StatementAnyItem(LanguageItemType languageItemType, TextPosition textPosition) {
        super(languageItemType, textPosition);
    }
}
