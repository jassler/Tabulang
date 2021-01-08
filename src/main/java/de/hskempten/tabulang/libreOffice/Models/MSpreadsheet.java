package de.hskempten.tabulang.libreOffice.Models;

import de.hskempten.tabulang.datatypes.InternalString;
import de.hskempten.tabulang.datatypes.Tuple;

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
    public void set_fontStyles(ArrayList<HashMap<String, String>> fontStyles) {
        this._fontStyles = fontStyles;
    }

    public void set_tableStyles(HashMap<String, HashMap<String, String>> tableStyles) {
        this._tableStyles = tableStyles;
    }

    public void set_rowStyles(HashMap<String, HashMap<String, String>> rowStyles) {
        this._rowStyles = rowStyles;
    }

    public void set_columnStyles(HashMap<String, HashMap<String, String>> columnStyles) {
        this._columnStyles = columnStyles;
    }

    public void set_cellStyles(HashMap<String, HashMap<String, String>> cellStyles) {
        this._cellStyles = cellStyles;
    }

    public void set_tables(ArrayList<MTableWrapper> tables) {
        this._tables = tables;
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

    public ArrayList<InternalString> row_to_list(int tableIndex, int rowNum) {
        var row = _tables.get(tableIndex).get_rows().get(rowNum).get_cells();
        var result = new ArrayList<InternalString>(row.size() - 1);

        for(int i = 0; i < row.size() - 1; i++) {
            result.add(new InternalString((String) row.get(i).get_value()));
        }

        return result;
    }

    public ArrayList<InternalString> get_headlines(int index){
        return row_to_list(index, 0);
    }

    public ArrayList<Tuple<InternalString>> get_values(int index){

        var rows = _tables.get(index).get_rows();
        var returnList = new ArrayList<Tuple<InternalString>>(rows.size());

        for (int row = 1; row < rows.size() - 1; row++) {
            returnList.add(new Tuple<>(row_to_list(index, row)));
        }

        return returnList;
    }
}
