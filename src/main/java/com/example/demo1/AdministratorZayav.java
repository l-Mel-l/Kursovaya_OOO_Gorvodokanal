package com.example.demo1;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

public class AdministratorZayav {
    @FXML
    private Label IdZayav;
    @FXML
    private Label fioZayav;
    @FXML
    private Label mailZayavid;
    @FXML
    private Label DatepodZayavId;
    @FXML
    private ScrollPane zayavScrollPane;
    @FXML
    private TextArea zayavTextArea;
    @FXML
    private Button BackAdministratorZayavBtn;
    @FXML
    private Label StatuszayavId;
    @FXML
    private Label numberZayavid;
    @FXML
    private Label addressZayavid;
    @FXML
    private  Label IborndateZayavid;

    public void initializeWithData(String[] application) {
        IdZayav.setText(application[0]);
        fioZayav.setText(application[1]);
        mailZayavid.setText(application[2]);
        DatepodZayavId.setText(application[3]);
        numberZayavid.setText(application[5]);
        addressZayavid.setText(application[6]);
        IborndateZayavid.setText(application[7]);

        // Устанавливаем текст в TextArea
        zayavTextArea.setText(application[4]);

        if (IdZayav.getText().length() > 10) {
            Tooltip fioTooltip = new Tooltip(IdZayav.getText());
            Tooltip.install(IdZayav, fioTooltip);
        }
        if (fioZayav.getText().length() > 10) {
            Tooltip numberTooltip = new Tooltip(fioZayav.getText());
            Tooltip.install(fioZayav, numberTooltip);
        }
        if (mailZayavid.getText().length() > 10) {
            Tooltip addressTooltip = new Tooltip(mailZayavid.getText());
            Tooltip.install(mailZayavid, addressTooltip);
        }
        if (DatepodZayavId.getText().length() > 10) {
            Tooltip mailTooltip = new Tooltip(DatepodZayavId.getText());
            Tooltip.install(DatepodZayavId, mailTooltip);
        }

        BackAdministratorZayavBtn.setOnAction(actionEvent -> {
            // Закрываем текущее окно
            BackAdministratorZayavBtn.getScene().getWindow().hide();

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("Administrator-view.fxml"));

            try {
                Parent root = loader.load();

                // Получаем текущий Stage
                Stage stage = (Stage) BackAdministratorZayavBtn.getScene().getWindow();

                // Устанавливаем новое содержимое для окна
                stage.setScene(new Scene(root));
                stage.show();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

    }
}