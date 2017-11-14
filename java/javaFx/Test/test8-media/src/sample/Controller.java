package sample;

import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.util.Duration;

public class Controller {
    private SplitPane root;
    private final String MEDIA_URL;

    public Controller(SplitPane root, String url){
        this.root = root;
        this.MEDIA_URL = url;
    }

    public void setMedia(){
        AnchorPane mediaPane = (AnchorPane) root.getItems().get(0);
        MediaView mediaView = (MediaView) mediaPane.lookup("#mediaView");

        Media media = new Media(MEDIA_URL);
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaView.setMediaPlayer(mediaPlayer);

        setButtonAction(mediaPlayer);
    }

    private void setButtonAction(MediaPlayer mediaPlayer){
        AnchorPane setting = ((AnchorPane) root.getItems().get(1));

        Button playButton = (Button) setting.lookup("#startButton");
        playButton.setOnAction(e -> {
            if (playButton.getText().equals(">")) {
                mediaPlayer.play();
                playButton.setText("||");
            } else {
                mediaPlayer.pause();
                playButton.setText(">");
            }
        });

        Button rePlay = (Button) setting.lookup("#restartButton");
        rePlay.setOnAction(e->mediaPlayer.seek(Duration.ZERO));

        Slider slVolume = (Slider)setting.lookup("#volumeSlider");
        slVolume.setValue(50);
        mediaPlayer.volumeProperty().bind(slVolume.valueProperty().divide(100));
    }
}
