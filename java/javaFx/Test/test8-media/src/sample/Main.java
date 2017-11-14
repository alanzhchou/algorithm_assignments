package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.SplitPane;
import javafx.stage.Stage;


public class Main extends Application {
    private final String MEDIA_URL = "http://owzbe53ov.bkt.clouddn.com/%E8%B4%BA%E8%A5%BF%E6%A0%BC%20-%20%E6%9D%A8%E6%9F%B3.mp3";

    @Override
    public void start(Stage primaryStage) throws Exception{
        SplitPane root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        Controller controller = new Controller(root,MEDIA_URL);
        controller.setMedia();

        primaryStage.setTitle("Media Demo");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
