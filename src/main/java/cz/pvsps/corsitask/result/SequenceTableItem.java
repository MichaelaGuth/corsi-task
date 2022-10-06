package cz.pvsps.corsitask.result;

import cz.pvsps.corsitask.tools.Block;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class SequenceTableItem {
    private final SimpleIntegerProperty sequenceLength;
    private final SimpleStringProperty time;
    private final SimpleIntegerProperty score;
    private final SimpleIntegerProperty trialNumber;
    private final SimpleListProperty<Block> sequence;

    public SequenceTableItem(SequenceScore sequenceScore) {
        this.score = new SimpleIntegerProperty(sequenceScore.getScore());
        this.time = new SimpleStringProperty((double)sequenceScore.getUserTime()/1000 + " s");
        this.trialNumber = new SimpleIntegerProperty(sequenceScore.getTrialNumber());
        this.sequenceLength = new SimpleIntegerProperty(sequenceScore.getCorrectSequence().size());
        this.sequence = new SimpleListProperty<Block>(FXCollections.observableArrayList(sequenceScore.getCorrectSequence()));
    }

    @Override
    public String toString() {
        return "SequenceTableItem{" +
                "sequenceLength=" + sequenceLength +
                ", time=" + time +
                ", score=" + score +
                ", trialNumber=" + trialNumber +
                ", sequence=" + sequence +
                '}';
    }

    public int getSequenceLength() {
        return sequenceLength.get();
    }

    public SimpleIntegerProperty sequenceLengthProperty() {
        return sequenceLength;
    }

    public String getTime() {
        return time.get();
    }

    public SimpleStringProperty timeProperty() {
        return time;
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
}
