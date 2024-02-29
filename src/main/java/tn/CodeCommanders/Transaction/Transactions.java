package tn.CodeCommanders.Transaction;

import tn.CodeCommanders.JDBC.JDBC;
import tn.CodeCommanders.Panier.Panier;


import java.sql.*;
import java.util.ArrayList;

public class Transactions {
    private static Connection cnx;

    public Transactions() {
        cnx = JDBC.getInstance().getCnx();
    }

    public void add(Panier panier) {
        String query = "INSERT INTO `Panier`(`id_enchere`, `id_acteur`, `prixTotal`, `Date_Enchere`) VALUES (?,?,?,?)";
        try {
            PreparedStatement stm = cnx.prepareStatement(query);

            stm.setInt(1, panier.getId_enchere());
            stm.setInt(2, panier.getId_acteur());
            stm.setDouble(3, panier.getPrixTotal());
            stm.setDate(4, new java.sql.Date(panier.getDate_Enchere().getTime()));
            stm.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Panier> getAll() {
        ArrayList<Panier> paniers = new ArrayList<>();

        String query = "SELECT Panier.*, Enchere.produit " +
                "FROM `Panier` " +
                "JOIN `Enchere` ON Panier.id_enchere = Enchere.id";

        try (Statement stm = cnx.createStatement();
             ResultSet rs = stm.executeQuery(query)) {
            while (rs.next()) {
                Panier p = new Panier();
                p.setId_enchere(rs.getInt("id_enchere"));
                p.setPrixTotal(rs.getDouble("prixTotal"));
                p.setId_panier(rs.getInt("id_panier"));
                String produit = rs.getString("produit");
                // Assuming you have a setProduct method in the Panier class
                p.setProduit(produit);

                paniers.add(p);
            }
        } catch (SQLException e) {
            // Handle the exception or log it
            throw new RuntimeException("Error while retrieving Paniers: " + e.getMessage(), e);
        }

        return paniers;
    }

    public void Delete(int idP) throws SQLException {
        String query = "DELETE FROM Panier WHERE id_panier = ? ";
System.out.println("here panier" + idP);
        try (PreparedStatement stm = cnx.prepareStatement(query)) {
            stm.setInt(1, idP);
            stm.executeUpdate();
        } catch (SQLException e) {
            // Log or handle the exception appropriately
            System.err.println("Error deleting record with id_panier " + idP + ": " + e.getMessage());
        }
    }

    public void deletePanierById(int panierId) {
        // SQL query to delete a record from the Panier table based on the ID
        String deleteQuery = "DELETE FROM Panier WHERE id_panier = ?";

        try (PreparedStatement preparedStatement = cnx.prepareStatement(deleteQuery)) {
            // Set the ID parameter in the prepared statement
            preparedStatement.setInt(1, panierId);

            // Execute the delete query
            int rowsAffected = preparedStatement.executeUpdate();

            // Check if any rows were affected
            if (rowsAffected > 0) {
                System.out.println("Record with ID " + panierId + " deleted successfully.");
            } else {
                System.out.println("No record found with ID " + panierId + ".");
            }

        } catch (SQLException e) {
            // Handle any SQL exceptions
            e.printStackTrace();
        }
    }




    public void update(int idPanier, int idEnchere, int idActeur, double prixTotal, Date dateEnchere) {
        String query = "UPDATE `Panier` SET `id_enchere`= ?, `id_acteur`= ?, `prixTotal`= ?, `Date_Enchere`= ? WHERE id_panier = ?";
        try {
            PreparedStatement stm = cnx.prepareStatement(query);

            stm.setInt(1, idEnchere);
            stm.setInt(2, idActeur);
            stm.setDouble(3, prixTotal);
            stm.setDate(4, new java.sql.Date(dateEnchere.getTime()));

            // Set id_panier in the WHERE clause
            stm.setInt(5, idPanier);

            stm.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Panier getPanierById(int id_Panier) {
        System.out.println("did i receive id: " + id_Panier);
        try  {
            String query = "SELECT * FROM Panier WHERE id_panier = ?";
            PreparedStatement preparedStatement = cnx.prepareStatement(query);
            preparedStatement.setInt(1, id_Panier);
            ResultSet resultSet = preparedStatement.executeQuery();
            System.out.println("ResultSet in affiche method: " + query);

            if (resultSet.next()) {

                int idEnchere = resultSet.getInt("id_enchere");
                int idacteur = resultSet.getInt("id_acteur");
                double prixTotal = resultSet.getDouble("prixTotal");
                Date Date_enchere = resultSet.getDate(" Date_Enchere");

                return new Panier(id_Panier, idacteur, idEnchere, prixTotal,Date_enchere);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null; // Return null if Panier not found
    }/*
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


*/


}