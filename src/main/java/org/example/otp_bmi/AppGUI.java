package org.example.otp_bmi;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Locale;
import java.util.Map;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class AppGUI extends Application {
    private BMICalc bmiCalc = new BMICalc();
    private Map<String, String> localizedStrings;

    private Label label_info = new Label();
    private TextField heightField = new TextField();
    private TextField weightField = new TextField();
    private Button calcButton = new Button();
    private Button clearButton = new Button();
    private Label label_result = new Label();
    private Label label_time = new Label();

    private String selectedLanguage;


    @Override
    public void start(Stage stage) throws IOException {

        setLanguage(new Locale("en", "US"));
        selectedLanguage = "en";

        ComboBox<String> langComboBox = new ComboBox<>();
        langComboBox.getItems().setAll("English", "Finnish", "Swedish", "Japanese", "Vietnamese", "Urdu", "France");
        langComboBox.getSelectionModel().selectFirst();

        // Language selection handler
        langComboBox.setOnAction(event -> {
            String selectLanguage = langComboBox.getSelectionModel().getSelectedItem();

            switch (selectLanguage) {
                case "English":
                    setLanguage(new Locale("en", "US"));
                    selectedLanguage = "en";
                    break;
                case "Finnish":
                    setLanguage(new Locale("fi", "FI"));
                    selectedLanguage = "fi";
                    break;
                case "Swedish":
                    setLanguage(new Locale("sv", "SE"));
                    selectedLanguage = "sv";
                    break;
                case "Japanese":
                    setLanguage(new Locale("ja", "JP"));
                    selectedLanguage = "ja";
                    break;
                case "Vietnamese":
                    setLanguage(new Locale("vi", "VN"));
                    selectedLanguage = "vi";
                    break;
                case "Urdu":
                    setLanguage(new Locale("ur", "PK"));
                    selectedLanguage = "ur";
                    break;
                case "France":
                    setLanguage(new Locale("fr", "FR"));
                    selectedLanguage = "fr";
                    break;
            }
        });

        // Calculate BMI handler
        calcButton.setOnAction(event -> {
            onCalculateClick(event);
        });

        // Clear button handler
        clearButton.setOnAction(event -> {
            weightField.setText("");;
            heightField.setText("");
            label_result.setText("");

        });

        Label langLabel = new Label("Language");
        HBox langRow = new HBox(8, langLabel, langComboBox);
        
        GridPane inputGrid = new GridPane();
        inputGrid.setHgap(8);
        inputGrid.setVgap(8);
        inputGrid.add(langRow, 0, 0);
        inputGrid.add(heightField, 0, 2);
        inputGrid.add(weightField, 2, 2);
        
        GridPane buttonGrid = new GridPane();
        buttonGrid.setHgap(8);
        buttonGrid.setVgap(8);
        buttonGrid.add(calcButton, 0, 1);
        buttonGrid.add(clearButton, 1, 1);
        buttonGrid.add(label_result, 3, 1);
        buttonGrid.add(label_time, 4, 1);

        GridPane textGrid = new GridPane();
        textGrid.setHgap(8);
        textGrid.setVgap(8);
        textGrid.add(label_time, 0,1);
        textGrid.add(label_result, 2,1);

        VBox root = new VBox(inputGrid, buttonGrid, textGrid);

        Scene scene = new Scene(root, 350, 130);
        stage.setTitle("BMI (Ilkka Sinkonen)");
        stage.setScene(scene);
        stage.show();
    }

    private void setLanguage(Locale locale){
        label_result.setText("");
        localizedStrings = LocalizationService.getLocalizedStrings(locale);
        label_info.setText(localizedStrings.getOrDefault("info", "Welcome to the BMI calculator. Enter your information in the fields."));
        weightField.setPromptText(localizedStrings.getOrDefault("weight", "Weight"));
        heightField.setPromptText(localizedStrings.getOrDefault("height", "Height"));
        calcButton.setText(localizedStrings.getOrDefault("calculate", "Calculate"));
        clearButton.setText(localizedStrings.getOrDefault("clear", "Clear"));
        displayLocalTime(locale);
    }

    private void displayLocalTime(Locale locale){
        LocalTime currentTime = LocalTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm:ss", locale);
        String formattedTime = currentTime.format(formatter);
        label_time.setText(localizedStrings.getOrDefault("localTime", "Local Time") + " " + formattedTime);
    }

    public void onCalculateClick(ActionEvent actionEvent) {
        try {
            double weight = Double.parseDouble(weightField.getText());
            double height = Double.parseDouble(heightField.getText());
            double bmi = bmiCalc.calculate(weight,height);

            DecimalFormat df = new DecimalFormat("#0.00");
            label_result.setText(localizedStrings.getOrDefault("result", "Your BMI is") + " " + df.format(bmi));

            // Save to database
            String language = Locale.getDefault().getLanguage(); // or store current locale
            BMIResultService.saveResult(weight, height, bmi, selectedLanguage);

        } catch (NumberFormatException e) {
            label_result.setText(localizedStrings.getOrDefault("invalid", "Invalid input"));
        }
    }

    public static void main(String[] args) {
        launch();
    }
}