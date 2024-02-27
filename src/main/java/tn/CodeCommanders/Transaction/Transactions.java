package tn.CodeCommanders.Transaction;

import tn.CodeCommanders.Don.Don;
import tn.CodeCommanders.JDBC.JDBC;


import java.sql.*;
import java.util.ArrayList;

public class Transactions {
   private static Connection cnx;
    public Transactions(){
        cnx = JDBC.getInstance().getCnx();
    }

    public void add(Don don){
        String query ="INSERT INTO `Dons`(`association`,`title`,`description`) VALUES (?,?,?)";
        try{
            PreparedStatement stm=cnx.prepareStatement(query);

            stm.setString(2, don.getTitle());
            stm.setString(3, don.getDescription());
            stm.setString(1, don.getAssociation());
            stm.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }/*
    public void update(Don don) {
        String query = "UPDATE Panier SET title = ?, Description = ?, Association = ? WHERE id_don = ?";
        try {
            PreparedStatement stm = cnx.prepareStatement(query);

            stm.setInt(1, don.getId_don());
            stm.setString(2, don.getTitle());
            stm.setString(3, don.getDescription());
            stm.setString(4, don.getAssociation()); // Assuming getId_panier() method exists in your Don class

            stm.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
   public Don getDonById(int id_don) {
        try  {
            String query = "SELECT * FROM Don WHERE id_don = ?";
            PreparedStatement preparedStatement = cnx.prepareStatement(query);
            preparedStatement.setInt(1, id_don);
            ResultSet resultSet = preparedStatement.executeQuery();
            System.out.println("ResultSet in affiche method: " + query);

            if (resultSet.next()) {
                String title = resultSet.getString("title");
                String description = resultSet.getString("description");
                String association = resultSet.getString("association");

                return new Don();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null; // Return null if Don not found
    }
    public ArrayList<Don> getAll() {
        ArrayList<Don> dons = new ArrayList<>();
        String query = "SELECT * FROM `Panier`";

        try (Statement stm = cnx.createStatement();
             ResultSet rs = stm.executeQuery(query)) {

            while (rs.next()) {
                Don don = new Don();
                don.setId_don(rs.getInt(1));
                don.setAssociation(rs.getString(2));
                don.setTitle(rs.getString(3));
                don.setDescription(rs.getString(4));

                dons.add(don);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return dons;
    }
    public static void Delete(int idP) {
        String query = "DELETE FROM `Don` WHERE id_don = ?";

        try (PreparedStatement stm = cnx.prepareStatement(query)) {
            stm.setInt(1, idP);
            stm.executeUpdate();
        } catch (SQLException e) {
            // Log or handle the exception appropriately
            System.err.println("Error deleting record with id_panier " + idP + ": " + e.getMessage());
        }
    }
    public ResultSet affiche() {
        String query = "SELECT * FROM `Don`";
        ResultSet rs = null;

        try {
            Statement stm = cnx.createStatement();
            rs = stm.executeQuery(query);

            while (rs.next()) {
                System.out.println("Don ID: " + rs.getInt(1));
                System.out.println("Title: " + rs.getString(2));
                System.out.println("Description: " + rs.getString(3));
                System.out.println("Association: " + rs.getString(4));
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
