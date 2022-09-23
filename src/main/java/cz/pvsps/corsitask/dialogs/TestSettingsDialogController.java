package cz.pvsps.corsitask.dialogs;

import cz.pvsps.corsitask.Constants;
import cz.pvsps.corsitask.tools.Tools;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
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
    public TextField resultFileNameTextField;
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

    private ObservableList<String> sequenceFileOptions;

    private FileChooser fileChooser;

    public static String patientName;
    public static String patientSurname;

    public static UUID patientID;

    public static LocalDate patientBirthdate;

    private final String successMessage = "-fx-text-fill: GREEN;";
    private final String errorMessage = "-fx-text-fill: RED;";
    private final String errorStyle = "-fx-border-color: RED; -fx-border-width: 2; -fx-border-radius: 5;";
    private final String successStyle = "-fx-border-color: #A9A9A9; -fx-border-width: 2; -fx-border-radius: 5;";

    @FXML
    public void initialize() {
        patientID = UUID.randomUUID();
        patientIDLabel.setText("ID: " + patientID);
        configuration = Tools.loadConfiguration();
        showBlockNumbersCheckBox.setSelected(configuration.isShowBlockNumbers());
        showUserOrderCheckBox.setSelected(configuration.isShowUserSelectedOrderOnBlocks());
        allowResetButtonCheckBox.setSelected(configuration.isAllowResetButton());
        birthdatePicker.setShowWeekNumbers(true);
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
        patientSurname = surnameTextField.getText();
        patientName = nameTextField.getText();
        patientBirthdate = birthdatePicker.getValue();

        if (isNameInvalid(patientName) && isNameInvalid(patientSurname)) {
            nameTextField.setStyle(errorStyle);
            new animatefx.animation.Shake(nameTextField).play();

            surnameTextField.setStyle(errorStyle);
            new animatefx.animation.Shake(surnameTextField).play();

            statusLabel.setStyle(errorMessage);

            if (patientBirthdate == null) {
                statusLabel.setText("Neplatně zadané údaje pacienta!");
                birthdatePicker.setStyle(errorStyle);
                new animatefx.animation.Shake(birthdatePicker).play();
            } else {
                statusLabel.setText("Neplatně zadané příjmení a jméno pacienta!");
                birthdatePicker.setStyle(successStyle);
            }

        } else if (isNameInvalid(patientName)) {
            surnameTextField.setStyle(successStyle);
            nameTextField.setStyle(errorStyle);
            new animatefx.animation.Shake(nameTextField).play();
            statusLabel.setStyle(errorMessage);
            if (patientBirthdate == null) {
                statusLabel.setText("Neplatně zadané příjmení a datum narození pacienta!");
                birthdatePicker.setStyle(errorStyle);
                new animatefx.animation.Shake(birthdatePicker).play();
            } else {
                statusLabel.setText("Neplatně zadané příjmení pacienta!");
                birthdatePicker.setStyle(successStyle);
            }

        } else if (isNameInvalid(patientSurname)) {
            statusLabel.setStyle(errorMessage);
            surnameTextField.setStyle(errorStyle);
            new animatefx.animation.Shake(surnameTextField).play();
            nameTextField.setStyle(successStyle);
            if (patientBirthdate == null) {
                statusLabel.setText("Neplatně zadané jméno a datum narození pacienta!");
                birthdatePicker.setStyle(errorStyle);
                new animatefx.animation.Shake(birthdatePicker).play();
            } else {
                statusLabel.setText("Neplatně zadané jméno pacienta!");
                birthdatePicker.setStyle(successStyle);
            }

        } else if (patientBirthdate == null) {
            statusLabel.setStyle(errorMessage);
            surnameTextField.setStyle(successStyle);
            new animatefx.animation.Shake(birthdatePicker).play();
            nameTextField.setStyle(successStyle);

        } else {
            statusLabel.setStyle(successMessage);
            surnameTextField.setStyle(successStyle);
            nameTextField.setStyle(successStyle);
            birthdatePicker.setStyle(successStyle);
            statusLabel.setText("Zadané údaje jsou ve správném formátu.");
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Potvrdit nastavení");
            alert.setHeaderText(null);
            alert.setContentText("Jsou zadané informace správné?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK){
                Tools.saveObjectToJSON(configuration, Constants.CONFIGURATION_LOCATION);
                Tools.changeScene(Constants.FxmlFile.TRIAL);
            }
        }

/*
        if (!isNameValid(patientName)) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Neplatné jméno pacienta!");
            alert.setHeaderText(null);
            alert.setContentText(String.format("Jméno pacienta, které jste zadal (\"%s\"), není ve správném formátu nebo chybí. Zkontrolujte, zda máte na začátku velké písmeno.", patientName));
            alert.showAndWait();
        } else if(!isNameValid(patientSurname)) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Neplatné příjmení pacienta!");
            alert.setHeaderText(null);
            alert.setContentText(String.format("Příjmení pacienta, které jste zadal (\"%s\"), není ve správném formátu nebo chybí. Zkontrolujte, zda máte na začátku velké písmeno.", patientSurname));
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Potvrdit nastavení.");
            alert.setHeaderText(null);
            alert.setContentText("Jsou zadané informace správné?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK){
                Tools.saveObjectToJSON(configuration, Constants.CONFIGURATION_LOCATION);
                Tools.changeScene(Constants.FxmlFile.TRIAL);
            }
        }

 */
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

    private boolean isNameInvalid(String name) {
        return !name.matches("^[a-zA-ZàáâäãåąčćęèéêëėįìíîïłńòóôöõøùúûüųūÿýżźñçčšžÀÁÂÄÃÅĄĆČĖĘÈÉÊËÌÍÎÏĮŁŃÒÓÔÖÕØÙÚÛÜŲŪŸÝŻŹÑßÇŒÆČŠŽ∂ð ,.'-]+$");
    }

}
