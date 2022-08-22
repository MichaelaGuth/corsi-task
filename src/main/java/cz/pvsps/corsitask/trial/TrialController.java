package cz.pvsps.corsitask.trial;

import javafx.fxml.FXML;
import javafx.geometry.Rectangle2D;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.stage.Screen;

import java.util.ArrayList;

public class TrialController {
    public Rectangle block1;
    public Rectangle block2;
    public Rectangle block3;
    public Rectangle block4;
    public Rectangle block6;
    public Rectangle block5;
    public Rectangle block7;
    public Rectangle block8;
    public Rectangle block9;
    public AnchorPane anchorPane;
    public BorderPane borderPane;


    private ArrayList<Rectangle> allBlocks;

    private final Color YELLOW = Color.web("#f5da0f");
    private final Color BLUE = Color.web("#1f3bff");
    private final Color WHITE = Color.WHITE;


    @FXML
    public void initialize() {
        resize();
        allBlocks = getListOfBlocks();
    }

    private ArrayList<Rectangle> getListOfBlocks() {
        ArrayList<Rectangle> blocks = new ArrayList<>();
        blocks.add(block1);
        blocks.add(block2);
        blocks.add(block3);
        blocks.add(block4);
        blocks.add(block5);
        blocks.add(block6);
        blocks.add(block7);
        blocks.add(block8);
        blocks.add(block9);
        return blocks;
    }

    private void resetColors() {
        for (Rectangle block :
                allBlocks) {
            changeBlockColor(block, BLUE);
        }
    }

    public void resize() {
        Rectangle2D resolution = Screen.getPrimary().getBounds();
        double scale = resolution.getHeight() / borderPane.getPrefHeight();
        System.out.println(scale);
        anchorPane.setScaleY(scale);
        anchorPane.setScaleX(scale);
    }

    // TODO
    private void playSequence(int[] sequence) {

    }

    private void changeBlockColor(Rectangle block, Paint color) {
        block.setFill(color);
        block.setStroke(color);
    }

    private void changeBlockStrokeColor(Rectangle block, Paint color) {
        block.setStroke(color);
    }

    public void block1_OnMouseClicked(MouseEvent mouseEvent) {
        changeBlockColor(block1,YELLOW);
    }

    public void block2_OnMouseClicked(MouseEvent mouseEvent) {
        if (block2.getFill().equals(YELLOW)) {
            resetColors();
        } else {
            changeBlockColor(block2,YELLOW);
        }
    }

    public void block3_OnMouseClicked(MouseEvent mouseEvent) {
        changeBlockColor(block3,YELLOW);
    }

    public void block4_OnMouseClicked(MouseEvent mouseEvent) {
        changeBlockColor(block4,YELLOW);
    }

    public void block5_OnMouseClicked(MouseEvent mouseEvent) {
        changeBlockColor(block5,YELLOW);
    }

    public void block6_OnMouseClicked(MouseEvent mouseEvent) {
        changeBlockColor(block6,YELLOW);
    }

    public void block7_OnMouseClicked(MouseEvent mouseEvent) {
        changeBlockColor(block7,YELLOW);
    }

    public void block8_OnMouseClicked(MouseEvent mouseEvent) {
        changeBlockColor(block8,YELLOW);
    }

    public void block9_OnMouseClicked(MouseEvent mouseEvent) {
        changeBlockColor(block9,YELLOW);
    }

    public void block1_OnMouseEntered(MouseEvent mouseEvent) {
        changeBlockStrokeColor(block1, WHITE);
    }

    public void block1_OnMouseExited(MouseEvent mouseEvent) {
        changeBlockStrokeColor(block1, block1.getFill());
    }

    public void block2_OnMouseEntered(MouseEvent mouseEvent) {
        changeBlockStrokeColor(block2, WHITE);
    }

    public void block2_OnMouseExited(MouseEvent mouseEvent) {
        changeBlockStrokeColor(block2, block2.getFill());
    }

    public void block3_OnMouseEntered(MouseEvent mouseEvent) {
        changeBlockStrokeColor(block3, WHITE);
    }

    public void block3_OnMouseExited(MouseEvent mouseEvent) {
        changeBlockStrokeColor(block3, block3.getFill());
    }

    public void block4_OnMouseEntered(MouseEvent mouseEvent) {
        changeBlockStrokeColor(block4, WHITE);
    }

    public void block4_OnMouseExited(MouseEvent mouseEvent) {
        changeBlockStrokeColor(block4, block4.getFill());
    }

    public void block5_OnMouseEntered(MouseEvent mouseEvent) {
        changeBlockStrokeColor(block5, WHITE);
    }

    public void block5_OnMouseExited(MouseEvent mouseEvent) {
        changeBlockStrokeColor(block5, block5.getFill());
    }

    public void block6_OnMouseEntered(MouseEvent mouseEvent) {
        changeBlockStrokeColor(block6, WHITE);
    }

    public void block6_OnMouseExited(MouseEvent mouseEvent) {
        changeBlockStrokeColor(block6, block6.getFill());
    }

    public void block7_OnMouseEntered(MouseEvent mouseEvent) {
        changeBlockStrokeColor(block7, WHITE);
    }

    public void block7_OnMouseExited(MouseEvent mouseEvent) {
        changeBlockStrokeColor(block7, block7.getFill());
    }

    public void block8_OnMouseEntered(MouseEvent mouseEvent) {
        changeBlockStrokeColor(block8, WHITE);
    }

    public void block8_OnMouseExited(MouseEvent mouseEvent) {
        changeBlockStrokeColor(block8, block8.getFill());
    }

    public void block9_OnMouseEntered(MouseEvent mouseEvent) {
        changeBlockStrokeColor(block9, WHITE);
    }

    public void block9_OnMouseExited(MouseEvent mouseEvent) {
        changeBlockStrokeColor(block9, block9.getFill());
    }
}
