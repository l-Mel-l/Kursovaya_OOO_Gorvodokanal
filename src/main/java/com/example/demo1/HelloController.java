package com.example.demo1;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.ScrollPane;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.scene.control.Label;
import javafx.stage.Popup;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;

import static com.example.demo1.DataBase.*;


public class HelloController implements javafx.fxml.Initializable{
    @FXML
    public Label FIOID;
    public Label NUMBERID;
    public Label ADRESSID;
    public Label MAILID;
    public Label BORNDATEID;
    public TextArea ZAYAVID;
    @FXML
    private Button LogOutBtn;
    @FXML
    private Button signUpRegButton;
    @FXML
    private VBox MyzayavID;
    @FXML
    private  Label DATEID;
    @FXML
    private ScrollPane scrollPane;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ZAYAVID.setWrapText(true);
        String[] userinfo = DataBase.getUerInfo(InfoBank.currentMail);
        FIOID.setText(userinfo[0]);
        BORNDATEID.setText(userinfo[1]);
        ADRESSID.setText(userinfo[2]);
        NUMBERID.setText(userinfo[3]);
        MAILID.setText(userinfo[4]);
        DATEID.setText(String.valueOf(LocalDate.now()));
        Popup popup = new Popup();
        Label popupLabel = new Label("Заявление отправлено");
        popup.getContent().add(popupLabel);
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);

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
        signUpRegButton.setOnAction(actionEvent -> {
            String loginText = MAILID.getText().trim();
            String numberText = NUMBERID.getText().trim();
            String adressText = ADRESSID.getText().trim();
            String dataText = BORNDATEID.getText().trim();
            String fioText = FIOID.getText().trim();
            String zayavText = ZAYAVID.getText();
            NewZayav(loginText,numberText,adressText,fioText,dataText,zayavText);
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Успех");
            alert.setHeaderText(null);
            alert.setContentText("Заявление отправлено");

            // Создаем кнопку "Ок" и добавляем ее в всплывающее окно
            ButtonType okButton = new ButtonType("Ок");
            alert.getButtonTypes().setAll(okButton);

            // Отображаем всплывающее окно и ждем закрытия
            alert.showAndWait();

            // Создание нового AnchorPane с Label
            AnchorPane newApplicationPane = new AnchorPane();
            newApplicationPane.setMinHeight(50);
            newApplicationPane.setPrefWidth(200);
            newApplicationPane.setStyle("-fx-background-color: #ECECEC; -fx-padding: 5px; -fx-margin: 5px;");

            Label idLabel = new Label("ID: " + "1");
            idLabel.setLayoutX(10);
            idLabel.setLayoutY(10);

            Label fioLabel = new Label("ФИО: " + FIOID.getText());
            fioLabel.setLayoutX(10);
            fioLabel.setLayoutY(30);

            Label dateLabel = new Label("Дата подачи: " + LocalDate.now());
            dateLabel.setLayoutX(10);
            dateLabel.setLayoutY(50);

            newApplicationPane.getChildren().addAll(idLabel, fioLabel, dateLabel);

            // Добавление нового AnchorPane в MyzayavID
            MyzayavID.getChildren().add(newApplicationPane);

            // Вывод информации о заявлении в консоль при клике на AnchorPane
            newApplicationPane.setOnMouseClicked(mouseEvent -> {
                System.out.println("Ваше заявление: " + fioLabel.getText());
            });



        });

    }

}