package tn.CodeCommanders.test;

import tn.CodeCommanders.Transaction.Transactions;
import tn.CodeCommanders.enchere.Enchere;

public class Main {
    public static void main(String[] args) {


       Enchere e=new Enchere(5,14,"gezouz franca",745.2);
        Transactions s = new Transactions();

        s.add(e);

        System.out.println(s.getAll());

        //s.update("3");

        //s.Delete("8");
    }
}