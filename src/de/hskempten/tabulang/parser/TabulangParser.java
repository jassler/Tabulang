package de.hskempten.tabulang.parser;

import de.hskempten.tabulang.Interpreter;
import de.hskempten.tabulang.nodes.Node;
import de.hskempten.tabulang.nodes.Program;
import de.hskempten.tabulang.tokenizer.Lexer;
import de.hskempten.tabulang.tokenizer.ParseTimeException;

public class TabulangParser {

    private final Interpreter interpreter;
    private Lexer lexer;


    public TabulangParser(Lexer lexer, Interpreter interpreter) {
        this.lexer = lexer;
        this.interpreter = interpreter;
    }

    public void parse() throws ParseTimeException {
        Node node = new Program(lexer);
        node.evaluate(interpreter);
    }
}
