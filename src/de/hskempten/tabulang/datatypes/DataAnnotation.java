package de.hskempten.tabulang.datatypes;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class DataAnnotation {

    private String key;
    private String value;

    public DataAnnotation(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }
}
