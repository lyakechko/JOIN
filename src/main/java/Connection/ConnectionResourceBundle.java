package Connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ConnectionResourceBundle {

    public static Connection connection;

    static {
        try {
            connection = ConnectionResourceBundle.getConnection();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public static Connection getConnection() throws SQLException {
        ResourceBundle resource = ResourceBundle.getBundle("dataBaseFile");
        String url = resource.getString("url");
        String driver = resource.getString("driver");
        String user = resource.getString("user");
        String pass = resource.getString("password");

        try {
            Class.forName(driver).newInstance();
        } catch (ClassNotFoundException e) {
            throw new SQLException("Драйвер не загружен!");
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return DriverManager.getConnection(url, user, pass);
    }
}
