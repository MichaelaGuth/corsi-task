package cz.pvsps.corsitask.settings;

import cz.pvsps.corsitask.Constants;
import cz.pvsps.corsitask.tools.Tools;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.FileChooser;

import java.io.File;
import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

import static cz.pvsps.corsitask.Main.configuration;
import static cz.pvsps.corsitask.Main.stage;

public class TestSettingsDialogController {

    public Button saveSettingsButton;
    public CheckBox showBlockNumbersCheckBox;
    public CheckBox showUserOrderCheckBox;
    public ChoiceBox<String> sequenceFileChoiceBox;
    public CheckBox allowResetButtonCheckBox;
    public TextField surnameTextField;
    public TextField nameTextField;
    public Button browseLocalFilesButton;
    public DatePicker birthdatePicker;
    public Label statusLabel;
    public Label patientIDLabel;
    public Label nameLabel;
    public Label surnameLabel;
    public Label birthdateLabel;
    public CheckBox allowTutorialCheckBox;
    private ObservableList<String> sequenceFileOptions;
    private FileChooser fileChooser;
    public static String patientName;
    public static String patientSurname;
    public static UUID patientID;
    public static LocalDate patientBirthdate;

    // TODO delete showUserOrderCheckBox

    @FXML
    public void initialize() {
        stage.setFullScreen(Constants.TEST_SETTINGS_DIALOG.isFullscreen());
        patientID = UUID.randomUUID();
        patientIDLabel.setText("ID: " + patientID);
        configuration = Tools.loadConfiguration();
        showBlockNumbersCheckBox.setSelected(configuration.isShowBlockNumbers());
        showUserOrderCheckBox.setDisable(true); // TODO delete showUserOrderCheckBox
        allowResetButtonCheckBox.setSelected(configuration.isAllowResetButton());
        allowTutorialCheckBox.setSelected(configuration.isAllowTutorial());
        birthdatePicker.setShowWeekNumbers(true);
        prepareSequenceFileChoiceBox();
        prepareFileChooser();
        showBlockNumbersCheckBox.setDisable(true);
        allowResetButtonCheckBox.setDisable(true);
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

    public void saveSettingsButtonOnAction() {
        configuration.setShowBlockNumbers(showBlockNumbersCheckBox.isSelected());
        configuration.setCurrentlyInUseSequenceFilePath(sequenceFileChoiceBox.getValue().toString());
        configuration.setShowUserSelectedOrderOnBlocks(showUserOrderCheckBox.isSelected());
        configuration.setAllowResetButton(allowResetButtonCheckBox.isSelected());
        configuration.setAllowTutorial(allowTutorialCheckBox.isSelected());
        patientSurname = surnameTextField.getText();
        patientName = nameTextField.getText();
        patientBirthdate = birthdatePicker.getValue();

        if (checkInsertedDataValidity()) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Potvrdit nastavení");                   // TODO konstanta?
            alert.setHeaderText(null);
            alert.setContentText("Jsou zadané informace správné?"); // TODO konstanta?
            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent()) {
                if (result.get() == ButtonType.OK) {
                    Tools.saveObjectToJSONFile(configuration, Constants.CONFIGURATION_LOCATION);
                    if (configuration.isAllowTutorial()) {
                        Tools.changeScene(Constants.TUTORIAL);
                    } else {
                        Tools.changeScene(Constants.START_TEST);
                    }

                }
            }
        }
    }

    public boolean checkInsertedDataValidity() {
        final String successMessage = "-fx-text-fill: GREEN;";
        final String errorMessage = "-fx-text-fill: RED;";
        final String errorStyle = "-fx-border-color: RED; -fx-border-width: 2; -fx-border-radius: 5;";
        final String successStyle = "-fx-border-color: #A9A9A9; -fx-border-width: 2; -fx-border-radius: 5;";

        boolean res = true;

        if (isNameInvalid(patientName)) {
            nameTextField.setStyle(errorStyle);
            new animatefx.animation.Shake(nameTextField).play();
            res = false;
        } else {
            nameTextField.setStyle(successStyle);
        }

        if (isNameInvalid(patientSurname)) {
            surnameTextField.setStyle(errorStyle);
            new animatefx.animation.Shake(surnameTextField).play();
            res = false;
        } else {
            surnameTextField.setStyle(successStyle);
        }

        if (patientBirthdate == null) {
            birthdatePicker.setStyle(errorStyle);
            new animatefx.animation.Shake(birthdatePicker).play();
            res = false;
        } else {
            birthdatePicker.setStyle(successStyle);
        }
        
        if (res) {
            statusLabel.setStyle(successMessage);
            statusLabel.setText("Zadané údaje jsou ve správném formátu.");
        } else {
            statusLabel.setStyle(errorMessage);
            statusLabel.setText("Neplatně zadané údaje pacienta!");
        }
        
        return res;
    }

    public void showBlockNumbersCheckBoxOnAction() {
        if (showBlockNumbersCheckBox.isSelected()) {
            showUserOrderCheckBox.setSelected(false);
            showUserOrderCheckBox.setDisable(true);
        } else {
            showUserOrderCheckBox.setDisable(false);
        }
    }

    public void showUserOrderCheckBoxOnAction() {
        if (showUserOrderCheckBox.isSelected()) {
            showBlockNumbersCheckBox.setSelected(false);
            showBlockNumbersCheckBox.setDisable(true);
        } else {
            showBlockNumbersCheckBox.setDisable(false);
        }
    }

    public void browseLocalFilesButtonOnAction() {
        var location = fileChooser.showOpenDialog(stage);
        if (location != null) {
            if (!sequenceFileOptions.contains(location.getPath())) {
                sequenceFileOptions.add(location.getPath());
            }
            sequenceFileChoiceBox.setValue(location.getPath());
        }
    }

    private boolean isNameInvalid(String name) {
        return !name.matches("^[a-zA-ZàáâäãåąčćęèéêëėįìíîïłńòóôöõøùúûüųūÿýżźñçřšžÀÁÂÄÃÅĄĆČĖĘÈÉÊËÌÍÎÏĮŁŃÒÓÔÖÕØÙÚÛÜŲŪŸÝŻŹÑßÇŒÆŘŠŽ∂ð ,.'-]+$");
    }

}
