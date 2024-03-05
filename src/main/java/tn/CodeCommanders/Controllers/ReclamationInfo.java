package tn.CodeCommanders.Controllers;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.List;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import tn.CodeCommanders.Reclamation.Reclamation;
import tn.CodeCommanders.Transactions.Transactions;

public class ReclamationInfo implements Initializable {
    public ScrollPane scrollreclamation;
    public GridPane grid;
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

    Transactions t = new Transactions();

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
    void onChatgpt(ActionEvent event) {
        String messageUtilisateur = "Obtenez le message de l'utilisateur depuis votre interface utilisateur";
        String apiKey = "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";

        String reponseChatbot = APIChatbot(messageUtilisateur, apiKey);

        System.out.println("Réponse du chatbot : " + reponseChatbot);
    }

    private String APIChatbot(String message, String apiKey) {
        /*try {
            URL url = new URL("URL_de_votre_API_ChatGPT");

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Authorization", "Bearer " + apiKey);
            connection.setDoOutput(true);

            // Créer le corps de la requête JSON avec le message de l'utilisateur
            String requestBody = "{\"message\": \"" + message + "\"}";
            // Envoyer le corps de la requête
            try (OutputStream os = connection.getOutputStream()) {
                byte[] input = requestBody.getBytes("utf-8");
                os.write(input, 0, input.length);
            }
            // Lire la réponse de l'API
            try (BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"))) {
                StringBuilder response = new StringBuilder();
                String responseLine;
                while ((responseLine = br.readLine()) != null) {
                    response.append(responseLine.trim());
                }
                return response.toString();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "Erreur lors de l'appel de l'API";
        */
        return message;
    }
}