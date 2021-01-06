package de.hskempten.tabulang.tokenizer;

import de.hskempten.tabulang.utils.StringUtils;


/**
 * A text coordinate is a range in a text and can be converted to the form
 * of a line and column information.
 * The very first line has line number 1 and the leftmost
 * symbol in a line has column number 0.
 */
public class TextPosition implements Comparable<TextPosition>, ParsedObject {

    private final ParameterizedString string;
    private final int start;
    private final int end;

    private LineColPosition startCoords;
    private LineColPosition endCoords;


    /**
     * Create new position range from the range between two ranges including
     * these ranges.
     *
     * @param positionStart Starting position
     * @param positionEnd   Ending position
     * @throws IllegalArgumentException When one argument is <tt>null</tt> or
     *                                  the ranges are defined over different {@link ParameterizedString}s.
     */
    public TextPosition(TextPosition positionStart,
                        TextPosition positionEnd) {

        if (positionStart == null)
            throw new IllegalArgumentException("Start position must not be null");
        if (positionEnd == null)
            throw new IllegalArgumentException("End position must not be null");
        if (!(positionEnd.string == positionStart.string))
            throw new IllegalArgumentException("Position are from different strings");

        this.string = positionStart.string;

        this.start = Math.min(positionStart.start, positionEnd.start);
        this.end = Math.max(positionStart.end, positionEnd.end);
        computeCoords();
    }

    /**
     * Create a new range with start and end positions
     * over a {@link ParameterizedString}.
     *
     * @param string Text this position is in.
     * @param start  Start position.
     * @param end    End position.
     */
    public TextPosition(ParameterizedString string, int start, int end) {
        if (string == null)
            throw new IllegalArgumentException("string may not be null");
        if (start > end)
            throw new IllegalArgumentException("start may not be larger then end");
        this.string = string;
        this.start = start;
        this.end = end;
        computeCoords();
    }

    /**
     * Create a new position with start position only
     * over a {@link ParameterizedString}.
     *
     * @param string Text this position is in.
     * @param start  Start position.
     */
    public TextPosition(ParameterizedString string, int start) {
        if (string == null)
            throw new IllegalArgumentException("string may not be null");
        this.string = string;
        this.start = start;
        this.end = start;
        computeCoords();
    }

    private void computeCoords() {
        TextCoordinate s = string.getCoordinate(start);
        TextCoordinate e = string.getCoordinate(end);
        startCoords = new LineColPosition(s.getLine(), s.getColumn());
        endCoords = new LineColPosition(e.getLine(), e.getColumn());
    }

    public String getSourceName() {
        return string.getName();
    }

    public int getStart() {
        return start;
    }

    public int getEnd() {
        return end;
    }

    public int getLength() {
        return end - start;
    }


    public TextPosition getStartPosition() {
        return new TextPosition(string, start);
    }

    public TextPosition getEndPosition() {
        return new TextPosition(string, end);
    }


    public LineColPosition getStartCoords() {
        return startCoords;
    }

    public LineColPosition getEndCoords() {
        return endCoords;
    }

    public String getContent() {
        return string.getTextSubstring(start, end);
    }

    public TextCoordinate getStartCoordinate() {
        return string.getCoordinate(start);
    }

    public TextCoordinate getEndCoordinate() {
        return string.getCoordinate(end);
    }

    @Override
    public int getFromLine() {
        return getStartCoordinate().getLine();
    }

    @Override
    public int getFromCol() {
        return getStartCoordinate().getColumn();
    }

    @Override
    public int getToLine() {
        return getEndCoordinate().getLine();
    }

    @Override
    public int getToCol() {
        return getEndCoordinate().getColumn();
    }


    /**
     * @param line Line number
     * @return All of the text of the given line, that belongs to this  TextPosition, without leading or trailing white space.
     */
    public String get(int line) {
        if ((line >= startCoords.line) && (line <= endCoords.line)) {
            String fullLine = string.getLine(line);
            if (line == startCoords.line) {
                int end = fullLine.length();
                if (endCoords.line == line) end = endCoords.col;
                try {
                    return fullLine.substring(startCoords.col, end).trim();
                } catch (StringIndexOutOfBoundsException e) {
                    e.printStackTrace();
                }
            }
            if (line == endCoords.line) {
                return fullLine.substring(0, endCoords.col).trim();
            }
            return fullLine.trim();
        }
        return null;
    }

    public int getIndent(int line) {
        if ((line >= startCoords.line) && (line <= endCoords.line)) {
            String fullLine = string.getLine(line);
            if (line == startCoords.line) return startCoords.col;
            int count = 0;
            while (count < fullLine.length() && fullLine.charAt(count) == ' ') count++;
            return count;
        }
        return -1;
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        //if (getSourceName() != null) sb.append(getSourceName()).append(", ");
        sb.append("Line ")
                .append(getFromLine())
                .append(", Column ")
                .append(getFromCol())
                .append(" to ")
                .append("Line ")
                .append(getToLine())
                .append(", Column ")
                .append(getToCol())
                .append(":\n");

        if (end == start) {
            sb.append(StringUtils.showPositionLine(string.getText(), start));
        } else {
            /*String sbTo = "  (End: Line " + getToLine() +
                    ", Column " + getToCol() +
                    ")";*/
            String sbTo = "";
            sb.append(StringUtils.showPositionLineRange(string.getText(), start, end, sbTo));
        }
        return sb.toString();
    }


    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + end;
        result = prime * result + start;
        result = prime * result + ((string == null) ? 0 : string.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        TextPosition other = (TextPosition) obj;
        if (end != other.end)
            return false;
        if (start != other.start)
            return false;
        if (string == null) {
			return other.string == null;
        } else return string == other.string;
	}


    public ParameterizedString getText() {
        return string;
    }

    @Override
    public int compareTo(TextPosition o) {
        if (o == null)
            throw new IllegalArgumentException("Other position may not be null");
        if (getSourceName() != null
                && o.getSourceName() != null
                && !getSourceName().equals(o.getSourceName()))
            throw new IllegalArgumentException("Position are from different sources");
        if (!(string == o.string))
            throw new IllegalArgumentException("Position are from different strings");
        return start - o.start;
    }


}
