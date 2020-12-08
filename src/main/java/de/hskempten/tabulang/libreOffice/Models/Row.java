package de.hskempten.tabulang.libreOffice.Models;

import java.util.ArrayList;
import java.util.HashMap;

public class Row {
    public HashMap<String, String> getAttributes() {
        return Attributes;
    }

    public void setAttributes(HashMap<String, String> attributes) {
        Attributes = attributes;
    }

    public ArrayList<Cell> get_cells() {
        return _cells;
    }

    public void set_cells(ArrayList<Cell> _cells) {
        this._cells = _cells;
    }

    public HashMap<String,String> Attributes;
    public ArrayList<Cell> _cells;
}