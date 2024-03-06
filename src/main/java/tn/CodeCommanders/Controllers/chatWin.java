package tn.CodeCommanders.Controllers;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import javafx.scene.control.TextField;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
public class chatWin {

    @FXML
    private TextField messageTextField;

    @FXML
    private TextArea zoneDeChat;

    private static final String API_KEY = "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";
    private static final String API_ENDPOINT = "https://api.openai.com/v1/chat/completions";
    String model = "gpt-3.5-turbo";
    @FXML
    private void initialize() {
    }

    @FXML
    void envoyer() {
        // Récupérez le message de l'utilisateur depuis votre interface utilisateur
        String messageUtilisateur = messageTextField.getText();


        // Appelez l'API du chatbot pour obtenir la réponse
        String reponseChatbot = APIChatbot(messageUtilisateur);

        // Mettez à jour la zone de chat avec la réponse du chatbot
        zoneDeChat.appendText("User : " + messageUtilisateur + "\n");
        zoneDeChat.appendText("Chatbot : " + reponseChatbot + "\n");
        messageTextField.clear();
    }
    public static String APIChatbot(String userMessage) {
        try {
            URL url;
            url = new URL(API_ENDPOINT);

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // Configuration de la requête
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Authorization", "Bearer " + API_KEY);
            connection.setDoOutput(true);

            // Création du corps de la requête JSON avec le message de l'utilisateur
            String requestBody = "{ \"messages\": ["
                    + "{ \"role\": \"system\", \"content\": \"You are a helpful assistant.\" },"
                    + "{ \"role\": \"user\", \"content\": \"" + userMessage + "\" }"
                    + "] }";

            // Envoi du corps de la requête
            try (OutputStream os = connection.getOutputStream()) {
                byte[] input = requestBody.getBytes(StandardCharsets.UTF_8);
                os.write(input, 0, input.length);
            }

            // Vérification du code de statut HTTP
            int responseCode = connection.getResponseCode();
            if (responseCode != HttpURLConnection.HTTP_OK) {
                System.out.println("Erreur HTTP : " + responseCode);
                // Gérer l'erreur en conséquence
                return "Erreur HTTP : " + responseCode;
            }

            // Lecture de la réponse de l'API
            try (BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8))) {
                StringBuilder response = new StringBuilder();
                String responseLine;
                while ((responseLine = br.readLine()) != null) {
                    response.append(responseLine);
                }
                return response.toString();
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Erreur lors de l'appel de l'API : " + e.getMessage());
            return "Erreur lors de l'appel de l'API";
        }
    }

}



