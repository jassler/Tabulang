package de.hskempten.tabulang.datatypes;

import java.util.function.Predicate;

public class DataCell<E> extends TableObject {

    private final E data;
    private final String name;
    private final Style style;
    private final Predicate<E> conditionalStyle;

//    public DataCell(E data, String name, Style style) {
//        this(data, name, style, null);
//    }

    public DataCell(E data, String name, Style style, TableObject parent) {
        this(data, name, style, parent, null);
    }

    public DataCell(E data, String name, Style style, TableObject parent, Predicate<E> conditionForStyleToApply) {
        super(parent);
        this.data = data;
        this.name = name;
        this.style = style;
        this.conditionalStyle = conditionForStyleToApply;
    }

    public E getData() {
        return data;
    }

    public String getName() {
        return name;
    }

    public Style getStyle() {
        if (conditionalStyle == null || conditionalStyle.test(data))
            return style;
        return null;
    }
}
