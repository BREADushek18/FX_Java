package org.example.fx_java;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class RestaurantOrder extends Application {

    private static class Dish {
        String name;
        double price;

        Dish(String name, double price) {
            this.name = name;
            this.price = price;
        }
    }

    private final List<Dish> menu = List.of(
            new Dish("Суши: 4 шт.", 300),
            new Dish("Роллы: 8 шт.", 400),
            new Dish("Темпура", 500),
            new Dish("Удон", 250),
            new Dish("Мисо-суп", 350),
            new Dish("Поке", 430),
            new Dish("Десерт", 180),
            new Dish("Зеленый чай", 70)
    );

    private final List<CheckBox> checkBoxes = new ArrayList<>();
    private final List<TextField> quantityFields = new ArrayList<>();

    @Override
    public void start(Stage primaryStage) {
        VBox vbox = new VBox(10);
        vbox.setStyle("-fx-padding: 20px; -fx-text-fill: #d4bcdc;");

        // Создаем элементы для каждого блюда
        for (Dish dish : menu) {
            HBox hbox = createDishRow(dish);
            vbox.getChildren().add(hbox);
        }

        Button orderButton = new Button("Оформить заказ");
        orderButton.setOnAction(event -> showReceipt());

        vbox.getChildren().add(orderButton);

        Scene scene = new Scene(vbox, 400, 400);
        scene.getRoot().setStyle("-fx-background-color: #747090; -fx-text-fill: #d4bcdc;");
        primaryStage.setScene(scene);
        primaryStage.setTitle("Заказ в ресторане");
        primaryStage.show();
    }

    private HBox createDishRow(Dish dish) {
        CheckBox checkBox = new CheckBox(dish.name + " - " + dish.price + " руб.");
        TextField quantityField = new TextField("0");
        quantityField.setEditable(false);
        quantityField.setPrefWidth(50);

        checkBoxes.add(checkBox);
        quantityFields.add(quantityField);

        Button increaseButton = new Button("+");
        Button decreaseButton = new Button("-");

        increaseButton.setOnAction(event -> {
            if (checkBox.isSelected()) {
                int quantity = Integer.parseInt(quantityField.getText());
                quantityField.setText(String.valueOf(quantity + 1));
            }
        });

        decreaseButton.setOnAction(event -> {
            if (checkBox.isSelected()) {
                int quantity = Integer.parseInt(quantityField.getText());
                if (quantity > 1) {
                    quantityField.setText(String.valueOf(quantity - 1));
                } else {
                    checkBox.setSelected(false);
                    quantityField.setText("0");
                }
            }
        });

        checkBox.setOnAction(event -> {
            if (checkBox.isSelected()) {
                quantityField.setText("1");
            } else {
                quantityField.setText("0");
            }
        });

        return new HBox(10, checkBox, quantityField, decreaseButton, increaseButton);
    }

    private void showReceipt() {
        Stage receiptStage = new Stage();
        receiptStage.initModality(Modality.APPLICATION_MODAL);
        VBox receiptBox = new VBox(10);
        receiptBox.setStyle("-fx-padding: 20px; -fx-background-color: #747090;");

        double totalCost = 0;

        for (int i = 0; i < menu.size(); i++) {
            Dish dish = menu.get(i);
            CheckBox checkBox = checkBoxes.get(i);
            TextField quantityField = quantityFields.get(i);

            if (checkBox.isSelected()) {
                int quantity = Integer.parseInt(quantityField.getText());
                double cost = quantity * dish.price;
                totalCost += cost;
                Label label = new Label(dish.name + " x" + quantity + " = " + cost + " руб.");
                label.setStyle("-fx-text-fill: #d4bcdc;");
                receiptBox.getChildren().add(label);
            }
        }

        Label totalLabel = new Label("Итого: " + totalCost + " руб.");
        totalLabel.setStyle("-fx-font-weight: bold; -fx-text-fill: #d4bcdc;");

        receiptBox.getChildren().add(totalLabel);

        Scene receiptScene = new Scene(receiptBox, 300, 200);
        receiptStage.setScene(receiptScene);
        receiptStage.setTitle("Чек");
        receiptStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}