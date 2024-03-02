/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tn.CodeCommanders.Service;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import tn.CodeCommanders.Controllers.Login;
import tn.CodeCommanders.JDBC.*;
import tn.CodeCommanders.Entities.User;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author karim
 */
public class ServiceUser implements IServiceUser<User> {

    Connection cnx = JDBC.getInstance().getCnx();
    private static User currentUser ;

    @Override
    public void ajouter(User user) {
        String qry = "INSERT INTO `User`(`Email`, `Password`, `Firstname`, `Lastname`, `Address`, `Telephone`, `role`) VALUES (?,?,?,?,?,?,?)";
        try {
            PreparedStatement stm = cnx.prepareStatement(qry);

            stm.setString(1, user.getEmail());
            stm.setString(2, user.getPassword());

            stm.setString(3, user.getFirstname());
            stm.setString(4, user.getLastname());
            stm.setString(5, user.getAddress());
            stm.setInt(6, user.getTelephone());
            stm.setString(7, user.getRole());




            stm.executeUpdate();
            System.out.println("User added!");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    @Override
    public void supprimer(User user) {
        String qry = "DELETE FROM `User` WHERE `id`=?";
        try {
            PreparedStatement stm = cnx.prepareStatement(qry);
            stm.setInt(1, user.getId());

            stm.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("user deleted!");

    }

    @Override
    public void modifier(User user) {
        String qry = "UPDATE `User` SET `Email`=?, `Password`=?, `Role`=?, `Firstname`=?, `Lastname`=?, `Address`=?, `Telephone`=? WHERE `id`=?";
        try {
            PreparedStatement stm = cnx.prepareStatement(qry);
            stm.setString(1, user.getEmail());
            stm.setString(2, user.getPassword());
            stm.setString(3, user.getRole());
            stm.setString(4, user.getFirstname());
            stm.setString(5, user.getLastname());
            stm.setString(6, user.getAddress());
            stm.setInt(7, user.getTelephone());
            stm.setInt(8, user.getId());

            stm.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }



    }

    @Override
    public ArrayList<User> getAll() {
        ArrayList<User> userList = new ArrayList<>();

        String qry = "SELECT * FROM `User`";
        try {
            PreparedStatement stm = cnx.prepareStatement(qry);
            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                User user = new User(rs.getInt("ID"), rs.getString("EMAIL"), rs.getString("PASSWORD"), rs.getString("ROLE"), rs.getString("FIRSTNAME"), rs.getString("LASTNAME"), rs.getString("ADDRESS"), rs.getInt("TELEPHONE"));
                user.setId(rs.getInt("id"));
                user.setEmail(rs.getString("Email"));
                user.setPassword(rs.getString("Password"));
                user.setRole(rs.getString("Role"));
                user.setFirstname(rs.getString("Firstname"));
                user.setLastname(rs.getString("Lastname"));
                user.setAddress(rs.getString("Address"));
                user.setTelephone(rs.getInt("Telephone"));
                userList.add(user);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return userList;

    }

    @Override
    public User getOneByID(int id) {
        User user = null;

        String qry = "SELECT * FROM `User` WHERE `id`=?";
        try {
            PreparedStatement stm = cnx.prepareStatement(qry);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();

            if (rs.next()) {
                user = new User(rs.getInt("ID"), rs.getString("EMAIL"), rs.getString("PASSWORD"), rs.getString("ROLE"), rs.getString("FIRSTNAME"), rs.getString("LASTNAME"), rs.getString("ADDRESS"), rs.getInt("TELEPHONE"));
                user.setId(rs.getInt("id"));
                user.setEmail(rs.getString("Email"));
                user.setPassword(rs.getString("Password"));
                user.setRole(rs.getString("Role"));
                user.setFirstname(rs.getString("Firstname"));
                user.setLastname(rs.getString("Lastname"));
                user.setAddress(rs.getString("Address"));
                user.setTelephone(rs.getInt("Telephone"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return user;
    }

    public boolean userLogIn(String email, String password) throws SQLException {
        String req = "SELECT * FROM `User` WHERE email=? AND password=?";
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setString(1, email);
        ps.setString(2, password);
        ResultSet rs = ps.executeQuery();
        if(rs.next()){
            currentUser = new User(
                    rs.getInt("id"),
                    rs.getString("email"),
                    rs.getString("password"),
                    rs.getString("role"),
                    rs.getString("firstname"),
                    rs.getString("lastname"),
                    rs.getString("address"),
                    rs.getInt("telephone"));
            return true;
        }
        return false;
    }


    public void changeScreen(ActionEvent event, String fxmlFile, String title){
        try {
            FXMLLoader loader = new FXMLLoader(Login.class.getResource(fxmlFile));
            Parent root = loader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setTitle(title);
            stage.setScene(new Scene(root));
            stage.show();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public boolean isEmailUnique(String email) {

        String qry = "SELECT * FROM `User` WHERE `email`=?";
        try {
            PreparedStatement stm = cnx.prepareStatement(qry);
            stm.setString(1, email);
            ResultSet rs = stm.executeQuery();
            return !rs.next();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public User getCurrentUser() {
        return currentUser;
    }
}
