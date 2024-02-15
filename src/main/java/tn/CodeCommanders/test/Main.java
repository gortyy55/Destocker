package tn.CodeCommanders.test;

import tn.CodeCommanders.models.Stock;
import tn.CodeCommanders.services.ServiceStock;
import tn.CodeCommanders.utils.MyDataBase;

import java.sql.Connection;

public class Main {
    public static void main(String[] args) {

        Stock s1 = new Stock(1000,455, "milk ","shibob@milk.com");

        ServiceStock sp = new ServiceStock();
        sp.add(s1);





        System.out.println(sp.getAll());

      /* System.out.println("stock before deleting" +sp.getAll());


        Stock stockToDelete = new Stock();
    stockToDelete.setId(1);


        boolean isDeleted = sp.delete(stockToDelete);

        if(isDeleted){
            System.out.println("produit deleted successfully");
        }
        else{
            System.out.println("Failed to deleted produit" );
        }

        System.out.println("produit after deleting" +sp.getAll());*/


    }
}