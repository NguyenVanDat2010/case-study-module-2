
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;

public class Main extends Application {
    private static final String MEDIA_URL =
            new File("sample.mp3").toURI().toString();
    @Override
    public void start(Stage primaryStage) {
        Media media = new Media(MEDIA_URL);
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        MediaView mediaView = new MediaView(mediaPlayer);

        Button playButton = new Button("Play");
        playButton.setOnAction(e -> {
            if (playButton.getText().equals("Play")) {
                mediaPlayer.play();
                playButton.setText("Pause");
            } else {
                mediaPlayer.pause();
                playButton.setText("Play");
            }
        });

        Button stopButton = new Button("Stop");
        stopButton.setOnAction(e -> {
            mediaPlayer.seek(Duration.ZERO);
            mediaPlayer.pause();
            playButton.setText("Play");
        });

        Button muteButton = new Button("Mute");
        muteButton.setOnAction(e -> {
            if (muteButton.getText().equals("Mute")) {
                mediaPlayer.setMute(true);
                muteButton.setText("Unmute");
            }
            else {
                mediaPlayer.setMute(false);
                muteButton.setText("Mute");
            }
        });

        Slider slVolumne = new Slider();
        slVolumne.setPrefWidth(150);
        slVolumne.setMaxWidth(Region.USE_PREF_SIZE);
        slVolumne.setMinWidth(30);
        slVolumne.setValue(50);
        mediaPlayer.volumeProperty().bind(slVolumne.valueProperty().divide(100));

        HBox hBox = new HBox(10);
        hBox.setAlignment(Pos.CENTER);
        hBox.getChildren().addAll(playButton,stopButton,muteButton, new Label("Volumne"),slVolumne);

        BorderPane pane = new BorderPane();
        pane.setCenter(mediaView);
        pane.setBottom(hBox);

        Scene scene = new Scene(pane,650,500);
        primaryStage.setTitle("MediaDemo");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}
