package cz.pvsps.corsitask.settings;

import cz.pvsps.corsitask.Constants;
import cz.pvsps.corsitask.animations.Shake;
import cz.pvsps.corsitask.tools.FileNameFormat;
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

import static cz.pvsps.corsitask.Constants.*;
import static cz.pvsps.corsitask.Main.configuration;
import static cz.pvsps.corsitask.Main.stage;

public class TestSettingsDialogController {

    public Button saveSettingsButton;
    public CheckBox showBlockNumbersCheckBox;
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
    public ChoiceBox<String> resultFileNameFormatChoiceBox;
    public Label resultFileNameFormatExampleLabel;
    private ObservableList<String> sequenceFileOptions;
    private FileChooser fileChooser;
    public static String patientName;
    public static String patientSurname;
    public static UUID patientID;
    public static LocalDate patientBirthdate;

    @FXML
    public void initialize() {
        stage.setFullScreen(Constants.TEST_SETTINGS_DIALOG.isFullscreen());
        patientID = UUID.randomUUID();
        patientIDLabel.setText("ID: " + patientID);
        configuration = Tools.loadConfiguration();
        showBlockNumbersCheckBox.setSelected(configuration.isShowBlockNumbers());
        allowResetButtonCheckBox.setSelected(configuration.isAllowResetButton());
        allowTutorialCheckBox.setSelected(configuration.isAllowTutorial());
        birthdatePicker.setShowWeekNumbers(true);
        prepareSequenceFileChoiceBox();
        prepareResultFileNameFormatChoiceBox();
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

    private void prepareResultFileNameFormatChoiceBox() {
        ObservableList<String> resultFileNameFormatOptions = FXCollections.observableArrayList(
                DATE_SURNAME_NAME_TIME.getFormat(),
                ID.getFormat(),
                DATE_ID_TIME.getFormat()
        );
        resultFileNameFormatChoiceBox.setItems(resultFileNameFormatOptions);
        resultFileNameFormatChoiceBox.setValue(configuration.getResultFileNameFormat().getFormat());
        setResultFileNameFormatExample(configuration.getResultFileNameFormat());
    }

    private void setResultFileNameFormatExample(FileNameFormat resultFileNameFormat) {
        resultFileNameFormatExampleLabel.setText("např.: " + resultFileNameFormat.getExample());
    }

    public void saveSettingsButtonOnAction() {
        configuration.setShowBlockNumbers(showBlockNumbersCheckBox.isSelected());
        configuration.setCurrentlyInUseSequenceFilePath(sequenceFileChoiceBox.getValue().toString());
        configuration.setAllowResetButton(allowResetButtonCheckBox.isSelected());
        configuration.setAllowTutorial(allowTutorialCheckBox.isSelected());
        configuration.setResultFileNameFormat(FileNameFormat.findFileNameFormat(resultFileNameFormatChoiceBox.getValue()));
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
                        Tools.changeScene(Constants.START_TUTORIAL);
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
            new Shake(nameTextField).play();
            res = false;
        } else {
            nameTextField.setStyle(successStyle);
        }

        if (isNameInvalid(patientSurname)) {
            surnameTextField.setStyle(errorStyle);
            new Shake(surnameTextField).play();
            res = false;
        } else {
            surnameTextField.setStyle(successStyle);
        }

        if (patientBirthdate == null) {
            birthdatePicker.setStyle(errorStyle);
            new Shake(birthdatePicker).play();
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

    public void resultFileNameFormatChoiceBoxOnAction() {
        setResultFileNameFormatExample(FileNameFormat.findFileNameFormat(resultFileNameFormatChoiceBox.getValue()));
    }
}
