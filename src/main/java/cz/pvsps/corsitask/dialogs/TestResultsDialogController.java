package cz.pvsps.corsitask.dialogs;

import cz.pvsps.corsitask.Constants;
import cz.pvsps.corsitask.tools.Tools;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.FileChooser;

import java.io.File;

import static cz.pvsps.corsitask.Main.configuration;
import static cz.pvsps.corsitask.Main.stage;
import static cz.pvsps.corsitask.result.ResultController.file;

public class TestResultsDialogController {

    public Button confirmSelectionButton;
    public ComboBox<String> pathToResultsComboBox;
    public Button browseLocalFilesButton;

    private ObservableList<String> resultsFileOptions;

    private FileChooser fileChooser;

    @FXML
    public void initialize() {
        configuration = Tools.loadConfiguration();
        prepareResultsFileChoiceBox();
        prepareFileChooser();
    }

    private void prepareFileChooser() {
        fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File(configuration.getPathToResultsDir()));
        fileChooser.setTitle("Vyberte soubor se sekvencemi:");
        FileChooser.ExtensionFilter fileExtensions = new FileChooser.ExtensionFilter("JSON files (*.json)", "*.json");
        fileChooser.getExtensionFilters().add(fileExtensions);
    }

    private void prepareResultsFileChoiceBox() {
        File folder = new File(configuration.getPathToResultsDir());
        File[] listOfFiles = folder.listFiles();
        resultsFileOptions = FXCollections.observableArrayList();
        if (listOfFiles != null) {
            for (File file :
                    listOfFiles) {
                resultsFileOptions.add(file.getPath());
            }
        }
        pathToResultsComboBox.setItems(resultsFileOptions);
        pathToResultsComboBox.setValue(configuration.getPathToResultsDir()+"results.json");
    }

    public void confirmSelectionButtonOnAction(ActionEvent actionEvent) {
        // TODO check if file exists and is correct
        // else throw new exception
        file = new File(pathToResultsComboBox.getValue());
        if (file.exists()) {
            Tools.changeScene(Constants.FxmlFile.RESULT);
        }

    }

    public void browseLocalFilesButtonOnAction(ActionEvent actionEvent) {
        var location = fileChooser.showOpenDialog(stage);
        if (location != null) {
            if (!resultsFileOptions.contains(location.getPath())) {
                resultsFileOptions.add(location.getPath());
            }
            pathToResultsComboBox.setValue(location.getPath());
        }
    }
}
