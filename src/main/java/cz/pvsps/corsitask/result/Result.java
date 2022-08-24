package cz.pvsps.corsitask.result;

import java.util.ArrayList;

public class Result {
    private final ArrayList<Integer> correctSequence;
    private final ArrayList<Integer> userSequence;
    private final int userTime; //Duration?
    private final boolean isUserCorrect;
    private final int score;

    public Result(ArrayList<Integer> correctSequence, ArrayList<Integer> userSequence, int userTime) {
        this.correctSequence = correctSequence;
        this.userSequence = userSequence;
        this.userTime = userTime;
        this.isUserCorrect = correctSequence.equals(userSequence);
        this.score = this.isUserCorrect ? 1 : 0;
    }

    public ArrayList<Integer> getCorrectSequence() {
        return correctSequence;
    }

    public ArrayList<Integer> getUserSequence() {
        return userSequence;
    }

    public int getUserTime() {
        return userTime;
    }

    public boolean isUserCorrect() {
        return isUserCorrect;
    }

    public int getScore() {
        return score;
    }
}
