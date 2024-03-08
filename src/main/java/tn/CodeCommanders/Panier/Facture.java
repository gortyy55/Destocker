package tn.CodeCommanders.Panier;

import java.sql.CallableStatement;
import java.util.Date;

public class Facture {
    private int id_facture;
    private int id_acteur;
    private int id_panier;
    private String name_card;
    private int ccn;
    private Date exp_date;
    private int security_code;
    private String address;
    private  String city;
    private String state;
    private  int zip_code;
    private String country;

    private String acteurFirstName;

    public Facture(int id_acteur, int id_panier, String name_card, int ccn, Date exp_date, int security_code, String address, String city, String state, int zip_code, String country) {
        this.id_acteur = id_acteur;
        this.id_panier = id_panier;
        this.name_card = name_card;
        this.ccn = ccn;
        this.exp_date = exp_date;
        this.security_code = security_code;
        this.address = address;
        this.city = city;
        this.state = state;
        this.zip_code = zip_code;
        this.country = country;
        this.acteurFirstName= acteurFirstName;
    }

    public Facture(int id_facture, int id_acteur, int id_panier, String name_card, int ccn, Date exp_date, int security_code, String address, String city, String state, int zip_code, String country) {
        this.id_facture = id_facture;
        this.id_acteur = id_acteur;
        this.id_panier = id_panier;
        this.name_card = name_card;
        this.ccn = ccn;
        this.exp_date = exp_date;
        this.security_code = security_code;
        this.address = address;
        this.city = city;
        this.state = state;
        this.zip_code = zip_code;
        this.country = country;
        this.acteurFirstName= acteurFirstName;
    }


    public Facture() {
    }

    public int getId_facture() {
        return id_facture;
    }

    public void setId_facture(int id_facture) {
        this.id_facture = id_facture;
    }

    public int getId_acteur() {
        return id_acteur;
    }

    public void setId_acteur(int id_acteur) {
        this.id_acteur = id_acteur;
    }

    public int getId_panier() {
        return id_panier;
    }

    public void setId_panier(int id_panier) {
        this.id_panier = id_panier;
    }

    public String getName_card() {
        return name_card;
    }

    public void setName_card(String name_card) {
        this.name_card = name_card;
    }

    public int getCcn() {
        return ccn;
    }

    public void setCcn(int ccn) {
        this.ccn = ccn;
    }



    public void setExp_date(Date exp_date) {
        this.exp_date = exp_date;
    }

    public Date getExp_date() {
        return exp_date;
    }

    public int getSecurity_code() {
        return security_code;
    }

    public void setSecurity_code(int security_code) {
        this.security_code = security_code;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getZip_code() {
        return zip_code;
    }

    public void setZip_code(int zip_code) {
        this.zip_code = zip_code;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getActeurFirstName() {
        return acteurFirstName;
    }

    public void setActeurFirstName(String acteurFirstName) {
        this.acteurFirstName = acteurFirstName;
    }

    @Override
    public String toString() {
        return "Facture{" +
                "id_facture=" + id_facture +
                ", id_acteur=" + id_acteur +
                ", id_panier=" + id_panier +
                ", name_card='" + name_card + '\'' +
                ", ccn=" + ccn +
                ", exp_date=" + exp_date +
                ", security_code=" + security_code +
                ", address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", zip_code=" + zip_code +
                ", country='" + country + '\'' +
                '}';
    }



}

