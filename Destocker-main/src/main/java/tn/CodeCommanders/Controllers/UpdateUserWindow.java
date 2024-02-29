package tn.CodeCommanders.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import tn.CodeCommanders.Entities.User;
import tn.CodeCommanders.Service.ServiceUser;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class UpdateUserWindow implements Initializable {

    public TextField firstname;
    public TextField lastname;
    public TextField role;
    public TextField email;
    public TextField mdp;
    public TextField addr;
    public TextField telephone;
    public Button updatebtn;
    private List<User> users = new ArrayList<>();
    ServiceUser su = new ServiceUser();

    User user;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void setEdit(User e) {
        this.user = e;

        System.out.println(user);

        firstname.setText(user.getFirstname());
        lastname.setText(user.getLastname());
        mdp.setText(user.getPassword());
        email.setText(user.getEmail());
        addr.setText(user.getAddress());
        telephone.setText(String.valueOf(user.getTelephone()));
        role.setText(user.getRole());

    }


    public void update(ActionEvent event) throws IOException {

        String fname = firstname.getText();
        String lname = lastname.getText();
        String md = mdp.getText();
        String mail = email.getText();
        String address = addr.getText();
        int tel = Integer.parseInt(telephone.getText());
        String rol = role.getText();

        user.setFirstname(fname);
        user.setLastname(lname);
        user.setEmail(mail);
        user.setRole(rol);
        user.setAddress(address);
        user.setPassword(md);
        user.setTelephone(tel);

        su.modifier(user);

        Dashboard.getInstance().FetchAll();

    }
}
