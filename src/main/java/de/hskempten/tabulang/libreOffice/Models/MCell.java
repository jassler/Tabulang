package de.hskempten.tabulang.libreOffice.Models;

import java.util.HashMap;

public class MCell {
    /* PROPERTIES */
    private HashMap<String,String> _attributes;
    private Object _value;

    /* SETTER */
    public void setAttributes(HashMap<String, String> attributes) {
        _attributes = attributes;
    }

    public void set_value(Object value) {
        this._value = value;
    }

    /* GETTER */
    public HashMap<String, String> getAttributes() {
        return _attributes;
    }

    public Object get_value() {
        return _value;
    }
}
