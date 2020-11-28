package de.hskempten.tabulang.datatypes;

public class DataCell<E> {

    private E data;
    private String name;
    private Style style;

    public DataCell(E data, String name, Style style) {
        this.data = data;
        this.name = name;
        this.style = style;
    }

    public E getData() {
        return data;
    }

    public String getName() {
        return name;
    }

    public Style getStyle() {
        return style;
    }
}
