package de.hskempten.tabulang.items.types;

import de.hskempten.tabulang.TokenType;
import de.hskempten.tabulang.items.FunCallItem;
import de.hskempten.tabulang.items.LanguageItemType;
import de.hskempten.tabulang.items.StatementItem;
import de.hskempten.tabulang.tokenizer.Lexer;
import de.hskempten.tabulang.tokenizer.ParseTimeException;
import de.hskempten.tabulang.tokenizer.TextPosition;

public class StatementType implements Parser {

    public static StatementType instance = new StatementType();

    public StatementItem parse(Lexer l) throws ParseTimeException {

        StatementItem statement;
        TextPosition startP = l.lookahead().getPosition();
        switch (l.lookahead().getType()) {
            case "keyword" -> {
                switch (l.lookahead().getContent()) {
                    case "for" -> statement = new StatementItem(LoopType.instance.parse(l));
                    case "if" -> statement = new StatementItem(IfStmntType.instance.parse(l));
                    case "function", "var" -> statement = new StatementItem(VarDefType.instance.parse(l));
                    default -> throw new ParseTimeException(l, "Illegal keyword: " + l.lookahead().getContent());
                }
            }
            case "bracket" -> {
                if ("{".equals(l.lookahead().getContent())) {
                    statement = new StatementItem(BodyType.instance.parse(l));
                } else {
                    throw new ParseTimeException(l, "Illegal bracket: " + l.lookahead().getContent());
                }
            }
            case "variable" -> {
                if ("bracket".equals(l.lookahead(2).getType()) && "(".equals(l.lookahead(2).getContent())) {
                    FunCallItem funCall = FunCallType.instance.parse(l);
                    funCall.setLanguageItemType(LanguageItemType.STATEMENT_FUNCALL);
                    statement = new StatementItem(funCall);
                    l.getNextTokenAndExpect(TokenType.SEMICOLON);
                } else {
                    statement = new StatementItem(VarDefType.instance.parse(l));
                }
            }
            default -> throw new ParseTimeException(l, "Illegal Type: " + l.lookahead().getType() + " at " + l.lookahead().getContent());
        }

        TextPosition endP = l.lookbehind().getPosition();
        statement.setTextPosition(new TextPosition(startP, endP));
        return statement;
    }
}
