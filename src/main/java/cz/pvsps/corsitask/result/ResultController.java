package cz.pvsps.corsitask.result;

import cz.pvsps.corsitask.Constants;
import cz.pvsps.corsitask.tools.Block;
import cz.pvsps.corsitask.tools.Tools;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.File;
import java.time.format.DateTimeFormatter;

import static cz.pvsps.corsitask.Main.stage;

public class ResultController {
    public Label patientNameLabel;
    public Label patientSurnameLabel;
    public Label patientBirthdateLabel;
    public TableView<SequenceTableItem> table;
    public TableColumn<SequenceTableItem, SimpleIntegerProperty> sequenceLengthColumn;
    public TableColumn<SequenceTableItem, SimpleIntegerProperty> trialNumberColumn;
    public TableColumn<SequenceTableItem, SimpleIntegerProperty> scoreColumn;
    public TableColumn<SequenceTableItem, SimpleDoubleProperty> userTimeColumn;
    public TableColumn<SequenceTableItem, SimpleListProperty<Block>> sequenceColumn;
    public Label totalScoreLabel;
    public Label blockSpanLabel;
    public Label correctTrialsLabel;
    public Button showSequenceButton;
    public Label patientIDLabel;
    public Label testDateLabel;
    private Score score;
    public static File file;


    // TODO change table design to include times between block clicks
    @FXML
    public void initialize() {
        stage.setFullScreen(Constants.RESULT.isFullscreen());
        if (file != null) {
            score = Tools.loadScore(file);
            setTable();
            ObservableList<SequenceTableItem> list = getTableItemsList();
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
            showSequenceButton.setDisable(true);
        }
    }

    public void setTable() {
        sequenceLengthColumn.setCellValueFactory(new PropertyValueFactory<>("sequenceLength"));
        trialNumberColumn.setCellValueFactory(new PropertyValueFactory<>("trialNumber"));
        scoreColumn.setCellValueFactory(new PropertyValueFactory<>("score"));
        userTimeColumn.setCellValueFactory(new PropertyValueFactory<>("time"));
        sequenceColumn.setCellValueFactory(new PropertyValueFactory<>("sequence"));
    }

    private ObservableList<SequenceTableItem> getTableItemsList() {
        ObservableList<SequenceTableItem> list = FXCollections.observableArrayList();
        for (SequenceScore sequenceScore :
                score.getSequencesScores()) {
            list.add(new SequenceTableItem(sequenceScore));
        }
        return list;
    }


    public void showSequenceButtonOnAction() {
        // TODO add this feature
        //SequenceTableItem sequenceTableItem = table.getSelectionModel().getSelectedItem();
        System.out.println(table.getSelectionModel().getSelectedItem());
    }
}
