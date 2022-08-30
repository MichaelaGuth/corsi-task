package cz.pvsps.corsitask.dialogs;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

public class TestSettingsDialogController {

    public Button saveSettingsButton;
    public TextField resultFileNameTextField;
    public CheckBox showBlockNumbersCheckBox;
    public CheckBox showUserOrderCheckBox;
    public ChoiceBox sequenceFileChoiceBox;
    public TextField surnameTextField;
    public TextField nameTextField;

    private ObservableList<String> sequenceFileOptions;

    @FXML
    public void initialize() {

    }
}
