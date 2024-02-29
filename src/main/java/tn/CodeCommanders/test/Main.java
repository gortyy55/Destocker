package tn.CodeCommanders.test;
import tn.CodeCommanders.Transactions.Transactions;
import tn.CodeCommanders.JDBC.JDBC;
import tn.CodeCommanders.Reclamation.Reclamation;
import java.sql.Connection;
import java.util.ArrayList;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    private static Connection cnx;
    public static void main(String[] args) {
        cnx = JDBC.getInstance().getCnx();

//Ajout
        /*Transactions transactions = new Transactions();
        Reclamation rec1 = new Reclamation();
        rec1.setTitre("un verre");
        rec1.setType("retour");
        rec1.setId_produit(01);
        rec1.setDescription("je veux retourner");
        rec1.setId_user(100);

        Reclamation rec2 = new Reclamation();
        rec2.setTitre("un pull");
        rec2.setType("retour");
        rec2.setId_produit(02);
        rec2.setDescription("je veux retourner");
        rec2.setId_user(101);

        transactions.add(rec1);
        transactions.add(rec2);
        ArrayList<Reclamation> toutesLesReclamations = transactions.getAll();
//Affichage
       System.out.println("Toutes les réclamations :");
        for (Reclamation rec : toutesLesReclamations) {
            System.out.println(rec);
        }
//Update
        Reclamation rec = toutesLesReclamations.get(0);
        rec.setTitre("oussema");
        rec.setDescription("oussema trabelsi");
        transactions.update(rec);
            System.out.println(rec);
//delete
        Reclamation rec = toutesLesReclamations.get(0);
        //rec.setId_reclamation(1);
        boolean suppressionReussie = transactions.delete(rec);
        if (suppressionReussie) {
            System.out.println("Réclamation supprimée avec succès.");
        } else {
            System.out.println("Erreur lors de la suppression de la réclamation.");
        }

        //Affichage
        System.out.println("Toutes les réclamations :");
        for (Reclamation rec : toutesLesReclamations) {
            System.out.println(rec);
        }*/
    }
}