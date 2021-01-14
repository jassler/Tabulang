package de.hskempten.tabulang.items;

import de.hskempten.tabulang.tokenizer.ParseTimeException;
import de.hskempten.tabulang.tokenizer.TextPosition;

public abstract class LanguageItemAbstract {

    private LanguageItemType languageItemType;
    private TextPosition textPosition;

    public LanguageItemAbstract(LanguageItemType languageItemType, TextPosition textPosition) {
        this.setLanguageItemType(languageItemType);
        this.setTextPosition(textPosition);
    }

    public LanguageItemAbstract() {
        this.setLanguageItemType(LanguageItemType.NULL);
        this.setTextPosition(null);
    }

    public LanguageItemAbstract(LanguageItemType languageItemType) {
        this.setLanguageItemType(languageItemType);
        this.setTextPosition(null);
    }

    public LanguageItemType getLanguageItemType() {
        return languageItemType;
    }

    public void setLanguageItemType(LanguageItemType languageItemType) {
        this.languageItemType = languageItemType;
    }

    public TextPosition getTextPosition() {
        if (this.textPosition == null) {
            try {
                throw new ParseTimeException("Missing textPosition");
            } catch (ParseTimeException e) {
                e.printStackTrace();
            }
        }
        return this.textPosition;
    }

    public void setTextPosition(TextPosition textPosition) {
        this.textPosition = textPosition;
    }
}
