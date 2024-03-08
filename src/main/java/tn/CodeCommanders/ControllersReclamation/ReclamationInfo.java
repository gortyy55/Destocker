package tn.CodeCommanders.ControllersReclamation;
import java.util.ArrayList;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.List;

import javafx.scene.Node;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import tn.CodeCommanders.Reclamation.Reclamation;
import tn.CodeCommanders.Transactions.TransactionsReclamation;

public class ReclamationInfo implements Initializable {
    public ScrollPane scrollreclamation;
    public GridPane grid;
    public Label NbRec;
    public Label NbAtt;
    public Label NbCours;
    public Label NbRes;
    @FXML
    private Stage stage;
    @FXML
    private Button switchToAddReclamation;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private BorderPane border;

    @FXML
    private HBox hboxCard;

    @FXML
    private Button chatGPT;

    @FXML
    private Button evaluer;
    TransactionsReclamation t = new TransactionsReclamation();

    private ArrayList<Reclamation> getlist() {
        return t.getAll();
    }

    private final List<Reclamation> reclamations = new ArrayList<>();

    private List<Reclamation> getData() {
        List<Reclamation> reclamations = getlist();
        return reclamations;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        int column = 0;
        int row = 1;

        reclamations.addAll(getData());

        try {
            for (int i = 0; i < reclamations.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/CardRec.fxml"));

                AnchorPane anchorPane = fxmlLoader.load();

                CardRec cardRec = fxmlLoader.getController();
                cardRec.setData(reclamations.get(i));

                anchorPane.setMinHeight(236);
                anchorPane.setMinWidth(255);
                if (column == 2) {
                    column = 0;
                    row++;
                }
                grid.add(anchorPane, column++, row);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        updateReclamationCounts();
    }
    private void updateReclamationCounts() {
        TransactionsReclamation transactionsReclamation = new TransactionsReclamation();

        // Calculate and set the total number of reclamations for the user
        int totalReclamations = transactionsReclamation.countReclamationsByUserId(1);
        NbRec.setText(String.valueOf(totalReclamations));

// Calculate and set the number of reclamations en attend
        int enAttendCount = transactionsReclamation.countReclamationsByStatusAndUserId("En attente", 1);
        NbAtt.setText(String.valueOf(enAttendCount));

// Calculate and set the number of reclamations en cours
        int enCoursCount = transactionsReclamation.countReclamationsByStatusAndUserId("En cours", 1);
        NbCours.setText(String.valueOf(enCoursCount));

// Calculate and set the number of reclamations finish
        int finishCount = transactionsReclamation.countReclamationsByStatusAndUserId("finish", 1);
        NbRes.setText(String.valueOf(finishCount));

    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    private void switchToAdd(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/AddReclamation.fxml"));
        try {
            Parent root = loader.load();
            Scene scene = new Scene(root);

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    @FXML
    void switchToAvis(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/avisClient.fxml"));
        try {
            Parent root = loader.load();
            Scene scene = new Scene(root);

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    void onChatgpt(ActionEvent event) {
            // Charger la fenêtre du chat
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/chatWin.fxml"));
            Parent chatParent;
            try {
                chatParent = loader.load();
            } catch (IOException e) {
                e.printStackTrace();
                return;
            }
            chatWin chatWin = loader.getController();

            Scene chatScene = new Scene(chatParent);

            Stage chatStage = new Stage();
            chatStage.setTitle("Chat avec le support client");
            chatStage.setScene(chatScene);
            chatStage.show();
        }
    }