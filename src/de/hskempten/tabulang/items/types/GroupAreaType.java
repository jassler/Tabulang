package de.hskempten.tabulang.items.types;

import de.hskempten.tabulang.items.GroupAreaItem;
import de.hskempten.tabulang.tokenizer.Lexer;
import de.hskempten.tabulang.tokenizer.ParseTimeException;

public class GroupAreaType implements Parser {

    public static GroupAreaType instance = new GroupAreaType();

    @Override
    public GroupAreaItem parse(Lexer l) throws ParseTimeException {
        throw new ParseTimeException(l, "Not yet implemented");
    }
}
