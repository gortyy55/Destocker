package tn.CodeCommanders.ControllersStock;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.event.Event;
import javafx.event.EventTarget;
import javafx.event.EventType;
import javafx.util.StringConverter;
import tn.CodeCommanders.Transactions.TransactionsStock;
import tn.CodeCommanders.stock.Category;
import tn.CodeCommanders.stock.Stock;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

import static java.lang.Thread.sleep;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;


public class Controllers implements Initializable  {
    @FXML
    private ComboBox<Category> categoryN;
    @FXML
    private AnchorPane opacityPane,drawerPane;
    @FXML
    private ImageView exit;
    private Stage stage;
    private Scene scene;
    private Parent parent;
    @FXML
    private Label resultat;
    @FXML
    private TextField idproduit;
    @FXML
    private TextField idqq;
    @FXML
    private TextField mail;
    String emailcheker = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Z|a-z]{2,}$";

@FXML
     void addP(ActionEvent event) throws InterruptedException {
    if (!verifyEmail()) {
        System.out.println("correct this");
        return ;
    }
    String produit = idproduit.getText();
    int  qq= Integer.parseInt(idqq.getText());
    String Mail = mail.getText();

    Category selectedCategory = categoryN.getValue();
   /* if (selectedCategory == null) {
        System.out.println("Please select a category.");
        return;
    }*/
    if (produit == null) {
        System.out.println("Please put your produit name");
        return;
    }
    if (qq == 0) {
        System.out.println("Please put the quantite of the produit");
        return;
    }


    int IdCat = selectedCategory.getIdc();
    Stock s = new Stock(produit,qq,Mail,IdCat);
    TransactionsStock t = new TransactionsStock();
t.add(s);
/*
    final String ACCOUNT_SID = "AC98bdf891b664839b0e404cc157821a3a";
    final String AUTH_TOKEN = "c0fb1c9a32e2751c26244e6b104160c8";
    Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

//        Envoyer le SMS
    String toPhoneNumber = "+21622097795";
    String fromPhoneNumber = "+14159911333";
    String messageBody = "vous avez un nouveau produit a ajouter ";
    Message message = Message.creator(new PhoneNumber(toPhoneNumber), new PhoneNumber(fromPhoneNumber), messageBody).create();*/
idproduit.setText("");
idqq.setText("");
mail.setText("");
resultat.setText("saved");

}
    TransactionsStock transactions = new TransactionsStock();

    private ArrayList<Category> getlist(){ return transactions.getAllC();}
    private List<Category> categories = new ArrayList<>();
    private List<Category> getData(){List<Category> categories = getlist();
    return categories;}

@Override
public void initialize(URL location, ResourceBundle resource) {
    exit.setOnMouseClicked(event -> { System.exit(0); });
    categories.addAll(getData());

    categoryN.getItems().addAll(categories);
    categoryN.setConverter(new StringConverter<Category>() {
        @Override
        public String toString(Category category) {
            return (category != null) ? category.getCategoryName() : null;
        }

        @Override
        public Category fromString(String string) {
            // You may need to implement this method if needed
            return null;
        }
    });

    categoryN.setCellFactory(param -> new ListCell<Category>() {
        @Override
        protected void updateItem(Category category, boolean empty) {
            super.updateItem(category, empty);
            if (empty || category == null) {
                setText(null);
            } else {
                setText(category.getCategoryName());
            }
        }
    });
}
    //categoryN.addEventHandler(CategoryAddedEvent.CATEGORY_ADDED, event -> refreshCategoryComboBox());

@FXML
    public void clear(KeyEvent event) {
    resultat.setText("");
    }
    public boolean verifyEmail(){
        if(mail.getText().matches(emailcheker)){
            mail.setStyle("-fx-border-color: green; -fx-border-width: 2px;");
            return true;}
        else
        {mail.setStyle("-fx-border-color: red; -fx-border-width: 2px;");
            return false ;}
    }
@FXML
    public void change(ActionEvent event) {
    try{

        Parent root= FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/affichestock.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }catch (IOException e){
        System.out.println(e.getMessage());
    }
    }

   /* public void change2(ActionEvent event) {
        try{

            Parent root= FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/Affichuser.fxml")));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }*/

    private void refreshCategoryComboBox() {
        TransactionsStock transactions = new TransactionsStock();
        ArrayList<Category> categoryList = transactions.getAllC();

        ArrayList<String> categoryNames = new ArrayList<>();
        for (Category category : categoryList) {
            categoryNames.add(category.getCategoryName());
        }
    }

    public void home(MouseEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/Dashboard.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    }

