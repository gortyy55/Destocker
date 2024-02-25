/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tn.CodeCommanders.Service;

import tn.CodeCommanders.test.PasswordGenerator;
import tn.CodeCommanders.JDBC.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import tn.CodeCommanders.Entities.User;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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


    @Override
    public void ajouter(User user) {
        String qry = "INSERT INTO `User`(`Email`, `Password`, `Firstname`, `Lastname`, `Address`, `Telephone`) VALUES (?,?,?,?,?,?)";
        try {
            PreparedStatement stm = cnx.prepareStatement(qry);

            stm.setString(1, user.getEmail());
            stm.setString(2, user.getPassword());

            stm.setString(3, user.getFirstname());
            stm.setString(4, user.getLastname());
            stm.setString(5, user.getAddress());
            stm.setInt(6, user.getTelephone());




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
        String qry = "UPDATE `User` SET `Email`=?, `Password`=?, `Role`=?, `Firstname`=?, `Lastname`=?, `Address`=?, `Telephone`=?, `Animal`=?, `Question`=?, `Ban`=? WHERE `id`=?";
        try {
            PreparedStatement stm = cnx.prepareStatement(qry);
            stm.setString(1, user.getEmail());
            stm.setString(2, user.getPassword());
            stm.setString(3, user.getRole());
            stm.setString(4, user.getFirstname());
            stm.setString(5, user.getLastname());
            stm.setString(6, user.getAddress());
            stm.setInt(7, user.getTelephone());
            stm.setString(8, user.getAnimal());
            stm.setString(9, user.getQuestion());
            stm.setInt(10, user.getBan());
            stm.setInt(11, user.getId());

            stm.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }



    }

    @Override
    public List<User> getAll() {
        List<User> userList = new ArrayList<>();

        String qry = "SELECT * FROM `User`";
        try {
            PreparedStatement stm = cnx.prepareStatement(qry);
            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setEmail(rs.getString("Email"));
                user.setPassword(rs.getString("Password"));
                user.setRole(rs.getString("Role"));
                user.setFirstname(rs.getString("Firstname"));
                user.setLastname(rs.getString("Lastname"));
                user.setAddress(rs.getString("Address"));
                user.setTelephone(rs.getInt("Telephone"));
                user.setAnimal(rs.getString("Animal"));
                user.setQuestion(rs.getString("Question"));
                user.setBan(rs.getInt("Ban"));

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
                user = new User();
                user.setId(rs.getInt("id"));
                user.setEmail(rs.getString("Email"));
                user.setPassword(rs.getString("Password"));
                user.setRole(rs.getString("Role"));
                user.setFirstname(rs.getString("Firstname"));
                user.setLastname(rs.getString("Lastname"));
                user.setAddress(rs.getString("Address"));
                user.setTelephone(rs.getInt("Telephone"));
                user.setAnimal(rs.getString("Animal"));
                user.setQuestion(rs.getString("Question"));
                user.setBan(rs.getInt("Ban"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return user;
    }
}
