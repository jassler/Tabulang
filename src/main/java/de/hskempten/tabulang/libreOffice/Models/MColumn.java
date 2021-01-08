package de.hskempten.tabulang.libreOffice.Models;

import java.util.HashMap;

public class MColumn {
    /* PROPERTIES */
    private HashMap<String,String> _attributes;

    /* SETTER */
    public void setAttributes(HashMap<String, String> attributes) {
        _attributes = attributes;
    }

    /* GETTER */
    public HashMap<String, String> getAttributes() {
        return _attributes;
    }
}
