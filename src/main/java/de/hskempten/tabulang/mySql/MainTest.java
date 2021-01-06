package de.hskempten.tabulang.mySql;

import de.hskempten.tabulang.mySql.Models.MSqlConnectionParameters;

/**
 * @author Simon Staiger
 * @project Projekt-HSKempten-Java
 */
public class MainTest {
    public static void main(String[] args){
        var query = "SELECT a.first_name, a.last_name, f.title\n" +
                "FROM sakila.actor as a \n" +
                "JOIN sakila.film_actor as fa \n" +
                "ON a.actor_id = fa.actor_id \n" +
                "JOIN sakila.film as f \n" +
                "ON f.film_id = fa.film_id \n" +
                "WHERE (a.last_name LIKE 'A%' OR a.last_name LIKE 'S%') \n" +
                "AND f.title LIKE 'J%';";
        var query2 = "SELECT f.title\n" +
                "FROM sakila.actor as a \n" +
                "JOIN sakila.film_actor as fa \n" +
                "ON a.actor_id = fa.actor_id \n" +
                "JOIN sakila.film as f \n" +
                "ON f.film_id = fa.film_id \n" +
                "WHERE (a.last_name LIKE 'A%' OR a.last_name LIKE 'S%') \n" +
                "AND f.title LIKE 'J%'\n" +
                "GROUP BY f.title";
        var parameters = new MSqlConnectionParameters("85.214.33.119", 3306, "sakila", "db_user", "HsKemptenProjekt2020");
        DatabaseConnection.OpenConnection(parameters);
        var table = DatabaseConnection.ExportAsTable(query);
        System.out.println(table);
        var table2 = DatabaseConnection.ExportAsTable(query2);
        System.out.println(table2);
    }
}
