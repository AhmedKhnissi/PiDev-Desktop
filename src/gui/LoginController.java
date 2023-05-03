/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package gui;

import java.io.IOException;
import java.net.URL;
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
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import entities.CrypterPassword;
import entities.User;
import entities.UserSession;
import services.UserService;

/**
 * FXML Controller class
 *
 * @author user
 */
public class LoginController implements Initializable {

    @FXML
    private TextField mail;
    @FXML
    private PasswordField mot_de_passe;
    @FXML
    private Button login;
    CrypterPassword cps=new CrypterPassword();
    UserService us=new UserService();
  private static final Pattern EMAIL_REGEX =
            Pattern.compile("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$");
  
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

    @FXML
    private void mdp_oublie(MouseEvent event) {
                 try{
        Stage nouveauStage;
        Parent root = FXMLLoader.load(getClass().getResource("forget_password.fxml"));
        nouveauStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        nouveauStage.setScene(scene);
        }catch(IOException ex){
          System.out.println("nooooooooooooooooooooooooooooooooooooooooooonnnnnnnnnnn");
        }
    }

    @FXML
    private void register_veterinaire(MouseEvent event) {
                try{
        Stage nouveauStage;
        Parent root = FXMLLoader.load(getClass().getResource("Ajouter_Veterinaire.fxml"));
        nouveauStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        nouveauStage.setScene(scene);
        }catch(IOException ex){
          System.out.println("nooooooooooooooooooooooooooooooooooooooooooonnnnnnnnnnn");
        }
    }

    @FXML
    private void register_proprietaire(MouseEvent event) {
                   try{
        Stage nouveauStage;
        Parent root = FXMLLoader.load(getClass().getResource("Ajouter_Proprietaire.fxml"));
        nouveauStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        nouveauStage.setScene(scene);
        }catch(IOException ex){
          System.out.println("nooooooooooooooooooooooooooooooooooooooooooonnnnnnnnnnn");
        }
    }

    @FXML
    private void register_magasin(MouseEvent event) {
                      try{
        Stage nouveauStage;
        Parent root = FXMLLoader.load(getClass().getResource("Ajouter_Magasin.fxml"));
        nouveauStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        nouveauStage.setScene(scene);
        }catch(IOException ex){
          System.out.println("nooooooooooooooooooooooooooooooooooooooooooonnnnnnnnnnn");
        }
    }

    @FXML
    private void authenticate(ActionEvent event) throws IOException {
                         System.out.println(cps.CrypterPassword("123456"));

        if(mail.getText().isEmpty() || mot_de_passe.getText().isEmpty()){
        Alert alert = new Alert(Alert.AlertType.WARNING);
               alert.setTitle("erreur");
               alert.setHeaderText(null);
               alert.setContentText(" vous devez saisir vos données !");
               alert.showAndWait();
        
        }else if (!EmailIsValid(mail.getText())){
             Alert alert = new Alert(Alert.AlertType.WARNING);
               alert.setTitle("erreur");
               alert.setHeaderText(null);
               alert.setContentText("  vos données sont invalides !");
               alert.showAndWait();  
        
        }
        else if(us.authenticate(mail.getText(),mot_de_passe.getText()).getId() !=0 && us.authenticate(mail.getText(),mot_de_passe.getText()).getDemande_acces()==0 && us.authenticate(mail.getText(),mot_de_passe.getText()).getBloque()==0){
            
                User user=new User();
                user=us.authenticate(mail.getText(),mot_de_passe.getText());
                 


                if("[\"ROLE_VETERINAIRE\"]".equals(user.getRole())){
                   System.out.println("rrrrrrrrooooooooollleeee   "+user.getRole());

                    UserSession session = UserSession.getInstance();
                    session.setId(user.getId());

                    session.setEmail(user.getEmail());
                    session.setNom(user.getNom());
                    session.setPrenom(user.getPrenom());
                    session.setPays(user.getPays());
                    session.setGouvernorat(user.getGouvernorat());
                    session.setVille(user.getVille());
                    session.setRue(user.getRue());
                    session.setTel(user.getTel());
                    session.setIsLoggedIn(true);
                    session.setBloque(user.getBloque());
                    session.setPermistravail(user.getPermistravail());
                    session.setPassword(user.getPassword());


                   try{
                   Stage nouveauStage;
                   Parent root = FXMLLoader.load(getClass().getResource("Sidebar_veterinaire.fxml"));
                   nouveauStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                   Scene scene = new Scene(root, 1900 , 1000);
                   nouveauStage.setScene(scene);
                   }catch(IOException ex){
                    System.out.println("nooooooooooooooooooooooooooooooooooooooooooonnnnnnnnnnn");
                   }
                  
                }else if("[\"ROLE_PROPRIETAIRE\"]".equals(user.getRole())){
                    UserSession session = UserSession.getInstance();
                    session.setId(user.getId());

                    session.setEmail(user.getEmail());
                    session.setNom(user.getNom());
                    session.setPrenom(user.getPrenom());
                    session.setPays(user.getPays());
                    session.setGouvernorat(user.getGouvernorat());
                    session.setVille(user.getVille());
                    session.setRue(user.getRue());
                    session.setTel(user.getTel());
                    session.setBloque(user.getBloque());
                    session.setPermistravail(user.getPermistravail());
                    session.setPassword(user.getPassword());

                    session.setIsLoggedIn(true);

                   try{
                   Stage nouveauStage;
                   Parent root = FXMLLoader.load(getClass().getResource("Sidebar_proprietaire.fxml"));
                   nouveauStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                   Scene scene = new Scene(root, 1500, 1000);
                   nouveauStage.setScene(scene);
                   }catch(IOException ex){
                    System.out.println("nooooooooooooooooooooooooooooooooooooooooooonnnnnnnnnnn");
                   }
                }else if("[\"ROLE_ADMIN\"]".equals(user.getRole())){
                    
                 UserSession session = UserSession.getInstance();
                    session.setId(user.getId());
                 
                    session.setEmail(user.getEmail());
                    session.setNom(user.getNom());
                    session.setPrenom(user.getPrenom());
                    session.setPays(user.getPays());
                    session.setGouvernorat(user.getGouvernorat());
                    session.setVille(user.getVille());
                    session.setRue(user.getRue());
                    session.setTel(user.getTel());
                    session.setPermistravail(user.getPermistravail());
                    session.setPassword(user.getPassword());

                    session.setIsLoggedIn(true);

                   try{
                   Stage nouveauStage;
                   Parent root = FXMLLoader.load(getClass().getResource("NavbarAdmin.fxml"));
                   nouveauStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                   Scene scene = new Scene(root);
                   nouveauStage.setScene(scene);
                   }catch(IOException ex){
                    System.out.println("nooooooooooooooooooooooooooooooooooooooooooonnnnnnnnnnn");
                   }
                }else if("[\"ROLE_MAGASIN\"]".equals(user.getRole())){
                 UserSession session = UserSession.getInstance();
                    session.setId(user.getId());
                 
                    session.setEmail(user.getEmail());
                    session.setNom(user.getNom());
                    session.setPrenom(user.getPrenom());
                    session.setPays(user.getPays());
                    session.setGouvernorat(user.getGouvernorat());
                    session.setVille(user.getVille());
                    session.setRue(user.getRue());
                    session.setTel(user.getTel());
                    session.setBloque(user.getBloque());
                    session.setPermistravail(user.getPermistravail());
                    session.setPassword(user.getPassword());

                    session.setIsLoggedIn(true);

                   try{
                   Stage nouveauStage;
                   Parent root = FXMLLoader.load(getClass().getResource("Sidebar_magasin.fxml"));
                   nouveauStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                   Scene scene = new Scene(root);
                   nouveauStage.setScene(scene);
                   }catch(IOException ex){
                    System.out.println("nooooooooooooooooooooooooooooooooooooooooooonnnnnnnnnnn");
                   }
                }
        

            } else if(us.authenticate(mail.getText(),mot_de_passe.getText()).getDemande_acces()==1){
               Alert alert = new Alert(Alert.AlertType.WARNING);
               alert.setTitle("erreur");
               alert.setHeaderText(null);
               alert.setContentText(" votre compte est pas encore activer !");
               alert.showAndWait();
             } else if(us.authenticate(mail.getText(),mot_de_passe.getText()).getBloque()==1){
               Alert alert = new Alert(Alert.AlertType.WARNING);
               alert.setTitle("erreur");
               alert.setHeaderText(null);
               alert.setContentText(" votre compte est bloqué !");
               alert.showAndWait();
             }/*else {
                   Alert alert = new Alert(Alert.AlertType.WARNING);
                   alert.setTitle("erreur");
                   alert.setHeaderText(null);
                   alert.setContentText(" verifiez votre adresse e-mail ou votre mot de passe !");
                   alert.showAndWait();
               } */
             
    }
    
}
