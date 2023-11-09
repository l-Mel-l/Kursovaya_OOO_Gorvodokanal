package com.example.demo1;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
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
    private ScrollPane zayavScrollPane;
    @FXML
    private TextArea zayavTextArea;
    @FXML
    private Button BackZayavBtn;
    @FXML
    private Label StatuszayavId;

    public void initializeWithData(String[] application) {
        IdZayav.setText(application[0]);
        fioZayav.setText(application[1]);
        mailZayavid.setText(application[2]);
        DatepodZayavId.setText(application[3]);
        StatuszayavId.setText(application[8]);

        String status = application[8];
        if (status.equals("Создано") || status.equals("На рассмотрении")) {
            StatuszayavId.setTextFill(Color.DARKGOLDENROD); // Желтый цвет
        } else if (status.equals("Отклонено")) {
            StatuszayavId.setTextFill(Color.RED); // Красный цвет
        } else if (status.equals("Завершено")) {
            StatuszayavId.setTextFill(Color.DARKGREEN); // Зеленый цвет
        }else if (status.equals("В работе")) {
            StatuszayavId.setTextFill(Color.GREEN); // Красный цвет
        }

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
        if (StatuszayavId.getText().equals("Создано")) {
            String statusDescription = "Этот статус обозначает, что заявление было успешно создано и зарегистрировано в системе.\nВ данном статусе заявление еще не было обработано или рассмотрено сотрудниками компании \"Горводоканал\".";
            Tooltip statusTooltip = new Tooltip(statusDescription);
            statusTooltip.setStyle("-fx-font-size: 12px;");
            Tooltip.install(StatuszayavId, statusTooltip);
        }
        if (StatuszayavId.getText().equals("На рассмотрении")) {
            String statusDescription = "Заявление находится в процессе рассмотрения и проверки сотрудниками \"Горводоканал\".\nЭтот статус указывает на то, что заявление находится на этапе анализа и принятия решения о дальнейших действиях.";
            Tooltip statusTooltip = new Tooltip(statusDescription);
            statusTooltip.setStyle("-fx-font-size: 12px;");
            Tooltip.install(StatuszayavId, statusTooltip);
        }if (StatuszayavId.getText().equals("Отклонено")) {
            String statusDescription = "Заявление было отклонено сотрудниками \"Горводоканал\". Этот статус означает, \nчто заявление не будет выполнено или не соответствует требованиям и политике компании.";
            Tooltip statusTooltip = new Tooltip(statusDescription);
            statusTooltip.setStyle("-fx-font-size: 12px;");
            Tooltip.install(StatuszayavId, statusTooltip);
        }
        if (StatuszayavId.getText().equals("Завершено")) {
            String statusDescription = "Заявление было успешно выполнено и закрыто. Этот статус указывает на то, \nчто требуемые действия были выполнены, и заявление больше не требует дополнительных мероприятий.";
            Tooltip statusTooltip = new Tooltip(statusDescription);
            statusTooltip.setStyle("-fx-font-size: 12px;");
            Tooltip.install(StatuszayavId, statusTooltip);
        }
        if (StatuszayavId.getText().equals("В работе")) {
            String statusDescription = "Заявление находится в процессе выполнения и обработки. Этот статус означает, \nчто сотрудники компании активно работают над выполнением заявления и могут потребовать дополнительного времени для завершения.";
            Tooltip statusTooltip = new Tooltip(statusDescription);
            statusTooltip.setStyle("-fx-font-size: 12px;");
            Tooltip.install(StatuszayavId, statusTooltip);
        }
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