package utils;

import dao.StatisticDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Statistic;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CollectionHelper {

    public static ObservableList<Statistic> mapToObservableList(Map<String, Integer> map) {
        List<Statistic> list = new ArrayList<>();
        StatisticDAO statisticDAO = new StatisticDAO();
        int requestId = statisticDAO.getGetRequestId();
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            Statistic statistic = new Statistic(requestId, entry.getKey(), entry.getValue());
            statisticDAO.insert(statistic);
            list.add(new Statistic(-1, entry.getKey(), entry.getValue()));
        }
        return FXCollections.observableList(list);
    }
}
