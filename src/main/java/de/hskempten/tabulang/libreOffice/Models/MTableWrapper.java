package de.hskempten.tabulang.libreOffice.Models;

import java.util.ArrayList;
import java.util.HashMap;

public class MTableWrapper {
    /* PROPERTIES */
    private HashMap<String,String> _attributes;
    private ArrayList<MColumn> _columns;
    private ArrayList<MRow> _rows;

    /* SETTER */
    public void set_attributes(HashMap<String, String> _attributes) {
        this._attributes = _attributes;
    }

    public void set_columns(ArrayList<MColumn> _columns) {
        this._columns = _columns;
    }

    public void set_rows(ArrayList<MRow> _rows) {
        this._rows = _rows;
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
