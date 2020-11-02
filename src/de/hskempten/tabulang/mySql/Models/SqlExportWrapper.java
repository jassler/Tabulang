package de.hskempten.tabulang.mySql.Models;

import java.util.ArrayList;

public class SqlExportWrapper {
    String Title;

    public String getTitle() {
        return Title;
    }

    public ArrayList<String> getHeadlines() {
        return Headlines;
    }

    public ArrayList<ArrayList<String>> getContent() {
        return Content;
    }

    ArrayList<String> Headlines;
    ArrayList<ArrayList<String>> Content;

    public SqlExportWrapper(String title, ArrayList<String> headlines, ArrayList<ArrayList<String>> content) {
        Title = title;
        Headlines = headlines;
        Content = content;
    }
}
