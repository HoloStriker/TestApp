package database;

import java.sql.*;

public class CorrectSQLiteDBController extends SQLiteDBController{

    private static final CorrectSQLiteDBController DB_CONTROLLER = new CorrectSQLiteDBController();

    private CorrectSQLiteDBController() {
    }

    public static CorrectSQLiteDBController getInstance() { return DB_CONTROLLER; }

    public boolean checkLogin(String _username, String _password) throws SQLException {
        PreparedStatement ps = connection.prepareStatement("SELECT * FROM users WHERE username = ? AND password = ?;");
        ps.setString(1, _username);
        ps.setString(1, _password);
        ResultSet rs = ps.executeQuery();

        boolean success = rs.next();

        ps.close();

        return success;
    }
}
