package com.example.demo1;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import static com.example.demo1.DataBase.getAllClient;
import static com.example.demo1.DataBase.getAllZayav;

public class Administrator implements javafx.fxml.Initializable {
    @FXML
    private VBox myApplicationsVBox;
    @FXML
    private Button LogOutBtn;
    @FXML
    private VBox myApplicationsVBoxClent;
    @FXML
    private ChoiceBox<String> clientChoisebox;
    @FXML
    private TextField Clienttextfield;
    @FXML
    private  Button clientButton;
    @FXML
    private ChoiceBox<String> ZayavChoisebox;
    @FXML
    private TextField Zayavtextfield;
    @FXML
    private Button ZayavButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Load all applications from the database
        List<String[]> allApplications = getAllZayav();
        List<String[]> СlientData = getAllClient();

        // Create panes for each application
        for (String[] application : allApplications) {
            createApplicationPane(application);
        }
        for (String[] clientData : СlientData) {
            createApplicationPane(clientData);
            createClientPane(clientData);
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
        clientChoisebox.getItems().addAll("ФИО", "Почта", "Телефон","Адрес","Дата Рождения","Пароль");

        // Установите обработчик события выбора значения
        clientButton.setOnAction(event -> {
            String selectedParameter = clientChoisebox.getValue();
            String searchValue = Clienttextfield.getText();

            List<String[]> allClients = DataBase.getAllClient();
            List<String[]> filteredClients = new ArrayList<>();

            for (String[] clientData : allClients) {
                String valueToCompare = "";
                if ("ФИО".equals(selectedParameter)) {
                    valueToCompare = clientData[0];
                } else if ("Почта".equals(selectedParameter)) {
                    valueToCompare = clientData[1];
                } else if ("Телефон".equals(selectedParameter)) {
                    valueToCompare = clientData[2];
                } else if ("Адрес".equals(selectedParameter)) {
                    valueToCompare = clientData[3];
                } else if ("Дата Рождения".equals(selectedParameter)) {
                    valueToCompare = clientData[4];
                } else if ("Пароль".equals(selectedParameter)) {
                    valueToCompare = clientData[5];
                }

                if (valueToCompare.contains(searchValue)) {
                    filteredClients.add(clientData);
                }
            }

            // Очищаем VBox перед добавлением отфильтрованных клиентов
            myApplicationsVBoxClent.getChildren().clear();

            // Создаем панели для каждого отфильтрованного клиента
            for (String[] clientData : filteredClients) {
                createClientPane(clientData);
            }
        });
        ZayavChoisebox.getItems().addAll("Id", "ФИО", "Телефон","Адрес","Дата Рождения","Почта","Дата Подачи","Статус");
        ZayavButton.setOnAction(event -> {
            String selectedParameter = ZayavChoisebox.getValue();
            String searchValue = Zayavtextfield.getText();

            List<String[]> allZayavki = DataBase.getAllZayav();
            List<String[]> filteredZayavki = new ArrayList<>();

            for (String[] zayavkaData : allZayavki) {
                String valueToCompare = "";
                if ("Id".equals(selectedParameter)) {
                    valueToCompare = zayavkaData[0];
                } else if ("ФИО".equals(selectedParameter)) {
                    valueToCompare = zayavkaData[1];
                } else if ("Телефон".equals(selectedParameter)) {
                    valueToCompare = zayavkaData[5];
                } else if ("Адрес".equals(selectedParameter)) {
                    valueToCompare = zayavkaData[6];
                } else if ("ДатаРождения".equals(selectedParameter)) {
                    valueToCompare = zayavkaData[7];
                } else if ("Почта".equals(selectedParameter)) {
                    valueToCompare = zayavkaData[2];
                } else if ("ДатаПодачи".equals(selectedParameter)) {
                    valueToCompare = zayavkaData[3];
                } else if ("Статус".equals(selectedParameter)) {
                        valueToCompare = zayavkaData[8];
                 }
                if (valueToCompare.contains(searchValue)) {
                    filteredZayavki.add(zayavkaData);
                }
            }

            // Очищаем VBox перед добавлением отфильтрованных заявок
            myApplicationsVBox.getChildren().clear();

            // Создаем панели для каждой отфильтрованной заявки
            for (String[] zayavkaData : filteredZayavki) {
                createApplicationPane(zayavkaData);
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

    private void createClientPane(String[] clientData) {
        String fio = clientData[0];
        String email = clientData[1];
        String number = clientData[2];
        String address = clientData[3];
        String date = clientData[4];
        String parol = clientData[5];


        AnchorPane newApplicationPane2 = new AnchorPane();
        newApplicationPane2.setMinHeight(50);
        newApplicationPane2.setPrefWidth(200);
        newApplicationPane2.setStyle("-fx-background-color: #ECECEC; -fx-padding: 5px; -fx-margin: 5px;");

        Label fioLabelclient = new Label("ФИО: " + fio);
        fioLabelclient.setLayoutX(10);
        fioLabelclient.setLayoutY(10);

        Label mailLabelclient = new Label("Почта: " + email);
        mailLabelclient.setLayoutX(10);
        mailLabelclient.setLayoutY(90);

        Label phoneLabelclient = new Label("Телефон: " + number);
        phoneLabelclient.setLayoutX(10);
        phoneLabelclient.setLayoutY(70);

        Label addressLabelclient = new Label("Адрес: " + address);
        addressLabelclient.setLayoutX(10);
        addressLabelclient.setLayoutY(50);

        Label dateLabelclient = new Label("Дата рождения: " + date);
        dateLabelclient.setLayoutX(10);
        dateLabelclient.setLayoutY(30);

        newApplicationPane2.getChildren().addAll(fioLabelclient, mailLabelclient, phoneLabelclient, addressLabelclient, dateLabelclient);

        // Add click event handler to display application information
        newApplicationPane2.setOnMouseClicked(mouseEvent -> {
            newApplicationPane2.getScene().getWindow().hide();
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Client-view.fxml"));
                Parent root = loader.load();

                Client zayavInfoController = loader.getController();
                zayavInfoController.initializeWithData(clientData);

                Stage stage = new Stage();
                stage.setTitle("Информация о Клиенте");
                stage.setScene(new Scene(root));
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        myApplicationsVBoxClent.getChildren().add(newApplicationPane2);
    }
}



