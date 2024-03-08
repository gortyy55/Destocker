package tn.CodeCommanders.ControllersPanier;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import tn.CodeCommanders.Panier.Enchere;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class afficheEnchereUser implements Initializable {


    @FXML
    private GridPane grid;


    @FXML
    private Text itemCountText;
    @FXML
    private ScrollPane scroll;
    private Stage stage;
    private Scene scene;
    tn.CodeCommanders.Transaction.TransactionsPanier t = new tn.CodeCommanders.Transaction.TransactionsPanier();
    private ArrayList<Enchere> getlist(){
        return t.getAllE();
    }
    private List<Enchere> encheres = new ArrayList<>();
    protected int itemCount = 0;
    private lotitemController lotitemController;


    private List<Enchere> getData() {
        List<Enchere> encheres = getlist();



        return encheres;


    }


    public void updateItemCountText(int count) {
        itemCountText.setText(String.valueOf(count));
    }

    public int idact;


    public void handleAddPanierEvent(int idActeur) {
        this.idact = idActeur;
        System.out.println("hihaaa." + idActeur);


    }
    public void setLotitemController(lotitemController lotitemControler) {
        this.lotitemController = lotitemControler;
        lotitemControler.setIdact(idact); // Set the idact in lotitemController
    }
    @Override
    public  void initialize(URL location, ResourceBundle resources) {

        int column = 0;
        int row = 0;
        encheres.clear();
        grid.getChildren().clear();
        encheres.addAll(getData());


        try {
            for (int i = 0; i < encheres.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/Card1.fxml"));

                AnchorPane anchorPane = fxmlLoader.load();


                Card1Controller Card1Controller = fxmlLoader.getController();
                Card1Controller.setData(encheres.get(i));
                Card1Controller.setAfficheEnchereUser(this);

                if (column == 3) {
                    column = 0;
                    row++;
                }


                grid.add(anchorPane, column++, row);
                GridPane.setMargin(anchorPane, new Insets(10));

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public  void refreshGrid() {
        grid.getChildren().clear();
        initialize(null, null);
    }

    public void gopay() {
    }


    public void handleImageViewClick() {


        FXMLLoader loader = new FXMLLoader(getClass().getResource("/afficheCard.fxml"));
        Parent root;
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        lotitemController lotController = loader.getController();
        lotController.setIdact(idact);
        // Create a new stage
        Stage stage = new Stage();
        stage.setScene(new Scene(root));

        // Show the new stage
        stage.show();
    }

    public void home1(MouseEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/homepage.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
