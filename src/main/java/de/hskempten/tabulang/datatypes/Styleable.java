package de.hskempten.tabulang.datatypes;

/**
 * <p>Internal objects that can hold data attributes. Used for generics.</p>
 *
 * <p>For implementation and more documentation, see {@link InternalObject}</p>
 */
public interface Styleable {

    Style computeStyle();
    Style getStyle();
    void setStyle(Style s);

}
