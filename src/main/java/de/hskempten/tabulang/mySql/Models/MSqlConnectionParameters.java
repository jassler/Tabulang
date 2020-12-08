package de.hskempten.tabulang.mySql.Models;

public class MSqlConnectionParameters {
    /* PROPERTIES */
    private String _ip;
    private int _port;

    public void set_ip(String _ip) {
        this._ip = _ip;
    }

    public void set_port(int _port) {
        this._port = _port;
    }

    public void set_dbName(String _dbName) {
        this._dbName = _dbName;
    }

    public void set_username(String _username) {
        this._username = _username;
    }

    public void set_password(String _password) {
        this._password = _password;
    }

    private String _dbName;
    private String _username;
    private String _password;

    /* GETTER */
    public String get_ip() {
        return _ip;
    }

    public int get_port() {
        return _port;
    }

    public String get_dbName() {
        return _dbName;
    }

    public String get_username() {
        return _username;
    }

    public String get_password() {
        return _password;
    }

    /* CONSTRUCTOR */
    public MSqlConnectionParameters(String ip, int port, String dbName, String username, String password) {
        _ip = ip;
        _port = port;
        _dbName = dbName;
        _username = username;
        _password = password;
    }
}
