package tn.CodeCommanders.Reclamation;

public class Reclamation {
    private int id_reclamation;
    private String titre;
    private String type;
    private int id_produit;
    private String Description;
    private int id_user;

    public Reclamation() {
    }

    public Reclamation(int id_reclamation, String titre, String type, int id_produit, String description, int id_user) {
        this.id_reclamation = id_reclamation;
        this.titre = titre;
        this.type = type;
        this.id_produit = id_produit;
        Description = description;
        this.id_user = id_user;
    }

    public Reclamation(String titre, String description, String type) {
        this.titre = titre;
        this.type = type;
        Description = description;
    }

    public int getId_reclamation() {
        return id_reclamation;
    }

    public void setId_reclamation(int id_reclamation) {
        this.id_reclamation = id_reclamation;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getId_produit() {
        return id_produit;
    }

    public void setId_produit(int id_produit) {
        this.id_produit = id_produit;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    @Override
    public String toString() {
        return "Reclamation{" +
                "id_reclamation=" + id_reclamation +
                ", titre='" + titre + '\'' +
                ", type='" + type + '\'' +
                ", id_produit=" + id_produit +
                ", Description='" + Description + '\'' +
                ", id_user=" + id_user +
                '}';
    }


    }


