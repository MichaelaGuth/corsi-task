package cz.pvsps.corsitask.dialogs;

import cz.pvsps.corsitask.Constants;
import cz.pvsps.corsitask.tools.Tools;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;

import java.io.File;

import static cz.pvsps.corsitask.Main.configuration;
import static cz.pvsps.corsitask.Main.stage;

public class TestSettingsDialogController {

    public Button saveSettingsButton;
    public TextField resultFileNameTextField;
    public CheckBox showBlockNumbersCheckBox;
    public CheckBox showUserOrderCheckBox;
    public ChoiceBox<String> sequenceFileChoiceBox;
    public CheckBox allowResetButtonCheckBox;
    public TextField surnameTextField;
    public TextField nameTextField;
    public Button browseLocalFilesButton;

    private ObservableList<String> sequenceFileOptions;

    private FileChooser fileChooser;

    public static String patientName;
    public static String patientSurname;

    @FXML
    public void initialize() {
        configuration = Tools.loadConfiguration();
        showBlockNumbersCheckBox.setSelected(configuration.isShowBlockNumbers());
        showUserOrderCheckBox.setSelected(configuration.isShowUserSelectedOrderOnBlocks());
        allowResetButtonCheckBox.setSelected(configuration.isAllowResetButton());
        prepareSequenceFileChoiceBox();
        prepareFileChooser();
    }

    private void prepareFileChooser() {
        fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File(configuration.getPathToSequenceDir()));
        fileChooser.setTitle("Vyberte soubor se sekvencemi:");
        FileChooser.ExtensionFilter fileExtensions = new FileChooser.ExtensionFilter("JSON files (*.json)", "*.json");
        fileChooser.getExtensionFilters().add(fileExtensions);
    }

    private void prepareSequenceFileChoiceBox() {
        File folder = new File(configuration.getPathToSequenceDir());
        File[] listOfFiles = folder.listFiles();
        sequenceFileOptions = FXCollections.observableArrayList();
        if (listOfFiles != null) {
            for (File file :
                    listOfFiles) {
                sequenceFileOptions.add(file.getPath());
            }
        }
        if (!sequenceFileOptions.contains(configuration.getCurrentlyInUseSequenceFilePath())) {
            sequenceFileOptions.add(configuration.getCurrentlyInUseSequenceFilePath());
        }
        sequenceFileChoiceBox.setItems(sequenceFileOptions);
        sequenceFileChoiceBox.setValue(configuration.getCurrentlyInUseSequenceFilePath());
    }

    public void saveSettingsButtonOnAction(ActionEvent actionEvent) {
        configuration.setShowBlockNumbers(showBlockNumbersCheckBox.isSelected());
        configuration.setCurrentlyInUseSequenceFilePath(sequenceFileChoiceBox.getValue().toString());
        configuration.setShowUserSelectedOrderOnBlocks(showUserOrderCheckBox.isSelected());
        configuration.setAllowResetButton(allowResetButtonCheckBox.isSelected());
        patientName = nameTextField.getText();
        patientSurname = nameTextField.getText();
        Tools.saveObjectToJSON(configuration, Constants.CONFIGURATION_LOCATION);
        Tools.changeScene(Constants.FxmlFile.TEST_INSTRUCTIONS);
    }

    public void showBlockNumbersCheckBoxOnAction(ActionEvent actionEvent) {
        if (showBlockNumbersCheckBox.isSelected()) {
            showUserOrderCheckBox.setSelected(false);
            showUserOrderCheckBox.setDisable(true);
        } else {
            showUserOrderCheckBox.setDisable(false);
        }
    }

    public void showUserOrderCheckBoxOnAction(ActionEvent actionEvent) {
        if (showUserOrderCheckBox.isSelected()) {
            showBlockNumbersCheckBox.setSelected(false);
            showBlockNumbersCheckBox.setDisable(true);
        } else {
            showBlockNumbersCheckBox.setDisable(false);
        }
    }

    public void browseLocalFilesButtonOnAction(ActionEvent actionEvent) {
        var location = fileChooser.showOpenDialog(stage);
        if (location != null) {
            if (!sequenceFileOptions.contains(location.getPath())) {
                sequenceFileOptions.add(location.getPath());
            }
            sequenceFileChoiceBox.setValue(location.getPath());
        }
    }
}
