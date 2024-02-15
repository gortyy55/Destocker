package tn.CodeCommanders.services;
import tn.CodeCommanders.interfaces.iservice;
import tn.CodeCommanders.models.Stock;
import tn.CodeCommanders.utils.MyDataBase;

import java.sql.*;
import java.util.ArrayList;
public class ServiceStock implements iservice<Stock>{
    private Connection cnx ;
    public ServiceStock(){cnx =MyDataBase.getInstance().getCnx();}
    @Override
    public void add(Stock Stock) {
        String qry ="INSERT INTO `stockP`(`produitname`, `quantite`, `mail`) VALUES (?,?,?)";
        try {
            PreparedStatement stm = cnx.prepareStatement(qry);
            stm.setString(1, Stock.getProduitname());
            stm.setInt(2,Stock.getQuantite());
            stm.setString(3,Stock.getMail());

            stm.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());

        }


    }




    @Override
    public ArrayList<Stock> getAll() {
        ArrayList<Stock> stocks = new ArrayList<>();
        String qry="SELECT * FROM `stockP`";
        try {
            Statement stm = cnx.createStatement();
            ResultSet rs = stm.executeQuery(qry);
            while (rs.next()) { Stock s = new Stock();
                s.setId(rs.getInt(1));
                s.setProduitname(rs.getString("Produitname"));
                s.setQuantite(rs.getInt(3));
                s.setMail(rs.getString("Mail"));

                stocks.add(s);
            }



        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
                return stocks;
            }


    @Override
    public void update(Stock stock) {
        String qry = "UPDATE `stockP` SET `idproduit`=?,`produitname`=?,`quantite`=?,`mail`=? WHERE id = ?";
        try {
            PreparedStatement stm = cnx.prepareStatement(qry);
            stm.setInt(1, stock.getId());
            stm.setString(2, stock.getProduitname());
            stm.setInt(3, stock.getQuantite());
            stm.setString(4, stock.getMail());
            stm.setInt(5, stock.getId());
            int resultat = stm.executeUpdate();
            if (resultat > 0) {
                System.out.println("Post modif avec succs !");
            } else {
                System.out.println("Erreur lors de la modification du post.");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public boolean delete(Stock stock) {
        String qry ="DELETE FROM `stockP` WHERE `idproduit`=?";
        try{
            PreparedStatement stm = cnx.prepareStatement(qry);
            stm.setInt(1,stock.getId());

            int affectedRows = stm.executeUpdate();
            return affectedRows > 0 ;
        }catch (SQLException e){
            System.out.println(e.getMessage());
            return false;
        }
    }

}

