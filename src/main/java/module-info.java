module pl.sdacademy {
    requires javafx.controls;
    requires javafx.fxml;

    opens pl.sdacademy to javafx.fxml;
    exports pl.sdacademy;
}