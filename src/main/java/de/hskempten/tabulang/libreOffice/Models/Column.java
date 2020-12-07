package de.hskempten.tabulang.libreOffice.Models;

import java.util.HashMap;

public class Column {
    public HashMap<String, String> getAttributes() {
        return Attributes;
    }

    public void setAttributes(HashMap<String, String> attributes) {
        Attributes = attributes;
    }

    public HashMap<String,String> Attributes;
}
