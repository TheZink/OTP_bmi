module org.example.otp_bmi {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens org.example.otp_bmi to javafx.fxml;
    exports org.example.otp_bmi;
}