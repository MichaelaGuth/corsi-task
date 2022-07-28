module cz.pvsps.corsitask {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.ikonli.javafx;

    opens cz.pvsps.corsitask to javafx.fxml;
    exports cz.pvsps.corsitask;
}