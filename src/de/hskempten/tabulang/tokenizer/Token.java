package de.hskempten.tabulang.tokenizer;


/**
 * Scanned text part.
 */
public class Token {

    // The type "E" is forbidden; such a token will never be scanned
    // since it stands for the empty word, which is catched by the parser
    private final TokenExpression type;
    /**
     * A direct reference to the token in the text in the form of a CodeBlock.
     */
    private TextPosition position = null;
    /**
     * The scanner that generated this token.
     */
    private final Lexer myLexer;


    public Token(TokenExpression type, Lexer scan) {
        if (type == null)
            throw new IllegalArgumentException("Token type may not be null");
        if (scan == null)
            throw new IllegalArgumentException("Token's scanner may not be null");

        this.type = type;
        this.myLexer = scan;
    }

    public Token(TokenExpression type, TextPosition myCodeBlock,
                 Lexer scan) {
        this(type, scan);
        this.position = myCodeBlock;
    }

    public int getTypeNumber() {
        return type.getNumber();
    }

    public String getType() {
        return type.getType();
    }

    public String getContent() {
        if (position == null)
            throw new IllegalStateException(
                    "Cannot retrieve content from a positionless token");
        return position.getContent();
    }

    public int getFrom() {
        if (position == null)
            throw new IllegalStateException(
                    "Cannot retrieve position from a positionless token");
        return position.getStart();
    }

    public int getTo() {
        if (position == null)
            throw new IllegalStateException(
                    "Cannot retrieve position from a positionless token");
        return position.getEnd();
    }

    public int getColumnNumber() {
        if (position == null)
            throw new IllegalStateException(
                    "Cannot retrieve col number from a positionless token");
        return position.getFromCol();
    }

    public int getLineNumber() {
        if (position == null)
            throw new IllegalStateException(
                    "Cannot retrieve line number from a positionless token");
        return position.getFromLine();
    }

    public boolean hasPosition() {
        return (position != null);
    }

    public TextPosition getPosition() {
        return position;
    }

    public Lexer getLexer() {
        return myLexer;
    }

    @Override
    public String toString() {
        return "T. " + getType() + (position != null ? "\n" + position.toString() : "");
    }

}
