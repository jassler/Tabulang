package de.hskempten.tabulang.items.types;

import de.hskempten.tabulang.TokenType;
import de.hskempten.tabulang.items.SetStmntItem;
import de.hskempten.tabulang.items.TermItem;
import de.hskempten.tabulang.tokenizer.Lexer;
import de.hskempten.tabulang.tokenizer.ParseTimeException;
import de.hskempten.tabulang.types.LanguageType;

public class SetStmntType implements LanguageType {

    public static SetStmntType instance = new SetStmntType();

    @Override
    public SetStmntItem parse(Lexer l) throws ParseTimeException {
        SetStmntItem item;

        TermItem myTerm;

        l.getNextTokenAndExpect(TokenType.KEYWORD);
        myTerm = TermType.instance.parse(l);
        l.getNextTokenAndExpect(TokenType.SEMICOLON);
        item = new SetStmntItem(myTerm);

        return item;

    }
}
