package tn.CodeCommanders.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MyDataBase {
    private static MyDataBase instance ;
    private final String URL ="jdbc:mysql://ragequit.tn/Destocker";
    private final String USERNAME="salim";
    private final String PASSWORD ="Testtest";

    Connection cnx ;


    private MyDataBase(){

        try {
            cnx = DriverManager.getConnection(URL,USERNAME,PASSWORD);

            System.out.println("Connected ...");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("____not connected____ ");

        }

    }
    public static MyDataBase getInstance(){
        if (instance == null)
            instance = new MyDataBase();

        return instance;
    }
    public Connection getCnx(){
        return cnx;
    }

}
