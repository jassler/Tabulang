package de.hskempten.tabulang.tokenizer;


/**
 * Exception thrown with a position in the source code
 */
public abstract class PositionedException extends Exception {

    private TextPosition position = null;

    public PositionedException() {
        super();
    }

    public PositionedException(String message, Throwable cause) {
        super(message, cause);
    }

    public PositionedException(String message) {
        super(message);
    }

    public PositionedException(Throwable cause) {
        super(cause);
    }

    public PositionedException(TextPosition position) {
        super();
        setPosition(position);
    }

    public PositionedException(TextPosition position, String message, Throwable cause) {
        super(message, cause);
        setPosition(position);
    }

    public PositionedException(TextPosition position, String message) {
        super(message);
        setPosition(position);
    }


    public PositionedException(TextPosition position, Throwable cause) {
        super(cause);
        setPosition(position);
    }

    public PositionedException(Throwable cause, String message) {
        super(message, cause);
    }

    public TextPosition getPosition() {
        return position;
    }

    protected void setPosition(TextPosition position) {
        this.position = position;
    }

    public String getUnpositionedMessage() {
        return super.getMessage();
    }

    @Override
    public String getMessage() {
        if (position == null) return super.getMessage();
        return super.getMessage() + "\n" + position.toString();
    }

}
