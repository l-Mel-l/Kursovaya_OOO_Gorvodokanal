package com.example.demo1;

import java.sql.*;
import java.time.LocalDate;

public class DataBase {
    public static void createDB() {
        try (
                Connection connection = DriverManager.getConnection("jdbc:sqlite:" + "Vodokanal.db");
                Statement statement = connection.createStatement()) {
            statement.execute("CREATE TABLE IF NOT EXISTS Заявление (Id INTEGER PRIMARY KEY, ФИО VARCHAR, Телефон INTEGER, Адрес VARCHAR,ДатаРождения VARCHAR, Почта  VARCHAR,ДатаПодачи VARCHAR, Заявка VARCHAR)");
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
        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:" + "Vodokanal.db");
             Statement statement = connection.createStatement()) {
            ResultSet result = statement.executeQuery("SELECT Пароль FROM Клиент WHERE Почта=\"" + mail + "\"");
            while (result.next()) {
                if (result.getString("Пароль").equals(password)) {
                    return 1;
                } else {
                    return 0;
                }
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return 0;
    }

    public static String[] getUerInfo(String mail) {
        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:" + "Vodokanal.db");
             Statement statement = connection.createStatement()) {
            ResultSet result = statement.executeQuery("SELECT * FROM Клиент WHERE Почта=\"" + mail + "\"");
            while (result.next()) {
                return new String[]{result.getString("ФИО"), result.getString("Дата"), result.getString("Адрес"), result.getString("Телефон"), result.getString("Почта")};
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    public static void NewZayav(String loginText, String numberText, String adressText, String fioText, String dataText, String datapodText, String zayavText) {
        try (

                Connection connection = DriverManager.getConnection("jdbc:sqlite:" + "Vodokanal.db");
                Statement statement = connection.createStatement()) {
            statement.execute("INSERT INTO Заявление (ФИО, Телефон, Адрес, ДатаРождения, Почта,ДатаПодачи, Заявка) VALUES (\"" + fioText + "\",\"" + numberText + "\",\"" + adressText + "\",\"" + dataText + "\",\"" + loginText + "\",\"" + datapodText + "\",\""+ zayavText + "\")");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static String[] getUerZayav(String mail) {
        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:" + "Vodokanal.db");
             Statement statement = connection.createStatement()) {
            ResultSet result = statement.executeQuery("SELECT * FROM Заявление WHERE Почта=\"" + mail + "\"");
            while (result.next()) {
                return new String[]{result.getString("Id"), result.getString("ФИО"), result.getString("Почта"), result.getString("ДатаПодачи")};
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }
}