package dao;

import model.Statistic;
import utils.ConnectionHelper;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class StatisticDAO {
    static private final String INSERT_STATISTIC = "INSERT INTO Statistic ( request_id, keyword, quantity)\n" +
            "VALUES( %d, '%s', %d)";
    static private final String GET_REQUEST_ID = "SELECT MAX(id) FROM Requests";
    private static Connection connection = ConnectionHelper.getInstance();

    public int getGetRequestId (){
        Statement statement;
        int lastId = 0;
        try {
            statement = connection.createStatement();
            ResultSet getId;
            getId = statement.executeQuery (GET_REQUEST_ID);
            while(getId.next()){
                lastId=getId.getInt("MAX(id)");
            }
            getId.close();
            System.out.println("last request ID = " + lastId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lastId;
    }
    public void insert(Statistic statistic) {
        Statement statement;
        try {
            statement = connection.createStatement();
            statement.execute(String.format(INSERT_STATISTIC, statistic.getRequestId(), statistic.getKeyWord(),
                    statistic.getQuantity()));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
