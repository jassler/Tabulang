package de.hskempten.tabulang.items.types;

import de.hskempten.tabulang.items.IfStmntItem;
import de.hskempten.tabulang.tokenizer.Lexer;
import de.hskempten.tabulang.tokenizer.ParseTimeException;

public class IfStmntType implements Parser {

    public static IfStmntType instance = new IfStmntType();

    @Override
    public IfStmntItem parse(Lexer l) throws ParseTimeException {
        throw new ParseTimeException(l, "Not yet implemented");
    }
}
