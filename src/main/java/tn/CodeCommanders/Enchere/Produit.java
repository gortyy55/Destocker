package tn.CodeCommanders.Enchere;

public class Produit {


    private int id;
    private String name;

    private int quantite;

    public Produit() {

    }


    public Produit(int id, String name, int quantite) {
        this.id=id;
        this.name=name;
        this.quantite=quantite;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

}
