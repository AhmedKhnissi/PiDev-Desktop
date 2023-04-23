/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package myvet_pidev;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;
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
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.mail.MessagingException;
import myvet.services.sendmailverification;
import myvet.utils.MaConnection;

/**
 * FXML Controller class
 *
 * @author user
 */
public class Forget_passwordController implements Initializable {

    @FXML
    private TextField pwdemail;
    @FXML
    private TextField pwdcode;
    @FXML
    private Button codebutton;
    @FXML
    private Button resetbutton;
        int test;
    Parent root;
    private Stage stage;
    private Stage scene;
    
    Connection cnx;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        pwdcode.setDisable(true);
        resetbutton.setDisable(true);
        // TODO
    }  
    
        public Integer rechercheEmail(String email){
                    Integer exist = 0;
                    Connection cnx = MaConnection.getInstance().getCnx();
                    PreparedStatement pst;
                    ResultSet rs = null;
                    try {
                        pst = cnx.prepareStatement("SELECT COUNT(*) AS count FROM user WHERE email=? ");
                        pst.setString(1, email);
                      
                        rs = pst.executeQuery();
                        if (rs.next()) {
                             exist=rs.getInt("count"); 
                        }
                    } catch (SQLException ex) {
                        System.out.println(ex.getMessage());
                    }
                    return exist;
    }
        
          public int setCode (){
            Random random = new Random();
            int code;
            code = random.nextInt(100000);
            test=code;
            return code;
    } 
    
    

    @FXML
    private int sendcode(ActionEvent event) throws MessagingException {
         if (rechercheEmail(pwdemail.getText())>=1){
            pwdcode.setDisable(false);
            resetbutton.setDisable(false);
            
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("l étape suivante");
            alert.setHeaderText("code de vérification est envoyé par mail");
            alert.setContentText("s'il vous plait vérifier votre mail ");
            alert.show();
            
            test=setCode();
            sendmailverification.envoyer(pwdemail.getText(),test);
            System.out.println("tekhdem");
        
        }
        return test;
    }

    @FXML
    private void reset_onclick(ActionEvent event) {
             try {
              int code = Integer.parseInt(pwdcode.getText());
            if(code==test){

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("derniére étape");
            alert.setHeaderText("noveau mot de passe ");
            alert.setContentText("s'il vous plait saisir le mot de passe");
            alert.show();
            root = FXMLLoader.load(getClass().getResource("nwpassword.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
                
            }
            else{
                
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                     alert.setTitle("Attention");
                     alert.setHeaderText("Incorrect code !!");
                     //alert.setContentText("votre compte est desactivé du des actions inderdites ");
                     alert.show();
                System.out.println("falseeee");
            }
            
            
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        
    }
    
}
