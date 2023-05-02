/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package gui;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import entities.User;
import entities.UserSession;
import services.UserService;

/**
 * FXML Controller class
 *
 * @author user
 */
public class Modifier_magasinController implements Initializable {

    @FXML
    private TextField nom;
    @FXML
    private TextField email;
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
    
    private Button modifier;
    UserService userservice=new UserService();

    
         private static final Pattern EMAIL_REGEX =
            Pattern.compile("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$");
   private static final Pattern NAME_REGEX =
            Pattern.compile("^[A-Za-zÀ-ÖØ-öø-ÿ]+([\\s-][A-Za-zÀ-ÖØ-öø-ÿ]+)*$");
   private static final Pattern NUMBER_REGEX =
            Pattern.compile("^\\d{8}$");
   private static final Pattern PASSWORD_REGEX =
            Pattern.compile("^(?=.*\\d).{6}$");
   
   
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
    private PasswordField password;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        UserSession session=UserSession.getInstance();
        email.setText(session.getEmail());
        nom.setText(session.getNom());
        tel.setText(session.getTel());
        pays.setText(session.getPays());
        gouvernorat.setText(session.getGouvernorat());
        ville.setText(session.getVille());
        rue.setText(session.getRue());
        session.setPassword(session.getPassword());

        // TODO
    }    

    @FXML
    private void modifier_user(ActionEvent event) throws SQLException {
                        if(nom.getText().isEmpty()  || email.getText().isEmpty()  ||tel.getText().isEmpty() ||pays.getText().isEmpty() ||gouvernorat.getText().isEmpty() || rue.getText().isEmpty() || ville.getText().isEmpty() ){
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
    
      }
        else if( NameIsValid(nom.getText())== false  || NameIsValid(pays.getText())== false || NameIsValid(gouvernorat.getText())== false ||NameIsValid(ville.getText())== false || NameIsValid(rue.getText())== false  ){
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

    }else{
    //else if(PasswordisValid(password.getText())== false){
     //Alert alert = new Alert(Alert.AlertType.WARNING);
       // alert.setTitle("Mot de passe invalide");
        //alert.setHeaderText(null);
        //alert.setContentText("Le mot de passe doit contenir au moins un chiffre et avoir une longueur de 6 caractères.");
        //alert.showAndWait();
    //}
        UserSession session=UserSession.getInstance();
        String email_ancien=session.getEmail();
        User u=new User();
        u.setNom(nom.getText());
        u.setEmail(email.getText());
        u.setTel(tel.getText());
        u.setPays(pays.getText());
        u.setGouvernorat(gouvernorat.getText());
        u.setVille(ville.getText());
        u.setRue(rue.getText());
        u.setPassword(password.getText());

        session.setEmail(u.getEmail());
        session.setNom(u.getNom());
        session.setPrenom(u.getPrenom());
        session.setTel(u.getTel());
        session.setPays(u.getPays());
        session.setGouvernorat(u.getGouvernorat());
        session.setVille(u.getVille());
        session.setRue(u.getRue());
        session.setPassword(u.getPassword());

        
        userservice.modifier_vet_et_prop(u,email_ancien);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Modifier");
        alert.setHeaderText(null);
        alert.setContentText("Information Utilisateurs a été modifié avec succès");
        alert.showAndWait();
    }
    }
    
}
