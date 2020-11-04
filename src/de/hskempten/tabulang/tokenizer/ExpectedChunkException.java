package de.hskempten.tabulang.tokenizer;

public class ExpectedChunkException extends ParseTimeException {

    public ExpectedChunkException(TextPosition position, String expected) {
        super("Expected " + expected + ".", position);
    }

    public ExpectedChunkException(Lexer lexer, String expected) {
        super(lexer, "Expected " + expected + ".");
    }

}
