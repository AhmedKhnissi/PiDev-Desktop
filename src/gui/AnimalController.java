/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Animal;
import entities.User;
import entities.RapportMedical;
import entities.UserSession;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import services.RapportMedicalService;
import services.UserService;

/**
 * FXML Controller class
 *
 * @author heha
 */
public class AnimalController implements Initializable {

    @FXML
    private AnchorPane animalPane;
    @FXML
    private Button afficherRm;
    @FXML
    private Label nomAnimal;
    @FXML
    private Button ajouterRm;
    @FXML
    private Label poidsAnimal;
    @FXML
    private Label ageAnimal;
    @FXML
    private Label nomprop;
    @FXML
    private Label prenomprop;
    @FXML
    private Label mailprop;

    /**
     * Initializes the controller class.
     */
    Animal pe = new Animal();
    UserService userx = new UserService();
    
       RapportMedicalService aa = new RapportMedicalService();
    int ids;
    int iddd = UserSession.getInstance().getId();
    
    public void setAnimal(Animal c) throws SQLException {
        User y = userx.veterinaireRole(iddd);
    ids = c.getId();
        System.out.println(c);
    nomAnimal.setText(c.getNom());
    ageAnimal.setText(String.valueOf(c.getAge()));
    poidsAnimal.setText(String.valueOf(c.getPoids()));
    User u = userx.recupererUserByid(iddd);
       nomprop.setText(u.getNom());
    prenomprop.setText(u.getPrenom());
    if (y.getRole().equals("[\"ROLE_PROPRIETAIRE\"]")){ajouterRm.setVisible(false);}
    mailprop.setText(u.getEmail());
    pe.setId(c.getId());
    pe.setNom(c.getNom());
}
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void afficherRmAnimal(ActionEvent event) throws SQLException, IOException {
        // Récupération de l'ID de l'animal à partir de l'objet "pe" (supposons que "pe" soit un objet Animal)
        
        int animalId = pe.getId();

        //System.err.println("1");
        System.out.println(animalId);

        List<RapportMedical> animals = aa.recupererRapportByAnimal(animalId);
        System.out.println(animals);

        if (animals.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Aucun Rapport trouvé");
            alert.setHeaderText(null);
            alert.setContentText("Il n'y a aucun Rapport associé à cet Animal.");
            alert.showAndWait();

        } else {
               try {
                   FXMLLoader loader = new FXMLLoader(getClass().getResource("Sidebar_veterinaire.fxml"));
        Parent root1 = loader.load();
                      BorderPane borderPane = new BorderPane();   
                   FXMLLoader loader1 = new FXMLLoader(getClass().getResource("AfficherRapportByAnimal.fxml"));
                   Parent root2 = loader1.load();
                   gui.AfficherRapportByAnimalController controller = loader1.getController();
            controller.dynamicinitialize(pe.getId());
          
                   HBox hbox = new HBox(root1, new Pane(), root2);
            hbox.setSpacing(20);

            borderPane.setRight(hbox);
            borderPane.setLeft(root1);
            

            borderPane.setPadding(new Insets(10, 10, 30, 10));
            afficherRm.getScene().setRoot(borderPane); 
 } catch (IOException ex) {
            System.out.println("error" + ex.getMessage());
        }
    }
    }

    @FXML
    private void ajouterRmAnimal(ActionEvent event) throws IOException, SQLException{
            int animalid = pe.getId();

        //System.err.println("1");
        
        List<RapportMedical> animals = aa.recupererRapportByAnimal(animalid);
        

        if (!(animals.isEmpty())) {
            // Aucun véhicule n'a été trouvé pour ce siège
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Un Rapport existe déja");
            alert.setHeaderText(null);
            alert.setContentText("Il existe déja un rapport ");
            alert.showAndWait();

        } else {try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Sidebar_veterinaire.fxml"));
        Parent root1 = loader.load();
                      BorderPane borderPane = new BorderPane();   
                   FXMLLoader loader1 = new FXMLLoader(getClass().getResource("AjouterRapport.fxml"));
                   Parent root2 = loader1.load();
                    AjouterRapportController controller = loader1.getController();
            controller.dynamicinitialize(pe.getId());
          
                   HBox hbox = new HBox(root1, new Pane(), root2);
            hbox.setSpacing(20);

            borderPane.setRight(hbox);
            borderPane.setLeft(root1);
            

            borderPane.setPadding(new Insets(10, 10, 30, 10));
            afficherRm.getScene().setRoot(borderPane); 
 } catch (IOException ex) {
            System.out.println("error" + ex.getMessage());
        }
    }
    }

    
}
