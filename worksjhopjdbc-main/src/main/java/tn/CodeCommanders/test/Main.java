package tn.CodeCommanders.test;

import tn.CodeCommanders.models.Panier;
import tn.CodeCommanders.services.ServicePanier;
import tn.CodeCommanders.utils.MyDataBase;

import java.sql.Connection;

public class Main {
    public static void main(String[] args) {

        //MyDataBase db=MyDataBase.getInstance();

        //Connection connection= db.getCnx();

        Panier p1= new Panier(1111,222,"clim",3,4);

        ServicePanier sp=new ServicePanier();
        //sp.add(p1);
        System.out.println("Paniers before deleting" +sp.getAll());


        Panier panierToDelete = new Panier();
       panierToDelete.setId_panier(6);


       boolean isDeleted = sp.delete(panierToDelete);

        if(isDeleted){
            System.out.println("Panier deleted successfully");
        }
        else{
            System.out.println("Failed to deleted panier" );
        }

        System.out.println("Paniers after deleting" +sp.getAll());

}}