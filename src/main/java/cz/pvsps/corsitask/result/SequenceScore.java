package cz.pvsps.corsitask.result;

import com.fasterxml.jackson.annotation.JsonProperty;
import cz.pvsps.corsitask.tools.Block;

import java.util.ArrayList;

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
}
