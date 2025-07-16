package cz.pvsps.corsitask.result;

import cz.pvsps.corsitask.tools.Block;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ResultTableItem {
    private final SimpleIntegerProperty sequenceLength;
    private final SimpleStringProperty totalTime;
    private final SimpleStringProperty responseTime;
    private final SimpleStringProperty executionTime;
    private final SimpleIntegerProperty score;
    private final SimpleIntegerProperty trialNumber;
    private final SimpleListProperty<Block> sequence;
    private final SimpleListProperty<Block> userSequence;

    public ResultTableItem(SequenceScore sequenceScore) {
        this.score = new SimpleIntegerProperty(sequenceScore.getScore());
        this.totalTime = new SimpleStringProperty((double)sequenceScore.getTotalTime()/1000 + " s");
        this.responseTime = new SimpleStringProperty((double)sequenceScore.getResponseTime()/1000 + " s");
        this.executionTime = new SimpleStringProperty((double)sequenceScore.getExecutionTime()/1000 + " s");
        this.trialNumber = new SimpleIntegerProperty(sequenceScore.getTrialNumber());
        this.sequenceLength = new SimpleIntegerProperty(sequenceScore.getCorrectSequence().size());
        this.sequence = new SimpleListProperty<>(FXCollections.observableArrayList(sequenceScore.getCorrectSequence()));
        this.userSequence = new SimpleListProperty<>(FXCollections.observableArrayList(sequenceScore.getUserSequence()));
    }

    @Override
    public String toString() {
        return "ResultTableItem{" +
                "sequenceLength=" + sequenceLength +
                ", totalTime=" + totalTime +
                ", responseTime= " + responseTime +
                ", executionTime= " + executionTime +
                ", score=" + score +
                ", trialNumber=" + trialNumber +
                ", sequence=" + sequence +
                ", userSequence=" + userSequence +
                '}';
    }

    public int getSequenceLength() {
        return sequenceLength.get();
    }

    public SimpleIntegerProperty sequenceLengthProperty() {
        return sequenceLength;
    }

    public String getTotalTime() {
        return totalTime.get();
    }

    public SimpleStringProperty totalTimeProperty() {
        return totalTime;
    }

    public String getResponseTime() {
        return responseTime.get();
    }

    public SimpleStringProperty responseTimeProperty() {
        return responseTime;
    }

    public String getExecutionTime() {
        return executionTime.get();
    }

    public SimpleStringProperty executionTimeProperty() {
        return executionTime;
    }

    public int getScore() {
        return score.get();
    }

    public SimpleIntegerProperty scoreProperty() {
        return score;
    }

    public int getTrialNumber() {
        return trialNumber.get();
    }

    public SimpleIntegerProperty trialNumberProperty() {
        return trialNumber;
    }

    public ObservableList<Block> getSequence() {
        return sequence.get();
    }

    public SimpleListProperty<Block> sequenceProperty() {
        return sequence;
    }

    public ObservableList<Block> getUserSequence() {
        return userSequence.get();
    }

    public SimpleListProperty<Block> userSequenceProperty() {
        return userSequence;
    }
}
