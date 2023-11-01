package com.example.demo1;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Register {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button SignUpButton;

    @FXML
    private TextField loginField;

    @FXML
    private TextField passwordField;

    @FXML
    private Button signInButton;
    @FXML
    private Label wrongLoginLabel;

    @FXML
    void initialize() {
        SignUpButton.setOnAction(actionEvent -> {
            // Закрываем текущее окно
            SignUpButton.getScene().getWindow().hide();

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("SignUp.fxml"));

            try {
                Parent root = loader.load();

                // Получаем текущий Stage
                Stage stage = (Stage) SignUpButton.getScene().getWindow();

                // Устанавливаем новое содержимое для окна
                stage.setScene(new Scene(root));
                stage.show();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        signInButton.setOnAction(actionEvent -> {
            String loginText = loginField.getText().trim();
            String passwoedText = passwordField.getText().trim();
            if (!loginText.equals("") && !passwoedText.equals(""))
                loginUser(loginText, passwoedText);
            else
                System.out.println("Логин пустой");
        });
    }

    private void loginUser(String loginText, String passwordText) {
        int result = DataBase.getUserByMail(loginText, passwordText);
        if (result == 2) {
            InfoBank.currentMail = loginText;
            signInButton.getScene().getWindow().hide();

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("Administrator-view.fxml"));

            try {
                Parent root = loader.load();

                // Получаем текущий Stage
                Stage stage = (Stage) SignUpButton.getScene().getWindow();

                // Устанавливаем новое содержимое для окна
                stage.setScene(new Scene(root));
                stage.show();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        if (result == 1) {
            InfoBank.currentMail = loginText;
            signInButton.getScene().getWindow().hide();

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("hello-view.fxml"));

            try {
                Parent root = loader.load();

                // Получаем текущий Stage
                Stage stage = (Stage) SignUpButton.getScene().getWindow();

                // Устанавливаем новое содержимое для окна
                stage.setScene(new Scene(root));
                stage.show();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        else{
            wrongLoginLabel.setText("Неверный логин или пароль");
        }

    }
}


