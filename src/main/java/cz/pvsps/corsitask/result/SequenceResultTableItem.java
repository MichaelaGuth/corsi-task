package cz.pvsps.corsitask.result;

import javafx.beans.property.SimpleStringProperty;

public class SequenceResultTableItem {
    private final SimpleStringProperty time;
    private final SimpleStringProperty fromTo;


    public SequenceResultTableItem(long time, String start, String end) {
        this.time = new SimpleStringProperty((double)time/1000 + " s");
        this.fromTo = new SimpleStringProperty(start + " - " + end);
    }

    public String getTime() {
        return time.get();
    }

    public SimpleStringProperty timeProperty() {
        return time;
    }

    public String getFromTo() {
        return fromTo.get();
    }

    public SimpleStringProperty fromToProperty() {
        return fromTo;
    }

}
