package org.example.fx_java;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class WidgetToggle extends Application {

    @Override
    public void start(Stage primaryStage) {

        CheckBox checkBox1 = new CheckBox();
        CheckBox checkBox2 = new CheckBox();
        CheckBox checkBox3 = new CheckBox();

        String text1 = "Я - виджет 1";
        String text2 = "Я - виджет 2";
        String text3 = "Я - виджет 3";

        TextField textField = new TextField();
        textField.setText(text2);
        textField.setStyle("-fx-text-fill: #d4bcdc; -fx-border-width: 2px; -fx-border-color: #d3bce4; -fx-background-color: #35334c;");

        Button button = new Button(text3);
        button.setStyle("-fx-font-size: 20px; -fx-background-color: #35334c; -fx-text-fill: #d4bcdc; -fx-border-color: #d3bce4; -fx-border-width: 2px;");

        javafx.scene.control.Label label1 = new javafx.scene.control.Label(text1);
        label1.setTextFill(Color.web("#d4bcdc"));
        label1.setFont(Font.font(14));

        checkBox1.selectedProperty().addListener((observable, oldValue, newValue) -> {
            label1.setVisible(newValue);
        });

        checkBox2.selectedProperty().addListener((observable, oldValue, newValue) -> {
            textField.setVisible(newValue);
        });

        checkBox3.selectedProperty().addListener((observable, oldValue, newValue) -> {
            button.setVisible(newValue);
        });

        VBox vbox = new VBox(10, checkBox1, label1, checkBox2, textField, checkBox3, button);
        vbox.setStyle("-fx-padding: 20px 20px 20px 20px; -fx-spacing: 10px; -fx-alignment: top-left;");


        Scene scene = new Scene(vbox, 400, 300);
        scene.getRoot().setStyle("-fx-background-color: #747090;");

        primaryStage.setScene(scene);
        primaryStage.setTitle("Виджеты с чекбоксами");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
