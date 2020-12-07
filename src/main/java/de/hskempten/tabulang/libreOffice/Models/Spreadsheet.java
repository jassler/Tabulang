package de.hskempten.tabulang.libreOffice.Models;

import java.util.ArrayList;
import java.util.HashMap;

public class Spreadsheet {
    public ArrayList<HashMap<String, String>> get_fontStyles() {
        return _fontStyles;
    }

    public void set_fontStyles(ArrayList<HashMap<String, String>> _fontStyles) {
        this._fontStyles = _fontStyles;
    }

    public HashMap<String, HashMap<String, String>> get_tableStyles() {
        return _tableStyles;
    }

    public void set_tableStyles(HashMap<String, HashMap<String, String>> _tableStyles) {
        this._tableStyles = _tableStyles;
    }

    public HashMap<String, HashMap<String, String>> get_rowStyles() {
        return _rowStyles;
    }

    public void set_rowStyles(HashMap<String, HashMap<String, String>> _rowStyles) {
        this._rowStyles = _rowStyles;
    }

    public HashMap<String, HashMap<String, String>> get_columnStyles() {
        return _columnStyles;
    }

    public void set_columnStyles(HashMap<String, HashMap<String, String>> _columnStyles) {
        this._columnStyles = _columnStyles;
    }

    public HashMap<String, HashMap<String, String>> get_cellStyles() {
        return _cellStyles;
    }

    public void set_cellStyles(HashMap<String, HashMap<String, String>> _cellStyles) {
        this._cellStyles = _cellStyles;
    }

    public ArrayList<TableWrapper> get_tables() {
        return _tables;
    }

    public void set_tables(ArrayList<TableWrapper> _tables) {
        this._tables = _tables;
    }

    public ArrayList<HashMap<String, String>> _fontStyles;
    public HashMap<String, HashMap<String, String>> _tableStyles;
    public HashMap<String, HashMap<String, String>> _rowStyles;
    public HashMap<String, HashMap<String, String>> _columnStyles;
    public HashMap<String, HashMap<String, String>> _cellStyles;
    public ArrayList<TableWrapper> _tables;
}
