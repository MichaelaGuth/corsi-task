package cz.pvsps.corsitask.result;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class Score {
    @JsonProperty("patientName")
    private String patientName;
    @JsonProperty("patientSurname")
    private String patientSurname;
    @JsonProperty("patientBirthdate")
    private LocalDate patientBirthdate;
    @JsonProperty("testDate")
    private LocalDateTime testDate;
    @JsonProperty("patientID")
    private UUID patientID;
    @JsonProperty("sequencesScores")
    private List<SequenceScore> sequencesScores;
    @JsonProperty("blockSpan")
    private int blockSpan;
    @JsonProperty("totalScore")
    private int totalScore;
    @JsonProperty("numberOfCorrectTrials")
    private int numberOfCorrectTrials;

    public Score(String patientName, String patientSurname, LocalDate patientBirthdate, UUID patientID) {
        this.patientName = patientName;
        this.patientSurname = patientSurname;
        this.sequencesScores = new ArrayList<>();
        this.totalScore = 0;
        this.blockSpan = 0;
        this.numberOfCorrectTrials = 0;
        this.patientBirthdate = patientBirthdate;
        this.patientID = patientID;
        this.testDate = LocalDateTime.now();
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

    public int getNumberOfCorrectTrials() {
        numberOfCorrectTrials = 0;
        for (SequenceScore sequenceScore : sequencesScores) {
            if (sequenceScore.isUserCorrect()) numberOfCorrectTrials++;
        }
        return numberOfCorrectTrials;
    }

    /**
     *
     * @return The length of the longest correctly selected sequence.
     */
    public int getBlockSpan() {
        Collections.reverse(sequencesScores);
        for (SequenceScore sequenceScore :
                sequencesScores) {
            if (sequenceScore.isUserCorrect()) {
                blockSpan = sequenceScore.correctSequence().size();
                break;
            }
        }
        Collections.reverse(sequencesScores);
        return blockSpan;
    }

    /**
     *
     * @return (BlockSpan) * (Number of correctly selected sequences)
     */
    public float getTotalScore() {
        totalScore = getBlockSpan() * getNumberOfCorrectTrials();
        return totalScore;
    }

    public int getNumberOfSequences() {
        return sequencesScores.size();
    }

    public LocalDateTime getTestDate() {
        return testDate;
    }
}
