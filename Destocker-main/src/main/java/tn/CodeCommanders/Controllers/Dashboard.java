package tn.CodeCommanders.Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import tn.CodeCommanders.Entities.User;

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
    private Stage stage;
    private Scene scene;
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

    public void logout(MouseEvent event) {try{

        Parent root= FXMLLoader.load(getClass().getResource("/Login.fxml"));
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

}