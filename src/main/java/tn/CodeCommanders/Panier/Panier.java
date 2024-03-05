package tn.CodeCommanders.Panier;

import java.util.Date;

public class Panier {
    private int id_panier;
    private int id_enchere;
    private int id_acteur;
    private double prixTotal;
    private Date Date_Enchere;
    private String produit;

    private String acteurFirstName;

    public Panier() {
        // No need to assign id_panier here, let the database handle it
    }

    public Panier(int id_enchere, int id_acteur, double prixTotal, Date Date_Enchere) {
        this.id_enchere = id_enchere;
        this.id_acteur = id_acteur;
        this.prixTotal = prixTotal;
        this.Date_Enchere = Date_Enchere;
        this.produit = produit;
        this.acteurFirstName = acteurFirstName;

    }

    // Constructor with id_panier (for retrieval from the database)
    public Panier(int id_panier, int id_enchere, int id_acteur, double prixTotal, Date Date_Enchere) {
        this.id_panier = id_panier;
        this.id_enchere = id_enchere;
        this.id_acteur = id_acteur;
        this.prixTotal = prixTotal;
        this.Date_Enchere = Date_Enchere;
        this.produit = produit;
        this.acteurFirstName=acteurFirstName;

    }

    public int getId_panier() {
        return id_panier;
    }

    public void setId_panier(int id_panier) {
        this.id_panier = id_panier;
    }

    public int getId_acteur() {
        return id_acteur;
    }
    public String getProduit() {
        return produit;
    }

    public void setProduit(String produit) {
        this.produit = produit;
    }

    public String getActeurFirstName() {
        return acteurFirstName;
    }

    public void setActeurFirstName(String acteurFirstName) {
        this.acteurFirstName = acteurFirstName;
    }
    public void setId_acteur(int id_acteur) {
        this.id_acteur = id_acteur;
    }

    public Date getDate_Enchere() {
        return Date_Enchere;
    }

    public void setDate_Enchere(Date date_Enchere) {
        this.Date_Enchere = date_Enchere;
    }

    public int getId_enchere() {
        return id_enchere;
    }

    public void setId_enchere(int id_enchere) {
        this.id_enchere = id_enchere;
    }

    public double getPrixTotal() {
        return prixTotal;
    }

    public void setPrixTotal(double prixTotal) {
        this.prixTotal = prixTotal;
    }

    @Override
    public String toString() {
        return "Panier{" +
                "id_panier=" + id_panier +
                ", id_enchere=" + id_enchere +
                ", id_acteur=" + id_acteur +
                ", prixTotal=" + prixTotal +
                ", Date_Enchere=" + Date_Enchere +
                '}';
    }
}
