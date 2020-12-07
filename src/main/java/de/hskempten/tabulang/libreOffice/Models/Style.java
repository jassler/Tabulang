package de.hskempten.tabulang.libreOffice.Models;

import org.odftoolkit.odfdom.dom.style.props.OdfStyleProperty;

public class Style {
    public OdfStyleProperty getProperty() {
        return property;
    }

    public void setProperty(OdfStyleProperty property) {
        this.property = property;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public OdfStyleProperty property;
    public String value;

    public Style(OdfStyleProperty property, String value) {
        this.property = property;
        this.value = value;
    }
}
