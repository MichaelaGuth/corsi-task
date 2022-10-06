module cz.pvsps.corsitask {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.ikonli.javafx;
    requires java.logging;
    requires json.simple;
    requires com.fasterxml.jackson.databind;
    requires  com.fasterxml.jackson.datatype.jsr310;
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.annotation;
    requires java.desktop;
    requires AnimateFX;

    opens cz.pvsps.corsitask to javafx.fxml, javafx.base;
    opens cz.pvsps.corsitask.corsitest to javafx.fxml;
    opens cz.pvsps.corsitask.menu to javafx.fxml;
    opens cz.pvsps.corsitask.result to javafx.fxml, com.fasterxml.jackson.databind, javafx.base;
    opens cz.pvsps.corsitask.tools to javafx.fxml, com.fasterxml.jackson.databind;
    opens cz.pvsps.corsitask.settings to com.fasterxml.jackson.databind, javafx.fxml;
    exports cz.pvsps.corsitask;
    exports cz.pvsps.corsitask.tools;
    exports cz.pvsps.corsitask.settings;
    exports cz.pvsps.corsitask.result;
}

