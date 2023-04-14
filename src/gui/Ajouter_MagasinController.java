/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package myvet_pidev;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
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
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import myvet.entities.User;
import myvet.services.UserService;
import static myvet_pidev.Ajouter_VeterinaireController.EmailIsValid;
import static myvet_pidev.Ajouter_VeterinaireController.NameIsValid;
import static myvet_pidev.Ajouter_VeterinaireController.PasswordisValid;
import static myvet_pidev.Ajouter_VeterinaireController.TelIsValid;

/**
 * FXML Controller class
 *
 * @author user
 */
public class Ajouter_MagasinController implements Initializable {

    @FXML
    private TextField nom;
    @FXML
    private TextField email;
    @FXML
    private PasswordField password;
    @FXML
    private TextField tel;
    @FXML
    private TextField pays;
    @FXML
    private TextField gouvernorat;
    @FXML
    private TextField ville;
    @FXML
    private TextField rue;
    @FXML
    private Button ajouter;
    @FXML
    private ImageView permis;
    
       private static final Pattern EMAIL_REGEX =
            Pattern.compile("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$");
   private static final Pattern NAME_REGEX =
            Pattern.compile("^[A-Za-zÀ-ÖØ-öø-ÿ]+([\\s-][A-Za-zÀ-ÖØ-öø-ÿ]+)*$");
   private static final Pattern NUMBER_REGEX =
            Pattern.compile("^\\d{8}$");
   private static final Pattern PASSWORD_REGEX =
            Pattern.compile("^(?=.*\\d).{6}$");

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
   public static boolean EmailIsValid(String email) {
        Matcher matcher = EMAIL_REGEX.matcher(email);
        return matcher.matches();
        
    }
      public static boolean NameIsValid(String name) {
        Matcher matcher = NAME_REGEX.matcher(name);
        return matcher.matches();
    }
      public static boolean TelIsValid(String number) {
        Matcher matcher = NUMBER_REGEX.matcher(number);
        return matcher.matches();
    }
      public static boolean PasswordisValid(String password) {
        Matcher matcher = PASSWORD_REGEX.matcher(password);
        return matcher.matches();
    }
    @FXML
    private void sauvegarder(ActionEvent event) {
             if(nom.getText().isEmpty()  || email.getText().isEmpty() || password.getText().isEmpty() ||tel.getText().isEmpty() ||pays.getText().isEmpty() ||gouvernorat.getText().isEmpty() || rue.getText().isEmpty() || ville.getText().isEmpty() ){
         Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Il faut remplir les champs obligatoires ");
            alert.showAndWait();
        }else if(EmailIsValid(email.getText())==false ){
         Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Adresse e-mail invalide");
        alert.setHeaderText(null);
        alert.setContentText("L'adresse e-mail que vous avez saisie est invalide. Veuillez saisir une adresse e-mail valide.");
        alert.showAndWait();
    
    }else if( NameIsValid(nom.getText())== false || NameIsValid(pays.getText())== false || NameIsValid(gouvernorat.getText())== false ||NameIsValid(ville.getText())== false || NameIsValid(rue.getText())== false  ){
       Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("champ invalide");
        alert.setHeaderText(null);
        alert.setContentText("Le champ que vous avez saisi est invalide. Veuillez saisir un champ valide.");
        alert.showAndWait();

    }else if(TelIsValid(tel.getText())== false){
    
    Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Numéro téléphone invalide");
        alert.setHeaderText(null);
        alert.setContentText("Le numero téléphone que vous avez saisi est invalide. Veuillez saisir un nombre de 8 chiffres.");
        alert.showAndWait();

    }
    else if(PasswordisValid(password.getText())== false){
     Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Mot de passe invalide");
        alert.setHeaderText(null);
        alert.setContentText("Le mot de passe doit contenir au moins un chiffre et avoir une longueur de 6 caractères.");
        alert.showAndWait();
    }
        else{
          User u = new User();
        u.setNom(nom.getText());
        u.setEmail(email.getText());
        u.setPassword(password.getText());
        u.setPays(pays.getText());
        u.setGouvernorat(gouvernorat.getText());
         u.setVille(ville.getText());
        u.setRue(rue.getText());
        u.setTel(tel.getText());
        try {
            UserService us= new UserService();
            us.ajouter_magasin(u);
                  Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Succes");
            alert.setHeaderText(null);
            alert.setContentText(" magasin a été ajouté avec succès ");
            alert.showAndWait();
                       try{
        Stage nouveauStage;
        Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
        nouveauStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        nouveauStage.setScene(scene);
        }catch(IOException ex){
          System.out.println("nooooooooooooooooooooooooooooooooooooooooooonnnnnnnnnnn");
        }
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
      }
    }
    
}
