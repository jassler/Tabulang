package de.hskempten.tabulang.libreOffice.Models;

import java.util.HashMap;

public class MColumn {
    /* PROPERTIES */
    private HashMap<String,String> Attributes;

    /* SETTER */
    public void setAttributes(HashMap<String, String> attributes) {
        Attributes = attributes;
    }

    /* GETTER */
    public HashMap<String, String> getAttributes() {
        return Attributes;
    }
}
