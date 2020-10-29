package de.hskempten.tabulang.importFromSql;

import de.hskempten.tabulang.importFromSql.Models.FilmText;

import java.sql.SQLException;
import java.util.Objects;

public class MainClass {
    public static void main(String[] args) throws SQLException {
        DatabaseConnection.OpenConnection();
        var query = "SELECT * FROM film_text";
        var list = DatabaseConnection.SelectStatement(query, FilmText.class);
        for(var item : Objects.requireNonNull(list)){
            System.out.format("%d:  %s --> %s", item.film_id, item.title, item.description).println();
        }
    }
}