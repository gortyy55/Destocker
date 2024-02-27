package tn.CodeCommanders.test;

import tn.CodeCommanders.Don.Don;
import tn.CodeCommanders.JDBC.JDBC;
import tn.CodeCommanders.Transaction.Transactions;

import java.sql.Connection;

public class Main {
    public static void main(String[] args) {
    JDBC db=JDBC.getInstance();
    Connection connection=db.getCnx();
      Don T =new Don();
        Transactions transactions=new Transactions();
      transactions.add(T);
    }}
