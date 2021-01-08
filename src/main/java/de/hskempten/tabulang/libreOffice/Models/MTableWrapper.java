package de.hskempten.tabulang.libreOffice.Models;

import java.util.ArrayList;
import java.util.HashMap;

public class MTableWrapper {
    /* PROPERTIES */
    private HashMap<String,String> _attributes;
    private ArrayList<MColumn> _columns;
    private ArrayList<MRow> _rows;

    /* SETTER */
    public void set_attributes(HashMap<String, String> attributes) {
        this._attributes = attributes;
    }

    public void set_columns(ArrayList<MColumn> columns) {
        this._columns = columns;
    }

    public void set_rows(ArrayList<MRow> rows) {
        this._rows = rows;
    }

    /* GETTER */
    public HashMap<String, String> get_attributes() {
        return _attributes;
    }

    public ArrayList<MColumn> get_columns() {
        return _columns;
    }

    public ArrayList<MRow> get_rows() {
        return _rows;
    }
}
