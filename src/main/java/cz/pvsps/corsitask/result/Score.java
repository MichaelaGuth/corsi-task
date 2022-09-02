package cz.pvsps.corsitask.result;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class Score {
    @JsonProperty("patientName")
    private final String patientName;
    @JsonProperty("patientSurname")
    private final String patientSurname;
    @JsonProperty("sequencesScores")
    private List<SequenceScore> sequencesScores;
    @JsonProperty("blockSpan")
    private int blockSpan;
    @JsonProperty("totalScore")
    private int totalScore;
    @JsonProperty("numberOfCorrectTrials")
    private int numberOfCorrectTrials;

    public Score(String patientName, String patientSurname) {
        this.patientName = patientName;
        this.patientSurname = patientSurname;
        this.sequencesScores = new ArrayList<>();
        this.totalScore = 0;
        this.blockSpan = 0;
        this.numberOfCorrectTrials = 0;
    }

    public List<SequenceScore> getSequencesScores() {
        return sequencesScores;
    }

    public void addSequenceScore(SequenceScore sequenceScore) {
        this.sequencesScores.add(sequenceScore);
    }

    public String getPatientName() {
        return patientName;
    }

    public String getPatientSurname() {
        return patientSurname;
    }

    private int getNumberOfCorrectTrials() {
        numberOfCorrectTrials = 0;
        for (SequenceScore sequenceScore : sequencesScores) {
            if (sequenceScore.isUserCorrect()) numberOfCorrectTrials++;
        }
        return numberOfCorrectTrials;
    }

    public int getBlockSpan() {
        // TODO test this
        for (int i = sequencesScores.size()-1; i < 0 ; i--) {
            if (sequencesScores.get(i).isUserCorrect()) {
                blockSpan = sequencesScores.get(i).correctSequence().size();
                break;
            }
        }
        return blockSpan;
    }

    public float getTotalScore() {
        totalScore = getBlockSpan() * getNumberOfCorrectTrials();
        return totalScore;
    }

    public int getNumberOfSequences() {
        return sequencesScores.size();
    }
}
