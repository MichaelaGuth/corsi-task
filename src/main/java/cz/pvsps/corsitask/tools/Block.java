package cz.pvsps.corsitask.tools;

public record Block(int number) {

    @Override
    public String toString() {
        return String.valueOf(number);
    }
}
