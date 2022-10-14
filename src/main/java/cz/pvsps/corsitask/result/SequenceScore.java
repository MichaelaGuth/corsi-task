package cz.pvsps.corsitask.result;

import com.fasterxml.jackson.annotation.JsonProperty;
import cz.pvsps.corsitask.tools.Block;

import java.util.ArrayList;

import static cz.pvsps.corsitask.Constants.SEPARATOR_CSV;

public final class SequenceScore {
    @JsonProperty("correctSequence")
    private ArrayList<Block> correctSequence;
    @JsonProperty("userSequence")
    private ArrayList<Block> userSequence;
    @JsonProperty("userTime")
    private long userTime;
    @JsonProperty("trialNumber")
    private int trialNumber;
    @JsonProperty("userCorrect")
    private boolean userCorrect;
    @JsonProperty("score")
    private int score;
    @JsonProperty("timesBetweenBlocks")
    private ArrayList<Long> timesBetweenBlocks;

    public SequenceScore() {}

    public SequenceScore(ArrayList<Block> correctSequence, ArrayList<Block> userSequence, long userTime, ArrayList<Long> timesBetweenBlocks) {
        this(correctSequence, userSequence, userTime);
        this.timesBetweenBlocks = timesBetweenBlocks;
    }

    public SequenceScore(ArrayList<Block> correctSequence, ArrayList<Block> userSequence, long userTime) {
        this.correctSequence = correctSequence;
        this.userSequence = userSequence;
        this.userTime = userTime;
        this.trialNumber = 1;
        this.userCorrect = correctSequence.equals(userSequence);
        this.score = userCorrect ? 1 : 0;
    }

    public boolean isUserCorrect() {
        return userCorrect;
    }

    public int getScore() {
        return score;
    }

    public ArrayList<Block> getCorrectSequence() {
        return correctSequence;
    }

    public ArrayList<Block> getUserSequence() {
        return userSequence;
    }

    public long getUserTime() {
        return userTime;
    }


    public int getTrialNumber() {
        return trialNumber;
    }

    public void setTrialNumber(int trialNumber) {
        this.trialNumber = trialNumber;
    }

    public ArrayList<Long> getTimesBetweenBlocks() {
        return timesBetweenBlocks;
    }

    public ArrayList<String> createCSV() {
        ArrayList<String> result = new ArrayList<>();
        StringBuilder header = new StringBuilder("correctSequence" + SEPARATOR_CSV + "userSequence" + SEPARATOR_CSV
                + "userTime" + SEPARATOR_CSV);
        StringBuilder csv = new StringBuilder(correctSequence.toString() + SEPARATOR_CSV);
        csv.append(userSequence.toString()).append(SEPARATOR_CSV);
        csv.append(userTime / 1000.0).append(SEPARATOR_CSV);
        int i = 1;
        for (long time :
                timesBetweenBlocks) {
            csv.append(time / 1000.0).append(SEPARATOR_CSV);
            header.append("time").append(i++).append(SEPARATOR_CSV);
        }
        csv.delete(csv.length()-1, csv.length()-1);
        header.delete(header.length()-1, header.length()-1);
        result.add(header.toString());
        result.add(csv.toString());
        return result;
    }
}
