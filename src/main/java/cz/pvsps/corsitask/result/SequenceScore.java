package cz.pvsps.corsitask.result;

import java.util.List;

public record SequenceScore(List<Integer> correctSequence, List<Integer> userSequence, long userTime) {

    public boolean isUserCorrect() {
        return correctSequence.equals(userSequence);
    }

    public int getScore() {
        return isUserCorrect() ? 1 : 0;
    }

}
