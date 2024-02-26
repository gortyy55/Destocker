package tn.CodeCommanders.Panier;

public class Panier {
    private static int lastAssignedId = 0;
    private int id_panier;
    private int id_lots;
    private int id_enchere;
    private double prixTotal;

    public Panier() {
        lastAssignedId++;
        this.id_panier = lastAssignedId;
    }

    public Panier(int id_lots, int id_enchere, double prixTotal) {
        lastAssignedId++;
        this.id_panier = lastAssignedId;
        this.id_lots = id_lots;
        this.id_enchere = id_enchere;
        this.prixTotal = prixTotal;
    }

    // New constructor that accepts idPanier, idLots, idEnchere, and prixTotal
    public Panier(int idPanier, int idLots, int idEnchere, double prixTotal) {
        this.id_panier = idPanier;
        this.id_lots = idLots;
        this.id_enchere = idEnchere;
        this.prixTotal = prixTotal;
    }

    public int getId_panier() {
        return id_panier;
    }

    public void setId_panier(int id_panier) {
        this.id_panier = id_panier;
    }

    public int getId_lots() {
        return id_lots;
    }

    public void setId_lots(int id_lots) {
        this.id_lots = id_lots;
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
                ", id_lots=" + id_lots +
                ", id_enchere=" + id_enchere +
                ", prixTotal=" + prixTotal +
                '}';
    }
}
