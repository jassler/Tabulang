package de.hskempten.tabulang.libreOffice.Models;

import org.odftoolkit.odfdom.dom.style.props.OdfStyleProperty;

public class MStyle {
    /* PROPERTIES */
    private OdfStyleProperty property;
    private String value;

    /* SETTER */
    public void setProperty(OdfStyleProperty property) {
        this.property = property;
    }

    public void setValue(String value) {
        this.value = value;
    }

    /* GETTER */
    public OdfStyleProperty getProperty() {
        return property;
    }
    public String getValue() {
        return value;
    }

    /* CONSTRUCTOR */

    public MStyle(OdfStyleProperty property, String value) {
        this.property = property;
        this.value = value;
    }
}
