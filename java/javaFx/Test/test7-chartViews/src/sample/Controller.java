package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.Pane;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Controller {
    private Pane root;
    private static ObservableList<XYChart.Data<String,Number>>
            data = FXCollections.observableArrayList();

    public Controller(Pane root){
        this.root = root;
    }

    public void open(String dataFile){
        BarChart<String,Number> bc = (BarChart<String, Number>)root.lookup("#barChart");
        loadData(dataFile);

        XYChart.Series<String,Number> series = new XYChart.Series<String,Number>();
        series.setData(data);

        bc.getData().add(series);
    }

    private void loadData(String dataFile) {
        try{
            BufferedReader reader = Files.newBufferedReader(Paths.get(dataFile));
            String line = null;
            String[] XYAxis = new String[2];
            while ((line = reader.readLine()) != null) {
                if (!line.startsWith("#")) {
                    XYAxis[0] = line.split(",")[0].replace("\"","");
                    XYAxis[1] = line.split(",")[2];
                    data.add(new XYChart.Data<String,Number>(XYAxis[0],new Float(XYAxis[1])));
                }
            }
        } catch (IOException x) {
            System.err.format("IOException: %s%n", x);
        }
    }
}
