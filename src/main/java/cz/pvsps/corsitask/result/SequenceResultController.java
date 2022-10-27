package cz.pvsps.corsitask.result;

import cz.pvsps.corsitask.Constants;
import cz.pvsps.corsitask.tools.Block;
import cz.pvsps.corsitask.tools.Point;
import cz.pvsps.corsitask.tools.Tools;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.List;

import static cz.pvsps.corsitask.Main.stage;

public class SequenceResultController {
    public AnchorPane anchorPane;
    public Rectangle block1;
    public Rectangle block2;
    public Rectangle block3;
    public Rectangle block4;
    public Rectangle block5;
    public Rectangle block6;
    public Rectangle block7;
    public Rectangle block8;
    public Rectangle block9;
    public Label labelBlock1;
    public Label labelBlock2;
    public Label labelBlock3;
    public Label labelBlock4;
    public Label labelBlock5;
    public Label labelBlock6;
    public Label labelBlock7;
    public Label labelBlock8;
    public Label labelBlock9;
    public TableColumn<SequenceResultTableItem, SimpleStringProperty> fromBlockToBlockColumn;
    public TableColumn<SequenceResultTableItem, SimpleStringProperty> timeColumn;
    public Label correctSequenceLabel;
    public Label userSequenceLabel;
    public Button showCorrectSequenceButton;
    public Button goBackButton;
    public Button showUserSequenceButton;
    public TableView<SequenceResultTableItem> table;
    public Button showNeutralButton;
    public Button showRangeButton;
    private ArrayList<Rectangle> allBlocks;
    private ArrayList<Label> allBlockLabels;
    private ArrayList<Line> userSequenceLines;
    private ArrayList<Line> correctSequenceLines;
    public static SequenceScore sequenceScore;

    @FXML
    public void initialize() {
        stage.setOnCloseRequest(windowEvent -> {
            Tools.changeScene(Constants.RESULT);
            windowEvent.consume();
        });
        correctSequenceLabel.setText(sequenceScore.getCorrectSequence().toString());
        userSequenceLabel.setText(sequenceScore.getUserSequence().toString());
        allBlocks = getListOfBlocks();
        allBlockLabels = getListOfBlockLabels();
        setTable();
        table.getSelectionModel().select(0);
        correctSequenceLines = drawLinesForSequence(sequenceScore.getCorrectSequence(), Color.GREEN, correctSequenceLines);
        userSequenceLines = drawLinesForSequence(sequenceScore.getUserSequence(), Color.RED, userSequenceLines);

    }

    private void setTable() {
        fromBlockToBlockColumn.setCellValueFactory(new PropertyValueFactory<>("fromTo"));
        timeColumn.setCellValueFactory(new PropertyValueFactory<>("time"));
        ObservableList<SequenceResultTableItem> list = getTableItemsList(sequenceScore);
        table.setItems(list);
    }

    private ObservableList<SequenceResultTableItem> getTableItemsList(SequenceScore sequenceScore) {
        ObservableList<SequenceResultTableItem> list = FXCollections.observableArrayList();
        ArrayList<Block> sequence = sequenceScore.getUserSequence();
        ArrayList<Long> times = sequenceScore.getTimesBetweenBlocks();
        for (int i = 0; i <= sequence.size(); i++) {
            if (i == 0) {
                list.add(new SequenceResultTableItem(times.get(i), "start", sequence.get(i).toString()));
            } else if (i == sequence.size()) {
                list.add(new SequenceResultTableItem(times.get(i), sequence.get(i-1).toString(),"konec"));
            } else {
                list.add(new SequenceResultTableItem(times.get(i), sequence.get(i-1).toString(),sequence.get(i).toString()));
            }
        }
        return list;
    }

    private ArrayList<Label> getListOfBlockLabels() {
        ArrayList<Label> labels = new ArrayList<>();
        labels.add(labelBlock1);
        labels.add(labelBlock2);
        labels.add(labelBlock3);
        labels.add(labelBlock4);
        labels.add(labelBlock5);
        labels.add(labelBlock6);
        labels.add(labelBlock7);
        labels.add(labelBlock8);
        labels.add(labelBlock9);
        return labels;
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

    private ArrayList<Line> drawLinesForSequence(List<Block> sequence, Color color, ArrayList<Line> listOfLines) {
        deleteLinesFromAnchorPane(listOfLines);
        listOfLines = new ArrayList<>();
        for (int i = 0; i < sequence.size()-1; i++) {
            Line line = drawLineBetweenTwoBlocks(allBlocks.get(sequence.get(i).number()-1), allBlocks.get(sequence.get(i+1).number()-1), color);
            listOfLines.add(line);
        }
        return listOfLines;
    }

    private Line drawLineBetweenTwoBlocks(Rectangle block1, Rectangle block2, Paint color) {
        double tmp = block1.getHeight() / 2;
        Point startPoint = new Point((int) (block1.getX() + tmp), (int) (block1.getY() + tmp));
        Point endPoint = new Point((int) (block2.getX() + tmp), (int) (block2.getY() + tmp));
        return drawLineBetweenTwoPoints(startPoint, endPoint, color);
    }

    private Line drawLineBetweenTwoPoints(Point startPoint, Point endPoint, Paint color) {
        Line line = new Line();
        line.setStroke(color);
        line.setStrokeWidth(3);
        line.setVisible(true);
        line.setStartX(startPoint.x());
        line.setStartY(startPoint.y());
        line.setEndX(endPoint.x());
        line.setEndY(endPoint.y());
        anchorPane.getChildren().add(line);
        return line;
    }

    private void deleteLinesFromAnchorPane(ArrayList<Line> listOfLines) {
        if (listOfLines != null) {
            for (Line line :
                    listOfLines) {
                anchorPane.getChildren().remove(line);
            }
        }
    }

    public void showUserSequenceButtonOnAction() {
        correctSequenceLines = drawLinesForSequence(sequenceScore.getCorrectSequence(), Color.GREEN, correctSequenceLines);
        userSequenceLines = drawLinesForSequence(sequenceScore.getUserSequence(), Color.RED, userSequenceLines);
        changeLinesWidth(userSequenceLines);
    }

    public void showCorrectSequenceButtonOnAction() {
        userSequenceLines = drawLinesForSequence(sequenceScore.getUserSequence(), Color.RED, userSequenceLines);
        correctSequenceLines = drawLinesForSequence(sequenceScore.getCorrectSequence(), Color.GREEN, correctSequenceLines);
        changeLinesWidth(correctSequenceLines);
    }

    public void goBackButtonOnAction() {
        sequenceScore = null;
        Tools.changeScene(Constants.RESULT);
    }

    public void showNeutralButtonOnAction() {
        correctSequenceLines = drawLinesForSequence(sequenceScore.getCorrectSequence(), Color.GREEN, correctSequenceLines);
        userSequenceLines = drawLinesForSequence(sequenceScore.getUserSequence(), Color.RED, userSequenceLines);
    }

    private void changeLinesWidth(ArrayList<Line> lines) {
        for (Line line : lines) {
            line.setStrokeWidth(8);
        }
    }

    public void showRangeButtonOnAction() {
        SequenceResultTableItem sequenceResultTableItem = table.getSelectionModel().getSelectedItem();
        int index = table.getItems().indexOf(sequenceResultTableItem);
        showNeutralButtonOnAction();
        if (index-1 >= 0 && index-1 < userSequenceLines.size()) {
            userSequenceLines.get(index-1).setStrokeWidth(8);
        }
    }
}
