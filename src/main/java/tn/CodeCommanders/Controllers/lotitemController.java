package tn.CodeCommanders.Controllers;

import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import tn.CodeCommanders.Panier.Panier;
import tn.CodeCommanders.Transaction.Transactions;

import javax.swing.text.html.ImageView;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class lotitemController implements Initializable {



    @FXML
    private GridPane grid;

    @FXML
    private ScrollPane scroll;
Transactions t = new Transactions();
private  ArrayList<Panier> getlist(){
    return t.getAll();
}
    private List<Panier> paniers = new ArrayList<>();

    private List<Panier> getData() {
        List<Panier> paniers = getlist();



        return paniers;


    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        int column = 0;
        int row = 0;
        paniers.addAll(getData());

        try {
            for (int i = 0; i < paniers.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/Card.fxml"));

                AnchorPane anchorPane = fxmlLoader.load();


                CardController CardController = fxmlLoader.getController();
                CardController.setData(paniers.get(i));

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


}





/*    private Stage stage;
    private Scene scene;
    private Parent parent;

    @FXML
    private VBox dataVBox;



    Transactions t = new Transactions();

    private ArrayList<Panier> getlist(){

        return t.getAll();
    }

    private List<Panier> paniers = new ArrayList<>();

    private List<Panier> getData(){

        List<Panier> paniers = getlist();
        return paniers;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        int column = 0;
        int row = 0;
        paniers.addAll(getData());


        try {
            for(int i=0;i<paniers.size();i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/afficheC.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                cardController cardController = fxmlLoader.getController();
                cardController.setData(paniers.get(i));

                if (column == 4) {
                    column = 0;
                    row++;
                }

                VBox.add(anchorPane, column++, row);

                VBox.setMargin(anchorPane, new Insets(10));

            }}catch (IOException e){
            throw new RuntimeException(e);
        }}


*/





/*
    @FXML
    private TableView<Panier> dataTableView;
    @FXML
    private TableColumn<Panier, Integer> idPanierColumn;
    @FXML
    private TableColumn<Panier, Integer> lotsColumn;
    @FXML
    private TableColumn<Panier, Integer> idEnchereColumn;
    @FXML
    private TableColumn<Panier, Double> prixTotalColumn;
    @FXML
    private TableColumn<Panier, String> ActionsColumn;

    private ObservableList<Panier> panierList = FXCollections.observableArrayList();

    private static final String URL = "jdbc:mysql://ragequit.tn/Destocker";
    private static final String USER = "ghofrane";
    private static final String PASSWORD = "Testtest";

    public void initialize() {
        idPanierColumn.setCellValueFactory(new PropertyValueFactory<>("id_panier"));
        lotsColumn.setCellValueFactory(new PropertyValueFactory<>("id_lots"));
        idEnchereColumn.setCellValueFactory(new PropertyValueFactory<>("id_enchere"));
        prixTotalColumn.setCellValueFactory(new PropertyValueFactory<>("prixTotal"));

        // Add "Actions" column with buttons
        ActionsColumn.setCellFactory(param -> new TableCell<Panier, String>() {
            final Button deleteButton = new Button("Delete");
            final Button updateButton = new Button("Update");

            {
                deleteButton.setOnAction(event -> {
                    Panier panier = getTableView().getItems().get(getIndex());
                    int idPanierToDelete = panier.getId_panier(); // Assuming you have a getId_panier method in your Panier class

                    try {
                        Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
                        String deleteQuery = "DELETE FROM Panier WHERE id_panier = ?";
                        PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery);
                        preparedStatement.setInt(1, idPanierToDelete);

                        int rowsAffected = preparedStatement.executeUpdate();

                        if (rowsAffected > 0) {
                            // Successfully deleted from the database, now remove from panierList
                            panierList.remove(panier);
                        }

                        preparedStatement.close();
                        connection.close();

                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                });

                updateButton.setOnAction(event -> {
                    Panier panier = getTableView().getItems().get(getIndex());

                    try {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Update.fxml"));
                        Parent root = loader.load();

                        Update updateController = loader.getController();
                        updateController.setPanier(panier);

                        Stage stage = new Stage();
                        Scene scene = new Scene(root);
                        stage.setScene(scene);
                        stage.show();

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });

            }

            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);

                if (empty) {
                    setGraphic(null);
                } else {
                    HBox buttons = new HBox(30,deleteButton, updateButton);
                    setGraphic(buttons);
                }
            }
        });

        dataTableView.setItems(panierList);

        getAllData();
        dataTableView.setFixedCellSize(25); // Set the preferred height of each row
        dataTableView.prefHeightProperty().bind(Bindings.size(panierList).multiply(dataTableView.getFixedCellSize()).add(30)); // Add 30 for padding
    }

    private void getAllData() {
        try {
            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            String query = "SELECT * FROM Panier";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int idPanier = resultSet.getInt("id_panier");
                int idLots = resultSet.getInt("id_lots");
                int idEnchere = resultSet.getInt("id_enchere");
                double prixTotal = resultSet.getDouble("prixTotal");

                panierList.add(new Panier(idPanier, idLots, idEnchere, prixTotal));
            }

            dataTableView.setItems(panierList);

            resultSet.close();
            preparedStatement.close();
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

 */
