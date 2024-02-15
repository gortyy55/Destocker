package tn.CodeCommanders.test.JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class JDBC {



        private static JDBC instance;
        private final String URL = "jdbc:mysql://ragequit.tn/Destocker";
        private final String USERNAME = "Gortyy";
        private final String PASSWORD = "Testtest";

    Connection cnx ;

    private JDBC(){

        try {
            cnx = DriverManager.getConnection(URL,USERNAME,PASSWORD);

            System.out.println("Connected ...");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("____not connected____ ");
            System. exit(0);

        }

    }


    public static JDBC getInstance(){
        if (instance == null)
            instance = new JDBC();

        return instance;
    }

    public Connection getCnx(){
        return cnx;
    }
}
