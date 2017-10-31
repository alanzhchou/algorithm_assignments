package sample;

import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    private Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();

    private static Random rand_generator;

    private double x;
    private double y;

    @Override
    public void initialize(URL location, ResourceBundle resources){
        rand_generator = new Random();
    }

    public void moveWindow(MouseEvent me){
        Node node = (Node) me.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        double height = screenBounds.getHeight();
        double width = screenBounds.getWidth();

        double x_move = width/10 + rand_generator.nextDouble() * width/2;
        double y_move = height/10 + rand_generator.nextDouble() * height/2;

        this.x = (double)((long)(this.x + x_move)%(long)(width - stage.getWidth()));
        this.y = (double)((long)(this.y + y_move)%(long)(height - stage.getHeight()));

        stage.setX(this.x);
        stage.setY(this.y);
    }
}