import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import model.Statistic;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;


public class Controller {


    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField urlTextField;

    @FXML
    private Button startButton;

    @FXML
    private TableView<?> dataTable;

    @FXML
    private TableColumn<Statistic, String> keyWordColumn;

    @FXML
    private TableColumn<Statistic, Integer> quantityColumn;

    @FXML
    void initialize() {

        keyWordColumn.setCellValueFactory(new PropertyValueFactory<Statistic, String>("keyWord"));
        quantityColumn.setCellValueFactory(new PropertyValueFactory<Statistic, Integer>("quantity"));
        startButton.setOnAction(event -> {
            String url = urlTextField.getText();
            System.out.println(url);
            Downloader.DownloadWebPage(url);
            File input = new File("Download.html");
            Document doc = null;
            try {
                doc = Jsoup.parse(input, "UTF-8", "http://example.com/");
            } catch (IOException e) {
                e.printStackTrace();
            }
            String text = doc.text();
            Parser parser = new Parser();
            try {
                dataTable.setItems(parser.read(text, url));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        assert urlTextField != null : "fx:id=\"urlTextField\" was not injected: check your FXML file 'sample.fxml'.";
        assert startButton != null : "fx:id=\"startButton\" was not injected: check your FXML file 'sample.fxml'.";
        assert dataTable != null : "fx:id=\"dataTable\" was not injected: check your FXML file 'sample.fxml'.";
        assert keyWordColumn != null : "fx:id=\"keyWordColumn\" was not injected: check your FXML file 'sample.fxml'.";
        assert quantityColumn != null : "fx:id=\"quantityColumn\" was not injected: check your FXML file 'sample.fxml'.";

    }
}

