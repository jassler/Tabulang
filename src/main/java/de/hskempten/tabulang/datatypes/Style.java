package de.hskempten.tabulang.datatypes;

import java.awt.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;

/**
 * Style class for internal data annotations.
 */
public class Style implements Iterable<Map.Entry<String, String>>, Cloneable {

    public static final String FONT_COLOR = "font-color";
    public static final String FONT_FAMILY = "font-family";
    public static final String FONT_SIZE = "font-size";
    public static final String TEXT_ALIGN = "text-align";
    public static final String BACKGROUND_COLOR = "background-color";
    public static final String BOLD = "bold";
    public static final String ITALICS = "italics";
    public static final String UNDERLINED = "underlined";
    public static final String ROW_HEIGHT = "rowheight";
    public static final String COLUMN_WIDTH = "colwidth";


    private final HashMap<String, String> annotations;

    /**
     * Create new Style object with no annotations attached.
     */
    public Style() {
        this.annotations = new HashMap<>();
    }

    /**
     * <p>Create new Style object with set annotations.</p>
     *
     * <p>This list is deep-copied. To copy annotations from another Style object, see {@link Style#clone()}.</p>
     *
     * @param annotations Initialize annotations
     */
    public Style(HashMap<String, String> annotations) {
        this.annotations = new HashMap<>(annotations);
    }

    /**
     * <p>Get annotations object reference. While possible, it is discouraged to alter annotations this way.</p>
     *
     * @return annotations object
     */
    public HashMap<String, String> getAnnotations() {
        return annotations;
    }

    /**
     * <p>Check if map of annotations is empty.</p>
     *
     * @return true if no annotation has been applied yet
     */
    public boolean isEmpty() {
        return annotations.isEmpty();
    }

    public Style setFontSize(double size) {
        annotations.put(FONT_SIZE, Double.toString(size));
        return this;
    }

    public Style setFontSize(String align) {
        annotations.put(TEXT_ALIGN, align);
        return this;
    }

    public Style setFont(String font) {
        annotations.put(FONT_FAMILY, font);
        return this;
    }

    public Style setColor(String c) {
        annotations.put(FONT_COLOR, c);
        return this;
    }

    /**
     * <p>Set {@link Style#FONT_COLOR} to given color object. Transforms color into hex-representation.</p>
     *
     * @param c Color object
     * @return this Style object
     */
    public Style setColor(Color c) {
        annotations.put(FONT_COLOR, String.format("#%02x%02x%02x", c.getRed(), c.getGreen(), c.getBlue()));
        return this;
    }

    public Style setBackgroundColor(String c) {
        annotations.put(BACKGROUND_COLOR, c);
        return this;
    }

    /**
     * <p>Set {@link Style#BACKGROUND_COLOR} to given color object. Transforms color into hex-representation.</p>
     *
     * @param c Color object
     * @return this Style object
     */
    public Style setBackgroundColor(Color c) {
        annotations.put(BACKGROUND_COLOR, String.format("#%02x%02x%02x", c.getRed(), c.getGreen(), c.getBlue()));
        return this;
    }

    public Style setBold(boolean bold) {
        annotations.put(BOLD, Boolean.toString(bold));
        return this;
    }

    public Style setItalics(boolean italics) {
        annotations.put(ITALICS, Boolean.toString(italics));
        return this;
    }

    public Style setUnderlined(boolean underlined) {
        annotations.put(UNDERLINED, Boolean.toString(underlined));
        return this;
    }

    /**
     * <p>Set any String attribute to any String value. Preexisting annotations will be overridden.</p>
     *
     * @param attribute Annotation name
     * @param value Annotation value
     * @return this Style object
     */
    public Style setAttribute(String attribute, String value) {
        annotations.put(attribute, value);
        return this;
    }

    /**
     * <p>Remove all annotations.</p>
     *
     * @return this Style object
     */
    public Style reset() {
        this.annotations.clear();
        return this;
    }

    /**
     * <p>Add all annotations from another Style to this Style object. Annotations with the same name will be
     * overridden.</p>
     *
     * <p>If you wish to only add Style annotations that are not in this Style object yet,
     * see {@link Style#importStyleIfAbsent(Style)}</p>
     *
     * @param s Style to import
     * @return this Style object
     */
    public Style importStyle(Style s) {
        annotations.putAll(s.annotations);
        return this;
    }

    /**
     * <p>Add all annotations from another Style to this Style object. Annotations with the same name will be
     * ignored (see {@link HashMap#putIfAbsent(Object, Object)}).</p>
     *
     * <p>If you wish to override preexisting annotations with the same name, see {@link Style#importStyle(Style)}.</p>
     *
     * @param s Annotations to import that are not in the current map of annotations
     * @return this Style object
     */
    public Style importStyleIfAbsent(Style s) {
        for (var a : s) {
            annotations.putIfAbsent(a.getKey(), a.getValue());
        }
        return this;
    }

    @SuppressWarnings("MethodDoesntCallSuperMethod")
    @Override
    public Style clone() {
        return new Style(this.annotations);
    }

    @Override
    public Iterator<Map.Entry<String, String>> iterator() {
        return annotations.entrySet().iterator();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Style entries = (Style) o;
        return annotations.equals(entries.annotations);
    }

    @Override
    public int hashCode() {
        return Objects.hash(annotations);
    }

    @Override
    public String toString() {
        return "Style{" +
                "annotations=" + annotations +
                '}';
    }
}
