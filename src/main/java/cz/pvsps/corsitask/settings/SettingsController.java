package cz.pvsps.corsitask.settings;

import cz.pvsps.corsitask.Constants;
import cz.pvsps.corsitask.tools.Tools;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.stage.DirectoryChooser;

import java.io.File;
import java.util.Objects;

import static cz.pvsps.corsitask.Constants.BROWSE_OPTION;
import static cz.pvsps.corsitask.Main.configuration;
import static cz.pvsps.corsitask.Main.stage;

public class SettingsController {
    public ChoiceBox<String> sequencesLocationChoiceBox;
    public ChoiceBox<String> resultsLocationChoiceBox;
    public Button backToMenuButton;
    public CheckBox showBlockNumbersCheckBox;
    public CheckBox showUserOrderCheckBox;

    private ObservableList<String> sequenceLocationOptions;
    private ObservableList<String> resultsLocationOptions;

    private DirectoryChooser directoryChooser;

    private final String DEFAULT_SEQUENCE_DIR_LOCATION = Tools.getDocumentsPath()+"\\Corsi Test\\sekvence";
    private final String DEFAULT_RESULT_DIR_LOCATION = Tools.getDocumentsPath()+"\\Corsi Test\\výsledky";



    @FXML
    public void initialize() {
        stage.setFullScreen(Constants.FxmlFile.SETTINGS.isFullscreen());

        directoryChooser = new DirectoryChooser();
        directoryChooser.setInitialDirectory(new File(Tools.getDocumentsPath()));
        sequenceLocationOptions = FXCollections.observableArrayList(
                DEFAULT_SEQUENCE_DIR_LOCATION,
                BROWSE_OPTION
        );
        sequencesLocationChoiceBox.setItems(sequenceLocationOptions);
        sequencesLocationChoiceBox.setValue(sequenceLocationOptions.get(0));

        resultsLocationOptions = FXCollections.observableArrayList(
                DEFAULT_RESULT_DIR_LOCATION,
                BROWSE_OPTION
        );
        resultsLocationChoiceBox.setItems(resultsLocationOptions);
        resultsLocationChoiceBox.setValue(resultsLocationOptions.get(0));
    }

    public void backToMenuButtonOnAction() {
        configuration.setPathToResultsDir(resultsLocationChoiceBox.getValue().toString());
        configuration.setPathToSequenceDir(sequencesLocationChoiceBox.getValue().toString());
        Tools.saveObjectToJSON(configuration, Constants.CONFIGURATION_LOCATION);
        Tools.changeScene(Constants.FxmlFile.MENU);
    }

    public void sequencesLocationChoiceBoxOnAction() {
        if (sequencesLocationChoiceBox.getValue() != null) {
            if (Objects.equals(sequencesLocationChoiceBox.getValue(), BROWSE_OPTION)) {
                var location = directoryChooser.showDialog(stage);
                sequenceLocationOptions.remove(BROWSE_OPTION);
                sequenceLocationOptions.add(location.getPath());
                sequenceLocationOptions.add(BROWSE_OPTION);
                sequencesLocationChoiceBox.setValue(location.getPath());
            }
        }
    }

    public void resultsLocationChoiceBoxOnAction() {
        if (resultsLocationChoiceBox.getValue() != null) {
            if (Objects.equals(resultsLocationChoiceBox.getValue(), BROWSE_OPTION)) {
                var location = directoryChooser.showDialog(stage);
                resultsLocationOptions.remove(BROWSE_OPTION);
                resultsLocationOptions.add(location.getPath());
                resultsLocationOptions.add(BROWSE_OPTION);
                resultsLocationChoiceBox.setValue(location.getPath());
            }
        }
    }

    public void showUserOrderCheckBoxOnAction() {
    }

    public void showBlockNumbersCheckBoxOnAction() {
    }
}
