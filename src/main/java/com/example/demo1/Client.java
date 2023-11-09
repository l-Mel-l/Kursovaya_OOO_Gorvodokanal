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
import java.util.List;
import java.util.ResourceBundle;

import static com.example.demo1.DataBase.getUerZayav;

public class Client {
    @FXML
    private Label Fioclientid;
    @FXML
    private Label BorndateClientid;
    @FXML
    private Label MailClientid;
    @FXML
    private Label NimberClientid;
    @FXML
    private Label addressClientid;
    @FXML
    private Label ParolClientid;
    @FXML
    private Button BackAdministratorClientBtn;
    @FXML
    private VBox myApplicationsVBox;
    public void initializeWithData(String[] clientData) {
        Fioclientid.setText(clientData[0]);
        BorndateClientid.setText(clientData[4]);
        addressClientid.setText(clientData[3]);
        NimberClientid.setText(clientData[2]);
        MailClientid.setText(clientData[1]);
        ParolClientid.setText(clientData[5]);
        List<String[]> userApplications = getUerZayav(MailClientid.getText());

        // Create panes for each old application
        for (String[] application : userApplications) {
            createApplicationPane(application);
        }
        BackAdministratorClientBtn.setOnAction(actionEvent -> {
            // Закрываем текущее окно
            BackAdministratorClientBtn.getScene().getWindow().hide();

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("Administrator-view.fxml"));

            try {
                Parent root = loader.load();

                // Получаем текущий Stage
                Stage stage = (Stage) BackAdministratorClientBtn.getScene().getWindow();

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
        if (Fioclientid.getText().length() > 10) {
            Tooltip fioTooltip = new Tooltip(Fioclientid.getText());
            Tooltip.install(Fioclientid, fioTooltip);
        }
        if (NimberClientid.getText().length() > 10) {
            Tooltip numberTooltip = new Tooltip(NimberClientid.getText());
            Tooltip.install(NimberClientid, numberTooltip);
        }
        if (BorndateClientid.getText().length() > 10) {
            Tooltip addressTooltip = new Tooltip(BorndateClientid.getText());
            Tooltip.install(BorndateClientid, addressTooltip);
        }
        if (MailClientid.getText().length() > 10) {
            Tooltip mailTooltip = new Tooltip(MailClientid.getText());
            Tooltip.install(MailClientid, mailTooltip);
        }
        if (addressClientid.getText().length() > 10) {
            Tooltip bornDateTooltip = new Tooltip(addressClientid.getText());
            Tooltip.install(addressClientid, bornDateTooltip);
        }
        if (ParolClientid.getText().length() > 10) {
            Tooltip dateTooltip = new Tooltip(ParolClientid.getText());
            Tooltip.install(ParolClientid, dateTooltip);
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
}
