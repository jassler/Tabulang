package de.hskempten.tabulang.tokenizer;

public class ExpectedChunkException extends ParseTimeException {

    private static final long serialVersionUID = -4676581783453482421L;

    public ExpectedChunkException(TextPosition position, String expected) {
        super("Expected " + expected + ".", position);
    }

    public ExpectedChunkException(Lexer lexer, String expected) {
        super(lexer, "Expected " + expected + ".");
    }

}
