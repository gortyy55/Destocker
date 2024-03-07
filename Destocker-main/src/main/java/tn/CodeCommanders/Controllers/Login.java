package tn.CodeCommanders.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import tn.CodeCommanders.Entities.User;
import tn.CodeCommanders.Service.ServiceUser;




import java.awt.*;
import java.io.IOException;
import java.sql.SQLException;

public class Login extends RememberME {

    ServiceUser serviceUser = new ServiceUser();


    @FXML
    private Button btncreate;

    @FXML
    private TextField emailLabel;

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
    private Stage stage;
    private Scene scene;

    private void handleLogin(ActionEvent event) throws SQLException {
            if (!serviceUser.userLogIn(emailLabel.getText(), passLabel.getText())) {
                echecLoginLabel.setTextFill(Color.RED);
                echecLoginLabel.setText("Pseudo or Password is incorrect");
            } else {
                if(serviceUser.getCurrentUser().getRole().equals("Admin")){
                    serviceUser.changeScreen(event,"/Dashboard.fxml","Dashboard");

                }else{
                    serviceUser.changeScreen(event,"/Window.fxml","Dashboard");
                }
            }

    }




   @FXML
     void GooleRegister(ActionEvent event) {
    /*     // Your updated JSON data
        String jsonData = "{\"web\":{\"client_id\":\"34198700278-5o1n0105jn1u2m8odioepf3kavqh04dn.apps.googleusercontent.com\",\"project_id\":\"batahapp-415619\",\"auth_uri\":\"https://accounts.google.com/o/oauth2/auth\",\"token_uri\":\"https://oauth2.googleapis.com/token\",\"auth_provider_x509_cert_url\":\"https://www.googleapis.com/oauth2/v1/certs\",\"client_secret\":\"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\",\"redirect_uris\":[\"http://localhost/google/callback/\"]}}";
        // Parse JSON
        JsonObject jsonObject = new Gson().fromJson(jsonData, JsonObject.class);
        JsonObject web = jsonObject.getAsJsonObject("web");
        String gClientId = web.get("client_id").getAsString();
        String gRedir = web.get("redirect_uris").getAsJsonArray().get(0).getAsString(); // Assuming there's only one redirect URI
        String gScope = "https://www.googleapis.com/auth/userinfo.profile " +
                "https://www.googleapis.com/auth/userinfo.email " +
                "https://www.googleapis.com/auth/user.phonenumbers.read";
        String gSecret = web.get("client_secret").getAsString();

        OAuthAuthenticator auth = new OAuthGoogleAuthenticator(gClientId, gRedir, gSecret, gScope);
        auth.startLogin(() -> {

            membreSession =membreSession.convertToGoogleMembre(auth.getJsonData().toString()) ;
            if(membreService.readByIGoogle(membreSession.getIdGoogle()) == null ){
                membreService.addGoogle(membreSession);
            }
            membreSession=membreService.readByIGoogle(membreSession.getIdGoogle()) ;
            membreSession.clearBinFile();
            membreSession.saveJsonToBinFile(membreSession);
            try {
                if(membreSession.getRoleUtilisateur()=='U') {root = FXMLLoader.load(getClass().getResource("/InterfaceUserAdmin/AccueilUser.fxml"));}
                else {root = FXMLLoader.load(getClass().getResource("/InterfaceUserAdmin/AccueilAdmin.fxml"));}
                stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                root.setOnMousePressed((MouseEvent events) -> {
                    xOffset = events.getSceneX();
                    yOffset = events.getSceneY();
                });
                root.setOnMouseDragged((MouseEvent events) -> {
                    stage.setX(events.getScreenX() - xOffset);
                    stage.setY(events.getScreenY() - yOffset);
                });
                stage.setScene(scene);
                stage.show();

            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        });*/
    }
@FXML
    public void addUser(ActionEvent event) throws SQLException {
        if (!passLabel.isVisible()){
            if (!emailLabel.getText().isBlank() && !passLabel.getText().isBlank()) {
                passLabel.setText(passLabel.getText());
                handleLogin(event);
            } else {
                echecLoginLabel.setTextFill(Color.RED);
                echecLoginLabel.setText(" Pseudo or Password is invalid ");
            }
        }
        else {
            if (!emailLabel.getText().isBlank()&& !passLabel.getText().isBlank()){
                handleLogin(event);
            }
            else {
                echecLoginLabel.setTextFill(Color.RED);
                echecLoginLabel.setText("Pseudo or Password is invalid");
            }

        }

    }

    public void back(ActionEvent event) {
        try{

            Parent root= FXMLLoader.load(getClass().getResource("/Window.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }
}
