/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import entities.CrypterPassword;
import services.UserService;

/**
 * FXML Controller class
 *
 * @author user
 */
public class NwpasswordController implements Initializable {

    @FXML
    private TextField txtemail1;
    @FXML
    private PasswordField txtnew;
    @FXML
    private PasswordField txtconfirm;
    @FXML
    private Button newpasswordbutton;
     UserService us = new UserService();
     CrypterPassword cps=new CrypterPassword();

     Stage Stage1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void NewPwd(ActionEvent event) {
        String new_pwd, confirm_pwd, email;
        new_pwd=txtnew.getText();
        confirm_pwd=txtconfirm.getText();
        email=txtemail1.getText();
        
        
        if (new_pwd==null || confirm_pwd ==null)      
        {   Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Attention !!");
            alert.setContentText("les champs doivent etre non vide");
            alert.show();
        }
        else if (new_pwd.equals(confirm_pwd)==false)      
        {   Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Attention !!");
            alert.setContentText("confirmez votre mot de passe ");
            alert.show();
        }else {
           us.ModifierMdp(email,cps.CrypterPassword(new_pwd));
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("succès");
            alert.setHeaderText("modification mot de passe");
            alert.setContentText("mot de passe est modifié avec succès");
            alert.show();
            try {
                     Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
                        Stage1 = (Stage)((Node)event.getSource()).getScene().getWindow();
                        root.setOnMousePressed(pressEvent -> {
                        root.setOnMouseDragged(dragEvent -> {
                            Stage1.setX(dragEvent.getScreenX() - pressEvent.getSceneX());
                            Stage1.setY(dragEvent.getScreenY() - pressEvent.getSceneY());
                        });
                    });
                        Scene  scene = new Scene(root);
                        Stage1.setScene(scene);
                        Stage1.show();

                } catch (IOException ex) {
                     System.out.println(ex.getMessage());
                }
        }
    }
    
}
