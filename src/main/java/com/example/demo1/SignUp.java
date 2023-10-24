package com.example.demo1;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Popup;
import javafx.stage.Stage;

import static com.example.demo1.DataBase.NewUser;

public class SignUp {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField Adresreg;

    @FXML
    private TextField Datereg;

    @FXML
    private TextField FIOreg;

    @FXML
    private TextField Loginreg;

    @FXML
    private TextField Numberreg;

    @FXML
    private TextField passwordreg;

    @FXML
    private Button signUpRegButton;
    @FXML
    private Button BackButtonReg;

    @FXML
    void initialize() {
        Popup popupReg = new Popup();
        Label popupLabel = new Label("Вы зарегистрировались!");
        popupReg.getContent().add(popupLabel);
        signUpRegButton.setOnAction(actionEvent -> {
            String loginText = Loginreg.getText().trim();
            String passwordText = passwordreg.getText().trim();
            String numberText = Numberreg.getText().trim();
            String adressText = Adresreg.getText().trim();
            String dataText = Datereg.getText().trim();
            String fioText = FIOreg.getText().trim();
            if (!loginText.equals("") && !passwordText.equals("")&& !numberText.equals("")&& !adressText.equals("")&& !dataText.equals("")&& !fioText.equals(""))
                NewUser(loginText, passwordText,numberText,adressText,fioText,dataText);
            else
                System.out.println("Логин пустой");
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Успех");
            alert.setHeaderText(null);
            alert.setContentText("Вы зарегистрировались!");

            // Создаем кнопку "Ок" и добавляем ее в всплывающее окно
            ButtonType okButton = new ButtonType("Ок");
            alert.getButtonTypes().setAll(okButton);

            // Отображаем всплывающее окно и ждем закрытия
            alert.showAndWait();
            signUpRegButton.getScene().getWindow().hide();

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("Register-view.fxml"));

            try {
                Parent root = loader.load();

                // Получаем текущий Stage
                Stage stage = (Stage) signUpRegButton.getScene().getWindow();

                // Устанавливаем новое содержимое для окна
                stage.setScene(new Scene(root));
                stage.show();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        BackButtonReg.setOnAction(actionEvent -> {
            // Закрываем текущее окно
            BackButtonReg.getScene().getWindow().hide();

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("Register-view.fxml"));

            try {
                Parent root = loader.load();

                // Получаем текущий Stage
                Stage stage = (Stage) BackButtonReg.getScene().getWindow();

                // Устанавливаем новое содержимое для окна
                stage.setScene(new Scene(root));
                stage.show();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }
        };




