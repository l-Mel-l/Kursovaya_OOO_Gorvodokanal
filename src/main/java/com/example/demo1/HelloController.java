package com.example.demo1;

import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;
import javafx.scene.control.Label;
import javafx.stage.Popup;
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
    private VBox myApplicationsVBox;
    @FXML
    private  Label DATEID;
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

        // Load user's old applications from the database
        List<String[]> userApplications = getUerZayav(InfoBank.currentMail);

        // Create panes for each old application
        for (String[] application : userApplications) {
            createApplicationPane(application);
        }

        Popup popup = new Popup();
        Label popupLabel = new Label("Заявление отправлено");
        popup.getContent().add(popupLabel);

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
            String datepodText = DATEID.getText();
            if(ZAYAVID == null){
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Ошибка");
                alert.setHeaderText(null);
                alert.setContentText("Заявление не должно быть пустым");

                // Создаем кнопку "Ок" и добавляем ее в всплывающее окно
                ButtonType okButton = new ButtonType("Ок");
                alert.getButtonTypes().setAll(okButton);

                // Отображаем всплывающее окно и ждем закрытия
                alert.showAndWait();}
            else{
                NewZayav(loginText,numberText,adressText,fioText,dataText,datepodText,zayavText);
                updateApplicationsUI();
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Успех");
                alert.setHeaderText(null);
                alert.setContentText("Заявление отправлено");

                // Создаем кнопку "Ок" и добавляем ее в всплывающее окно
                ButtonType okButton = new ButtonType("Ок");
                alert.getButtonTypes().setAll(okButton);

                // Отображаем всплывающее окно и ждем закрытия
                alert.showAndWait();
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
            if (FIOID.getText().length() > 10) {
                Tooltip fioTooltip = new Tooltip(FIOID.getText());
                Tooltip.install(FIOID, fioTooltip);
            }
        if (NUMBERID.getText().length() > 10) {
            Tooltip numberTooltip = new Tooltip(NUMBERID.getText());
            Tooltip.install(NUMBERID, numberTooltip);
        }
        if (ADRESSID.getText().length() > 10) {
            Tooltip addressTooltip = new Tooltip(ADRESSID.getText());
            Tooltip.install(ADRESSID, addressTooltip);
        }
        if (MAILID.getText().length() > 10) {
            Tooltip mailTooltip = new Tooltip(MAILID.getText());
            Tooltip.install(MAILID, mailTooltip);
        }
        if (BORNDATEID.getText().length() > 10) {
            Tooltip bornDateTooltip = new Tooltip(BORNDATEID.getText());
            Tooltip.install(BORNDATEID, bornDateTooltip);
        }
        if (DATEID.getText().length() > 10) {
            Tooltip dateTooltip = new Tooltip(DATEID.getText());
            Tooltip.install(DATEID, dateTooltip);
        }


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
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Zayav-view.fxml"));
                Parent root = loader.load();

                Zayav zayavInfoController = loader.getController();
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
    private void updateApplicationsUI() {
        // Очистите существующие заявления
        myApplicationsVBox.getChildren().clear();

        // Загрузите заявления из базы данных и создайте новые панели
        List<String[]> userApplications = getUerZayav(InfoBank.currentMail);
        for (String[] application : userApplications) {
            createApplicationPane(application);
        }
    }


}
