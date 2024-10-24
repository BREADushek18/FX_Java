package org.example.fx_java;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class WordSwitcher extends Application {

    private boolean isFirstToSecond = true;

    @Override
    public void start(Stage primaryStage) {
        TextField textField1 = new TextField();
        TextField textField2 = new TextField();
        Button switchButton = new Button("→");

        // Установка стилей
        textField1.setStyle("-fx-text-fill: #d4bcdc; -fx-border-width: 2px;" +
                " -fx-border-color: #d3bce4; -fx-background-color: #35334c;");
        textField2.setStyle("-fx-text-fill: #d4bcdc; -fx-border-width: 2px; " +
                "-fx-border-color: #d3bce4; -fx-background-color: #35334c;");
        textField2.setEditable(false);
        switchButton.setStyle("-fx-font-size: 20px; -fx-background-color: #35334c; -fx-border-width: 2px; -fx-border-color: #d3bce4;-fx-text-fill: #d4bcdc;");

        switchButton.setOnAction(event -> {
            String temp = textField1.getText();
            textField1.setText(textField2.getText());
            textField2.setText(temp);
            switchButton.setText(isFirstToSecond ? "←" : "→");
            isFirstToSecond = !isFirstToSecond;
        });

        HBox hbox = new HBox(10, textField1, switchButton, textField2);
        hbox.setStyle("-fx-padding: 40px 0 0 10px;");
        Pane root = new Pane(hbox);
        Scene scene = new Scene(root, 400, 100);
        scene.getRoot().setStyle("-fx-background-color: #747090;");
        primaryStage.setScene(scene);
        primaryStage.setTitle("Перекидыватель слов");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
