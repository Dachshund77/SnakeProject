package Domain.Sound;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;
import java.util.ArrayList;

/**
 * Simple MediaPlayer That can plat Background music (indefinitely) or once a soundEffect.
 */
public class SoundPlayer {

    private static MediaPlayer backGroundMusic;
    private static ArrayList<MediaPlayer> soundEffects = new ArrayList<>();

    private static SimpleDoubleProperty soundEffectVolume = new SimpleDoubleProperty(1);
    private static SimpleDoubleProperty backgroundMusicVolume = new SimpleDoubleProperty(1);

    /**
     * Plays a MediaFile once. Disposed the MediaPlayer after one Cycle.
     * @param file The File to be played.
     */
    public static void playSoundEffect(File file){
        System.out.println("SoundPlayer.playSoundEffect");
        Media media = new Media(file.toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        soundEffects.add(mediaPlayer); //Need to keep a reference so it does not becomes garbage collected.
        mediaPlayer.volumeProperty().bind(soundEffectVolume);
        mediaPlayer.setOnEndOfMedia(() -> {
            mediaPlayer.dispose();
            soundEffects.remove(mediaPlayer);
        });

        mediaPlayer.play();
    }

    /**
     * Plays a MediaFile as background music.
     * @param file The File to be played.
     */
    public static void playBackGroundMusic(File file){ //If there is ever a problem with suddenly stopping, use a MediaView
        System.out.println("SoundPlayer.playBackGroundMusic");
        if (backGroundMusic != null){
            backGroundMusic.dispose();
        }
        Media media = new Media(file.toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        mediaPlayer.volumeProperty().bind(backgroundMusicVolume);
        mediaPlayer.play();

        backGroundMusic = mediaPlayer;
    }

    /**
     * Disposed the backgroundMedia Player. This will make the music stop.
     */
    public static void disposeBackgroundMusic(){
        if (backGroundMusic != null){
            backGroundMusic.dispose();
        }
        backGroundMusic = null;
    }
}
