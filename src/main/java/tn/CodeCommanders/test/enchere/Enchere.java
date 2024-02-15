package tn.CodeCommanders.test.enchere;

public class Enchere {

    private int id,stock;
    private String produit;

    private double prixint;

    public Enchere(){

    }

    public Enchere(int id, int stock, String produit, double prixint){
        this.id=id;
        this.stock=stock;
        this.produit=produit;
        this.prixint=prixint;


    }


    public String getProduit() {
        return produit;
    }

    public void setProduit(String produit) {
        this.produit = produit;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public double getPrixint() {
        return prixint;
    }

    public void setPrixint(double prixint) {
        this.prixint = prixint;
    }

    @Override
    public String toString(){
        return "enchere("+" id : "+id+" /stock : "+stock+" /produit : "+produit+" /prix initiale : "+prixint+")\n";
    }
}
