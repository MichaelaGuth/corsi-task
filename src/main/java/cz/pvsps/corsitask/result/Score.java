package cz.pvsps.corsitask.result;

import java.util.List;

public class Score {
    private final List<SequenceScore> sequencesScores;
    private int blockSpan;
    private final float totalScore;
    private int numberOfCorrectTrials;

    public Score(List<SequenceScore> sequencesScores) {
        this.sequencesScores = sequencesScores;
        for (int i = sequencesScores.size()-1; i < 0 ; i--) {
            if (sequencesScores.get(i).isUserCorrect()) {
                this.blockSpan = sequencesScores.get(i).getCorrectSequence().size();
                break;
            }
        }
        this.numberOfCorrectTrials = 0;
        for (SequenceScore sequenceScore : sequencesScores) {
            if (sequenceScore.isUserCorrect()) this.numberOfCorrectTrials++;
        }
        this.totalScore = this.blockSpan * numberOfCorrectTrials;

    }

    public int getNumberOfCorrectTrials() {
        return numberOfCorrectTrials;
    }

    public float getBlockSpan() {
        return blockSpan;
    }

    public float getTotalScore() {
        return totalScore;
    }
}
