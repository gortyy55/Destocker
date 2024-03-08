package tn.CodeCommanders.ControllersUser;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.input.KeyEvent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import tn.CodeCommanders.User.User;
import javafx.scene.control.Button;

import tn.CodeCommanders.Service.ServiceUser;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Dashboard implements Initializable {

    @FXML
    public ScrollPane scrol;
    @FXML
    public GridPane grid;
    public javafx.scene.control.TextField recherche;
    private Stage stage;
    private Scene scene;

    @FXML
    private Button charts;
    private List<User> users = new ArrayList<>();
    public static Dashboard instance;

    private List<User> getData(){
        List<User> users =getlist();
                return users;
    }

    public Dashboard() {
        instance = this;
    }

    public static Dashboard getInstance() {
        return instance;
    }

ServiceUser t = new ServiceUser();
    private ArrayList<User> getlist(){

        return t.getAll();
    }




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        scrol.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrol.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        int column = 0;
        int row = 1;
        users.addAll(getData());



        try {
            for (int i = 0; i < users.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/cart.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                Usercart usercart = fxmlLoader.getController();
                usercart.setData(users.get(i));

                if (column == 2) {
                    column = 0;
                    row++;
                }

                grid.add(anchorPane, column++, row);



            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        recherche.setOnKeyReleased(event -> {String searchTerm = recherche.getText().trim().toLowerCase();
            filterAndRefresh(searchTerm);});
    loadAndDisplayData();
    }

    private void loadAndDisplayData() {
        int column = 0;
        int row = 1;
        users.addAll(getData());

        try {
            for (int i = 0; i < users.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/cart.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                Usercart usercart = fxmlLoader.getController();
                usercart.setData(users.get(i));

                if (column == 2) {
                    column = 0;
                    row++;
                }

                grid.add(anchorPane, column++, row);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void filterAndRefresh(String searchTerm) {
        grid.getChildren().clear();

        if (searchTerm.isEmpty()) {
            // If the search term is empty, display all users
            refreshGrid(users);
        } else {
            // Filter users based on the entered username
            List<User> filteredUsers = new ArrayList<>();
            for (User user : users) {
                if (user.getFirstname().toLowerCase().contains(searchTerm)) {
                    filteredUsers.add(user);
                }
            }
            refreshGrid(filteredUsers);
        }
    }

    private void refreshGrid(List<User> usersToDisplay) {
        int column = 0;
        int row = 1;

        for (int i = 0; i < usersToDisplay.size(); i++) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/cart.fxml"));
            AnchorPane anchorpane;
            try {
                anchorpane = loader.load();

                //anchorpane.setStyle("-fx-border-color:#000");

                Usercart userItem = loader.getController();
                userItem.setData(usersToDisplay.get(i));

                if (column == 2) {
                    column = 0;
                    row++;
                }

                grid.add(anchorpane, column++, row);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void logout(MouseEvent event) {
        try{

        Parent root= FXMLLoader.load(getClass().getResource("/Login.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }catch (IOException e){
        System.out.println(e.getMessage());
    }
    }
    public void stockadmin(MouseEvent event) {
        try{

            Parent root= FXMLLoader.load(getClass().getResource("/Stock.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }

    public void FetchAll() throws IOException {
        int column = 0;
        int row = 1;
        grid.getChildren().clear();

                users = t.getAll();

            ObservableList<User> ole = FXCollections.observableArrayList(users);

            for (int i = 0; i < ole.size(); i++) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/cart.fxml"));
                AnchorPane anchorpane = loader.load();

                //anchorpane.setStyle("-fx-border-color:#000");

                Usercart userItem = loader.getController();
                userItem.setData(ole.get(i));

                if (column == 2) {
                    column = 0;
                    row++;
                }
                //EvItemGrid.setHgap(500);

                grid.add(anchorpane, column++, row);
                //EvItemGrid.setMargin(anchorpane, new Insets(10));
            }

    }




    @FXML
    void charts(ActionEvent event) {

        try{

            Parent root= FXMLLoader.load(getClass().getResource("/charts.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }


    public void panier(MouseEvent event) {
        try{

            Parent root= FXMLLoader.load(getClass().getResource("/AdminaffichePanier.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }
    public void paymentadmin(MouseEvent event) {
        try{

            Parent root= FXMLLoader.load(getClass().getResource("/affichefacture.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }

    public void reclamationadmin(MouseEvent event) {
        try{

            Parent root= FXMLLoader.load(getClass().getResource("/admin.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        }catch (IOException e){
            System.out.println(e.getMessage());
        }

    }

}




