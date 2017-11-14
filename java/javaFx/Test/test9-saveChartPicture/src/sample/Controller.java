package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Controller {
    private Pane root;
    private Stage stage;
    private static ObservableList<XYChart.Data<String,Number>>
            data = FXCollections.observableArrayList();

    public Controller(Pane root,Stage stage){
        this.root = root;
        this.stage = stage;
    }

    public void open(String dataFile){
        BarChart<String,Number> bc = (BarChart<String, Number>)root.lookup("#barChart");
        loadData(dataFile);

        XYChart.Series<String,Number> series = new XYChart.Series<String,Number>();
        series.setData(data);

        bc.getData().add(series);
        setSaveButton(bc);
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

    private void setSaveButton(BarChart<String,Number> bc){
        Button save = (Button)root.lookup("#save");
        save.setOnAction((e)->{
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Save Chart");
            fileChooser.setInitialFileName("barchart.png");
            File selectedFile = fileChooser.showSaveDialog(stage);
            if (selectedFile != null) {
                try {
                    WritableImage snap = bc.snapshot(null, null);
                    ImageIO.write(SwingFXUtils.fromFXImage(snap, null),
                            "png", selectedFile);
                } catch (IOException exc) {
                    System.err.println(exc.getMessage());
                }
            }
        });
    }
}
