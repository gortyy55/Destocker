package tn.CodeCommanders.Transaction;

import tn.CodeCommanders.JDBC.JDBC;
import tn.CodeCommanders.Panier.Enchere;
import tn.CodeCommanders.Panier.Facture;
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
                p.setId_acteur(rs.getInt("id_acteur"));
                p.setPrixTotal(rs.getDouble("prixTotal"));
                p.setId_panier(rs.getInt("id_panier"));
                String produit = rs.getString("produit");
                p.setDate_Enchere(rs.getDate("Date_Enchere"));
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

    public ArrayList<Panier> getAllP(int idact) {
        ArrayList<Panier> paniers = new ArrayList<>();

        // Use a prepared statement to safely include the id_acteur parameter in the query
        String query = "SELECT Panier.*, Enchere.produit " +
                "FROM `Panier` " +
                "JOIN `Enchere` ON Panier.id_enchere = Enchere.id " +
                "WHERE Panier.id_acteur = ?";

        System.out.println("Executing query with idact: " + idact);

        try (PreparedStatement stm = cnx.prepareStatement(query)) {
            // Set the id_acteur parameter in the prepared statement
            stm.setInt(1, idact);

            try (ResultSet rs = stm.executeQuery()) {
                while (rs.next()) {
                    Panier p = new Panier();
                    p.setId_enchere(rs.getInt("id_enchere"));
                    p.setId_acteur(rs.getInt("id_acteur"));
                    p.setPrixTotal(rs.getDouble("prixTotal"));
                    p.setId_panier(rs.getInt("id_panier"));
                    String produit = rs.getString("produit");
                    p.setDate_Enchere(rs.getDate("Date_Enchere"));
                    // Assuming you have a setProduct method in the Panier class
                    p.setProduit(produit);

                    paniers.add(p);
                }
            }
        } catch (SQLException e) {
            // Handle the exception or log it
            throw new RuntimeException("Error while retrieving Paniers: " + e.getMessage(), e);
        }

        System.out.println("Number of Paniers retrieved: " + paniers.size());
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
        try {
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

                return new Panier(id_Panier, idacteur, idEnchere, prixTotal, Date_enchere);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null; // Return null if Panier not found
    }


    public void addfacture(Facture facture) {
        String query = "INSERT INTO `Facture`(`id_acteur`, `id_panier`, `name_card`, `ccn`, `exp_date`, `security_code`, `adrress`, `city`, `state`, `zip_code`, `country`) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement stm = cnx.prepareStatement(query);


            stm.setInt(1, facture.getId_acteur());
            stm.setInt(2, facture.getId_panier());
            stm.setString(3, facture.getName_card());
            stm.setInt(4, facture.getCcn());
            stm.setDate(5, new java.sql.Date(facture.getExp_date().getTime()));
            stm.setInt(6, facture.getSecurity_code());
            stm.setString(7, facture.getAddress());
            stm.setString(8, facture.getCity());
            stm.setString(9, facture.getState());
            stm.setInt(10, facture.getZip_code());
            stm.setString(11, facture.getCountry());

            stm.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public ArrayList<Facture> getAllF() {
        ArrayList<Facture> factures = new ArrayList<>();
        String query = "SELECT * FROM `Facture`";

        try (Statement stm = cnx.createStatement();
             ResultSet rs = stm.executeQuery(query)) {

            while (rs.next()) {
                Facture facture = new Facture();
                facture.setId_panier(rs.getInt("id_panier"));
                facture.setId_facture(rs.getInt("id_facture"));
                facture.setId_acteur(rs.getInt("id_acteur"));
                facture.setCcn(rs.getInt("ccn"));
                facture.setAddress(rs.getString("adrress"));
                facture.setCountry(rs.getString("country"));
                facture.setExp_date(rs.getDate("exp_date"));
                facture.setName_card(rs.getString("name_card"));
                facture.setSecurity_code(rs.getInt("security_code"));
                facture.setState(rs.getString("state"));
                facture.setZip_code(rs.getInt("zip_code"));
                facture.setCity(rs.getString("city"));


                factures.add(facture);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return factures;
    }


    public void DeleteF(int idP) {
        String query = "DELETE FROM `Facture` WHERE id_facture = ?";

        System.out.println("Deleting facture with ID: " + idP);

        try (PreparedStatement stm = cnx.prepareStatement(query)) {
            stm.setInt(1, idP);
            int rowsAffected = stm.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Facture with ID " + idP + " deleted successfully.");
            } else {
                System.out.println("No record found with ID " + idP + ". Nothing deleted.");
            }
        } catch (SQLException e) {
            // Log or handle the exception appropriately
            System.err.println("Error deleting record with id_facture " + idP + ": " + e.getMessage());
            e.printStackTrace();  // Print the full stack trace for debugging
        }
    }

    public void updateFacture(int id_facture, Facture facture) {
        String query = "UPDATE `Facture` SET `id_panier`= ?, `id_acteur`= ?, `name_card`= ?, " +
                "`ccn`= ?, `security_code`= ?, `adrress`= ?, `city`= ?, `state`= ?, " +
                "`zip_code`= ?, `country`= ? WHERE id_facture = ?";

        try (PreparedStatement stm = cnx.prepareStatement(query)) {
            stm.setInt(1, facture.getId_panier());
            stm.setInt(2, facture.getId_acteur());
            stm.setString(3, facture.getName_card());
            stm.setInt(4, facture.getCcn());

            stm.setInt(5, facture.getSecurity_code());
            stm.setString(6, facture.getAddress());
            stm.setString(7, facture.getCity());
            stm.setString(8, facture.getState());
            stm.setInt(9, facture.getZip_code());
            stm.setString(10, facture.getCountry());
            stm.setInt(11, id_facture);

            stm.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public void addfactureAdmin(Facture facture) {
        String query = "INSERT INTO `Facture`( `id_acteur`, `id_panier`, `name_card`, `ccn`, `exp_date`, `security_code`, `adrress`, `city`, `state`, `zip_code`, `country`) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement stm = cnx.prepareStatement(query);


            stm.setInt(1, facture.getId_acteur());
            stm.setInt(2, facture.getId_panier());
            stm.setString(3, facture.getName_card());
            stm.setInt(4, facture.getCcn());
            stm.setDate(5, new java.sql.Date(facture.getExp_date().getTime()));
            stm.setInt(6, facture.getSecurity_code());
            stm.setString(7, facture.getAddress());
            stm.setString(8, facture.getCity());
            stm.setString(9, facture.getState());
            stm.setInt(10, facture.getZip_code());
            stm.setString(11, facture.getCountry());

            stm.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public ArrayList<Enchere> getAllE() {
        ArrayList<Enchere> encheres = new ArrayList<>();
        String query = "SELECT * FROM `Enchere`";

        try (Statement stm = cnx.createStatement();
             ResultSet rs = stm.executeQuery(query)) {
            System.out.println("No record found with ID " + rs + ". Nothing deleted.");
            while (rs.next()) {
                Enchere enchere = new Enchere();
                enchere.setId(rs.getInt(1));
                enchere.setStock(rs.getInt(2));
                enchere.setProduit(rs.getString(3));
                enchere.setPrixint(rs.getInt(4));
                encheres.add(enchere);

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return encheres;
    }
}



    /*
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


