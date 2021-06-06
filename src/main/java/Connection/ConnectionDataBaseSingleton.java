package Connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class ConnectionDataBaseSingleton {

    private Connection connection;
    private static ConnectionDataBaseSingleton connectionDataBaseSingleton;

    private ConnectionDataBaseSingleton() {
        try {
            this.connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/test?useUnicode=true&serverTimezone=UTC", "root",
                    "Kechko194@");
        } catch (
                SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static ConnectionDataBaseSingleton getInstance() {
        if (connectionDataBaseSingleton == null) {
            connectionDataBaseSingleton = new ConnectionDataBaseSingleton();
        }
        return connectionDataBaseSingleton;
    }

    public Connection getConnection() {
        return connection;
    }
}
