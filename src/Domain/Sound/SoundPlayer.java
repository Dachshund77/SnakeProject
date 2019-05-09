package Domain.Sound;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;
import java.util.ArrayList;
import java.util.prefs.Preferences;

/**
 * Simple Singelton of a MediaPlayer that can plat Background music (indefinitely) or once a soundEffect.
 */
public class SoundPlayer {

    private static SoundPlayer ourInstance = new SoundPlayer();

    public static SoundPlayer getInstance() {
        return ourInstance;
    }

    private MediaPlayer backGroundMusic;
    private ArrayList<MediaPlayer> soundEffects = new ArrayList<>();

    private SimpleDoubleProperty soundEffectVolume;
    private SimpleDoubleProperty backgroundMusicVolume;

    private SoundPlayer() {
        Preferences preferences = Preferences.userNodeForPackage(SoundPlayer.class);
        soundEffectVolume = new SimpleDoubleProperty(preferences.getDouble("soundEffectVolume",1.));
        backgroundMusicVolume = new SimpleDoubleProperty(preferences.getDouble("backgroundMusicVolume",1.));
    }

    /**
     * Plays a MediaFile once.
     * @param file The File to be played.
     */
    public void playSoundEffect(File file){
        Media media = new Media(file.toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        soundEffects.add(mediaPlayer); //Need to keep a reference so it does not becomes garbage collected.
        mediaPlayer.volumeProperty().bind(soundEffectVolume);
        mediaPlayer.setOnEndOfMedia(() -> {
            //mediaPlayer.dispose(); //This creates issue with performance
            soundEffects.remove(mediaPlayer);
        });

        mediaPlayer.play();
    }

    /**
     * Plays a MediaFile as background music.
     * @param file The File to be played.
     */
    public void playBackGroundMusic(File file){ //If there is ever a problem with suddenly stopping, use a MediaView
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
     * Stops the backgroundMedia Player. This will make the music stop.
     */
    public void disposeBackgroundMusic(){
        if (backGroundMusic != null){
            backGroundMusic.stop();
            backGroundMusic = null;
            //backGroundMusic.dispose(); //Creates performance issue
        }

    }

    /**
     * Removes a specific sound effect and stops it.
     * @param mediaPlayer MediaPlayer object to be removed.
     */
    public void disposeSoundEffect(MediaPlayer mediaPlayer){
        soundEffects.remove(mediaPlayer);
        mediaPlayer.stop();
        //mediaPlayer.dispose(); // Creates performance issue
    }

    /**
     * Removes all soundEffects and disposes them. Effectively stopping all Sound effect.
     */
    public void disposeAllSoundeffects(){
        //Creates formance issue
        for (MediaPlayer soundEffect : soundEffects) {
            //soundEffect.dispose();
            soundEffect.stop();
        }
        soundEffects.clear();
    }

    public double getSoundEffectVolume() {
        return soundEffectVolume.get();
    }

    public SimpleDoubleProperty soundEffectVolumeProperty() {
        return soundEffectVolume;
    }

    public double getBackgroundMusicVolume() {
        return backgroundMusicVolume.get();
    }

    public SimpleDoubleProperty backgroundMusicVolumeProperty() {
        return backgroundMusicVolume;
    }

    /**
     * Saves preferences for this class.
     * This method will save the volume settings.
     * @see UI.Main
     */
    public void savePreferences(){
        Preferences preferences = Preferences.userNodeForPackage(SoundPlayer.class);
        preferences.putDouble("soundEffectVolume", soundEffectVolume.getValue());
        preferences.putDouble("backgroundMusicVolume",backgroundMusicVolume.getValue());
    }
}
