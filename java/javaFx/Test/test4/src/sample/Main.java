package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("Hello World");
        Pane root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setScene(new Scene(root, 300, 275));
        Label a = (Label)root.lookup("#label");
        TextField b = (TextField)root.lookup("#text");

        a.setRotate(-60);
        b.setText("123");
        b.setRotate(-25);
        System.out.println(a);
        System.out.println(b);
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}
