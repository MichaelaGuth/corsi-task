module cz.pvsps.corsitask {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.ikonli.javafx;
    requires java.logging;
    requires json.simple;

    opens cz.pvsps.corsitask to javafx.fxml;
    opens cz.pvsps.corsitask.trial to javafx.fxml;
    opens cz.pvsps.corsitask.menu to javafx.fxml;
    opens cz.pvsps.corsitask.result to javafx.fxml;
    exports cz.pvsps.corsitask;
}