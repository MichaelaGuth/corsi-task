module cz.pvsps.corsitask {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.ikonli.javafx;
    requires java.logging;
    requires json.simple;
    requires java.desktop;

    opens cz.pvsps.corsitask to javafx.fxml;
    opens cz.pvsps.corsitask.corsitest to javafx.fxml;
    opens cz.pvsps.corsitask.menu to javafx.fxml;
    opens cz.pvsps.corsitask.result to javafx.fxml;
    opens cz.pvsps.corsitask.settings to javafx.fxml;
    opens cz.pvsps.corsitask.tools to javafx.fxml;
    exports cz.pvsps.corsitask;
}