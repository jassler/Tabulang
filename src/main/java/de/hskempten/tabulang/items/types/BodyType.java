package de.hskempten.tabulang.items.types;

import de.hskempten.tabulang.TokenType;
import de.hskempten.tabulang.items.BodyItem;
import de.hskempten.tabulang.items.StatementItem;
import de.hskempten.tabulang.tokenizer.Lexer;
import de.hskempten.tabulang.tokenizer.ParseTimeException;

import java.util.ArrayList;

public class BodyType implements Parser {

    public static BodyType instance = new BodyType();

    @Override
    public BodyItem parse(Lexer l) throws ParseTimeException {
        BodyItem item;

        //'{'
        ArrayList<StatementItem> myStatements = new ArrayList<>();
        //'}'

        if (!("bracket".equals(l.lookahead().getType()) && "{".equals(l.lookahead().getContent()))) {
            throw new ParseTimeException(l, "Expected '{', but got: " + l.lookahead().getContent());
        }
        l.getNextTokenAndExpect(TokenType.BRACKET);
        while (!("bracket".equals(l.lookahead().getType()) && "}".equals(l.lookahead().getContent()))) {
            myStatements.add(StatementType.instance.parse(l));
        }
        l.getNextTokenAndExpect(TokenType.BRACKET);

        item = new BodyItem(myStatements);

        return item;
    }
}
