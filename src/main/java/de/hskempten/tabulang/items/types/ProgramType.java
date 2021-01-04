package de.hskempten.tabulang.items.types;

import de.hskempten.tabulang.items.ProgramItem;
import de.hskempten.tabulang.items.StatementItem;
import de.hskempten.tabulang.tokenizer.Lexer;
import de.hskempten.tabulang.tokenizer.ParseTimeException;
import de.hskempten.tabulang.tokenizer.TextPosition;

import java.util.ArrayList;

public class ProgramType implements Parser {

    public static ProgramType instance = new ProgramType();

    public ProgramItem parse(Lexer l) throws ParseTimeException {

        ArrayList<StatementItem> statements = new ArrayList<>();

        //FunctionManager fm = new FunctionManager();
        //fm.publishThisManager();

        TextPosition startP = l.lookahead().getPosition();
        while (!l.isDone()) {
            statements.add(StatementType.instance.parse(l));
        }

        //fm.checkIfFunCallsMatchFunDefs();
        //if (!fm.hasMainMethod()) throw new ParseTimeException("No main method defined.");
        TextPosition endP = l.lookbehind().getPosition();
        ProgramItem prg = new ProgramItem(statements);
        prg.setTextPosition(new TextPosition(startP, endP));
        return prg;
    }
}
