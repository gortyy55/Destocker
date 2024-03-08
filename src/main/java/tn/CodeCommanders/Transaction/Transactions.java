package tn.CodeCommanders.Transaction;

import tn.CodeCommanders.JDBC.JDBC;
import tn.CodeCommanders.Objects.Enchere;
import tn.CodeCommanders.Objects.Produit;

import java.sql.*;
import java.util.ArrayList;

public class Transactions {

    private Connection cnx;

    public Transactions() {

        cnx = JDBC.getInstance().getCnx();

    }


    public void add(Enchere ench) {

        String query = "INSERT INTO `Enchere` VALUES (?,?,?,?,?,?)";
        try {
            PreparedStatement stm = cnx.prepareStatement(query);
            stm.setInt(1, ench.getId());
            stm.setInt(2, ench.getStock());
            stm.setString(3, ench.getProduit());
            stm.setDouble(4, ench.getPrixint());
            stm.setString(5, "");
            stm.setDouble(6, ench.getPrixint());
            stm.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


    public ArrayList<Enchere> getAll() {
        ArrayList<Enchere> encheres = new ArrayList();

        String query = "SELECT * FROM `Enchere`";

        try {
            Statement stm = cnx.createStatement();
            ResultSet rs = stm.executeQuery(query);
            while (rs.next()) {
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

    public void update(String idsql, String stock, String produit, String prixinit) {

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


        if (!stock.equals("")) {
            String query = "UPDATE `Enchere` SET `stock` = ? WHERE `id` = ?";
            try {
                PreparedStatement stm = cnx.prepareStatement(query);
                stm.setString(1, stock);
                stm.setString(2, idsql);

                stm.executeUpdate();

            } catch (SQLException e) {
                System.out.println(e.getMessage());

            }
        }

        if (!produit.equals("")) {
            String query = "UPDATE `Enchere` SET `produit` = ? WHERE `id` = ?";
            try {
                PreparedStatement stm = cnx.prepareStatement(query);
                stm.setString(1, produit);
                stm.setString(2, idsql);

                stm.executeUpdate();

            } catch (SQLException e) {
                System.out.println(e.getMessage());

            }
        }

        if (!prixinit.equals("")) {
            String query = "UPDATE `Enchere` SET `prixinit` = ? WHERE `id` = ?";
            try {
                PreparedStatement stm = cnx.prepareStatement(query);
                stm.setString(1, prixinit);
                stm.setString(2, idsql);

                stm.executeUpdate();

            } catch (SQLException e) {
                System.out.println(e.getMessage());

            }
        }


    }


    public void Delete(String idsql) {

        String query = "DELETE FROM `Enchere` WHERE id = ?";

        try {
            PreparedStatement stm = cnx.prepareStatement(query);
            stm.setString(1, idsql);
            stm.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());

        }

    }


    public ArrayList<Produit> getlot(int idench) {

        ArrayList<Integer> prodlot = new ArrayList<>();

        String query1 = "SELECT idproduit FROM `lot` where idenchere=" + idench;

        try {
            Statement stm1 = cnx.createStatement();
            ResultSet rs1 = stm1.executeQuery(query1);
            while (rs1.next()) {
                prodlot.add(rs1.getInt(1));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        ArrayList<Produit> produits = new ArrayList();

        for (int i = 0; i < prodlot.size(); i++) {
            String query = "SELECT idproduit,produitname,quantite FROM `stockP` where idproduit=" + prodlot.get(i);

            try {
                Statement stm = cnx.createStatement();
                ResultSet rs = stm.executeQuery(query);
                while (rs.next()) {
                    Produit e = new Produit();
                    e.setId(rs.getInt(1));
                    e.setName(rs.getString(2));
                    e.setQuantite(rs.getInt(3));

                    produits.add(e);


                }

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }


        return produits;


    }

    public double prixactuel(int id) {
        double prix = 0;
        System.out.println(id);
        String query = "SELECT `prixactuel` FROM `Enchere` WHERE `id` = ?";
        try (PreparedStatement stm = cnx.prepareStatement(query)) {
            stm.setInt(1, id); // Set the value of the parameter
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                prix = rs.getDouble("prixactuel");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return prix;
    }

    public void updateEnchere(int id, Double prix) {
        String query = "UPDATE `Enchere` SET `prixactuel` = ? WHERE `id` = ?";
        try {
            PreparedStatement stm = cnx.prepareStatement(query);
            stm.setDouble(1, prix);
            stm.setInt(2, id);

            stm.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());

        }

        String query2 = "UPDATE `Enchere` SET `idAchteur` = ? WHERE `id` = ?";
        try {
            PreparedStatement stm = cnx.prepareStatement(query2);
            stm.setInt(1, 744);
            stm.setInt(2, id);

            stm.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());

        }
    }

}


