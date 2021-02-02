package de.hskempten.tabulang.parser;

import de.hskempten.tabulang.interpretTest.Interpretation;
import de.hskempten.tabulang.items.ProgramItem;
import de.hskempten.tabulang.items.types.ProgramType;
import de.hskempten.tabulang.tokenizer.Lexer;
import de.hskempten.tabulang.tokenizer.ParseTimeException;

public class TabulangParser {

    private final Interpretation interpretation;
    private Lexer l;


    public TabulangParser(Lexer lexer, Interpretation interpretation) {
        this.l = lexer;
        this.interpretation = interpretation;
    }

    public ProgramItem parse() throws ParseTimeException{
        l.lookahead();
        ProgramItem prg = ProgramType.instance.parse(l);
        return prg;
    }

}
