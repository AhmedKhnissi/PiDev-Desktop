/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.User;
import entities.UserSession;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import services.UserService;

/**
 * FXML Controller class
 *
 * @author heha
 */
public class Sidemenu_veterinaireController implements Initializable {

    @FXML
    private Button rdveto;
    @FXML
    private Button rapport;
    @FXML
    private Button pubsbtn;
    @FXML
    private Button blogbtn;
    @FXML
    private Button adopt;
    UserService us=new UserService();
    int idd = UserSession.getInstance().getId();
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void rendezvousveto(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Sidebar_veterinaire.fxml"));
        Parent root1 = loader.load();
        BorderPane borderPane = new BorderPane();
        FXMLLoader loader1 = new FXMLLoader(getClass().getResource("AfficherListeVeto.fxml"));
        Parent root2 = loader1.load();
        HBox hbox = new HBox(root1, new Pane(), root2);
        hbox.setSpacing(20);

        borderPane.setRight(hbox);

        borderPane.setLeft(root1);

        borderPane.setPadding(new Insets(10, 10, 30, 10));
        rdveto.getScene().setRoot(borderPane);
        
        
       }
        
        
    

    @FXML
    private void rapportMedical(ActionEvent event) throws IOException {
        try{
          FXMLLoader loader = new FXMLLoader(getClass().getResource("Sidebar_veterinaire.fxml"));
        Parent root1 = loader.load();
        BorderPane borderPane = new BorderPane();
        FXMLLoader loader1 = new FXMLLoader(getClass().getResource("AfficherListeAnimal.fxml"));
        Parent root2 = loader1.load();
        HBox hbox = new HBox(root1, new Pane(), root2);
        hbox.setSpacing(20);

        borderPane.setRight(hbox);

        borderPane.setLeft(root1);

        borderPane.setPadding(new Insets(10, 10, 30, 10));
        rdveto.getScene().setRoot(borderPane);}
        catch (IOException ex) {System.out.println("chay"); }
    }

    @FXML
    private void profile(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("ModifierUser.fxml"));
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
        } catch (IOException ex) {
            System.out.print(ex);
        }
    }

    @FXML
    private void logout(ActionEvent event) {
         UserSession session=UserSession.getInstance();
        session.setId(null);
        session.setNom(null);
        session.setPrenom(null);
        session.setPassword(null);
        session.setEmail(null);
        session.setPays(null);
        session.setGouvernorat(null);
        session.setVille(null);
        session.setRue(null);
        session.setTel(null);
        session.setIsLoggedIn(false);

                         try{
        Stage nouveauStage;
        Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
        nouveauStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        nouveauStage.setScene(scene);
        }catch(IOException ex){
          System.out.println("nooooooooooooooooooooooooooooooooooooooooooonnnnnnnnnnn");
        }
    }

    @FXML
    private void mes_pubs(ActionEvent event) {
        try {
        // Load the new FXML file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Sidebar_veterinaire.fxml"));
            Parent root1 = loader.load();
            BorderPane borderPane = new BorderPane();
               
            FXMLLoader loader1 = new FXMLLoader(getClass().getResource("AfficherPublication.fxml"));
            Parent root2 = loader1.load();
            HBox hbox = new HBox(root1,new Pane(), root2);
            hbox.setSpacing(20);

            borderPane.setRight(hbox);
            borderPane.setLeft(root1);
            
            borderPane.setPadding(new Insets(10, 10, 30, 10));
            pubsbtn.getScene().setRoot(borderPane);
      

    } catch (IOException ex) {
        System.out.print("err");
    }
    }

    @FXML
    private void blogv(ActionEvent event) {
         try {
        // Load the new FXML file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Sidebar_veterinaire.fxml"));
            Parent root1 = loader.load();
            BorderPane borderPane = new BorderPane();
               
            FXMLLoader loader1 = new FXMLLoader(getClass().getResource("AffichageUser.fxml"));
            Parent root2 = loader1.load();
            HBox hbox = new HBox(root1,new Pane(), root2);
            hbox.setSpacing(20);

            borderPane.setRight(hbox);
            borderPane.setLeft(root1);
            
            borderPane.setPadding(new Insets(10, 10, 30, 10));
            blogbtn.getScene().setRoot(borderPane);
      

    } catch (IOException ex) {
        System.out.print("err");
    }
    }
    
    
    @FXML
    private void afficherAdopt(ActionEvent event) throws SQLException {
        User u = us.veterinaireRole(idd);
      System.out.println("hedha howa role : "+u.getRole());
     try {if (u.getRole().equals("[\"ROLE_VETERINAIRE\"]")){
        // Load the new FXML file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Sidebar_veterinaire.fxml"));
            Parent root1 = loader.load();
            BorderPane borderPane = new BorderPane();
               
            FXMLLoader loader1 = new FXMLLoader(getClass().getResource("AfficherListeAdoption.fxml"));
            Parent root2 = loader1.load();
            HBox hbox = new HBox(root1,new Pane(), root2);
            hbox.setSpacing(20);

            borderPane.setRight(hbox);
            borderPane.setLeft(root1);
            
            borderPane.setPadding(new Insets(10, 10, 30, 10));
            adopt.getScene().setRoot(borderPane);
     }
     else if (u.getRole().equals("[\"ROLE_PROPRIETAIRE\"]")){
     FXMLLoader loader = new FXMLLoader(getClass().getResource("Sidebar_veterinaire.fxml"));
            Parent root1 = loader.load();
            BorderPane borderPane = new BorderPane();
               
            FXMLLoader loader1 = new FXMLLoader(getClass().getResource("AfficherListeAdoption.fxml"));
            Parent root2 = loader1.load();
            HBox hbox = new HBox(root1,new Pane(), root2);
            hbox.setSpacing(20);

            borderPane.setRight(hbox);
            borderPane.setLeft(root1);
            
            borderPane.setPadding(new Insets(10, 10, 30, 10));
            adopt.getScene().setRoot(borderPane);
     }

    } catch (IOException ex) {
        System.out.print("err");
    }}
}
