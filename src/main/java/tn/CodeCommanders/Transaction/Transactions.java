package tn.CodeCommanders.Transaction;

import tn.CodeCommanders.JDBC.JDBC;
import tn.CodeCommanders.Panier.Panier;


import java.sql.*;
import java.util.ArrayList;

public class Transactions {
   private static Connection cnx;
    public Transactions(){
        cnx = JDBC.getInstance().getCnx();
    }

    public void add(Panier panier){
        String query ="INSERT INTO `Panier`(`id_lots`, `id_enchere`, `prixTotal`) VALUES (?,?,?)";
        try{
            PreparedStatement stm=cnx.prepareStatement(query);

            stm.setInt(1,panier.getId_lots());
            stm.setInt(2, panier.getId_enchere());
            stm.setDouble(3,panier.getPrixTotal());
            stm.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    public void update(Panier panier) {
        String query = "UPDATE Panier SET id_lots = ?, id_enchere = ?, prixTotal = ? WHERE id_panier = ?";
        try {
            PreparedStatement stm = cnx.prepareStatement(query);

            stm.setInt(1, panier.getId_lots());
            stm.setInt(2, panier.getId_enchere());
            stm.setDouble(3, panier.getPrixTotal());
            stm.setInt(4, panier.getId_panier()); // Assuming getId_panier() method exists in your Panier class

            stm.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public Panier getPanierById(int id_Panier) {
        try  {
            String query = "SELECT * FROM Panier WHERE id_panier = ?";
            PreparedStatement preparedStatement = cnx.prepareStatement(query);
            preparedStatement.setInt(1, id_Panier);
            ResultSet resultSet = preparedStatement.executeQuery();
            System.out.println("ResultSet in affiche method: " + query);

            if (resultSet.next()) {
                int idLots = resultSet.getInt("id_lots");
                int idEnchere = resultSet.getInt("id_enchere");
                double prixTotal = resultSet.getDouble("prixTotal");

                return new Panier(id_Panier, idLots, idEnchere, prixTotal);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null; // Return null if Panier not found
    }
    public ArrayList<Panier> getAll() {
        ArrayList<Panier> paniers = new ArrayList<>();
        String query = "SELECT * FROM `Panier`";

        try (Statement stm = cnx.createStatement();
             ResultSet rs = stm.executeQuery(query)) {

            while (rs.next()) {
                Panier panier = new Panier();
                panier.setId_panier(rs.getInt(1));
                panier.setId_lots(rs.getInt(2));
                panier.setId_enchere(rs.getInt(3));
                panier.setPrixTotal(rs.getDouble(4));

                paniers.add(panier);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return paniers;
    }
    public static void Delete(int idP) {
        String query = "DELETE FROM `Panier` WHERE id_panier = ?";

        try (PreparedStatement stm = cnx.prepareStatement(query)) {
            stm.setInt(1, idP);
            stm.executeUpdate();
        } catch (SQLException e) {
            // Log or handle the exception appropriately
            System.err.println("Error deleting record with id_panier " + idP + ": " + e.getMessage());
        }
    }
    public ResultSet affiche() {
        String query = "SELECT * FROM `Panier`";
        ResultSet rs = null;

        try {
            Statement stm = cnx.createStatement();
            rs = stm.executeQuery(query);

            while (rs.next()) {
                System.out.println("Panier ID: " + rs.getInt(1));
                System.out.println("Lots ID: " + rs.getInt(2));
                System.out.println("Enchere ID: " + rs.getInt(3));
                System.out.println("Total Price: " + rs.getDouble(4));
                System.out.println("---------------------------");
            }

        } catch (SQLException e) {
            // Log or handle the exception appropriately
            System.err.println("Error fetching and displaying data: " + e.getMessage());
        }
        System.out.println("ResultSet in affiche method: " + rs);
        // Do not close the ResultSet here
        return rs;
    }




}
