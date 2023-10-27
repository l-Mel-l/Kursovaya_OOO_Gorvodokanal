package com.example.demo1;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class Zayav {
    @FXML
    private Label IdZayav;
    @FXML
    private Label fioZayav;
    @FXML
    private Label mailZayavid;
    @FXML
    private Label DatepodZayavId;
    @FXML
    private Label zayavId;
    @FXML
    private Button BackZayavBtn;

    public void initializeWithData(String[] application) {
        IdZayav.setText(application[0]);
        fioZayav.setText(application[1]);
        mailZayavid.setText(application[2]);
        DatepodZayavId.setText(application[3]);
        zayavId.setText(application[4]);
        zayavId.setWrapText(true);
        BackZayavBtn.setOnAction(actionEvent -> {
            // Закрываем текущее окно
            BackZayavBtn.getScene().getWindow().hide();

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("hello-view.fxml"));

            try {
                Parent root = loader.load();

                // Получаем текущий Stage
                Stage stage = (Stage) BackZayavBtn.getScene().getWindow();

                // Устанавливаем новое содержимое для окна
                stage.setScene(new Scene(root));
                stage.show();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

    }

}