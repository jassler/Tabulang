package de.hskempten.tabulang.datatypes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public class Style implements Iterable {

    private ArrayList<DataAnnotation> annotations;

    public static final String FONT = "font";

    public Style(DataAnnotation... annotations) {
        this.annotations = new ArrayList<>(annotations.length);
        this.annotations.addAll(Arrays.asList(annotations));
    }

    public ArrayList<DataAnnotation> getAnnotations() {
        return annotations;
    }

    public Style addFont(String fonttype) {
        annotations.add(new DataAnnotation("font", fonttype));
        return this;
    }

    public Style addColor(String c) {
        annotations.add(new DataAnnotation("color", c));
        return this;
    }

    public Style addBackgroundColor(String c) {
        annotations.add(new DataAnnotation("background", c));
        return this;
    }

    @Override
    public Iterator<DataAnnotation> iterator() {
        return annotations.iterator();
    }
}
