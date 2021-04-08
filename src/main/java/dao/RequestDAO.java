package dao;

import model.Request;
import utils.ConnectionHelper;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class RequestDAO {
    static private final String INSERT_REQUESTS = "INSERT INTO Requests (url, dateTime) VALUES('%s',%d)";

    private static Connection connection = ConnectionHelper.getInstance();
    public void insert(Request request) {
        Statement statement;
        try {
            statement = connection.createStatement();
            statement.execute(String.format(INSERT_REQUESTS, request.getUrl(), request.getDateTime()));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
