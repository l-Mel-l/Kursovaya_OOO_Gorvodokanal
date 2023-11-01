package com.example.demo1;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import static com.example.demo1.DataBase.getAllZayav;

public class Administrator implements javafx.fxml.Initializable{
    @FXML
    private VBox myApplicationsVBox;
    @FXML
    private Button LogOutBtn;
    @FXML
    private VBox myApplicationsVBoxClent;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Load all applications from the database
        List<String[]> allApplications = getAllZayav();

        // Create panes for each application
        for (String[] application : allApplications) {
            createApplicationPane(application);
            createClientPane(application);
        }
        LogOutBtn.setOnAction(actionEvent -> {
            // Закрываем текущее окно
            LogOutBtn.getScene().getWindow().hide();

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("Register-view.fxml"));

            try {
                Parent root = loader.load();

                // Получаем текущий Stage
                Stage stage = (Stage) LogOutBtn.getScene().getWindow();

                // Устанавливаем новое содержимое для окна
                stage.setScene(new Scene(root));
                stage.show();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    private void createApplicationPane(String[] application) {
        String id = application[0];
        String fio = application[1];
        String date = application[3];

        AnchorPane newApplicationPane = new AnchorPane();
        newApplicationPane.setMinHeight(50);
        newApplicationPane.setPrefWidth(200);
        newApplicationPane.setStyle("-fx-background-color: #ECECEC; -fx-padding: 5px; -fx-margin: 5px;");

        Label idLabel = new Label("ID: " + id);
        idLabel.setLayoutX(10);
        idLabel.setLayoutY(10);

        Label fioLabel = new Label("ФИО: " + fio);
        fioLabel.setLayoutX(10);
        fioLabel.setLayoutY(30);

        Label dateLabel = new Label("Дата подачи: " + date);
        dateLabel.setLayoutX(10);
        dateLabel.setLayoutY(50);

        newApplicationPane.getChildren().addAll(idLabel, fioLabel, dateLabel);

        // Add click event handler to display application information
        newApplicationPane.setOnMouseClicked(mouseEvent -> {
            newApplicationPane.getScene().getWindow().hide();
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Administrator-Zayav-view.fxml"));
                Parent root = loader.load();

                AdministratorZayav zayavInfoController = loader.getController();
                zayavInfoController.initializeWithData(application);

                Stage stage = new Stage();
                stage.setTitle("Информация о заявлении");
                stage.setScene(new Scene(root));
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        myApplicationsVBox.getChildren().add(newApplicationPane);
    }
    private void createClientPane(String[] client) {
        String fio = client[1];
        String date = client[7];
        String address = client[4];
        String phone = client[5];
        String mail = client[2];

        AnchorPane newClientPane = new AnchorPane();
        newClientPane.setMinHeight(50);
        newClientPane.setPrefWidth(200);
        newClientPane.setStyle("-fx-background-color: #ECECEC; -fx-padding: 5px; -fx-margin: 5px;");

        Label fioLabel = new Label("ФИО: " + fio);
        fioLabel.setLayoutX(10);
        fioLabel.setLayoutY(10);

        Label dateLabel = new Label("Дата рождения: " + date);
        dateLabel.setLayoutX(10);
        dateLabel.setLayoutY(30);

        Label addressLabel = new Label("Адрес: " + address);
        addressLabel.setLayoutX(10);
        addressLabel.setLayoutY(50);

        Label phoneLabel = new Label("Телефон: " + phone);
        phoneLabel.setLayoutX(10);
        phoneLabel.setLayoutY(70);

        Label mailLabel = new Label("Почта: " + mail);
        mailLabel.setLayoutX(10);
        mailLabel.setLayoutY(90);

        newClientPane.getChildren().addAll(fioLabel, dateLabel, addressLabel, phoneLabel, mailLabel);

        // Add click event handler to display client information
        newClientPane.setOnMouseClicked(mouseEvent -> {
            newClientPane.getScene().getWindow().hide();
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Client-Info-view.fxml"));
                Parent root = loader.load();

                AdministratorZayav clientInfoController = loader.getController();
                clientInfoController.initializeWithData(client);

                Stage stage = new Stage();
                stage.setTitle("Информация о клиенте");
                stage.setScene(new Scene(root));
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        myApplicationsVBoxClent.getChildren().add(newClientPane);
    }

}



