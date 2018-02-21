package ru.mdg.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class DataAccess {
    private Connection connection ;

    public DataAccess(String dbURL, String user, String password) throws SQLException, ClassNotFoundException {
        Driver driver = new com.mysql.cj.jdbc.Driver();
        DriverManager.registerDriver(driver);
        connection = DriverManager.getConnection(dbURL, user, password);
    }

    public void shutdown() throws SQLException {
        if (connection != null) {
            connection.close();
        }
    }

    public ObservableList<Notation> getNoteList() throws SQLException {
        try (Statement sttmnt = connection.createStatement(); ResultSet rs = sttmnt.executeQuery("select * from notes");){
            ObservableList<Notation> noteList = FXCollections.observableArrayList();
            while (rs.next()) {
                LocalDate date = rs.getDate("date").toLocalDate();
                String text = rs.getString("text");
                Notation note = new Notation(date, text);
                noteList.add(note);
            }
            return noteList;
        }
    }
}
