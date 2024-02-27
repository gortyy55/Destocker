package tn.CodeCommanders.Don;

public class Don {
    private int id_don;

    private String title , description , association;



    public Don( String title, String description, String association) {

        this.title = title;
        this.description = description;
        this.association = association;
    }
    public Don() {
    }



    public int getId_don() {
        return id_don;
    }

    public void setId_don(int id_don) {
        this.id_don = id_don;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAssociation() {
        return association;
    }

    public void setAssociation(String association) {
        this.association = association;
    }

    @Override
    public String toString() {
        return "Don{" +
                "id_don=" + id_don +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", association='" + association + '\'' +
                '}';
    }
}
