package de.hskempten.tabulang.libreOffice.Models;

import java.util.HashMap;

public class Cell {
    public HashMap<String,String> Attributes;
    public Object _value;

    public HashMap<String, String> getAttributes() {
        return Attributes;
    }

    public void setAttributes(HashMap<String, String> attributes) {
        Attributes = attributes;
    }

    public Object get_value() {
        return _value;
    }

    public void set_value(Object _value) {
        this._value = _value;
    }
}
