package de.hskempten.tabulang.libreOffice.Models;

import org.odftoolkit.odfdom.dom.style.props.OdfStyleProperty;

public class Style {
    public OdfStyleProperty property;
    public String value;

    public Style(OdfStyleProperty property, String value) {
        this.property = property;
        this.value = value;
    }


}
