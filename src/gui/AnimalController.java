/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;
import entities.Animal;
import entities.RapportMedical;
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
    private Button modifierButton;

    /**
     * Initializes the controller class.
     */
    Animal pe = new Animal();
       RapportMedicalService aa = new RapportMedicalService();
    int ids;
    public void setAnimal(Animal c) {
    ids = c.getId();
        System.out.println(ids);
    nomAnimal.setText(c.getNom());
    
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
            // Aucun véhicule n'a été trouvé pour ce siège
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Aucun Rapport trouvé");
            alert.setHeaderText(null);
            alert.setContentText("Il n'y a aucun Rapport associé à cet Animal.");
            alert.showAndWait();

        } else {
               try {
                      BorderPane borderPane = new BorderPane();   
                   FXMLLoader loader1 = new FXMLLoader(getClass().getResource("AfficherRapportByAnimal.fxml"));
                   Parent root2 = loader1.load();
                   gui.AfficherRapportByAnimalController controller = loader1.getController();
            controller.dynamicinitialize(pe.getId());
          
                   HBox hbox = new HBox( new Pane(), root2);
            hbox.setSpacing(20);

            borderPane.setRight(hbox);

            

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
                      BorderPane borderPane = new BorderPane();   
                   FXMLLoader loader1 = new FXMLLoader(getClass().getResource("AjouterRapport.fxml"));
                   Parent root2 = loader1.load();
                    AjouterRapportController controller = loader1.getController();
            controller.dynamicinitialize(pe.getId());
          
                   HBox hbox = new HBox( new Pane(), root2);
            hbox.setSpacing(20);

            borderPane.setRight(hbox);

            

            borderPane.setPadding(new Insets(10, 10, 30, 10));
            afficherRm.getScene().setRoot(borderPane); 
 } catch (IOException ex) {
            System.out.println("error" + ex.getMessage());
        }
    }
    }

    
    
}
