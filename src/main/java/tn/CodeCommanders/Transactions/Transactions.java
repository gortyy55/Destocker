package tn.CodeCommanders.Transactions;

import tn.CodeCommanders.JDBC.JDBC;
import tn.CodeCommanders.Tache.NomTache;

import java.sql.*;
import java.util.ArrayList;

public class Transactions {

    private Connection cnx;

    public Transactions(){

        cnx= JDBC.getInstance().getCnx();

    }

}
