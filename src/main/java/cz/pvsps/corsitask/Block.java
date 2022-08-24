package cz.pvsps.corsitask;

import javafx.scene.shape.Rectangle;

public class Block {
    private int x;
    private int y;

    public Rectangle shape;


    public Block(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
