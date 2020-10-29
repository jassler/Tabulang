package de.hskempten.tabulang.importFromSql.Models;

public class FilmText {
    public int film_id;
    public String title;
    public String description;

    public int getId() {
        return film_id;
    }
    public void setFilmId(int id) { this.film_id = id; }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) { this.description = description; }
}
