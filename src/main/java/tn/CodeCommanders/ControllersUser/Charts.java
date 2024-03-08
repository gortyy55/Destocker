package tn.CodeCommanders.ControllersUser;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.stage.Stage;
import tn.CodeCommanders.Service.ServiceUser;

import java.io.IOException;
import java.util.Map;

public class Charts {

    @FXML
    private PieChart chart;

    private Stage stage;
    private Scene scene;
    ServiceUser userService = new ServiceUser();

    // Appel de la fonction countUsersByRole pour obtenir le nombre d'utilisateurs par rôle
    Map<String, Integer> roleCountMap = userService.countUsersByRole();

    // Affichage des résultats
    @FXML
    public void initialize(){
        Map<String, Integer> roleCounts = userService.countUsersByRole();

        // Create data for the pie chart
        for (Map.Entry<String, Integer> entry : roleCounts.entrySet()) {
            PieChart.Data data = new PieChart.Data(entry.getKey(), entry.getValue());
            chart.getData().add(data);
        }

        // Customize the chart if needed
        chart.setTitle("User Roles Distribution");
    }


    public void back(ActionEvent event) {
        try{

            Parent root= FXMLLoader.load(getClass().getResource("/Dashboard.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }
}




