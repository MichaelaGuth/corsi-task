package cz.pvsps.corsitask.tools;

public record Vector(double x, double y) {
    public double getLength() {
        return Math.sqrt((x * x) + (y * y));
    }
}
