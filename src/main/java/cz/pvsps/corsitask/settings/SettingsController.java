package cz.pvsps.corsitask.settings;

import cz.pvsps.corsitask.Constants;
import cz.pvsps.corsitask.tools.FileNameFormat;
import cz.pvsps.corsitask.tools.Tools;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.stage.DirectoryChooser;

import java.io.File;
import java.util.Objects;

import static cz.pvsps.corsitask.Constants.*;
import static cz.pvsps.corsitask.Main.configuration;
import static cz.pvsps.corsitask.Main.stage;

public class SettingsController {
    public ChoiceBox<String> sequencesLocationChoiceBox;
    public ChoiceBox<String> resultsLocationChoiceBox;
    public Button backToMenuButton;
    public CheckBox showBlockNumbersCheckBox;
    public CheckBox allowResetButtonCheckBox;
    public CheckBox allowTutorialCheckBox;
    public ChoiceBox<String> resultFileNameFormatChoiceBox;
    public Label resultFileNameFormatExampleLabel;

    private ObservableList<String> sequenceLocationOptions;
    private ObservableList<String> resultsLocationOptions;

    private DirectoryChooser directoryChooser;

    private final String DEFAULT_SEQUENCE_DIR_LOCATION = Tools.getDocumentsPath()+"\\Corsi Test\\sekvence";
    private final String DEFAULT_RESULT_DIR_LOCATION = Tools.getDocumentsPath()+"\\Corsi Test\\výsledky";

    // TODO přidat možnost zvolit lokalizaci

    @FXML
    public void initialize() {
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
        ObservableList<String> resultFileNameFormatOptions = FXCollections.observableArrayList(
                DATE_SURNAME_NAME_TIME.getFormat(),
                ID.getFormat(),
                DATE_ID_TIME.getFormat()
        );
        resultFileNameFormatChoiceBox.setItems(resultFileNameFormatOptions);
        resultFileNameFormatChoiceBox.setValue(configuration.getResultFileNameFormat().getFormat());
        setResultFileNameFormatExample(configuration.getResultFileNameFormat());
    }

    public void backToMenuButtonOnAction() {
        configuration.setPathToResultsDir(resultsLocationChoiceBox.getValue().toString());
        configuration.setPathToSequenceDir(sequencesLocationChoiceBox.getValue().toString());
        configuration.setAllowResetButton(allowResetButtonCheckBox.isSelected());
        configuration.setAllowTutorial(allowTutorialCheckBox.isSelected());
        configuration.setShowBlockNumbers(showBlockNumbersCheckBox.isSelected());
        configuration.setResultFileNameFormat(FileNameFormat.findFileNameFormat(resultFileNameFormatChoiceBox.getValue()));
        Tools.saveObjectToJSONFile(configuration, Constants.CONFIGURATION_LOCATION);
        Tools.changeScene(Constants.MENU);
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

    private void setResultFileNameFormatExample(FileNameFormat resultFileNameFormat) {
        // TODO rewrite for localization
        resultFileNameFormatExampleLabel.setText(EXAMPLE_SHORT + ": " + resultFileNameFormat.getExample());
    }

    public void resultFileNameFormatChoiceBoxOnAction() {
        setResultFileNameFormatExample(FileNameFormat.findFileNameFormat(resultFileNameFormatChoiceBox.getValue()));
    }
}
