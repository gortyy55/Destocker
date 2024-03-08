package tn.CodeCommanders.stock;

public class Stock {
    private int idproduit, quantite,IdCat ;
    private String produitname , mail;
    private String categoryName;

    public Stock() {
    }

    public Stock(String produitname, int quantite,  String mail, int IdCat) {
        this.produitname = produitname;
        this.quantite = quantite;
        this.mail = mail;
        this.IdCat = IdCat;
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
    public int getIdCat() {
        return IdCat;
    }

    public void setIdCat(int idCat) {
        IdCat = idCat;
    }
    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    @Override
    public String toString() {
        return "Stock{" +
                "idproduit=" + idproduit +
                ", quantite=" + quantite +
                ", IdCat=" + IdCat +
                ", produitname='" + produitname + '\'' +
                ", mail='" + mail + '\'' +
                '}';
    }
}

