package database;

import java.sql.*;

public class IncorrectSQLiteDBController extends SQLiteDBController{

    private static final IncorrectSQLiteDBController DB_CONTROLLER = new IncorrectSQLiteDBController();

    private IncorrectSQLiteDBController() {
    }

    public static IncorrectSQLiteDBController getInstance() {
        return DB_CONTROLLER;
    }

    public boolean checkLogin(String _username, String _password) throws SQLException {
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM users WHERE username = '" + _username + "' AND password ='"+ _password +"'");
        boolean success = rs.next();
        stmt.close();
        return success;
    }
}
