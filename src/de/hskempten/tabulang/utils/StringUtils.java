package de.hskempten.tabulang.utils;

import java.io.*;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.util.LinkedList;


/**
 * This class contains an assortment of useful methods
 * for strings.
 * <p>
 * A collection of methods of this class deals with strings s which are
 * interpreted as a list of strings as follows:
 * In these methods a delimiter argument besides s is always needed.
 * The list of strings consists of those substrings of s which are delimited
 * by that delimiter. Every substring of s which is equal to the delimiter
 * is not an element of the list; the strings between them are.
 * Each string of the such described list has a corresponding index: The leftmost
 * delimited substring of s has the index 1.
 */
public class StringUtils {


// --------------------------- Constants -----------------------------------

    /**
     * This is a substitute for the (ambigous) line break signs.
     */
    public static final char LINE_BREAK = '\n';
    public static char[] JAVA_CHARS = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
            'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm',
            'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
            'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M',
            'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
            '-', '_'};
    public static char[] LC_LETTERS = new char[]{'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm',
            'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
    public static char[] UC_LETTERS = new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M',
            'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
    public static char[] DIGITS = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};


// ------------------ Methods for lists of strings which are stored in one string ------------------
// ------------------------------------------- Start -----------------------------------------------

    /**
     * This method returns everything except trailing and leading Whitespace
     * from the start of the parameter s to the first occurrence of
     * separator or the end of the string.
     * If s is null, null is returned.
     */
    public static String getNextField(String s, String separator) {
        if (s != null) {
            int i = s.indexOf(separator);
            if (i == -1) return s;
            else return s.substring(0, i).trim();
        }
        return null;
    }

    /**
     * This method returns a shortened version of s
     * where everything to the first occurrence of separator is
     * deleted together with the separator.
     * If s does not contain the separator then an empty string is returned.
     * If s is null, null is returned.
     */
    public static String eatNextField(String s, String separator) {
        if (s != null) {
            int i = s.indexOf(separator);
            if (i == -1) return "";
            else return s.substring(i + separator.length());
        } else return null;
    }

    /**
     * This method returns everything except trailing and leading Whitespace
     * from the start of the parameter s to the count-th occurrence of
     * separator or the end of the string.
     * If s is null, null is returned.
     */
    public static String getNextFields(String s, String separator, int count) {
        if (s != null) {
            int i = s.indexOf(separator);
            int c = 0;
            while ((c < count) && (i != -1)) {
                i = s.indexOf(separator, i + 1);
                c++;
            }
            if (i == -1) return s;
            else return s.substring(0, i);
        } else return null;
    }

    /**
     * This method returns a shortened version of s
     * where everything to the count-th occurrence of separator is
     * deleted together with the separator.
     * If s does not contain enough separators then an empty string is returned.
     * If s is null, null is returned.
     */
    public static String eatNextFields(String s, String separator, int count) {
        if (s != null) {
            int i = s.indexOf(separator);
            int c = 0;
            while ((c < count) && (i != -1)) {
                i = s.indexOf(separator, i + 1);
                c++;
            }
            if (i == -1) return "";
            else return s.substring(i + 1);
        } else return null;
    }

    /**
     * This method returns a shortened version of s
     * where everything right of the last occurrence of separator is
     * deleted together with the separator.
     * If s does not contain the separator then an empty string is returned.
     * If s is null, null is returned.
     */
    public static String eatLastField(String s, String separator) {
        if (s != null) {
            int i = s.lastIndexOf(separator);
            if (i == -1) return "";
            else return s.substring(0, i);
        } else return null;
    }


    /**
     * This method returns everything except trailing and leading whitespace
     * from the index of the first occurrence of the given separator in the
     * given string s to the end of the string s.
     * If s is null, null is returned.
     */
    public static String getLastField(String s, String separator) {
        if (s != null) {
            int i = s.lastIndexOf(separator);
            if (i == -1) return s;
            else return s.substring(i + 1).trim();
        } else return null;
    }


    /**
     * This method expects a string, containing one or more substrings
     * which are diveded by the given separator. If these substrings are
     * the fields of the string, this method returns the field with the
     * given number.
     * Example: Let separator = "|" and s = "one|two|three".
     * getStringEntry(s,2) = "two"
     * getStringEntry(s,0) = null;
     * getStringEntry(s,4) = null;
     */
    public static String getEntry(String s, int number, String separator) {
        if (s != null) {
            String field = null;
            for (int i = 1; i <= number; i++) {
                if ((i > 1) && s.equals("")) return null;
                field = getNextField(s, separator);
                s = eatNextField(s, separator);
            }
            return field;
        }
        return null;
    }


    /**
     * @param s         A string which is interpreted as a list of strings as described
     *                  in the documentation of this class.
     * @param number    An index which points to an element of the list of strings
     *                  stored in s.
     * @param separator The delimiter which is used to distinguish between the
     *                  elements of ths list of strings encoded by s.
     * @return The position in s where the start of the substring that composes the entry with
     * the given index number in the list of strings which is encoded by s is situated.
     * If there is no entry with the given index number, -1 is returned.
     * Example: Let s = "one|two|three", let number = 2, let separator = "|"
     * Then 4 is returned.
     */
    public static int getEntryStart(String s, int number, String separator) {
        if (number < 0) return -1;
        if (number == 0) return 0;
        else {
            int i = s.indexOf(separator);
            if (i == -1) return -1;
            return i + separator.length() + getEntryStart(eatNextField(s, separator), number - 1, separator);
        }

    }


    /**
     * @param s         A string which is interpreted as a list of strings as described
     *                  in the documentation of this class.
     * @param number    An index which points to an element of the list of strings
     *                  stored in s.
     * @param separator The delimiter which is used to distinguish between the
     *                  elements of thIs list of strings encoded by s.
     * @return The position in s where the substring that composes the entry with
     * the given index number in the list of strings which is encoded by s is situated.
     * If there is no entry with the given index number, null is returned.
     * Example: Let s = "one|two|three", let number = 2, let separator = "|"
     * Then (4,7) is returned.
     */
    public static Pair<Integer, Integer> getEntryPosition(String s, int number, String separator) {
        int i = getEntryStart(s, number, separator);
        if (i != -1) {
            int j = s.indexOf(separator, i + 1);
            if (j == -1) j = s.length();
            return new Pair<Integer, Integer>(i, j);
        } else return null;
    }


    /**
     * @return A new string, that is derived from the given string s as follows:
     * The given s is interpreted as list (as described in the documentation
     * of this class). The entry of that list with the given index number
     * is replaced by the given replacement. Note, that the first string
     * in the list has number 1.
     * If there is no entry with the indicated index then s is returned.
     */
    public static String replaceEntry(String s, int number, String replacement, String separator) {
        Pair<Integer, Integer> pos = getEntryPosition(s, number, separator);
        if (pos != null) {
            return replaceSubString(s, pos.getFirst(), pos.getLast(), replacement);
        } else return s;
    }


    /**
     * Searches a string encoding a list of elements for its last entry.
     * Example with separator "/":
     * If s == "/one/two/three", the method returns "three".
     * If s == "/one/two/three/", the method returns "three".
     * If s == "one", the method returns "one".
     * If s == "", the method returns "";
     * If s == null, the method returns null;
     */
    public static String getLastPathEntry(String s, String separator) {
        s.trim();
        if (s != null) {
            int i = s.lastIndexOf(separator);
            if (i == -1) return s;
            if (i == s.length() - 1)
                return getLastPathEntry(s.substring(0, s.length() - 1), separator);
            return s.substring(i + 1);
        }
        return null;
    }


    /**
     * This method returns the number of substring elements of the given
     * string s. Basically this is the number of separators found plus one.
     */
    public static int getNumberOfEntries(String s, String separator) {
        int count = 0;
        while (!(s.equals(""))) {
            s = eatNextField(s, separator);
            count++;
        }
        return count;
    }


    /**
     * Returns all entries which are encoded in the given String s.
     * Examples: Input: s="blubb&b   &c", separator="&"
     * Result: ["blubb", "b", "c"]
     * Input: s=""
     * Result: []
     * Input: s=null
     * Result: []
     * Input: s="abc*bla**wump", separator = "*"
     * Result: ["abc","bla","","wump"]
     */
    public static LinkedList<String> extractEntries(String s, String separator) {
        LinkedList<String> l = new LinkedList<String>();
        if (s == null) return l;
        while (!"".equals(s)) {
            String t = getNextField(s, separator);
            s = eatNextField(s, separator);
            l.add(t);
        }
        return l;
    }


//------------------ Methods for lists of strings which are stored in one string ------------------
//-------------------------------------------- End ------------------------------------------------


    /**
     * Replaces all double-quotes with escaped double quotes, i.e.
     * " is transformed to \".
     */
    public static String escapeQuotes(String s) {
        if (s == null) return null;
        int i = 0;
        while (i != -1) {
            i = s.indexOf("\"", i);
            if (i != -1) {
                if (i > 0) {
                    if (s.charAt(i - 1) != '\\') {
                        s = s.substring(0, i) + "\\" + s.substring(i);
                    }
                } else {
                    s = "\\" + s;
                }
            }
        }
        return s;
    }


    /**
     * This method reads the file with the given name. It replaces
     * line breaks by LINE_BREAK. By doing so, we ensure that we
     * do not depend on the line break encoding of a specific
     * operating system.
     *
     * @return A string representation of the content of the file
     * with the given file name.
     */
    public static String readFileString(String fileName) throws IOException {
        File f = new File(fileName);
        if (!f.exists()) throw new IOException("File does not exist: " + fileName);
        LineNumberReader fis = new LineNumberReader(new FileReader(fileName));
        String s = "";
        String line = "";
        LinkedList<String> l = new LinkedList<String>();
        int len = 0;
        while (line != null) {
            line = fis.readLine();
            if (line != null) {
                //if (!"".equals(line)) {
                l.add(line);
                len = len + line.length();
                //}
            }
        }
        fis.close();
        char[] buf = new char[len + l.size()];
        int p = 0;
        for (String li : l) {
            char[] c = li.toCharArray();
            for (int j = 0; j < c.length; j++) buf[p + j] = c[j];
            p = p + c.length;
            buf[p] = LINE_BREAK;
            p++;
        }
        s = new String(buf);
        return s;
    }

    /**
     * Generates or overwrites a file with the given fileName and fills
     * it with the given content string.
     *
     * @throws IOException
     */
    public static void writefileString(String fileName, String content) throws IOException {
        File f = new File(fileName);
        FileWriter writer = new FileWriter(f);
        writer.write(content);
        writer.flush();
        writer.close();
    }


    /**
     * This method reads the file with the given name. It replaces
     * line breaks by LINE_BREAK. By doing so, we ensure that we
     * do not depend on the line break encoding of a specific
     * operating system.
     *
     * @return A string representation of the content of the file
     * with the given file name.
     */
    public static String readFileString(String fileName, String charEncoding) throws IOException {
        InputStreamReader r = new InputStreamReader(new FileInputStream(fileName), charEncoding);
        LineNumberReader fis = new LineNumberReader(r);
        String s = "";
        String line = "";
        LinkedList<String> l = new LinkedList<String>();
        int len = 0;
        while (line != null) {
            line = fis.readLine();
            if (line != null) {
                if (!"".equals(line)) {
                    l.add(line);
                    len = len + line.length();
                }
            }
        }
        fis.close();
        char[] buf = new char[len + l.size()];
        int p = 0;
        for (String li : l) {
            char[] c = li.toCharArray();
            for (int j = 0; j < c.length; j++) buf[p + j] = c[j];
            p = p + c.length;
            buf[p] = LINE_BREAK;
            p++;
        }
        s = new String(buf);
        return s;
    }


    /**
     * @return A string which is derived from the given string s, by replacing the
     * substring of s from the index from to the index to with the
     * given replacement string.
     * If from is larger than to or if either from or to is outside of
     * the string boundaries or if s is null then null is returned.
     */
    public static String replaceSubString(String s, int from, int to, String replacement) {
        if ((from <= to) && (from >= 0) && (s != null) && (to < s.length())) {
            return s.substring(0, from) + replacement + s.substring(to);
        } else return null;
    }


    /**
     * This method returns a new String that is derived from the given
     * String s1, by inserting the given String s2 at the given position.
     * If the given position is outside the String s1, s1 is returned.
     * Possible positions range from 0 to s1.length().
     */
    public static String insertString(String s1, String s2, int position) {
        if ((position >= 0) && (position <= s1.length())) {
            String sFront = s1.substring(0, position);
            String sBack = s1.substring(position);
            return sFront + s2 + sBack;
        } else return s1;
    }


    /**
     * Builds a new string from the given one, by inserting line-breaks such that
     * in the resulting string only lines with at most maxLineLength symbols appear.
     *
     * @param text             The text in which to insert line breaks.
     * @param lineBreakMarker  The string that marks the line breaks. In most cases you might
     *                         with to use "\n".
     * @param maxLineLength    The maximum allowed line length. Line breaks will be inserted
     *                         only where there is white-space. Hence if there is not enough
     *                         white-space for a line break to be inserted (for example if a word
     *                         is longer than the maxLineLength), then no line-break is inserted
     *                         and the result is a line that is longer than maxLineLength. In all
     *                         other cases a line-break is inserted in the white-space
     *                         position that is closest (but smaller or equal to) the maxLineLength.
     * @param whiteSpaceMarker These may be replaced by the lineBreakMarker. In most cases you
     *                         will like to use a space (" ").
     * @created 19.10.2012
     */
    public static String insertLineBreaks(String text, String lineBreakMarker, int maxLineLength, String whiteSpaceMarker) {
        LinkedList<String> build = new LinkedList<String>();
        while (text.length() > maxLineLength) {
            int i = maxLineLength - 1;
            while ((!whiteSpaceMarker.equals(text.charAt(i))) && (i > 0)) i--;
            if (i < 0) {
                i = maxLineLength;
                while ((!whiteSpaceMarker.equals(text.charAt(i))) && (i < text.length())) i++;
            }
            if ((i > 0) && (i < text.length())) {
                build.add(text.substring(0, i));
                text = text.substring(i + whiteSpaceMarker.length());
            } else {
                build.add(text);
                text = "";
            }
        }
        if (text.length() > 0) build.add(text);
        StringBuilder sb = new StringBuilder();
        for (String s : build) {
            while (s.startsWith(whiteSpaceMarker)) s = s.substring(whiteSpaceMarker.length());
            while (s.endsWith(whiteSpaceMarker)) s = s.substring(0, s.length() - whiteSpaceMarker.length());
            sb.append(s);
            sb.append(lineBreakMarker);
        }
        return sb.toString();
    }


    /**
     * This method returns a new String that is derived from the givne
     * String s, by inserting the given char c at the given position.
     * If the given position is outside the String, s is returned.
     * Possible positions range from 0 to s.length().
     */
    public static String insertChar(String s, char c, int position) {
        char[] ca = new char[1];
        ca[0] = c;
        return insertString(s, new String(ca), position);
    }


    /**
     * This method returns a string, where the given character c
     * is attached as last character to the given String s.
     */
    public static String attachChar(String s, char c) {
        return insertChar(s, c, s.length());
    }


    /**
     * Returns the largest number i such that s1[j] == s2[j]
     * for all j in {0..i}, where s[k] means the k-th character
     * of the string s.
     * <p>
     * Example:
     * If s1 = "hungry" and
     * s2 = "hungary"
     * then the method returns 3.
     * If s1 = "close" and
     * s2 = "cat"
     * then the method returns 0.
     * If s1 = "cat" and
     * s2 = "cat"
     * then teh method returns 2.
     * If s1 = "can" and
     * s2 = "cantrip"
     * then the method returns 2.
     * If s1 = "something" and
     * s2 = "different"
     * then the method returns -1.
     * If s1 or s2 is null, then the methd returns a value
     * which is smaller than -1.
     */
    public static int getMatchingRegionLeft(String s1, String s2) {
        if (s1 != null) {
            if (s2 != null) {
                int min = Math.min(s1.length(), s2.length());
                for (int i = 0; i < min; i++) {
                    if (s1.charAt(i) != s2.charAt(i)) return i - 1;
                }
                return min - 1;
            }
        }
        return -2;
    }


    /**
     * For two strings s1, s2, let k1 be the length of s1 and
     * let k2 be the length of s2.
     * The method returns the largest number i such that s1[k1-j] == s2[k2-j]
     * for all j in {0..i}, where s[l] means the l-th character
     * of the string s.
     * <p>
     * Example:
     * If s1 = "hungry" and
     * s2 = "hungary"
     * then the method returns 1.
     * If s1 = "close" and
     * s2 = "cat"
     * then the method returns -1.
     * If s1 = "cat" and
     * s2 = "cat"
     * then teh method returns 2.
     * If s1 = "trip" and
     * s2 = "cantrip"
     * then the method returns 3.
     * If s1 = "something" and
     * s2 = "different"
     * then the method returns -1.
     * If s1 or s2 is null, then the methd returns a value
     * which is smaller than -1.
     */
    public static int getMatchingRegionRight(String s1, String s2) {
        if (s1 != null) {
            if (s2 != null) {
                int k1 = s1.length();
                int k2 = s2.length();
                int min = Math.min(k1, k2);
                for (int i = 0; i < min; i++) {
                    if (s1.charAt(k1 - i) != s2.charAt(k2 - i)) return i - 1;
                }
                return min - 1;
            }
        }
        return -2;
    }

    /**
     * Removes every occurence of remove in source with replacement and
     * returns the result.
     * <p>
     * Note, that if remove
     * is a substring of replacement, it is still a substring of the
     * returned value.
     * <p>
     * Example: Input: source=hunger, remove=ung, replacement=umm
     * Output: hummer
     * Input: source= aabaabaa, remove=b, replacement= ab
     * Output: aaabaaabaa
     * Input: source= continent, remove=enter, replacement=humbug
     * Output: continent
     */
    public static String replaceEveryOccurrence(String source, String remove, String replacement) {
        int loc = source.indexOf(remove);
        int rpl = replacement.length();
        int rml = remove.length();
        while (loc != -1) {
            source = source.substring(0, loc) + replacement + source.substring(loc + rml);
            loc = source.indexOf(remove, loc + rpl);
        }
        return source;
    }


    /**
     * Puts the substring of the given text that is delimited by the
     * given indices where from is inclusive and to is exclusive to lower case.
     * <p>
     * If from is smaller than 0 then it is treated as if it were 0.
     * If to is larger than the length of the text, it is treated as
     * if it were the length of the text.
     * Example: Input: text = "Some BEAUTIFUL poem"
     * from = 8
     * to = 11
     * Output: "Some BEAutiFUL poem"
     */
    public static String substringToLowerCase(String text, int from, int to) {
        char[] chars = text.toCharArray();
        if (text != null) {
            if (from < 0) from = 0;
            if (to > text.length()) to = text.length();
            for (int i = from; i < to; i++) {
                chars[i] = Character.toLowerCase(chars[i]);
            }
            return new String(chars);
        } else return null;
    }


    /**
     * Returns the number of apppearances of the given pattern
     * in the given text.
     */
    public static int countAppearances(String text, String pattern) {
        int count = 0;
        int pointer = 0;
        if ((text != null) && (pattern != null)) {
            while ((pointer < text.length())) {
                int k = text.indexOf(pattern, pointer);
                if (k >= 0) {
                    pointer = k + 1;
                    count++;
                } else pointer = text.length();
            }
        }
        return count;
    }


    /**
     * @return A string of length s.length(), but every character
     * of this string is a space.
     */
    public static String phantom(String s) {
        String s1 = "";
        for (int i = 0; i < s.length(); i++) {
            s1 = s1 + " ";
        }
        return s1;
    }


    /**
     * If you compare two german words, one or both may contain german
     * umlauts. Using the String.compareTo()-method may lead to wrong results
     * for german words. For example, one would consider the word "ähnlich"
     * to appear before "zusammen" and to thus judged to be "smaller".
     * This is not what the compareTo-Mehtod of String produces, hence this method.
     *
     * @return A number smaller than 0 if s1 is lexicographically smaller than
     * s1, 0 if s1 equals s2 and a number larger than 0 if s1 is lexicographically
     * larger than s2.
     */
    public static int compareGerman(String s1, String s2) {
        String t1 = replaceEveryOccurrence(s1, "ä", "ae");
        t1 = replaceEveryOccurrence(t1, "ö", "oe");
        t1 = replaceEveryOccurrence(t1, "ü", "ue");
        t1 = replaceEveryOccurrence(t1, "ß", "sz");
        t1 = replaceEveryOccurrence(t1, "Ä", "Ae");
        t1 = replaceEveryOccurrence(t1, "Ö", "Oe");
        t1 = replaceEveryOccurrence(t1, "Ü", "Ue");
        String t2 = replaceEveryOccurrence(s2, "ä", "ae");
        t2 = replaceEveryOccurrence(t2, "ö", "oe");
        t2 = replaceEveryOccurrence(t2, "ü", "ue");
        t2 = replaceEveryOccurrence(t2, "ß", "sz");
        t2 = replaceEveryOccurrence(t2, "Ä", "Ae");
        t2 = replaceEveryOccurrence(t2, "Ö", "Oe");
        t2 = replaceEveryOccurrence(t2, "Ü", "Ue");
        return t1.compareTo(t2);
    }


    /**
     * @return A string s, where there is a line-break added behind every openDelim and
     * before and after each closeDelim. Additionally, there are put
     * indentSize*(number of openDelims active) spaces in front of every thus
     * existing line.
     */
    public static String indent(String s, String openDelim, String closeDelim, int indentSize) {
        String s1 = "";
        int i = s.indexOf(openDelim);
        int j = s.indexOf(closeDelim);
        int k = 0;
        int actualIndent = 0;
        while ((i != -1) || (j != -1)) {
            if ((i == -1) || (j < i)) {
                s1 = s1 +
                        getSpaces(actualIndent * indentSize) +
                        indentNewLines(s.substring(k, j), actualIndent * indentSize);
                s1 = s1 + "\n";
                actualIndent--;
                if (actualIndent < 0) {
                    s1 = s1 + "MISMATCHED PARANTHESES";
                    return s1;
                }
                s1 = s1 + getSpaces(actualIndent * indentSize) +
                        closeDelim + "\n";
                k = j + 1;
                i = s.indexOf(openDelim, j + 1);
                j = s.indexOf(closeDelim, j + 1);
            } else if ((j == -1) || (i < j)) {
                s1 = s1 +
                        getSpaces(actualIndent * indentSize) +
                        indentNewLines(s.substring(k, i), actualIndent * indentSize);
                s1 = s1 + openDelim + "\n";
                actualIndent++;
                k = i + 1;
                j = s.indexOf(closeDelim, i + 1);
                i = s.indexOf(openDelim, i + 1);
            }
        }
        return s1;
    }

    /**
     * @return A string that is derived from the given s such that
     * after every new line the given amount of indent spaces are inserted
     * if there are other characters besides the new-line in the same line.
     */
    public static String indentNewLines(String s, int indent) {
        String s1 = "";
        String spaces = getSpaces(indent);
        int i = s.indexOf("\n");
        int k = 0;
        while (i != -1) {
            int j = i + 1;
            while ((j < s.length()) && (s.charAt(j) == ' ')) j++;
            if ((j < s.length()) && (s.charAt(j) != '\n'))
                s1 = s1 + s.substring(k, i + 1) + spaces;
            else
                s1 = s1 + s.substring(k, i + 1);
            k = i + 1;
            i = s.indexOf("\n", k);
        }
        s1 = s1 + s.substring(k);
        return s1;
    }


    /**
     * @return A string consisting of spaces. The number
     * of spaces is determined by the given number.
     */
    public static String getSpaces(int number) {
        return getChars(number, ' ');
    }


    /**
     * @param number The number of times, the string is repeated.
     * @return A string consisting of a repetition of
     * the given string s.
     */
    public static String getRepetition(int number, String s) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < number; i++) sb.append(s);
        return sb.toString();
    }


    /**
     * @return A string that consists of the given char, repeated several
     * times, namely as often as indicated by the given number.
     */
    public static String getChars(int number, char c) {
        String s = "";
        for (int i = 0; i < number; i++) s = s + c;
        return s;
    }


    public static int getLevenshteinDistance(String s, String t) {
        int[][] d = new int[s.length()][t.length()];
        for (int i = 0; i < s.length(); i++)
            d[i][0] = i;
        for (int i = 0; i < t.length(); i++)
            d[0][i] = i;
        int c = 0;
        int m = 0;
        for (int j = 1; j < t.length(); j++) {
            for (int i = 1; i < s.length(); i++) {
                if (s.charAt(i) == t.charAt(j)) c = 0;
                else c = 1;
                if (d[i - 1][j] < d[i][j - 1]) m = d[i - 1][j];
                else m = d[i][j - 1];
                m++;
                if (d[i - 1][j - 1] + c < m) m = d[i - 1][j - 1] + c;
                d[i][j] = m;
            }
        }
        return d[s.length() - 1][t.length() - 1];
    }


    /**
     * @return A string representation of the content of the file
     * with the given file name.
     */
    public static String readString(String filename)
            throws IOException {
        LineNumberReader fis = new LineNumberReader(new FileReader(filename));
        String s = "";
        String line = "";
        while (line != null) {
            s = s + line;
            line = fis.readLine();
            if (line != null)
                line = line + "\n";
        }
        return s;
    }


    /**
     * @param message    The message of the header.
     * @param decorator  The string which shall be used to mark the header, e.g. "-".
     * @param size       The maximal length of the header line.
     * @param linesAbove The number of lines above the message line which shall
     *                   be filled with lines which are fille with decoration.
     * @param linesBelow The number of lines below the message line which shall
     *                   be filled with lines which are fille with decoration.
     * @param prefix     A prefix for all lines which are returned. For example, this
     *                   could be a comment marker, like "//".
     * @return A string which can be used as header, for example in source code.
     */
    public static String getHeaderDeco(String message, String decorator, int size,
                                       int linesAbove, int linesBelow, String prefix) {
        int decoSizeMainLine = Math.max(0, size - message.length() - prefix.length() - 2);
        int decoHalf = decoSizeMainLine / 2;
        int decoMod = decoSizeMainLine - 2 * decoHalf;
        String mainLine = prefix + fillUpTo(decorator, decoHalf) + " " + message + " " +
                fillUpTo(decorator, decoHalf + decoMod) + "\n";
        String s = "";
        String decoline = prefix + fillUpTo(decorator, size - prefix.length()) + "\n";
        for (int i = 0; i < linesAbove; i++) {
            s = s + decoline;
        }
        s = s + mainLine;
        for (int i = 0; i < linesBelow; i++) {
            s = s + decoline;
        }
        return s;
    }


    /**
     * @return A string that has the length which is given by the size argument.
     * Its content is the given pattern, which is repeated as often as
     * necessary in order to get a string of the necessary length.
     * The rightmost occurrence of the pattern in the returned string
     * may be cut (at its right end) if necessary.
     */
    public static String fillUpTo(String pattern, int size) {
        if ((pattern == null) || (pattern.length() == 0))
            return "";
        String s = "";
        while (s.length() + pattern.length() < size) {
            s = s + pattern;
        }
        s = s + pattern.substring(0, size - s.length());
        return s;
    }


    /**
     * Inserts spaces in front of the given s, until the result has the given length;
     * If the given string has a length at least as large as the given one, nothin happens.
     */
    public static String padToLength(String s, int length) {
        String t = s;
        while (t.length() < length) {
            t = " " + t;
        }
        return t;
    }


    /**
     * @returns A string, that is derived from the given source string such that
     * there are linebreaks inserted after separators, if the text following
     * the separator would be too long to fit into a line with the
     * given maxLineLength. After the linebreak, the text is indented
     * by the given amount of spaces.
     */
    public static String indentedLineBreaks(String source, String separator, int indent, int maxLineLength) {
        String s = "";
        int i = 0;
        int j = 0;
        String actLine = "";
        while (i < source.length()) {
            i = source.indexOf(separator, i);
            if (i == -1) {
                if (s.length() != 0) s = s + "\n" + getSpaces(indent);
                if (actLine.length() > 0) {
                    s = s + actLine.trim();
                    if (indent + actLine.length() + source.length() - j > maxLineLength)
                        s = s + "\n" + getSpaces(indent);
                }
                s = s + source.substring(j).trim();
                break;
            } else {
                i++;
                if (indent + actLine.length() + i - j <= maxLineLength) {
                    actLine = actLine + source.substring(j, i);
                } else {
                    if (actLine.length() == 0) {
                        if (s.length() != 0) s = s + "\n" + getSpaces(indent);
                        s = s + source.substring(j, i).trim();
                    } else {
                        if (s.length() != 0) s = s + "\n" + getSpaces(indent);
                        s = s + actLine.trim();
                        actLine = source.substring(j, i);
                    }
                }
            }
            j = i;
        }
        return s;
    }


    /**
     * @return A string, where the given position in the given text
     * is marked by the given marker.
     */
    public static String showPosition(String text, int pos, String marker) {
        return text.substring(0, pos) + marker + text.substring(pos);
    }


    /**
     * @return The line where the given position is in. The position
     * is marked by inserting the given marker at the respective place.
     */
    public static String showPositionLine(String text, int pos, String marker) {
        String s = marker;
        if ((pos >= 0) && (pos < text.length())) {
            int p = pos - 1;
            while ((p >= 0) && (text.charAt(p) != '\n')) {
                s = text.charAt(p) + s;
                p--;
            }
            p = pos;
            while ((p < text.length()) && (text.charAt(p) != '\n')) {
                s = s + text.charAt(p);
                p++;
            }
        }
        return s;
    }

    /**
     * @return The line where the given position is in. The method returns
     * three lines of text: The second contains exactly the line where
     * the given position is in. The first and the third point to that
     * position and are otherwise empty.
     * If the given position does not point into the given text,
     * an empty string is returned.
     */
    public static String showPositionLine(String text, int pos) {
        String s = "";
        if ((pos >= 0) && (pos < text.length())) {
            int p = pos - 1;
            int leftCount = 0;
            while ((p >= 0) && (text.charAt(p) != '\n')) {
                s = text.charAt(p) + s;
                p--;
                leftCount++;
            }
            p = pos;
            while ((p < text.length()) && (text.charAt(p) != '\n')) {
                s = s + text.charAt(p);
                p++;
            }
            s = getSpaces(leftCount) + "v\n" + s + "\n" + getSpaces(leftCount) + "^\n";
        }
        return s;
    }


    /**
     * @return The line where the given range is in. The method returns
     * three lines of text: The second contains exactly the line where
     * the given range is in. The first and the third point to that
     * range and are otherwise empty.
     * If the given position does not point into the given text,
     * an empty string is returned.
     */
    public static String showPositionLineRange(String text, int pos, int endpos) {
        return showPositionLineRange(text, pos, endpos, null);
    }

    /**
     * @see #showPositionLineRange(String, int, int)
     */
    public static String showPositionLineRange(String text, int pos, int endpos,
                                               String comment) {
        final String ELIPSIS = "[...]";
        final int HALFMAXLENGTH = 20;

        String s = "";

        if (pos > endpos) return s;
        if (pos == endpos) return showPositionLine(text, pos);

        if (pos + 1 == endpos) return showPositionLine(text, pos);
        endpos--;

        if ((pos >= 0) && (pos < text.length()) && ((endpos >= 0)) && (endpos < text.length())) {
            int p = endpos - 1;
            int midCount = 0;
            while ((p >= 0) && (p >= pos)) {
                if (midCount > HALFMAXLENGTH &&
                        (p > pos + HALFMAXLENGTH + ELIPSIS.length())) {
                    // Insert elipsis in middle part:
                    s = ELIPSIS + s;
                    midCount += ELIPSIS.length();
                    p = pos + HALFMAXLENGTH;
                } else if (text.charAt(p) == '\n') {
                    s = "\\n" + s;
                    p--;
                    midCount += 2;
                } else {
                    s = text.charAt(p) + s;
                    p--;
                    midCount++;
                }
            }
            int leftCount = 0;
            while ((p >= 0) && (text.charAt(p) != '\n')) {
                s = text.charAt(p) + s;
                p--;
                leftCount++;
            }
            p = endpos;
            s = s + (text.charAt(endpos) == '\n' ? "\\n" : "");
            while ((p < text.length()) && (text.charAt(p) != '\n')) {
                s = s + text.charAt(p);
                p++;
            }
            s = getSpaces(leftCount) + "v"
                    + getChars(midCount - 1, '.') + "v"
                    + "\n" + s + "\n"
                    + getSpaces(leftCount) + "^"
                    + getChars(midCount - 1, '.') + "^"
                    + (comment == null ? "" : comment)
                    + "\n";
        }
        return s;
    }


    /**
     * If the length of the given string is larger than maxLength,
     * the method interprets the given string as little-endian number,
     * where the characters of JAVA_CHARS are digits.
     * The method takes this number modulo
     * the biggest number which is representable with the given
     * destAlphabet and has length maxLength +1. It returns
     * a string representation of that number.
     * Note, that if you choose destAlphabet == JAVA_CHARS, this
     * method will remove the leftmost part of the input such that
     * the length becomes equal to maxLength.
     *
     * @param s            A string over the JAVA_CHARS alphabet (which is defined
     *                     in this class).
     * @param maxLength    The maximum length of the return value.
     * @param destAlphabet The alphabet over which to build the return
     *                     string, if the given s is longer than maxLength.
     *                     The size of this alphabet has to be at least 1.
     * @return s, if the length of s is at most maxLength and
     * a string of length maxLength, otherwise. The
     * method tries to avoid collisions, i.e. the probability
     * that two random inputs of length greater than maxLength
     * are mapped to the same string should be small.
     * This method implements a function, i.e. for every
     * input there is a unique output.
     */
    public static String mapToShorterString(String s, int maxLength, char[] destAlphabet) {
        if (s.length() > maxLength) {
            int radix = destAlphabet.length;
            BigInteger modulo = new BigInteger(new Integer(radix).toString());
            modulo = modulo.pow(maxLength);
            BigInteger sMapping = getNumber(s, JAVA_CHARS);
            return getStringRepresentation(sMapping.mod(modulo), destAlphabet);
        } else return s;
    }


    /**
     * This method implements a bijective function from the strings
     * over the given alphabet to the natural numbers.
     * null is treatet as the empty string "", which is mapped to 0.
     * If the alphabet is {a_0,...,a_(k-1)}, then the computed function
     * is \SUM_{i=0}^{s.length()-1} s[s.length()-i-1] * k^i,
     * where s[j] is the number associated with the character at position
     * j in s; s[0] points to  the leftmost position, which means that
     * we think of s as a little-endian number with radix k. The characters
     * are associated with numbers according to the given alphabet.
     * Hence, if alphabet = {c_1,...,c_t}, then c_i is associated with i
     * for i \in {1,...,t}.
     * It will lead to an error, if the given string is not over the
     * given alphabet.
     */
    public static BigInteger getNumber(String s, char[] alphabet) {
        if ((s == null) || (s.equals(""))) return new BigInteger("0");
        BigInteger big = new BigInteger("0");
        int radix = alphabet.length;
        BigInteger bigRadix = new BigInteger(new Integer(radix).toString());
        for (int j = 0; j < s.length(); j++) {
            big = big.multiply(bigRadix);
            int i = 0;
            while (alphabet[i] != s.charAt(j)) i++;
            big = big.add(new BigInteger(new Integer(i).toString()));
        }
        return big;
    }


    /**
     * @return A string representation of the given natural number as
     * a little-endian with radix k, where k is alphabet.length.
     * alphabet[i] is identified with i in this representation.
     */
    public static String getStringRepresentation(BigInteger n, char[] alphabet) {
        String s = "";
        if (n.compareTo(BigInteger.ZERO) == 0) {
            s = s + alphabet[0];
            return s;
        }
        BigInteger radix = new BigInteger(new Integer(alphabet.length).toString());
        while (n.compareTo(BigInteger.ZERO) == 1) {
            BigInteger[] r = n.divideAndRemainder(radix);
            n = r[0];
            BigInteger rest = r[1];
            s = alphabet[rest.intValue()] + s;
        }
        return s;
    }


    /**
     * Returns a string that is the same as the given string
     * but written backwards.
     * Example: Input: "zuff"
     * Output: "ffuz"
     *
     * @param s The string to be reversed.
     * @return A string that is the same as the given string
     * but written backwards.
     */
    public static String getReverse(String s) {
        byte[] buf1 = s.getBytes();
        byte[] buf2 = new byte[buf1.length];
        for (int i = 0; i < buf1.length; i++) {
            buf2[i] = buf1[buf1.length - i - 1];
        }
        return new String(buf2);
    }


    /**
     * Reads a string from the given InputStream and returns it.
     * The method will read until it reaches the end of the InputStream,
     * i.e. until {@link InputStream.read()} returns -1.
     *
     * @param input
     * @return
     */
    public static String readFromInputStream(InputStream input) throws IOException {
        String s = "";
        byte[] buff = new byte[Math.max(input.available(), 1)];
        int b = input.read();
        int p = 0;
        while (b != -1) {
            if (p >= buff.length) {
                byte[] largerBuff = new byte[buff.length * 2];
                for (int i = 0; i < buff.length; i++) largerBuff[i] = buff[i];
                buff = largerBuff;
            }
            buff[p] = (byte) b;
            p++;
            b = input.read();
        }
        if (p > 0) {
            s = new String(buff, 0, p, StandardCharsets.UTF_8);
        }
        return s;
    }


// ---------------------- Private helper methods -------------------------


}