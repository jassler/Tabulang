package de.hskempten.tabulang.libreOffice.Models;

import java.util.ArrayList;
import java.util.HashMap;

public class MRow {
    /* PROPERTIES */
    private HashMap<String,String> Attributes;
    private ArrayList<MCell> _cells;

    /* SETTER */
    public void setAttributes(HashMap<String, String> attributes) {
        Attributes = attributes;
    }
    public void set_cells(ArrayList<MCell> _cells) {
        this._cells = _cells;
    }

    /* GETTER */
    public HashMap<String, String> getAttributes() {
        return Attributes;
    }

    public ArrayList<MCell> get_cells() {
        return _cells;
    }
}