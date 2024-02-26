package tn.CodeCommanders.Controllers;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.stage.WindowEvent;

public class SceneChange {
	private static Stage ActuellStage;
	public void ChangeToNewScenne(Stage NewStage ,String nomScenne) throws IOException {
			Parent root = FXMLLoader.load(getClass().getResource(nomScenne));
			Scene scene = new Scene(root);
			NewStage.setScene(scene);
			Window window = ActuellStage.getScene().getWindow();
		    window.fireEvent(new WindowEvent(window, WindowEvent.WINDOW_CLOSE_REQUEST));
		    ActuellStage=NewStage;
		    NewStage.show();
	}
	
	public void ChangeToOldScenne(Stage NewStage ) throws IOException {
	Window window = ActuellStage.getScene().getWindow();
    window.fireEvent(new WindowEvent(window, WindowEvent.WINDOW_CLOSE_REQUEST));
    ActuellStage=NewStage;
    NewStage.show();
	}
	
	
	public static Stage getActuellStage() {
		return ActuellStage;
	}
	public static void setActuellStage(Stage actuellStage) {
		ActuellStage = actuellStage;
	}
}