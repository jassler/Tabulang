package de.hskempten.tabulang.mySql.Models;

public class DbConnectionParameters {
    public String Ip;
    public int Port;
    public String DbName;
    public String Username;
    public String Password;

    public DbConnectionParameters(String ip, int port, String dbName, String username, String password) {
        Ip = ip;
        Port = port;
        DbName = dbName;
        Username = username;
        Password = password;
    }
}
