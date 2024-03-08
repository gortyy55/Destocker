package tn.CodeCommanders.Transactions;
import tn.CodeCommanders.Reclamation.*;
import tn.CodeCommanders.JDBC.JDBC;

import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.sql.ResultSet;
import java.sql.Statement;
public class TransactionsReclamation {

    public void add(Reclamation reclamation) {
        String qry = "INSERT INTO `Reclamation`(`titre`, `type`, `id_produit` , `Description` , `id_user` , `cheminFichierJoint`) VALUES (?,?,?,?,?,?)";
        try {
            PreparedStatement stm = JDBC.getInstance().getCnx().prepareStatement(qry);
            stm.setString(1, reclamation.getTitre());
            stm.setString(2, reclamation.getType());
            stm.setInt(3, reclamation.getId_produit());
            stm.setString(4, reclamation.getDescription());
            stm.setInt(5, reclamation.getId_user());
            stm.setString(6, reclamation.getCheminFichierJoint());

           stm.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Erreur lors de l'ajout de la réclamation" + e.getMessage());

        }
    }
    public static ArrayList<Reclamation> getAll() {
        ArrayList<Reclamation> reclamations = new ArrayList<>();
        String qry = "SELECT * FROM Reclamation";
        try {
            Statement stm = JDBC.getInstance().getCnx().createStatement();
            ResultSet rs = stm.executeQuery(qry);
            while (rs.next()) {
                Reclamation rec = new Reclamation();
                rec.setId_reclamation(rs.getInt("id_reclamation"));
                rec.setTitre(rs.getString("titre"));
                rec.setType(rs.getString("type"));
                rec.setId_produit(rs.getInt("id_produit"));
                rec.setDescription(rs.getString("Description"));
                rec.setId_user(rs.getInt("id_user"));
                rec.setStatut(rs.getString("statut"));  // Add this line to set the statut
                reclamations.add(rec);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return reclamations;
    }

    public void update(Reclamation reclamation) {
        String qry = "UPDATE Reclamation SET titre=?, type=?, id_produit=?, Description=?, id_user=?, statut=? WHERE id_reclamation=?";
        try {
            PreparedStatement stm = JDBC.getInstance().getCnx().prepareStatement(qry);
            stm.setString(1, reclamation.getTitre());
            stm.setString(2, reclamation.getType());
            stm.setInt(3, reclamation.getId_produit());
            stm.setString(4, reclamation.getDescription());
            stm.setInt(5, reclamation.getId_user());
            stm.setString(6, reclamation.getStatut()); // Set the statut
            stm.setInt(7, reclamation.getId_reclamation()); // Set the id_reclamation

            int resultat = stm.executeUpdate();
            if (resultat > 0) {
                System.out.println("Réclamation modif avec succès !");
            } else {
                System.out.println("Erreur lors de la modification de la réclamation.");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public Reclamation reclamation;
    public boolean delete(int id_Reclamation) {
        String qry = "DELETE FROM Reclamation WHERE id_reclamation=?";
        try (PreparedStatement stm = JDBC.getInstance().getCnx().prepareStatement(qry)) {
            stm.setInt(1, id_Reclamation);
            int affectedRows = stm.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            System.out.println("Erreur lors de la suppression de la réclamation : " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
    public int getIdReclamationByTitre(String titreReclamation) {
        String sql = "SELECT id_reclamation FROM Reclamation WHERE titre = ?";
        try (PreparedStatement statement = JDBC.getInstance().getCnx().prepareStatement(sql)) {
            statement.setString(1, titreReclamation);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getInt("id_reclamation");
            }
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return -1;
    }

    public void enregistrerAvis(int idReclamation, boolean satisfaction ,String comment ) {
        String sql = "INSERT INTO Avis_Client (id_reclamation, satisfaction, comment) VALUES (?, ?, ?)";

        try (PreparedStatement stm = JDBC.getInstance().getCnx().prepareStatement(sql)) {
            stm.setInt(1, idReclamation);
            stm.setBoolean(2, satisfaction);
            stm.setString(3, comment);
            stm.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erreur lors de l'ajout de l'avis : " + e.getMessage());
        }
    }

    public int countSatisfaitAvis() {
        String sql = "SELECT COUNT(*) FROM Avis_Client WHERE satisfaction = ?";
        try (PreparedStatement stm = JDBC.getInstance().getCnx().prepareStatement(sql)) {
            stm.setBoolean(1, true);
            ResultSet resultSet = stm.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors du comptage des avis satisfaits : " + e.getMessage());
        }
        return 0; // Return 0 if an error occurs or no records found
    }
    public int countReclamationsByStatusAndUserId(String status, int userId) {
        String qry = "SELECT COUNT(*) AS count FROM Reclamation WHERE statut = ? AND id_user = ?";
        int count = 0;
        try {
            PreparedStatement stm = JDBC.getInstance().getCnx().prepareStatement(qry);
            stm.setString(1, status);
            stm.setInt(2, userId);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                count = rs.getInt("count");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    public int countReclamationsByUserId(int userId) {
        String qry = "SELECT COUNT(*) AS count FROM Reclamation WHERE id_user = ?";
        int count = 0;
        try {
            PreparedStatement stm = JDBC.getInstance().getCnx().prepareStatement(qry);
            stm.setInt(1, userId);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                count = rs.getInt("count");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }


    public int countNonSatisfaitAvis() {
        String sql = "SELECT COUNT(*) FROM Avis_Client WHERE satisfaction = ?";
        try (PreparedStatement stm = JDBC.getInstance().getCnx().prepareStatement(sql)) {
            stm.setBoolean(1, false);
            ResultSet resultSet = stm.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors du comptage des avis non satisfaits : " + e.getMessage());
        }
        return 0; // Return 0 if an error occurs or no records found
    }

}








