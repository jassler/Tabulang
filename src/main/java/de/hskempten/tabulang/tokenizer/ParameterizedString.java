package de.hskempten.tabulang.tokenizer;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map.Entry;
import java.util.NavigableMap;
import java.util.TreeMap;
import java.util.Vector;

public class ParameterizedString implements Cloneable {

    /**
     * The name of this string.
     */
    private final String name;
    /**
     * The actual text string.
     */
    private String text;
    /**
     * This vector contains the positions of the starts of the lines of the text.
     */
    private List<Integer> lineStarts;
    /**
     * This field holds the line numbers with respect to text positions.
     * I.e. the keys are the text positions pointing to first char in new lines
     * and the values are line numbers.
     */
    private NavigableMap<Integer, Integer> lineBreaks = new TreeMap<>();

    private int minLineNr;


    public ParameterizedString(File input) throws IOException {
        this.name = input.getAbsolutePath();

        if (input.length() > Integer.MAX_VALUE) {
            throw new RuntimeException();
        }

        InputStream inputStream = new FileInputStream(input);
        int length = (int) input.length();
        byte[] buffer = new byte[length];

        int total = 0;
        while (total < length) {
            int result = inputStream.read(buffer, total, length - total);
            if (result == -1) {
                break;
            }
            total += result;
        }

        this.text = new String(buffer, StandardCharsets.UTF_8);
        computeLineInformation();
    }


    public ParameterizedString(String name, String text) {
        this.name = name;
        this.text = text;
        computeLineInformation();
    }

    public ParameterizedString(String text) {
        this.text = text;
        this.name = "text has no name";
        computeLineInformation();
    }

    public int getMinimalLineNumber() {
        return minLineNr;
    }

    private void computeLineInformation() {
        int textLength = text.length();
        if (textLength == 0) return;
        lineBreaks.put(0, 1);// "virtual" line break before first char
        int count = 2;
        for (int i = 0; i < textLength - 1; i++) {
            if (text.charAt(i) == '\n') {
                lineBreaks.put(i + 1, count);
                count++;
            }
        }
        if (text.charAt(textLength - 1) == '\n') {
            lineBreaks.put(textLength, count);
        } else {
            lineBreaks.put(textLength + 1, count);
        }
        lineStarts = new Vector<>(lineBreaks.size() + 1);
        lineStarts.addAll(lineBreaks.keySet());
        minLineNr = 1;
    }


    /**
     * Adds a line of text to this code. Counts down the line numbers
     * thus getting a line with number zero and negative line numbers.
     */
    public void pushLine(String line) {
        TreeMap<Integer, Integer> newLineBreaks = new TreeMap<>();
        if (!line.endsWith("\n")) line = line + "\n";
        int lineLength = line.length();
        for (int textPos : lineBreaks.keySet()) {
            int lineNr = lineBreaks.get(textPos);
            newLineBreaks.put(textPos + lineLength, lineNr);
        }
        int smallest = lineBreaks.firstKey();
        newLineBreaks.put(0, lineBreaks.get(smallest) - 1);
        lineBreaks = newLineBreaks;
        text = line + text;
        lineStarts = new Vector<>(lineBreaks.size() + 1);
        lineStarts.addAll(lineBreaks.keySet());
        minLineNr--;
    }


    public String getLine(int lnNr) {
        return text.substring(lineStarts.get(lnNr - minLineNr), lineStarts.get(lnNr - minLineNr + 1) - 1);
    }


    public String getText() {
        return text;
    }

    public String getName() {
        return name;
    }

    /**
     * @return The TextCoordinate (line and column) to which the given
     * text pointer points.
     */
    public TextCoordinate getCoordinate(int position) {
        if (text.length() == 0) return new TextCoordinate(0, 0);
        Entry<Integer, Integer> prevBreak = lineBreaks.floorEntry(position);
        int line = prevBreak.getValue();
        int col = position - prevBreak.getKey();

        return new TextCoordinate(line, col);
    }

    /**
     * @return The number of lines of the text.
     */
    public int getNumberOfLines() {
        return lineBreaks.size() - 1;
    }

    /**
     * @return The length of the line with the given number;
     * the line-break symbol is not counted in.
     */
    public int getLineLength(int lnNr) {
        if (lnNr == 0) return lineStarts.get(1) - 1;
        return lineStarts.get(lnNr) - lineStarts.get(lnNr - 1) - 1;
    }

    public String getTextSubstring(int start, int end) {
        return text.substring(start, end);
    }

    /**
     * @return The length of the longest line in the text.
     */
    public int getNumberOfColumns() {
        int max = 0;
        for (int i = 0; i < getNumberOfLines(); i++) {
            if (max < getLineLength(i)) max = getLineLength(i);
        }
        return max;
    }

    @Override
    public ParameterizedString clone() {
        try {
            return (ParameterizedString) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new IllegalStateException(e);
        }
    }

}
