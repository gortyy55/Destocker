package tn.CodeCommanders.models;

public class Stock {
    private int idproduit, quantite ;
    private String produitname , mail;

    public Stock() {
    }

    public Stock(int idproduit, int quantite, String produitname, String mail) {
        this.idproduit = idproduit;
        this.quantite = quantite;
        this.produitname = produitname;
        this.mail = mail;
    }

    public int getId() {
        return idproduit;
    }

    public void setId(int id) {
        this.idproduit = id;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public String getProduitname() {
        return produitname;
    }

    public void setProduitname(String produitname) {
        this.produitname = produitname;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    @Override
    public String toString() {
        return "Stock{" +
                "idproduit=" + idproduit +
                ", quantite=" + quantite +
                ", produitname='" + produitname + '\'' +
                ", mail='" + mail + '\'' +
                '}';
    }
}
