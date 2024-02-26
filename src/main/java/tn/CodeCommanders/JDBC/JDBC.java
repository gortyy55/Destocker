package tn.CodeCommanders.JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBC {
    private static JDBC instance;
    private final String URL = "jdbc:mysql://ragequit.tn/Destocker";
    private final String USERNAME = "ghofrane";
    private final String PASSWORD = "Testtest";

    private Connection cnx;

    private JDBC() {
        try {
            cnx = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            System.out.println("Connected ...");
        } catch (SQLException e) {
            // Log or propagate the exception for better debugging
            throw new RuntimeException("Error connecting to the database: " + e.getMessage(), e);
        }
    }

    public static JDBC getInstance() {
        if (instance == null)
            instance = new JDBC();
        return instance;
    }

    public Connection getCnx() {
        return cnx;
    }

    public void closeConnection() {
        try {
            if (cnx != null && !cnx.isClosed()) {
                cnx.close();
                System.out.println("Connection closed.");
            }
        } catch (SQLException e) {
            // Log or propagate the exception for better debugging
            System.err.println("Error closing the database connection: " + e.getMessage());
        }
    }
}
