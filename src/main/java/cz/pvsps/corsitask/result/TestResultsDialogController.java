package cz.pvsps.corsitask.result;

import cz.pvsps.corsitask.Constants;
import cz.pvsps.corsitask.tools.Tools;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.FileChooser;

import java.io.File;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

import static cz.pvsps.corsitask.Main.configuration;
import static cz.pvsps.corsitask.Main.stage;
import static cz.pvsps.corsitask.result.ResultController.file;

public class TestResultsDialogController {

    public Button confirmSelectionButton;
    public ComboBox<String> pathToResultsComboBox;
    public Button browseLocalFilesButton;
    private ObservableList<String> resultsFileOptions;
    private FileChooser fileChooser;
    private static final Logger LOGGER = Logger.getLogger(TestResultsDialogController.class.getName());

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
                resultsFileOptions.add(file.getPath() + "\\" + file.getName() + ".json");
            }
        }
        pathToResultsComboBox.setItems(resultsFileOptions);
        //pathToResultsComboBox.setValue(configuration.getPathToResultsDir()+"\\results.json");
    }

    public void confirmSelectionButtonOnAction() {
        // TODO check if file exists and is correct
        // else throw new exception
        if (pathToResultsComboBox.getValue() != null) {
            File directory = new File(pathToResultsComboBox.getValue());
            file = new File(directory.getPath() + "\\" + directory.getName() + ".json");
            if (file.exists()) {
                LOGGER.log(Level.INFO, "Results file: "+ file.getName() + " exists and has correct file extension. Loading results...");
                Tools.changeScene(Constants.RESULT);
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Chybně vybraný soubor!");
            alert.setHeaderText(null);
            alert.setContentText("Soubor, který byl vybrán neexistuje. Vyberte prosím jiný soubor.");
            alert.showAndWait();
            LOGGER.log(Level.SEVERE, "Results file: " + file.getName() + " does not have a correct extension. Showing dialog...");
            LOGGER.log(Level.INFO, "Location to results file was not selected.");
        }
    }

    public void browseLocalFilesButtonOnAction() {
        var location = fileChooser.showOpenDialog(stage);
        if (location != null) {
            if (!resultsFileOptions.contains(location.getPath())) {
                resultsFileOptions.add(location.getPath());
            }
            pathToResultsComboBox.setValue(location.getPath());
        }
    }
}
