package tn.CodeCommanders.Controllers;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.stage.Stage;

import java.util.prefs.Preferences;

public class RememberME extends Application {
   

        private static final String REMEMBER_ME_KEY = "rememberMe";
    private Scene scene;

    public static void main(String[] args) {
            launch(args);
        }

        @Override
        public void start(Stage primaryStage) {
            primaryStage.setTitle("Remember Me CheckBox Example");

            // Create CheckBox
            CheckBox rememberMeCheckBox = new CheckBox("Remember Me");

            // Load the previous state from Preferences
            Preferences preferences = Preferences.userNodeForPackage(RememberME.class);
            rememberMeCheckBox.setSelected(preferences.getBoolean(REMEMBER_ME_KEY, false));

            // Add listener to CheckBox to save the state when changed
            rememberMeCheckBox.selectedProperty().addListener((observable, oldValue, newValue) -> {
                preferences.putBoolean(REMEMBER_ME_KEY, newValue);
            });
            primaryStage.setScene(scene);
            primaryStage.show();
        }
    }


