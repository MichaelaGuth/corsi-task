package cz.pvsps.corsitask.result;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static cz.pvsps.corsitask.Constants.SEPARATOR_CSV;

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
    @JsonProperty("numberOfSequences")
    private int numberOfSequences;


    public Score(){}

    public Score(String patientName, String patientSurname, LocalDate patientBirthdate, UUID patientID) {
        this.patientName = patientName;
        this.patientSurname = patientSurname;
        this.sequencesScores = new ArrayList<>();
        this.numberOfSequences = 0;
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
        this.numberOfSequences++;
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
                blockSpan = sequenceScore.getCorrectSequence().size();
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
        System.out.println(numberOfSequences);
        System.out.println(sequencesScores.size());
        System.out.println(sequencesScores.size() == numberOfSequences);
        return sequencesScores.size();
    }

    public LocalDateTime getTestDate() {
        return testDate;
    }


    public LocalDate getPatientBirthdate() {
        return patientBirthdate;
    }

    public UUID getPatientID() {
        return patientID;
    }


    public ArrayList<String> createCSV() {
        ArrayList<String> res = new ArrayList<>();
        res.add("name" + SEPARATOR_CSV + "surname" + SEPARATOR_CSV + "patientID" + SEPARATOR_CSV + "dateOfBirth"
                + SEPARATOR_CSV + "dateOfTest" + SEPARATOR_CSV + "totalScore" + SEPARATOR_CSV + "blockSpan"
                + SEPARATOR_CSV + "totalRawScore");
        StringBuilder csv = new StringBuilder(patientName);
        csv.append(SEPARATOR_CSV).append(patientSurname).append(SEPARATOR_CSV);
        csv.append(patientID).append(SEPARATOR_CSV);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        csv.append(patientBirthdate.format(dtf)).append(SEPARATOR_CSV);
        dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        csv.append(testDate.format(dtf)).append(SEPARATOR_CSV);
        csv.append(getTotalScore()).append(SEPARATOR_CSV);
        csv.append(getBlockSpan()).append(SEPARATOR_CSV);
        csv.append(getNumberOfCorrectTrials());
        res.add(csv.toString());

        return res;
    }
}
