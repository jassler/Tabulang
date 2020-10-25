package de.hskempten.tabulang.tokenizer;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A scanner based on regular expressions.
 */
public class Lexer implements Cloneable {

    public static final String UNKNOWN_TOKEN_TEXT = "Unknown Token.";

    private static final String EOFTokenType = "EOF";
    /**
     * Token type returned when current position is out of bounds.
     */
    public static final TokenExpression EOFTOKEN_TOKEN_EXPRESSION =
            new TokenExpression(EOFTokenType, "");
    private static final String EmptyTokenType = "E";
    /**
     * Token returned when current position is out of bounds.
     */
    public final Token EOFToken = new Token(EOFTOKEN_TOKEN_EXPRESSION, this);
    protected LinkedList<TokenExpression> expressions;
    protected ArrayList<Pattern> expressionPatterns;
    private ParameterizedString text;
    private int tokenPointer;
    private int lastPointer;
    private int terminalCounter;
    private Hashtable<String, Integer> terminalNumbers;
    // Everything in a line starting with the given marker is ignored
    private LinkedList<String> oneLineCommentMarkers;
    private String parenthesedCommentStart;
    private String parenthesedCommentEnd;
    private List<Token> tokens;

    {
        EOFTOKEN_TOKEN_EXPRESSION.setNumber(-1);
    }


    public Lexer() {
        this.text = null;
        tokenPointer = 0;
        lastPointer = 0;
        expressions = new LinkedList<TokenExpression>();
        expressionPatterns = new ArrayList<Pattern>();
        terminalCounter = 0;
        terminalNumbers = new Hashtable<String, Integer>();
        oneLineCommentMarkers = new LinkedList<>();
        parenthesedCommentStart = null;
        parenthesedCommentEnd = null;
        tokens = null;

    }

    @SuppressWarnings("unchecked")
    @Override
    public Lexer clone() {
        Lexer newOne = new Lexer();
        newOne.tokenPointer = tokenPointer;
        newOne.lastPointer = lastPointer;
        newOne.expressions = (LinkedList<TokenExpression>) expressions.clone();
        newOne.expressionPatterns = (ArrayList<Pattern>) expressionPatterns.clone();
        newOne.terminalCounter = terminalCounter;
        newOne.terminalNumbers = terminalNumbers;
        newOne.oneLineCommentMarkers = new LinkedList<>();
        for (String s : oneLineCommentMarkers) newOne.oneLineCommentMarkers.add(s);
        newOne.parenthesedCommentEnd = parenthesedCommentEnd;
        newOne.parenthesedCommentStart = parenthesedCommentStart;
        newOne.text = text.clone();
        return newOne;
    }

    public void reset() {
        tokenPointer = 0;
        lastPointer = 0;
        tokens = null;
    }

    public void setText(String text, String name) {
        setText(new ParameterizedString(name, text));
    }

    public void addOneLineCommentMarker(String marker) {
        oneLineCommentMarkers.add(marker);
    }

    public void setParenthesedCommentMarkers(String start, String end) {
        parenthesedCommentEnd = end;
        parenthesedCommentStart = start;
    }

    public boolean tokenTypeKnown(String type) {
        if (EmptyTokenType.equals(type)) return true;
        for (TokenExpression e : expressions) {
            if (e.getType().equals(type)) return true;
        }
        return false;
    }

    public String getText() {
        return text.getText();
    }

    public void setText(ParameterizedString text) {
        this.text = text;
        tokenPointer = 0;
        lastPointer = 0;
        tokens = null;
    }

    public void setText(String text) {
        setText(new ParameterizedString(text));
    }

    public void addExpressions(List<TokenExpression> es) {
        for (TokenExpression e : es) addExpression(e);
    }


    public void addExpression(TokenExpression e) {
        e.setNumber(terminalCounter);
        expressions.add(e);
        expressionPatterns.add(Pattern.compile(e.getExpression()));
        terminalNumbers.put(e.getType(), terminalCounter);
        terminalCounter++;
    }

    public int getNumberOfTerminal(String type) {
        return terminalNumbers.get(type);
    }

    public int getNumberOfTerminals() {
        return terminalCounter;
    }

    public Token lookahead() throws ParseTimeException {
        return getTokenAt(tokenPointer);
    }

    public Token lookbehind() throws ParseTimeException {
        return getTokenAt(tokenPointer - 1);
    }

    public Token lookbehind(int k) throws ParseTimeException {
        return getTokenAt(tokenPointer - k);
    }

    /**
     * @return The token that is k positions to the right of the
     * actual text pointer. The text pointer itself is
     * not modified by this method.
     * lookahead(1) returns the same as lookahead().
     */
    public Token lookahead(int k) throws ParseTimeException {
        return getTokenAt(tokenPointer + k - 1);
    }

    public Token getNextToken() throws ParseTimeException {
        Token t = getTokenAt(tokenPointer);
        lastPointer = tokenPointer;
        tokenPointer++;
        return t;
    }

    public Token getNextToken(String type, String expected) throws ParseTimeException {
        Token t = getNextToken();
        if (t.getType().equals(type)) {
            return t;
        } else expectedException(expected, t);
        // line is never reached:
        assert false;
        return null;
    }

    public void reverseTokenOrder() throws ParseTimeException {
        makeTokenList();
        ArrayList<Token> newList = new ArrayList<Token>();
        for (int i = tokens.size() - 1; i >= 0; i--) {
            newList.add(tokens.get(i));
        }
        tokens = newList;
    }


    public TextPosition getPosition() {
        // Not yet parsed, or rewinded
        if (tokenPointer <= 0 || tokens == null || tokens.size() <= 0)
            return new TextPosition(text, 0);

        if (tokenPointer >= tokens.size())
            return tokens.get(tokens.size() - 1).getPosition().getEndPosition();

        Token currentToken = tokens.get(tokenPointer);
        return currentToken.getPosition().getStartPosition();
    }

    public Token getNextTokenAndExpect(String type) throws ParseTimeException {
        Token t = this.getNextToken();
        if(!t.getType().equals(type))
            expectedException("Expected " + type, t);
        return t;
    }

    public void expectedException(String expected, Token actual) throws ParseTimeException {
        throw new ParseTimeException(getPosition(), "Expected " + expected + ", got " + actual.getType());
    }


    public void generalParseException(String message) throws ParseTimeException {
        throw new ParseTimeException(getPosition(), message);
    }


    @Override
    public String toString() {
        try {
            return super.toString() + ", looking at " + lookahead().toString();
        } catch (ParseTimeException e) {
            return super.toString();
        }
    }


    /**
     * Retrieve token from the list, build token list if necessary.
     *
     * @param index Position of the token in the list.
     * @return The token at the position specified.
     * @throws ParseTimeException On invalid input texts.
     */
    private Token getTokenAt(int index) throws ParseTimeException {
        makeTokenList();
        if (index < 0 || index >= tokens.size())
            return EOFToken;
        return tokens.get(index);
    }


    private void makeTokenList() throws ParseTimeException {
        if (tokens != null || text == null) return;
        tokens = new ArrayList<Token>();
        int textPointer = 0;

        scanning:
        while (true) {
            textPointer = moveOverWhitespaceAndComments(textPointer);
            if (textPointer >= text.getText().length()) {
                break scanning;
            }
            int i = 0;
            String s = text.getText().substring(textPointer);

            TokenExpression bestTokenExpression = null;
            int bestEnd = 0;
            for (TokenExpression e : expressions) {
                Matcher m = expressionPatterns.get(i).matcher(s);
                if (m.lookingAt()) {
                    if (m.end() > bestEnd) {
                        bestEnd = m.end();
                        bestTokenExpression = e;
                    }
                }
                i++;
            }

            if (bestTokenExpression != null) {
                tokens.add(new Token(bestTokenExpression, new TextPosition(text, textPointer, textPointer + bestEnd), this));
                textPointer += bestEnd;
            } else throw new ParseTimeException(new TextPosition(text, textPointer), UNKNOWN_TOKEN_TEXT);
        }
    }


    /**
     * This method moves the text pointer over all white space
     * and all comments.
     *
     * @throws ParseTimeException
     */
    private int moveOverWhitespaceAndComments(int textPointer) throws ParseTimeException {
        if (textPointer < text.getText().length()) {
            textPointer = moveOverComment(textPointer);
            char c = text.getText().charAt(textPointer);
            while ((Character.isWhitespace(c) || Character.isSpaceChar(c)) &&
                    (textPointer < text.getText().length())) {
                textPointer++;
                textPointer = moveOverComment(textPointer);
                if (textPointer < text.getText().length())
                    c = text.getText().charAt(textPointer);
            }
        }
        return textPointer;
    }


    /**
     * This method moves the text pointer over all comments.
     *
     * @throws ParseTimeException
     */
    private int moveOverComment(int textPointer) throws ParseTimeException {
        int start = textPointer;
        do {
            start = textPointer;
            for (String marker : oneLineCommentMarkers) {
                if (textPointer + marker.length() < text.getText().length()) {
                    if (text.getText().startsWith(marker, textPointer)) {
                        while ((textPointer < text.getText().length()) &&
                                (text.getText().charAt(textPointer) != '\n'))
                            textPointer++;
                    }
                }
            }
            boolean commentStartet = false;
            if (parenthesedCommentStart != null) {
                int pcsl = parenthesedCommentStart.length();
                int pcel = parenthesedCommentEnd.length();
                if (textPointer + pcsl < text.getText().length()) {
                    if (text.getText().substring(textPointer, textPointer + pcsl).equals(parenthesedCommentStart)) {
                        commentStartet = true;
                        textPointer = textPointer + pcsl;
                        while (textPointer + pcel < text.getText().length() &&
                                (!text.getText().substring(textPointer, textPointer + pcel).equals(parenthesedCommentEnd)))
                            textPointer++;
                        if (text.getText().substring(textPointer, textPointer + pcel).equals(parenthesedCommentEnd)) {
                            textPointer = textPointer + pcel;
                            commentStartet = false;
                        }
                    }
                }
            }
            if (commentStartet) throw new ParseTimeException("Unclosed comment detected.");
        } while (start != textPointer);
        return textPointer;
    }

}
