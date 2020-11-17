package de.hskempten.tabulang.parser;

import de.hskempten.tabulang.Interpreter;
import de.hskempten.tabulang.items.ProgramItem;
import de.hskempten.tabulang.items.types.ProgramType;
import de.hskempten.tabulang.nodes.AnyStatement;
import de.hskempten.tabulang.nodes.Node;
import de.hskempten.tabulang.tokenizer.Lexer;
import de.hskempten.tabulang.tokenizer.ParseTimeException;

import java.util.ArrayList;
import java.util.List;

public class TabulangParser {

    private final Interpreter interpreter;
    private Lexer l;
    List<AnyStatement> lines = new ArrayList<AnyStatement>();


    public TabulangParser(Lexer lexer, Interpreter interpreter) {
        this.l = lexer;
        this.interpreter = interpreter;
    }

    public void parse() throws ParseTimeException {
        while (!l.isDone()) {
            lines.add(new AnyStatement(l));
        }
        for (Node node : lines) {
            node.evaluate(interpreter);
        }
    }

    public ProgramItem parseN() throws ParseTimeException{
        l.lookahead();
        ProgramItem prg = ProgramType.instance.parse(l);
        return prg;
    }

}
