package de.hskempten.tabulang.items.types;

import de.hskempten.tabulang.items.ProgramItem;
import de.hskempten.tabulang.items.StatementItem;
import de.hskempten.tabulang.tokenizer.Lexer;
import de.hskempten.tabulang.tokenizer.ParseTimeException;

import java.util.ArrayList;

public class ProgramType implements Parser {

    public static ProgramType instance = new ProgramType();

    public ProgramItem parse(Lexer l) throws ParseTimeException {

        ArrayList<StatementItem> statements = new ArrayList<>();

        //FunctionManager fm = new FunctionManager();
        //fm.publishThisManager();

        while (!l.isDone()) {
            statements.add(StatementType.instance.parse(l));
        }

        //fm.checkIfFunCallsMatchFunDefs();
        //if (!fm.hasMainMethod()) throw new ParseTimeException("No main method defined.");

        ProgramItem prg = new ProgramItem(statements);
        return prg;
    }
}
