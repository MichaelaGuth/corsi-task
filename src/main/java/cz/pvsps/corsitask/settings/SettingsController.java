package cz.pvsps.corsitask.settings;

import cz.pvsps.corsitask.Constants;
import cz.pvsps.corsitask.Tools;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.stage.DirectoryChooser;

import java.io.File;
import java.io.IOException;

import static cz.pvsps.corsitask.Main.configuration;
import static cz.pvsps.corsitask.Main.stage;

public class SettingsController {
    public ChoiceBox sequencesLocationChoiceBox;
    public ChoiceBox resultsLocationChoiceBox;
    public Button backToMenuButton;

    private ObservableList<String> sequenceLocationOptions;
    private ObservableList<String> resultsLocationOptions;

    private DirectoryChooser directoryChooser;

    private int indexOfBrowseSequence = 1;
    private int indexOfBrowseResults = 1;
    private final String BROWSE_OPTION = "Procházet místní soubory";

    private final String DEFAULT_SEQUENCE_DIR_LOCATION = Tools.getDocumentsPath()+"\\Corsi Test\\sekvence";
    private final String DEFAULT_RESULT_DIR_LOCATION = Tools.getDocumentsPath()+"\\Corsi Test\\výsledky";

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
        indexOfBrowseSequence = sequenceLocationOptions.indexOf(BROWSE_OPTION);

        resultsLocationOptions = FXCollections.observableArrayList(
                DEFAULT_RESULT_DIR_LOCATION,
                BROWSE_OPTION
        );
        resultsLocationChoiceBox.setItems(resultsLocationOptions);
        resultsLocationChoiceBox.setValue(resultsLocationOptions.get(0));
        indexOfBrowseResults = resultsLocationOptions.indexOf(BROWSE_OPTION);
    }


    public void backToMenuButtonOnAction(ActionEvent actionEvent) throws IOException {
        configuration.setPathToResultsDir(resultsLocationChoiceBox.getValue().toString());
        configuration.setPathToSequenceDir(sequencesLocationChoiceBox.getValue().toString());
        Tools.saveConfiguration(configuration);
        Tools.changeScene(Constants.FxmlFile.MENU);
    }

    public void sequencesLocationChoiceBoxOnAction(ActionEvent actionEvent) {
        if (sequencesLocationChoiceBox.getValue() != null) {
            if (sequencesLocationChoiceBox.getValue() == sequenceLocationOptions.get(indexOfBrowseSequence)) {
                var location = directoryChooser.showDialog(stage);
                sequenceLocationOptions.remove(BROWSE_OPTION);
                sequenceLocationOptions.add(location.getPath());
                sequenceLocationOptions.add(BROWSE_OPTION);
                indexOfBrowseSequence = sequenceLocationOptions.indexOf(BROWSE_OPTION);
                sequencesLocationChoiceBox.setValue(sequenceLocationOptions.get(sequenceLocationOptions.indexOf(location.getPath())));
            }
        }
    }

    public void resultsLocationChoiceBoxOnAction(ActionEvent actionEvent) {
        if (resultsLocationChoiceBox.getValue() != null) {
            if (resultsLocationChoiceBox.getValue() == resultsLocationOptions.get(indexOfBrowseResults)) {
                var location = directoryChooser.showDialog(stage);
                resultsLocationOptions.remove(BROWSE_OPTION);
                resultsLocationOptions.add(location.getPath());
                resultsLocationOptions.add(BROWSE_OPTION);
                indexOfBrowseResults = resultsLocationOptions.indexOf(BROWSE_OPTION);
                resultsLocationChoiceBox.setValue(resultsLocationOptions.get(resultsLocationOptions.indexOf(location.getPath())));
            }
        }
    }
}
