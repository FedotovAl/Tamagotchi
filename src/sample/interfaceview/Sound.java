package sample.interfaceview;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.nio.file.Paths;

import static sample.files.FilesConstNames.SOUNDTRACK;
import static sample.gameprocess.GameSettings.SOUNDVOLUME;

public class Sound {
    private static String path = Paths.get(SOUNDTRACK).toUri().toString();
    private static Media media = new Media(path);
    private static MediaPlayer mediaPlayer = new MediaPlayer(media);

    public static void playMusic(){
        mediaPlayer.setVolume(SOUNDVOLUME);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        mediaPlayer.play();

    }
    public static void muteMusic(){
        if(!mediaPlayer.isMute()){
            mediaPlayer.setMute(true);
        }
        else mediaPlayer.setMute(false);
    }

}
