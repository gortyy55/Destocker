/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tn.CodeCommanders.Entities;

import java.util.Objects;

/**
 *
 * @author karim
 */
public class User {

    int id;
    String Email;
    String Password;
    String Role;
    String Firstname;
    String Lastname;
    String Address;
    int Telephone;
    String animal;
    String question;
    int ban;
   public User(){

    }

    public User(int id, String email, String password, String role, String firstname, String lastname, String address, int telephone, String animal, String question, int ban) {
        this.id = id;
        this.Email = email;
        this.Password = password;
        this.Role = role;
        this.Firstname = firstname;
        this.Lastname = lastname;
        this.Address = address;
        this.Telephone = telephone;
        this.animal = animal;
        this.question = question;
        this.ban = ban;
    }

    public User(String email, String password, String role, String firstname, String lastname, String address, int telephone, String animal, String question, int ban) {
        this.Email = email;
        this.Password = password;
        this.Role = role;
        this.Firstname = firstname;
        this.Lastname = lastname;
        this.Address = address;
        this.Telephone = telephone;
        this.animal = animal;
        this.question = question;
        this.ban = ban;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getRole() {
        return Role;
    }

    public void setRole(String role) {
        Role = role;
    }

    public String getFirstname() {
        return Firstname;
    }

    public void setFirstname(String firstname) {
        Firstname = firstname;
    }

    public String getLastname() {
        return Lastname;
    }

    public void setLastname(String lastname) {
        Lastname = lastname;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public int getTelephone() {
        return Telephone;
    }

    public void setTelephone(int telephone) {
        Telephone = telephone;
    }

    public String getAnimal() {
        return animal;
    }

    public void setAnimal(String animal) {
        this.animal = animal;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public int getBan() {
        return ban;
    }

    public void setBan(int ban) {
        this.ban = ban;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", Email='" + Email + '\'' +
                ", Password='" + Password + '\'' +
                ", Role='" + Role + '\'' +
                ", Firstname='" + Firstname + '\'' +
                ", Lastname='" + Lastname + '\'' +
                ", Address='" + Address + '\'' +
                ", Telephone=" + Telephone +
                ", animal='" + animal + '\'' +
                ", question='" + question + '\'' +
                ", ban=" + ban +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
