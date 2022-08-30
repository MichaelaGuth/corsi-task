package cz.pvsps.corsitask.result;

import java.util.ArrayList;
import java.util.List;

public class SequenceScore {
    private final List<Integer> correctSequence;
    private final List<Integer> userSequence;
    private final long userTime; //Duration?

    public SequenceScore(List<Integer> correctSequence, List<Integer> userSequence, long userTime) {
        this.correctSequence = correctSequence;
        this.userSequence = userSequence;
        this.userTime = userTime;
    }

    public List<Integer> getCorrectSequence() {
        return correctSequence;
    }

    public List<Integer> getUserSequence() {
        return userSequence;
    }

    public long getUserTime() {
        return userTime;
    }

    public boolean isUserCorrect() {
        return correctSequence.equals(userSequence);
    }

    public int getScore() {
        return isUserCorrect() ? 1 : 0;
    }


}
