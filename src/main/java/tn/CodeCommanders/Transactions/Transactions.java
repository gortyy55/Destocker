package tn.CodeCommanders.Transactions;
import tn.CodeCommanders.Reclamation.*;
import tn.CodeCommanders.JDBC.JDBC;

import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.sql.ResultSet;
import java.sql.Statement;
public class Transactions{

    public void add(Reclamation reclamation) {
        String qry = "INSERT INTO `Reclamation`(`titre`, `type`, `id_produit` , `Description` , `id_user` ) VALUES (?,?,?,?,?)";
        try {
            PreparedStatement stm = JDBC.getInstance().getCnx().prepareStatement(qry);
            stm.setString(1, reclamation.getTitre());
            stm.setString(2, reclamation.getType());
            stm.setInt(3, reclamation.getId_produit());
            stm.setString(4, reclamation.getDescription());
            stm.setInt(5, reclamation.getId_user());

           stm.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Erreur lors de l'ajout de la réclamation" + e.getMessage());

        }
    }
    public ArrayList<Reclamation> getAll() {
        ArrayList<Reclamation> reclamations = new ArrayList<>();
        String qry="SELECT * FROM Reclamation";
        try {
            Statement stm = JDBC.getInstance().getCnx().createStatement();
            ResultSet rs = stm.executeQuery(qry);
            while (rs.next()) { Reclamation rec = new Reclamation();
                rec.setId_reclamation(rs.getInt("id_reclamation"));
                rec.setTitre(rs.getString("titre"));
                rec.setType(rs.getString("type"));
                rec.setId_produit(rs.getInt("id_produit"));
                rec.setDescription(rs.getString("Description"));
                rec.setId_user(rs.getInt("id_user"));
                reclamations.add(rec);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return reclamations;
    }

    public void update(Reclamation reclamation) {
        String qry = "UPDATE réclamation SET titre=?, type=?, id_produit=?, Description=?, id_user=? WHERE id_reclamation=?";
        try {
            PreparedStatement stm = JDBC.getInstance().getCnx().prepareStatement(qry);
            stm.setString(1, reclamation.getTitre());
            stm.setString(2, reclamation.getType());
            stm.setInt(3, reclamation.getId_produit());
            stm.setString(4, reclamation.getDescription());
            stm.setInt(5, reclamation.getId_user());

            int resultat = stm.executeUpdate();
            if (resultat > 0) {
                System.out.println("Réclamation modif avec succés !");
            } else {
                System.out.println("Erreur lors de la modification du réclamation.");
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
}






