module com.geekbrains.sep22.geekcloudclient {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.main to javafx.fxml;
    exports com.main;
}