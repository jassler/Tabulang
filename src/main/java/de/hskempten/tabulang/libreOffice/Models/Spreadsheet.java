package de.hskempten.tabulang.libreOffice.Models;

import java.util.ArrayList;
import java.util.HashMap;

public class Spreadsheet {
    public ArrayList<HashMap<String, String>> _fontStyles;
    public HashMap<String, HashMap<String, String>> _tableStyles;
    public HashMap<String, HashMap<String, String>> _rowStyles;
    public HashMap<String, HashMap<String, String>> _columnStyles;
    public HashMap<String, HashMap<String, String>> _cellStyles;
    public ArrayList<TableWrapper> _tables;
}
