package de.hskempten.tabulang.datatypes;

public abstract class InternalObject implements Styleable {

    private InternalObject parent;
    private Style style;

    public InternalObject(InternalObject parent) {
        this.parent = parent;
        this.style = new Style();
    }

    public InternalObject() {
        this.style = new Style();
    }

    public InternalObject getParent() {
        return parent;
    }

    public void setParent(InternalObject p) {
        this.parent = p;
    }

    public boolean hasParent() {
        return this.parent != null;
    }

    @Override
    public Style getStyle() {
        return style;
    }

    @Override
    public void setStyle(Style s) {
        this.style = s;
    }

    @Override
    public Style computeStyle() {
        if(style == null)
            style = new Style();

        if(parent == null)
            return style;

        Style s = style.clone();
        InternalObject p = parent;
        while(p != null) {
            s.importStyleIfAbsent(p.getStyle());
            p = p.getParent();
        }
        return style;
    }
}
