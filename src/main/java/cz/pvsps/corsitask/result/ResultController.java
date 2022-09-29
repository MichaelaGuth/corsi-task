package cz.pvsps.corsitask.result;

import cz.pvsps.corsitask.Constants;
import cz.pvsps.corsitask.tools.Tools;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
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
    public TableView table;
    public TableColumn sequenceLengthColumn;
    public TableColumn trialNumberColumn;
    public TableColumn scoreColumn;
    public TableColumn userTimeColumn;
    public TableColumn sequenceColumn;
    public TableColumn buttonColumn;
    public Label totalScoreLabel;
    public Label blockSpanLabel;
    public Label correctTrialsLabel;

    public static File file;
    private Score score;

    // TODO přidat řádek/zahlavi na konec tabulky s score celkem

    @FXML
    public void initialize() {
        stage.setFullScreen(Constants.FxmlFile.RESULT.isFullscreen());
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
            // TODO finallize -  add remaining info
        }
    }

    public void setTable() {
        sequenceLengthColumn.setCellValueFactory(new PropertyValueFactory("sequenceLength"));
        trialNumberColumn.setCellValueFactory(new PropertyValueFactory("trialNumber"));
        scoreColumn.setCellValueFactory(new PropertyValueFactory("score"));
        userTimeColumn.setCellValueFactory(new PropertyValueFactory("time"));
        sequenceColumn.setCellValueFactory(new PropertyValueFactory("sequence"));
    }

    private ObservableList<SequenceTableItem> getTableItemsList() {
        ObservableList<SequenceTableItem> list = FXCollections.observableArrayList();
        for (SequenceScore sequenceScore :
                score.getSequencesScores()) {
            list.add(new SequenceTableItem(sequenceScore));
        }
        return list;
    }


}
