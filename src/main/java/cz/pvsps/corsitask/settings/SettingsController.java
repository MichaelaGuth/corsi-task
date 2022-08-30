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

import static cz.pvsps.corsitask.Main.stage;

public class SettingsController {
    public ChoiceBox sequencesLocationChoiceBox;
    public ChoiceBox resultsLocationChoiceBox;
    public Button backToMenuButton;

    private ObservableList<String> sequenceLocationOptions;
    private ObservableList<String> resultsLocationOptions;

    private DirectoryChooser directoryChooser;

    private int indexOfBrowse;
    private final String BROWSE_OPTION = "Procházet místní soubory";

    @FXML
    public void initialize() {
        directoryChooser = new DirectoryChooser();
        directoryChooser.setInitialDirectory(new File(Tools.getDocumentsPath()));
        sequenceLocationOptions = FXCollections.observableArrayList(
                Tools.getDocumentsPath()+"\\Corsi Test\\sequences",
                BROWSE_OPTION
        );
        sequencesLocationChoiceBox.setItems(sequenceLocationOptions);
        sequencesLocationChoiceBox.setValue(sequenceLocationOptions.get(0));
        indexOfBrowse = sequenceLocationOptions.indexOf(BROWSE_OPTION);
    }


    public void backToMenuButtonOnAction(ActionEvent actionEvent) throws IOException {
        Tools.changeScene(Constants.FxmlFile.MENU);
    }

    public void sequencesLocationChoiceBoxOnAction(ActionEvent actionEvent) {
        if (sequencesLocationChoiceBox.getValue() != null) {
            if (sequencesLocationChoiceBox.getValue() == sequenceLocationOptions.get(indexOfBrowse)) {
                var location = directoryChooser.showDialog(stage);
                sequenceLocationOptions.remove(BROWSE_OPTION);
                sequenceLocationOptions.add(location.getPath());
                sequenceLocationOptions.add(BROWSE_OPTION);
                indexOfBrowse = sequenceLocationOptions.indexOf(BROWSE_OPTION);
                sequencesLocationChoiceBox.setValue(sequenceLocationOptions.get(sequenceLocationOptions.indexOf(location.getPath())));
            }
        }
    }
}
