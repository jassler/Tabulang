package de.hskempten.tabulang.libreOffice.Models;

import java.util.HashMap;

public class MCell {
    /* PROPERTIES */
    private HashMap<String,String> Attributes;
    private Object _value;

    /* SETTER */
    public void setAttributes(HashMap<String, String> attributes) {
        Attributes = attributes;
    }

    public void set_value(Object _value) {
        this._value = _value;
    }

    /* GETTER */
    public HashMap<String, String> getAttributes() {
        return Attributes;
    }

    public Object get_value() {
        return _value;
    }
}
