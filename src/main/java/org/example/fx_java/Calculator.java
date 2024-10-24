package org.example.fx_java;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Calculator extends Application {

    private TextField display;
    private Label historyLabel;
    private double num1 = 0;
    private String operator = "";
    private boolean startNewNumber = true;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Калькулятор");

        display = new TextField();
        display.setEditable(false);
        display.setPrefSize(200, 50);
        display.setAlignment(Pos.CENTER_RIGHT);
        display.setStyle("-fx-background-color: #35334c; -fx-text-fill: #d4bcdc; -fx-font-size: 20px;");

        historyLabel = new Label();
        historyLabel.setPrefWidth(200);
        historyLabel.setAlignment(Pos.CENTER_RIGHT);
        historyLabel.setStyle("-fx-text-fill: #35334c; -fx-font-size: 12px;");

        GridPane root = new GridPane();
        root.setPadding(new Insets(10));
        root.setHgap(10);
        root.setVgap(10);
        root.setAlignment(Pos.CENTER);
        root.setStyle("-fx-background-color: #747090; ");

        String[] buttonLabels = {
                "7", "8", "9", "/",
                "4", "5", "6", "*",
                "1", "2", "3", "-",
                "0", ",", "=", "+"
        };

        int row = 2;
        int col = 0;

        for (String label : buttonLabels) {
            Button button = new Button(label);
            button.setPrefSize(50, 50);
            button.setStyle("-fx-background-color: #35334c; -fx-text-fill: #d4bcdc; -fx-font-size: 20px;");
            button.setOnAction(e -> handleButtonClick(label));
            root.add(button, col, row);
            ++col;
            if (col > 3) {
                col = 0;
                ++row;
            }
        }

        // Добавляем историю и дисплей
        root.add(historyLabel, 0, 0, 4, 1);
        root.add(display, 0, 1, 4, 1);

        Scene scene = new Scene(root, 250, 350);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void handleButtonClick(String label) {
        if (label.matches("[0-9.]")) {
            if (startNewNumber) {
                display.setText(label);
                startNewNumber = false;
            } else {
                display.setText(display.getText() + label);
            }
        } else if (label.matches("[/*\\-+]")) {
            operator = label;
            num1 = Double.parseDouble(display.getText());
            updateHistory(num1 + " " + operator);
            startNewNumber = true;
        } else if (label.equals("=")) {
            calculateResult();
        }
    }

    private void updateHistory(String text) {
        historyLabel.setText(text);
    }

    private void calculateResult() {
        double num2 = Double.parseDouble(display.getText());
        double result;

        try {
            result = switch (operator) {
                case "+" -> num1 + num2;
                case "-" -> num1 - num2;
                case "*" -> num1 * num2;
                case "/" -> {
                    if (num2 == 0) {
                        throw new ArithmeticException("деление на 0");
                    }
                    yield num1 / num2;
                }
                default -> 0;
            };
            updateHistory(historyLabel.getText() + " " + num2 + " ="); // Обновляем историю с результатом
            display.setText(String.valueOf(result));
            startNewNumber = true;
        } catch (ArithmeticException ex) {
            showAlert();
            display.clear();
        }
    }

    private void showAlert() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Ошибка");
        alert.setHeaderText(null);
        alert.setContentText("Деление на 0 невозможно");
        alert.showAndWait();
    }
}

