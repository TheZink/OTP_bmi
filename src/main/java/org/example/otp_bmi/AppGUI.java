package org.example.otp_bmi;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

public class AppGUI extends Application {
    private ResourceBundle rb;
    private BMICalc bmiCalc = new BMICalc();

    private double bmi = 0.0;

    @Override
    public void start(Stage stage) throws IOException {
        setLocale("en", "US");

        ComboBox<String> langComboBox = new ComboBox<>();
        langComboBox.getItems().setAll("English", "Finnish", "Swedish", "Japanese", "Vietnamese","Urdu");
        langComboBox.getSelectionModel().selectFirst();

        Label label_info = new Label();
        label_info.setText(rb.getString("Prompt.prompt"));

        TextField heightField = new TextField();
        heightField.setPromptText(rb.getString("Prompt.height"));

        TextField weightField = new TextField();
        weightField.setPromptText(rb.getString("Prompt.weight"));

        Button calcButton = new Button();
        calcButton.setText(rb.getString("Button.calc"));

        Button clearButton = new Button();
        clearButton.setText(rb.getString("Button.clear"));

        Label label_result = new Label();

        // Language selection handler
        langComboBox.setOnAction(event -> {
            String selectLanguage = langComboBox.getSelectionModel().getSelectedItem();

            switch (selectLanguage) {
                case "English":
                    setLocale("en", "US");
                    break;
                case "Finnish":
                    setLocale("fi", "FI");
                    break;
                case "Swedish":
                    setLocale("sv", "SE");
                    break;
                case "Japanese":
                    setLocale("ja", "JP");
                    break;
                case "Vietnamese":
                    setLocale("vi", "VN");
                case "Urdu":
                    setLocale("ur", "PK");
            }

            // Clear field
            heightField.setText("");
            weightField.setText("");
            
            // Refresh texts
            label_info.setText(rb.getString("Prompt.prompt"));
            heightField.setPromptText(rb.getString("Prompt.height"));
            weightField.setPromptText(rb.getString("Prompt.weight"));
            calcButton.setText(rb.getString("Button.calc"));
            clearButton.setText(rb.getString("Button.clear"));
            label_result.setText("");
        });

        // Calculate BMI handler
        calcButton.setOnAction(event -> {
            try {
                Double height = Double.parseDouble(heightField.getText().trim());
                Double weight = Double.parseDouble(weightField.getText().trim());

                bmi = bmiCalc.calculate(weight, height);

                label_result.setText(rb.getString("Info.result") +": " + String.format("%.2f", bmi));

            } catch (NumberFormatException ex){
                Alert alert = new Alert(Alert.AlertType.WARNING, rb.getString("Info.error"));
                alert.showAndWait();
            }
        });

        // Clear button handler
        clearButton.setOnAction(event -> {
            weightField.setText("");;
            heightField.setText("");
            label_result.setText("");

            heightField.setPromptText(rb.getString("Prompt.height"));
            weightField.setPromptText(rb.getString("Prompt.weight"));
        });

        Label langLabel = new Label("Language");
        HBox langRow = new HBox(8, langLabel, langComboBox);
        
        GridPane inputGrid = new GridPane();
        inputGrid.setHgap(8);
        inputGrid.setVgap(8);
        inputGrid.add(langRow, 0, 0);
        inputGrid.add(heightField, 0,1);
        inputGrid.add(weightField, 2, 1);
        
        GridPane buttonGrid = new GridPane();
        buttonGrid.setHgap(0);
        buttonGrid.setVgap(0);
        buttonGrid.add(calcButton, 0, 1);
        buttonGrid.add(clearButton, 1, 1);
        buttonGrid.add(label_result, 3, 1);

        
        VBox root = new VBox(inputGrid, buttonGrid);

        Scene scene = new Scene(root, 400, 130);
        stage.setTitle("BMI (Ilkka Sinkonen)");
        stage.setScene(scene);
        stage.show();
    }

    private void setLocale(String lang, String country){
        Locale locale = new Locale(lang, country);
        rb = ResourceBundle.getBundle("MessageBundle", locale);
    }

    public static void main(String[] args) {
        launch();
    }
}