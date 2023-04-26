/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package gui;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import entities.User;
import entities.UserSession;
import services.UserService;

/**
 * FXML Controller class
 *
 * @author user
 */
public class ProfileController implements Initializable {

    @FXML
    private VBox vbox;
    @FXML
    private TextField nom;
    @FXML
    private TextField prenom;
    @FXML
    private TextField email;
    @FXML
    private Button modifier;
    @FXML
    private Label id;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        UserSession session=UserSession.getInstance();
        email.setText(session.getEmail());
        nom.setText(session.getNom());
        prenom.setText(session.getPrenom());

        // TODO
    }    

    @FXML
    private void modifier_profile(ActionEvent event) throws SQLException {
        UserService userservice=new UserService();
        UserSession session=UserSession.getInstance();
        String email_ancien=session.getEmail();
        User u=new User();
        u.setNom(nom.getText());
        u.setPrenom(prenom.getText());
        u.setEmail(email.getText());
        session.setEmail(u.getEmail());
        session.setNom(u.getNom());
        session.setPrenom(u.getPrenom());
        userservice.modifier(u,email_ancien);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Modifier");
        alert.setHeaderText(null);
        alert.setContentText("Information Admin a été modifié avec succès");
        alert.showAndWait();
    }
    
}
