    package tn.CodeCommanders.Controllers;


    import javafx.event.ActionEvent;
    import javafx.fxml.FXML;
    import javafx.fxml.FXMLLoader;
    import javafx.scene.Node;
    import javafx.scene.Parent;
    import javafx.scene.Scene;
    import javafx.scene.control.*;
    import javafx.scene.paint.Color;
    import javafx.stage.Stage;
    import org.w3c.dom.Text;
    import tn.CodeCommanders.Service.ServiceUser;


    import com.google.gson.Gson; // Import Gson
    import com.google.gson.JsonObject; // Import JsonObject



    import java.io.IOException;
    import java.sql.SQLException;

    public class Login extends RememberME {



        private static final String CLIENT_ID = "34198700278-5o1n0105jn1u2m8odioepf3kavqh04dn.apps.googleusercontent.com";
        private static final String CLIENT_SECRET = "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";
        private static final String REDIRECT_URI = "http://localhost/google/callback/";

        private ServiceUser serviceUser = new ServiceUser();
        private Stage stage;
        private Scene scene;

        @FXML
        private Button btncreate;

        @FXML
        private TextField emailLabel;

        @FXML
        private Button googleSignInButton;

        @FXML
        private Text errorMessage;

        @FXML
        private PasswordField passLabel;

        @FXML
        private ToggleGroup role;

        @FXML
        private CheckBox rememberME;

        @FXML
        private Label echecLoginLabel;

        @FXML
        private void handleLogin(ActionEvent event) throws SQLException {
            if (!serviceUser.userLogIn(emailLabel.getText(), passLabel.getText())) {
                echecLoginLabel.setTextFill(Color.RED);
                echecLoginLabel.setText("Pseudo or Password is incorrect");
            } else {
                if (serviceUser.getCurrentUser().getRole().equals("Admin")) {
                    serviceUser.changeScreen(event, "/Dashboard.fxml", "Dashboard");
                } else {
                    serviceUser.changeScreen(event, "/Window.fxml", "Dashboard");
                }
            }
        }

        @FXML
        void googleSignIn(ActionEvent event) {
            OAuthGoogleAuthenticator auth = new OAuthGoogleAuthenticator(CLIENT_ID, REDIRECT_URI, CLIENT_SECRET, "https://www.googleapis.com/auth/userinfo.profile https://www.googleapis.com/auth/userinfo.email");
            auth.startLogin(() -> {
                String jsonDataFromGoogle = String.valueOf(auth.getJsonData());
                handleGoogleSignInCallback(jsonDataFromGoogle);
                // Example: Navigate to a specific screen after successful login
                navigateAfterGoogleLogin(event);
            });
        }

        private void handleGoogleSignInCallback(String jsonDataFromGoogle) {
            JsonObject userData = new Gson().fromJson(String.valueOf(jsonDataFromGoogle), JsonObject.class);
            String userEmail = userData.getAsJsonPrimitive("email").getAsString();
            String userId = userData.getAsJsonPrimitive("id").getAsString();
            // Perform actions with userEmail and userId
            System.out.println("User Email: " + userEmail);
            System.out.println("User ID: " + userId);
        }

        private void navigateAfterGoogleLogin(ActionEvent event) {
            try {
                Parent root;
                // Adjust this logic based on your application requirements
                if (serviceUser.getCurrentUser().getRole().equals("Admin")) {
                    root = FXMLLoader.load(getClass().getResource("/Dashboard.fxml"));
                } else {
                    root = FXMLLoader.load(getClass().getResource("/Window.fxml"));
                }
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                // Add your existing mouse pressed and dragged event handlers here
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @FXML
        public void addUser(ActionEvent event) throws SQLException {
            if (!passLabel.isVisible()) {
                if (!emailLabel.getText().isBlank() && !passLabel.getText().isBlank()) {
                    passLabel.setText(passLabel.getText());
                    handleLogin(event);
                } else {
                    echecLoginLabel.setTextFill(Color.RED);
                    echecLoginLabel.setText(" Pseudo or Password is invalid ");
                }
            } else {
                if (!emailLabel.getText().isBlank() && !passLabel.getText().isBlank()) {
                    handleLogin(event);
                } else {
                    echecLoginLabel.setTextFill(Color.RED);
                    echecLoginLabel.setText("Pseudo or Password is invalid");
                }
            }
        }

        @FXML
        public void back(ActionEvent event) {
            try {
                Parent root = FXMLLoader.load(getClass().getResource("/Window.fxml"));
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }
