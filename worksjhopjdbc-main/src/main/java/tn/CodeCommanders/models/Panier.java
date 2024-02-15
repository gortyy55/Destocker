package tn.CodeCommanders.models;

import java.util.Objects;

public class Panier {

    private int id_panier, id_produit;
    private String nomP;
    private int Qq;
    private float prixT;

    public  Panier(){

    }

    public Panier(int id_panier, int id_produit, String nomP, int qq, float prixT) {
        this.id_panier = id_panier;
        this.id_produit = id_produit;
        this.nomP = nomP;
        Qq = qq;
        this.prixT = prixT;
    }



    public int getId_panier() {
        return id_panier;
    }

    public void setId_panier(int id_panier) {
        this.id_panier = id_panier;
    }

    public int getId_produit() {
        return id_produit;
    }

    public void setId_produit(int id_produit) {
        this.id_produit = id_produit;
    }

    public String getNomP() {
        return nomP;
    }

    public void setNomP(String nomP) {
        this.nomP = nomP;
    }

    public int getQq() {
        return Qq;
    }

    public void setQq(int qq) {
        Qq = qq;
    }

    public float getPrixT() {
        return prixT;
    }

    public void setPrixT(float prixT) {
        this.prixT = prixT;
    }

    @Override
    public String toString() {
        return "Panier{" +
                "id_panier=" + id_panier +
                ", id_produit=" + id_produit +
                ", nomP='" + nomP + '\'' +
                ", Qq=" + Qq +
                ", prixT=" + prixT +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Panier panier)) return false;
        return getId_panier() == panier.getId_panier() && getId_produit() == panier.getId_produit() && getQq() == panier.getQq() && Float.compare(getPrixT(), panier.getPrixT()) == 0 && Objects.equals(getNomP(), panier.getNomP());
    }


}
