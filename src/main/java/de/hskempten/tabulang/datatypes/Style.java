package de.hskempten.tabulang.datatypes;

import java.awt.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Style implements Iterable<Map.Entry<String, String>> {

    public static final String COLOR = "color";

    public static final String FONT = "font";
    public static final String BACKGROUNDCOLOR = "backgroundcolor";
    //    private ArrayList<DataAnnotation> annotations;
    private final HashMap<String, String> annotations;


    public Style() {
        this.annotations = new HashMap<>();
    }

    public HashMap<String, String> getAnnotations() {
        return annotations;
    }

    public Style setFont(String font) {
        annotations.put(FONT, font);
        return this;
    }

    public Style setColor(String c) {
        annotations.put(COLOR, c);
        return this;
    }

    public Style setColor(Color c) {
        annotations.put(COLOR, String.format("#%02x%02x%02x", c.getRed(), c.getGreen(), c.getBlue()));
        return this;
    }

    public Style setBackgroundColor(String c) {
        annotations.put(BACKGROUNDCOLOR, c);
        return this;
    }

    public Style setBackgroundColor(Color c) {
        annotations.put(BACKGROUNDCOLOR, String.format("#%02x%02x%02x", c.getRed(), c.getGreen(), c.getBlue()));
        return this;
    }

    public Style setAttribute(String attribute, String value) {
        annotations.put(attribute, value);
        return this;
    }

    public Style importStyle(Style s) {
        for (var a : s) {
            annotations.put(a.getKey(), a.getValue());
        }
        return this;
    }

    @Override
    public Iterator<Map.Entry<String, String>> iterator() {
        return annotations.entrySet().iterator();
    }
}
