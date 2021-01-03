package de.hskempten.tabulang.datatypes;

import java.awt.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;

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


    public Style() {
        this.annotations = new HashMap<>();
    }

    public Style(HashMap<String, String> annotations) {
        this.annotations = annotations;
    }

    public HashMap<String, String> getAnnotations() {
        return annotations;
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

    public Style setColor(Color c) {
        annotations.put(FONT_COLOR, String.format("#%02x%02x%02x", c.getRed(), c.getGreen(), c.getBlue()));
        return this;
    }

    public Style setBackgroundColor(String c) {
        annotations.put(BACKGROUND_COLOR, c);
        return this;
    }

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

    public Style setAttribute(String attribute, String value) {
        annotations.put(attribute, value);
        return this;
    }

    public Style reset() {
        this.annotations.clear();
        return this;
    }

    public Style importStyle(Style s) {
        annotations.putAll(s.annotations);
        return this;
    }

    public Style importStyleIfAbsent(Style s) {
        for (var a : s) {
            annotations.putIfAbsent(a.getKey(), a.getValue());
        }
        return this;
    }

    @Override
    public Style clone() {
        return new Style((HashMap<String, String>) this.annotations.clone());
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
