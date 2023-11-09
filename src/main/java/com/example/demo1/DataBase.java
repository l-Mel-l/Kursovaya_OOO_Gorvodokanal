package com.example.demo1;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DataBase {
    public static void createDB() {
        try (
                Connection connection = DriverManager.getConnection("jdbc:sqlite:" + "Vodokanal.db");
                Statement statement = connection.createStatement()) {
            statement.execute("CREATE TABLE IF NOT EXISTS Заявление (Id INTEGER PRIMARY KEY, ФИО VARCHAR, Телефон INTEGER, Адрес VARCHAR,ДатаРождения VARCHAR, Почта  VARCHAR,ДатаПодачи VARCHAR, Заявка VARCHAR, Статус VARCHAR)");
            statement.execute("CREATE TABLE IF NOT EXISTS Клиент (Почта PRIMARY KEY, Пароль VARCHAR, Телефон INTEGER, Адрес VARCHAR, Дата VARCHAR, ФИО VARCHAR)");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void NewUser(String loginText, String passwordText, String numberText, String adressText, String fioText, String dataText) {
        try (
                Connection connection = DriverManager.getConnection("jdbc:sqlite:" + "Vodokanal.db");
                Statement statement = connection.createStatement()) {
            statement.execute("INSERT INTO Клиент (Почта, Пароль, Телефон, Адрес, Дата, ФИО) VALUES (\"" + loginText + "\",\"" + passwordText + "\",\"" + numberText + "\",\"" + adressText + "\",\"" + dataText + "\",\"" + fioText + "\")");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static int getUserByMail(String mail, String password) {
        if (mail.equals("dudoroff.k@yandex.ru") && password.equals("qwertyuiop7")) {
            return 2; // Специфичная почта и пароль
        }

        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:" + "Vodokanal.db");
             Statement statement = connection.createStatement()) {
            ResultSet result = statement.executeQuery("SELECT Пароль FROM Клиент WHERE Почта=\"" + mail + "\"");
            while (result.next()) {
                String storedPassword = result.getString("Пароль");
                if (storedPassword.equals(password)) {
                    return 1; // Пароль совпадает с введенным
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return 0; // Если не найдено совпадений
    }

    public static String[] getUerInfo(String mail) {
        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:" + "Vodokanal.db");
             Statement statement = connection.createStatement()) {
            ResultSet result = statement.executeQuery("SELECT * FROM Клиент WHERE Почта=\"" + mail + "\"");
            while (result.next()) {
                return new String[]{result.getString("ФИО"), result.getString("Дата"), result.getString("Адрес"), result.getString("Телефон"), result.getString("Почта"),result.getString("Пароль")};
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    public static void NewZayav(String loginText, String numberText, String adressText, String fioText, String dataText, String datapodText, String zayavText, String status) {
        try (

                Connection connection = DriverManager.getConnection("jdbc:sqlite:" + "Vodokanal.db");
                Statement statement = connection.createStatement()) {
            statement.execute("INSERT INTO Заявление (ФИО, Телефон, Адрес, ДатаРождения, Почта,ДатаПодачи, Заявка , Статус) VALUES (\"" + fioText + "\",\"" + numberText + "\",\"" + adressText + "\",\"" + dataText + "\",\"" + loginText + "\",\"" + datapodText + "\",\""+ zayavText + "\",\"" + status + "\")");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<String[]> getUerZayav(String mail) {
        List<String[]> userZayavList = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:" + "Vodokanal.db");
             Statement statement = connection.createStatement()) {
            ResultSet result = statement.executeQuery("SELECT * FROM Заявление WHERE Почта=\"" + mail + "\"");
            while (result.next()) {
                String id = result.getString("Id");
                String fio = result.getString("ФИО");
                String date = result.getString("ДатаПодачи");
                String zayav = result.getString("Заявка");
                String number = result.getString("Телефон");
                String address = result.getString("Адрес");
                String borndate = result.getString("ДатаРождения");
                String status = result.getString("Статус");
                userZayavList.add(new String[]{id, fio, mail, date,zayav,number,address,borndate,status});
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return userZayavList;
    }
    public static List<String[]> getAllZayav() {
        // Retrieve all applications from the database
        List<String[]> allZayavList = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:" + "Vodokanal.db");
             Statement statement = connection.createStatement()) {
            ResultSet result = statement.executeQuery("SELECT * FROM Заявление");
            while (result.next()) {
                String id = result.getString("Id");
                String fio = result.getString("ФИО");
                String mail = result.getString("Почта");
                String date = result.getString("ДатаПодачи");
                String zayav = result.getString("Заявка");
                String number = result.getString("Телефон");
                String address = result.getString("Адрес");
                String borndate = result.getString("ДатаРождения");
                String status = result.getString("Статус");
                allZayavList.add(new String[]{id, fio, mail, date, zayav,number,address,borndate,status});
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return allZayavList;
    }
    public static List<String[]> getAllClient() {
        // Retrieve all applications from the database
        List<String[]> allClientList = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:" + "Vodokanal.db");
             Statement statement = connection.createStatement()) {
            ResultSet result = statement.executeQuery("SELECT * FROM Клиент");
            while (result.next()) {
                String fioclient = result.getString("ФИО");
                String mailclient = result.getString("Почта");
                String numberclient = result.getString("Телефон");
                String addressclient = result.getString("Адрес");
                String borndateclient = result.getString("Дата");
                String parolclient = result.getString("Пароль");
                allClientList.add(new String[]{fioclient, mailclient,numberclient,addressclient,borndateclient,parolclient});
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return allClientList;
    }
    public static void updateZayavStatus(String zayavId, String newStatus) {
        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:" + "Vodokanal.db");
             Statement statement = connection.createStatement()) {
            String query = "UPDATE Заявление SET Статус='" + newStatus + "' WHERE Id=" + zayavId;
            statement.executeUpdate(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}