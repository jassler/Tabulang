package de.hskempten.tabulang.mySql.Models;

import java.util.ArrayList;

public class MSqlTableContent {
    public void set_dbName(String _dbName) {
        this._dbName = _dbName;
    }

    public void set_headlines(ArrayList<String> _headlines) {
        this._headlines = _headlines;
    }

    public void set_content(ArrayList<ArrayList<String>> _content) {
        this._content = _content;
    }

    /* PROPERTIES */
    private String _dbName;
    private ArrayList<String> _headlines;
    private ArrayList<ArrayList<String>> _content;

    /* GETTER */
    public String get_dbName() {
        return _dbName;
    }

    public ArrayList<String> get_headlines() {
        return _headlines;
    }

    public ArrayList<ArrayList<String>> get_content() {
        return _content;
    }

    /* CONSTRUCTOR */
    public MSqlTableContent(String dbName, ArrayList<String> headlines, ArrayList<ArrayList<String>> content) {
        _dbName = dbName;
        _headlines = headlines;
        _content = content;
    }
}
