package Application;

import Domain.Sound.SoundPlayer;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;

public class SoundOptionsController {

    @FXML
    public Slider soundEffectSlider;
    @FXML
    public Label soundEffectLabel;
    @FXML
    public Slider backgroundMusicSlider;
    @FXML
    public Label backgroundMusicLabel;

    public void initialize(){
        //Get needed values
        SoundPlayer soundPlayer = SoundPlayer.getInstance();

        SimpleDoubleProperty backgroundVolumeProperty = soundPlayer.backgroundMusicVolumeProperty();
        SimpleDoubleProperty soundEffectVolumeProperty = soundPlayer.soundEffectVolumeProperty();

        double backgroundVolume = backgroundVolumeProperty.doubleValue();
        double soundEffectVolume = soundEffectVolumeProperty.doubleValue();

        //Set property to value
        backgroundMusicSlider.valueProperty().set(backgroundVolume);
        soundEffectSlider.valueProperty().set(soundEffectVolume);

        //binding the SoundManager properties to the sliders
        backgroundVolumeProperty.bind(backgroundMusicSlider.valueProperty());
        soundEffectVolumeProperty.bind(soundEffectSlider.valueProperty());

        //Binding the labels to the slider
        soundEffectLabel.textProperty().bind(Bindings.format("%.2f %%",soundEffectVolumeProperty.multiply(100)));
        backgroundMusicLabel.textProperty().bind(Bindings.format("%.2f %%",backgroundVolumeProperty.multiply(100)));
    }
}
