package tn.CodeCommanders.Transactions;

import tn.CodeCommanders.JDBC.JDBC;
import tn.CodeCommanders.stock.Category;
import tn.CodeCommanders.stock.Stock;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TransactionsStock {
    private Connection cnx ;
    public TransactionsStock(){cnx = JDBC.getInstance().getCnx();}

    public void add(Stock Stock) {
        String qry ="INSERT INTO `stockP`(`produitname`, `quantite`, `mail`,`id_cat`) VALUES (?,?,?,?)";
        try {
            PreparedStatement stm = cnx.prepareStatement(qry);
            stm.setString(1, Stock.getProduitname());
            stm.setInt(2,Stock.getQuantite());
            stm.setString(3,Stock.getMail());
            stm.setInt(4, Stock.getIdCat());

            stm.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());

        }


    }





    public ArrayList<Stock> getAll() {
        ArrayList<Stock> stocks = new ArrayList<>();
        String qry = "SELECT s.idproduit, s.produitname, s.quantite, s.mail, s.id_cat, c.category_name " +
                "FROM stockP s INNER JOIN category c ON s.id_cat = c.id_cat";
        try {
            Statement stm = cnx.createStatement();
            ResultSet rs = stm.executeQuery(qry);
            while (rs.next()) {
                Stock s = new Stock();
                s.setId(rs.getInt("idproduit"));
                s.setProduitname(rs.getString("produitname"));
                s.setQuantite(rs.getInt("quantite"));
                s.setMail(rs.getString("mail"));
                s.setIdCat(rs.getInt("id_cat"));
                s.setCategoryName(rs.getString("category_name"));
                stocks.add(s);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return stocks;
    }



    public void update(Stock stock) {
        String qry = "UPDATE `stockP` SET `produitname`=?, `quantite`=?, `mail`=?, `id_cat`=? WHERE `idproduit` = ?";
        try {
            PreparedStatement stm = cnx.prepareStatement(qry);
            stm.setString(1, stock.getProduitname());
            stm.setInt(2, stock.getQuantite());
            stm.setString(3, stock.getMail());
            stm.setInt(4, stock.getIdCat());
            stm.setInt(5, stock.getId());
            int result = stm.executeUpdate();
            if (result > 0) {
                System.out.println("Stock updated successfully!");
            } else {
                System.out.println("Error updating stock.");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


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
    public void addcat(Category category) {
        String qry ="INSERT INTO `category`(`category_name`) VALUES (?)";
        try {
            PreparedStatement stm = cnx.prepareStatement(qry);
            stm.setString(1,category.getCategoryName());



            stm.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());

        }


    }
    public ArrayList<Category> getAllC() {
        ArrayList<Category> categories = new ArrayList<>();
        String qry="SELECT * FROM `category`";
        try {
            Statement stm = cnx.createStatement();
            ResultSet rs = stm.executeQuery(qry);
            while (rs.next()) { Category c = new Category();

                c.setIdc(rs.getInt(1));
                c.setCategoryName(rs.getString("category_name"));


                categories.add(c);
            }



        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return categories;
    }
    public void updatecat(Category category) {
        String qry = "UPDATE `category` SET `id_cat`=?,`category_name`=? WHERE id-cat = ?";
        try {
            PreparedStatement stm = cnx.prepareStatement(qry);
            stm.setInt(1, category.getIdc());
            stm.setString(2, category.getCategoryName());

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
    public boolean deletecat(Category category) {
        String qry ="DELETE FROM `category` WHERE `id_cat`=?";
        try{
            PreparedStatement stm = cnx.prepareStatement(qry);
            stm.setInt(1,category.getIdc());

            int affectedRows = stm.executeUpdate();
            return affectedRows > 0 ;
        }catch (SQLException e){
            System.out.println(e.getMessage());
            return false;
        }
    }
    public boolean categoryExists(String categoryName) {
        String qry = "SELECT COUNT(*) FROM `category` WHERE `category_name` = ?";
        try (PreparedStatement stm = cnx.prepareStatement(qry);) {
            stm.setString(1, categoryName);
            try (ResultSet resultSet = stm.executeQuery()) {
                if (resultSet.next()) {
                    int count = resultSet.getInt(1);
                    return count > 0;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    private boolean categoryExists(int categoryId) throws SQLException {
        String qry = "SELECT COUNT(*) FROM category WHERE id_cat = ?";
        try (PreparedStatement stm = cnx.prepareStatement(qry)) {
            stm.setInt(1, categoryId);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                int count = rs.getInt(1);
                return count > 0;
            }
        }
        return false;
    }
    public Map<String, Integer> countproduitbycat() {
        Map<String, Integer> categorycount = new HashMap<>();
        ArrayList<Stock> stocks = getAll();

        for (Stock stock : stocks) {
            String category = stock.getCategoryName();

            // Vérifier si le rôle existe déjà dans la map
            if (categorycount.containsKey(category)) {
                // Si oui, incrémenter le compteur
                categorycount.put(category, categorycount.get(category) + 1);
            } else {
                // Sinon, ajouter le rôle à la map avec un compteur initial de 1
                categorycount.put(category, 1);
            }
        }

        return categorycount;
    }
    

}
