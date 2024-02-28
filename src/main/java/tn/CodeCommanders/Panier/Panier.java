package tn.CodeCommanders.Panier;

import java.util.Date;

public class Panier {
    private static int lastAssignedId = 0;
    private int id_panier;

    private int id_enchere;
    private int id_acteur;
    private double prixTotal;
    private Date Date_Enchere;

    public Panier() {
        lastAssignedId++;
        this.id_panier = lastAssignedId;
    }

    public Panier(int id_enchere, int id_acteur, double prixTotal,Date Date_Enchere) {
        lastAssignedId++;
        this.id_panier = lastAssignedId;
        this.id_enchere=id_enchere;
        this.id_acteur = id_acteur;
        this.prixTotal = prixTotal;
        this.Date_Enchere = Date_Enchere;
    }

    // New constructor that accepts idPanier, idLots, idEnchere, and prixTotal
    public Panier(int id_panier, int id_enchere, int id_acteur, double prixTotal, Date Date_Enchere) {
        this.id_panier = id_panier;
        this.id_enchere = id_enchere;
        this.id_acteur = id_acteur;
        this.prixTotal = prixTotal;
        this.Date_Enchere= Date_Enchere;
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

    public void setId_acteur(int id_acteur) {
        this.id_acteur = id_acteur;
    }

    public Date getDate_Enchere() {
        return Date_Enchere;
    }

    public void setDate_Enchere(Date date_Enchere) {
        Date_Enchere = date_Enchere;
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
