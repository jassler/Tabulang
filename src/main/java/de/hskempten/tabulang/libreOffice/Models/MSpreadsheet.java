package de.hskempten.tabulang.libreOffice.Models;

import java.util.ArrayList;
import java.util.HashMap;

public class MSpreadsheet {
    /* PROPERTIES */
    private ArrayList<HashMap<String, String>> _fontStyles;
    private HashMap<String, HashMap<String, String>> _tableStyles;
    private HashMap<String, HashMap<String, String>> _rowStyles;
    private HashMap<String, HashMap<String, String>> _columnStyles;
    private HashMap<String, HashMap<String, String>> _cellStyles;
    private ArrayList<MTableWrapper> _tables;

    /* SETTER */
    public void set_fontStyles(ArrayList<HashMap<String, String>> _fontStyles) {
        this._fontStyles = _fontStyles;
    }

    public void set_tableStyles(HashMap<String, HashMap<String, String>> _tableStyles) {
        this._tableStyles = _tableStyles;
    }

    public void set_rowStyles(HashMap<String, HashMap<String, String>> _rowStyles) {
        this._rowStyles = _rowStyles;
    }

    public void set_columnStyles(HashMap<String, HashMap<String, String>> _columnStyles) {
        this._columnStyles = _columnStyles;
    }

    public void set_cellStyles(HashMap<String, HashMap<String, String>> _cellStyles) {
        this._cellStyles = _cellStyles;
    }

    public void set_tables(ArrayList<MTableWrapper> _tables) {
        this._tables = _tables;
    }

    /* GETTER */
    public ArrayList<HashMap<String, String>> get_fontStyles() {
        return _fontStyles;
    }

    public HashMap<String, HashMap<String, String>> get_tableStyles() {
        return _tableStyles;
    }

    public HashMap<String, HashMap<String, String>> get_rowStyles() {
        return _rowStyles;
    }

    public HashMap<String, HashMap<String, String>> get_columnStyles() {
        return _columnStyles;
    }

    public HashMap<String, HashMap<String, String>> get_cellStyles() {
        return _cellStyles;
    }

    public ArrayList<MTableWrapper> get_tables() {
        return _tables;
    }
}
