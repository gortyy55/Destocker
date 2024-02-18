package tn.CodeCommanders.Transaction;

import tn.CodeCommanders.JDBC.JDBC;
import tn.CodeCommanders.enchere.Enchere;

import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;
public class Transactions {

private Connection cnx;

public Transactions(){

    cnx= JDBC.getInstance().getCnx();

}


    public void add(Enchere ench){

    String query="INSERT INTO `Enchere` VALUES (?,?,?,?)";
    try{
        PreparedStatement stm=cnx.prepareStatement(query);
        stm.setInt(1,ench.getId());
        stm.setInt(2,ench.getStock());
        stm.setString(3, ench.getProduit());
        stm.setDouble(4,ench.getPrixint());
        stm.executeUpdate();

    } catch (SQLException e) {
        throw new RuntimeException(e);
    }

        }


    public ArrayList<Enchere> getAll() {
        ArrayList<Enchere> encheres = new ArrayList();

        String query="SELECT * FROM `Enchere`";

        try {
            Statement stm= cnx.createStatement();
            ResultSet rs= stm.executeQuery(query);
            while (rs.next()){
                Enchere e = new Enchere();
                e.setId(rs.getInt(1));
                e.setStock(rs.getInt(2));
                e.setProduit(rs.getString(3));
                e.setPrixint(rs.getInt(4));

                encheres.add(e);


            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return encheres;
    }

    public void update(String idsql,String stock,String produit,String prixinit) {

    /*
        Scanner obj = new Scanner(System.in);

        System.out.println("Enter new id or press enter to not change \n");
        String id = (obj.nextLine());


        System.out.println("Enter new stock or press enter to not change \n");
        String stock = (obj.nextLine());


        System.out.println("Enter new produit or press enter to not change \n");
        String produit = obj.nextLine();


        System.out.println("Enter new prixint or press enter to not change \n");
        String prixinit = (obj.nextLine());*/



        if (!stock.equals("")){
            String query="UPDATE `Enchere` SET `stock` = ? WHERE `id` = ?";
            try {
                PreparedStatement stm = cnx.prepareStatement(query);
                stm.setString(1,stock);
                stm.setString(2,idsql);

                stm.executeUpdate();

            } catch (SQLException e) {
                System.out.println(e.getMessage());

            }
        }

        if (!produit.equals("")){
            String query="UPDATE `Enchere` SET `produit` = ? WHERE `id` = ?";
            try {
                PreparedStatement stm = cnx.prepareStatement(query);
                stm.setString(1,produit);
                stm.setString(2,idsql);

                stm.executeUpdate();

            } catch (SQLException e) {
                System.out.println(e.getMessage());

            }
        }

        if (!prixinit.equals("")){
            String query="UPDATE `Enchere` SET `prixinit` = ? WHERE `id` = ?";
            try {
                PreparedStatement stm = cnx.prepareStatement(query);
                stm.setString(1,prixinit);
                stm.setString(2,idsql);

                stm.executeUpdate();

            } catch (SQLException e) {
                System.out.println(e.getMessage());

            }
        }


    }


    public void Delete(String idsql) {

        String query="DELETE FROM `Enchere` WHERE id = ?";

        try {
            PreparedStatement stm = cnx.prepareStatement(query);
            stm.setString(1,idsql);
            stm.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());

        }

    }


}


