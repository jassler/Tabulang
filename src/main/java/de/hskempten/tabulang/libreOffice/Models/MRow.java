package de.hskempten.tabulang.libreOffice.Models;

import java.util.ArrayList;
import java.util.HashMap;

public class MRow {
    /* PROPERTIES */
    private HashMap<String,String> _attributes;
    private ArrayList<MCell> _cells;

    /* SETTER */
    public void setAttributes(HashMap<String, String> attributes) {
        _attributes = attributes;
    }
    public void set_cells(ArrayList<MCell> cells) {
        this._cells = cells;
    }

    /* GETTER */
    public HashMap<String, String> getAttributes() {
        return _attributes;
    }

    public ArrayList<MCell> get_cells() {
        return _cells;
    }
}