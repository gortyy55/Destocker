package tn.CodeCommanders.ControllersStock;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import tn.CodeCommanders.Transactions.TransactionsStock;

import java.io.IOException;
import java.util.Map;

public class Stat {
    private Stage stage;
    private Scene scene;
    @FXML
    private Button back;

    @FXML
    private PieChart chart;
    TransactionsStock transactions = new TransactionsStock();
    Map<String, Integer> categorycount = transactions.countproduitbycat();

    @FXML
    public void initialize(){
        TransactionsStock transactions = new TransactionsStock();
        Map<String, Integer> categorycount = transactions.countproduitbycat();

        // Create data for the pie chart
        for (Map.Entry<String, Integer> entry : categorycount.entrySet()) {
            PieChart.Data data = new PieChart.Data(entry.getKey(), entry.getValue());
            chart.getData().add(data);
        }

        // Customize the chart if needed
        chart.setTitle("Stat for the categories");
    }
    @FXML
    void back(ActionEvent event) {
        try {

            Parent root = FXMLLoader.load(getClass().getResource("/affichestock.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

}
