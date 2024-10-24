package org.example.fx_java;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class TextFlag extends Application {

    private ToggleGroup group1, group2, group3;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Текстовый флаг");
        primaryStage.setResizable(false); // Запрет изменения размера окна

        group1 = new ToggleGroup();
        group2 = new ToggleGroup();
        group3 = new ToggleGroup();

        String[] colors = {"Красный", "Зеленый", "Синий", "Белый"};
        RadioButton[] colorOptions1 = createColorOptions(colors, group1);
        RadioButton[] colorOptions2 = createColorOptions(colors, group2);
        RadioButton[] colorOptions3 = createColorOptions(colors, group3);

        Button drawButton = new Button("Нарисовать");
        drawButton.setOnAction(e -> drawFlag());
        drawButton.setStyle("-fx-background-color: #35334c; -fx-text-fill: #d4bcdc;");

        // Создаем сетку
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10));
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setAlignment(Pos.CENTER);
        grid.setStyle("-fx-background-color: #747090;");

        grid.add(new Label("Выберите цвет 1-й полосы:"), 0, 0);
        addRadioButtonsToGrid(grid, colorOptions1, 1);

        Label color2Label = new Label("Выберите цвет 2-й полосы:");
        grid.add(color2Label, 0, 5);
        addRadioButtonsToGrid(grid, colorOptions2, 6);

        Label color3Label = new Label("Выберите цвет 3-й полосы:");
        grid.add(color3Label, 0, 10);
        addRadioButtonsToGrid(grid, colorOptions3, 11);

        grid.add(drawButton, 0, 16);

        Scene scene = new Scene(grid, 300, 470);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private RadioButton[] createColorOptions(String[] colors, ToggleGroup group) {
        RadioButton[] radioButtons = new RadioButton[colors.length];
        for (int i = 0; i < colors.length; i++) {
            radioButtons[i] = new RadioButton(colors[i]);
            radioButtons[i].setToggleGroup(group);
        }
        return radioButtons;
    }

    private void addRadioButtonsToGrid(GridPane grid, RadioButton[] radioButtons, int row) {
        for (int i = 0; i < radioButtons.length; i++) {
            grid.add(radioButtons[i], 0, row + i);
        }
    }

    private void drawFlag() {
        String color1 = getSelectedColor(group1);
        String color2 = getSelectedColor(group2);
        String color3 = getSelectedColor(group3);

        if (color1 != null && color2 != null && color3 != null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Выбранные цвета");
            alert.setHeaderText(null);
            alert.setContentText("Выбранные цвета: " + color1 + ", " + color2 + ", " + color3);
            alert.showAndWait();
        } else {
            showAlert();
        }
    }

    private String getSelectedColor(ToggleGroup group) {
        RadioButton selected = (RadioButton) group.getSelectedToggle();
        return selected != null ? selected.getText() : null;
    }

    private void showAlert() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Предупреждение");
        alert.setHeaderText(null);
        alert.setContentText("Пожалуйста, выберите цвет для каждой полосы.");
        alert.showAndWait();
    }
}
