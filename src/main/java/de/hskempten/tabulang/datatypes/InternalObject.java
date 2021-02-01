package de.hskempten.tabulang.datatypes;

/**
 * Internal object representation. Every object can be annotated. This class only serves as a parent for
 * annotated objects, it does not hold any actual data. For this, see {@link InternalDataObject} (or
 * {@link InternalBoolean}, {@link InternalString} etc. for something more specific than just objects).
 */
public class InternalObject implements Styleable {

    private InternalObject parent;
    private Style style;

    /**
     * <p>Create internal object with set parent and no style. For no parent, set parent parameter to {@code null}
     * or call {@link InternalObject#InternalObject()}.</p>
     *
     * @param parent Parent object
     */
    public InternalObject(InternalObject parent) {
        this.parent = parent;
        this.style = new Style();
    }

    /**
     * <p>Create internal object with no parent and no style.</p>
     */
    public InternalObject() {
        this.style = new Style();
    }

    /**
     * <p>Parent object in tree structure. If object has no parent, it returns {@code null}</p>
     *
     * @return parent if exists, else null
     */
    public InternalObject getParent() {
        return parent;
    }

    /**
     * <p>Set parent object of InternalObject tree structure. It does not check for any cycles that may be introduced.</p>
     *
     * @param p Parent object
     */
    public void setParent(InternalObject p) {
        this.parent = p;
    }

    /**
     * <p>See if this object has a parent or not.</p>
     *
     * @return true if it has a parent (aka. parent is not null).
     */
    public boolean hasParent() {
        return this.parent != null;
    }

    /**
     * <p>Get Style object from this internal object.</p>
     *
     * <p>It does not contain any annotations it might inherit from its parents.
     * For this, use {@link InternalObject#computeStyle()}.</p>
     *
     * @return style object
     */
    @Override
    public Style getStyle() {
        return style;
    }

    /**
     * <p>Set Style for this object. It does not affect any styles from its parents' objects.</p>
     *
     * @param s Style to set it to
     */
    @Override
    public void setStyle(Style s) {
        this.style = s;
    }

    /**
     * <p>Get Style object with annotations from this and all its parent's Styles.</p>
     *
     * <p>Annotations from parent objects will not override annotations from their children (eg. if this object has an
     * annotation {@link Style#FONT_SIZE} and one of its parents has an annotation {@link Style#FONT_SIZE}, it will use
     * <i>this</i> object's font size annotation).</p>
     *
     * <p>Changes made to the Style returned will not affect the Style in this object. Either use
     * {@link InternalObject#setStyle(Style)} or {@link InternalObject#getStyle()} to modify its values.</p>
     *
     * @return computed Style object from this and all its parent's objects
     */
    @Override
    public Style computeStyle() {
        if (style == null)
            style = new Style();

        if (parent == null)
            return style;

        Style s = style.clone();
        InternalObject p = parent;
        while (p != null) {
            s.importStyleIfAbsent(p.getStyle());
            p = p.getParent();
        }
        return s;
    }
}
