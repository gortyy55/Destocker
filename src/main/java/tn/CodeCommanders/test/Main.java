package tn.CodeCommanders.test;

import tn.CodeCommanders.JDBC.JDBC;
import tn.CodeCommanders.Panier.Panier;
import tn.CodeCommanders.Transaction.Transactions;

import java.sql.Connection;

public class Main {
    public static void main(String[] args) {
    JDBC db=JDBC.getInstance();
    Connection connection=db.getCnx();
       /* Panier panier= new Panier();
        Transactions transactions=new Transactions();*/
       // transactions.add(panier);
    }}
