package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionHelper {
    static Connection instance;
    static final String URL = "jdbc:sqlite:data.db";
    public static Connection getInstance() {
        if (instance == null){
            try {
                instance = DriverManager.getConnection(URL);
                System.out.println("connected");
            } catch (SQLException e) {
                throw new RuntimeException("connection error");
            }
        }
        return instance;
    }
}
