package de.hskempten.tabulang.libreOffice.Models;

import java.util.ArrayList;
import java.util.HashMap;

public class TableWrapper {
    public HashMap<String, String> get_attributes() {
        return _attributes;
    }

    public void set_attributes(HashMap<String, String> _attributes) {
        this._attributes = _attributes;
    }

    public ArrayList<Column> get_columns() {
        return _columns;
    }

    public void set_columns(ArrayList<Column> _columns) {
        this._columns = _columns;
    }

    public ArrayList<Row> get_rows() {
        return _rows;
    }

    public void set_rows(ArrayList<Row> _rows) {
        this._rows = _rows;
    }

    public HashMap<String,String> _attributes;
    public ArrayList<Column> _columns;
    public ArrayList<Row> _rows;
}
