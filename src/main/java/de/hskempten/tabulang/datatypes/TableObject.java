package de.hskempten.tabulang.datatypes;

public abstract class TableObject {

    private final TableObject parent;

    public TableObject(TableObject parent) {
        this.parent = parent;
    }

    public TableObject getParent() {
        return parent;
    }
}
