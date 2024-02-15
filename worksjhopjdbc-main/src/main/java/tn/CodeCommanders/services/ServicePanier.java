package tn.CodeCommanders.services;

import tn.CodeCommanders.interfaces.IService;
import tn.CodeCommanders.models.Panier;
import tn.CodeCommanders.utils.MyDataBase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;


public class ServicePanier implements IService<Panier> {
    private Connection cnx;

    public ServicePanier() {
        cnx = MyDataBase.getInstance().getCnx();
    }

    @Override
    public void add(Panier panier) {
        String qry = "INSERT INTO `Panier` (`id_produit`, `nomP`, `Qq`, `prixT`) VALUES (?,?, ?,?)";
        try {
            PreparedStatement stm = cnx.prepareStatement(qry);
            stm.setInt(1, panier.getId_produit());
            stm.setString(2, panier.getNomP());
            stm.setInt(3, panier.getQq());
            stm.setFloat(4, panier.getPrixT());

            stm.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public ArrayList<Panier> getAll() {
        ArrayList<Panier> paniers = new ArrayList<>();
        String qry = "SELECT * FROM `Panier`";
        try {
            Statement stm = cnx.createStatement();
            ResultSet rs = stm.executeQuery(qry);
            while (rs.next()) {
                Panier p = new Panier();
                p.setId_produit(rs.getInt("id_produit"));
                p.setNomP(rs.getString("nomP"));
                p.setQq(rs.getInt("Qq"));
                p.setPrixT(rs.getFloat("prixT"));

                paniers.add(p);
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return paniers;
    }


    @Override
    public boolean delete(Panier panier) {
        String qry = "DELETE FROM `Panier` WHERE `id_panier`=?";
        try {
            PreparedStatement stm = cnx.prepareStatement(qry);
            stm.setInt(1, panier.getId_panier());

            int affectedRows = stm.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
}


