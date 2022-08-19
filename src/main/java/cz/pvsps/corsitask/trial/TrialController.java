package cz.pvsps.corsitask.trial;

import javafx.fxml.FXML;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Screen;

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


    @FXML
    public void initialize() {
        Resize();
    }

    public void Resize() {
        Rectangle2D resolution = Screen.getPrimary().getBounds();
        double scale = resolution.getHeight() / borderPane.getPrefHeight();
        System.out.println(scale);
        anchorPane.setScaleY(scale);
        anchorPane.setScaleX(scale);
    }
}
