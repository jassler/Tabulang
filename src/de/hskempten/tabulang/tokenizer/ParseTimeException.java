package de.hskempten.tabulang.tokenizer;


public class ParseTimeException extends PositionedException {

    private static final long serialVersionUID = 1L;

    public ParseTimeException() {
        super();
    }

    public ParseTimeException(String message, Throwable cause) {
        super(message, cause);
    }

    public ParseTimeException(String message) {
        super(message);
    }

    public ParseTimeException(TextPosition position, String message, Throwable cause) {
        super(position, message, cause);
    }

    public ParseTimeException(TextPosition position, String message) {
        super(position, message);
    }

    public ParseTimeException(TextPosition position, Throwable cause) {
        super(position, cause);
    }

    public ParseTimeException(TextPosition position) {
        super(position);
    }

    public ParseTimeException(String message, TextPosition position) {
        super(position, message);
    }


    public ParseTimeException(Throwable cause) {
        super(cause);
    }

    public ParseTimeException(Lexer lexer, String message,
                              Throwable cause) {
        this(lexer.getPosition(), message, cause);
    }

    public ParseTimeException(Lexer lexer, String message) {
        this(lexer.getPosition(), message);
    }

    public ParseTimeException(Lexer lexer, Throwable cause) {
        this(lexer.getPosition(), cause);
    }

    public ParseTimeException(Lexer lexer) {
        this(lexer.getPosition());
    }

}
