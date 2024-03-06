package tn.CodeCommanders.Reclamation;

public class AvisClient {
    private int idAvis;
    private int id_reclamation;
    private boolean satisfaction;
    private String comment;

    public AvisClient(int idAvis) {
        this.idAvis = idAvis;
    }

   public AvisClient(int idAvis, int idReclamation, boolean satisfaction, String comment) {
        this.idAvis = idAvis;
        this.id_reclamation = idReclamation;
        this.satisfaction = satisfaction;
        this.comment = comment;
    }

    public AvisClient(int idReclamation, boolean satisfaction, String comment) {
        this.id_reclamation = idReclamation;
        this.satisfaction = satisfaction;
        this.comment = comment;
    }

    public int getIdAvis() {
        return idAvis;
    }

    public void setIdAvis(int idAvis) {
        this.idAvis = idAvis;
    }

    public int getIdReclamation() {
        return id_reclamation;
    }

    public void setIdReclamation(int idReclamation) {
        this.id_reclamation = idReclamation;
    }

    public boolean isSatisfaction() {
        return satisfaction;
    }

    public void setSatisfaction(boolean satisfaction) {
        this.satisfaction = satisfaction;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "AvisClient{" +
                "idAvis=" + idAvis +
                ", idReclamation=" + id_reclamation +
                ", satisfaction=" + satisfaction +
                ", comment='" + comment + '\'' +
                '}';
    }
}
