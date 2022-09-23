package cz.pvsps.corsitask.result;

import cz.pvsps.corsitask.tools.Block;

import java.util.ArrayList;

public record SequenceScore(ArrayList<Block> correctSequence, ArrayList<Block> userSequence, long userTime) {

    public boolean isUserCorrect() {
        return correctSequence.equals(userSequence);
    }

    public int getScore() {
        return isUserCorrect() ? 1 : 0;
    }

}
