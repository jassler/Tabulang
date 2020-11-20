package de.hskempten.tabulang.items.types;

import de.hskempten.tabulang.items.BinRelSymItem;
import de.hskempten.tabulang.items.LanguageItem;
import de.hskempten.tabulang.tokenizer.Lexer;
import de.hskempten.tabulang.tokenizer.ParseTimeException;
import de.hskempten.tabulang.types.LanguageType;

public class BinRelSymType implements LanguageType {

    public static BinRelSymType instance = new BinRelSymType();

    @Override
    public BinRelSymItem parse(Lexer l) throws ParseTimeException {
        throw new ParseTimeException(l,"Not yet implemented");
    }
}
