package cz.pvsps.corsitask.result;

import cz.pvsps.corsitask.Constants;
import cz.pvsps.corsitask.tools.Block;
import cz.pvsps.corsitask.tools.Tools;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.File;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import static cz.pvsps.corsitask.Constants.CSV_FOLDER_NAME;
import static cz.pvsps.corsitask.Main.stage;

public class ResultController {
    public Label patientNameLabel;
    public Label patientSurnameLabel;
    public Label patientBirthdateLabel;
    public TableView<ResultTableItem> table;
    public TableColumn<ResultTableItem, SimpleIntegerProperty> sequenceLengthColumn;
    public TableColumn<ResultTableItem, SimpleIntegerProperty> trialNumberColumn;
    public TableColumn<ResultTableItem, SimpleIntegerProperty> scoreColumn;
    public TableColumn<ResultTableItem, SimpleDoubleProperty> userTimeColumn;
    public TableColumn<ResultTableItem, SimpleListProperty<Block>> sequenceColumn;
    public TableColumn<ResultTableItem, SimpleListProperty<Block>> userSequenceColumn;
    public Label totalScoreLabel;
    public Label blockSpanLabel;
    public Label correctTrialsLabel;
    public Button showSequenceButton;
    public Label patientIDLabel;
    public Label testDateLabel;
    public Button exportToPDFButton;
    public Button generateCSVFileButton;
    private Score score;
    public static File file;

    // TODO add export to PDF option
    @FXML
    public void initialize() {
        exportToPDFButton.setDisable(true);
        exportToPDFButton.setVisible(false);
        stage.setFullScreen(Constants.RESULT.isFullscreen());
        stage.setOnCloseRequest(windowEvent -> {
            Tools.changeScene(Constants.MENU);
            windowEvent.consume();
        });
        if (file != null) {
            score = Tools.loadScore(file);
            setTable();
            ObservableList<ResultTableItem> list = getTableItemsList();
            table.setItems(list);
            patientSurnameLabel.setText(score.getPatientSurname());
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            patientBirthdateLabel.setText(score.getPatientBirthdate().format(formatter));
            patientNameLabel.setText(score.getPatientName());
            totalScoreLabel.setText(String.valueOf(score.getTotalScore()));
            blockSpanLabel.setText(String.valueOf(score.getBlockSpan()));
            correctTrialsLabel.setText(String.valueOf(score.getNumberOfCorrectTrials()));
            table.getSelectionModel().select(0);
            patientIDLabel.setText("ID: "+ score.getPatientID());
            formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
            testDateLabel.setText(score.getTestDate().format(formatter));
        }
    }

    public void setTable() {
        sequenceLengthColumn.setCellValueFactory(new PropertyValueFactory<>("sequenceLength"));
        trialNumberColumn.setCellValueFactory(new PropertyValueFactory<>("trialNumber"));
        scoreColumn.setCellValueFactory(new PropertyValueFactory<>("score"));
        userTimeColumn.setCellValueFactory(new PropertyValueFactory<>("time"));
        sequenceColumn.setCellValueFactory(new PropertyValueFactory<>("sequence"));
        userSequenceColumn.setCellValueFactory(new PropertyValueFactory<>("userSequence"));
    }

    private ObservableList<ResultTableItem> getTableItemsList() {
        ObservableList<ResultTableItem> list = FXCollections.observableArrayList();
        for (SequenceScore sequenceScore :
                score.getSequencesScores()) {
            list.add(new ResultTableItem(sequenceScore));
        }
        return list;
    }


    public void showSequenceButtonOnAction() {
        ResultTableItem resultTableItem = table.getSelectionModel().getSelectedItem();
        int index = table.getItems().indexOf(resultTableItem);
        SequenceResultController.sequenceScore = score.getSequencesScores().get(index);
        Tools.changeScene(Constants.SEQUENCE_RESULT);
    }

    public void generateCSVFileButtonOnAction(ActionEvent actionEvent) {

        String filePath = file.getParentFile() + CSV_FOLDER_NAME + "score.csv";
        StringBuilder fileContent = new StringBuilder();
        ArrayList<String> scoreCSV = score.createCSV();
        for (String tmp :
                scoreCSV) {
            fileContent.append(tmp).append("\n");
        }
        Tools.saveFile(new File(filePath), fileContent.toString());

        // TODO upravit headery
        filePath = file.getParentFile() + CSV_FOLDER_NAME + "sequenceScores.csv";
        fileContent = new StringBuilder();
        StringBuilder sequenceScores = new StringBuilder();
        String header = "";
        for (SequenceScore sequenceScore :
                score.getSequencesScores()) {
            ArrayList<String> sequenceScoreCSV = sequenceScore.createCSV();
            if (sequenceScoreCSV.get(0).length() >= header.length()) {
                header = sequenceScoreCSV.get(0);
            }
            sequenceScores.append(sequenceScoreCSV.get(1)).append("\n");
        }
        fileContent.append(header).append("\n").append(sequenceScores);
        Tools.saveFile(new File(filePath), fileContent.toString());

    }
}
