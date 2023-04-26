/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entities.RapportMedical;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import services.RapportMedicalService;

/**
 * FXML Controller class
 *
 * @author heha
 */
public class ModifierRapportController implements Initializable {

    @FXML
    private TextField descriptionid;
    @FXML
    private Button modifierbtn;
    int idsiege =0;
    RapportMedicalService ps = new RapportMedicalService();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    int animalId;
    public int AnimalId (int animalId){
    return this.animalId = animalId
;    }
    
    public void setData(RapportMedical c) {

        descriptionid.setText(c.getDescription());
        animalId=c.getAnimal_id();
        idsiege=c.getId();
    
     }

    @FXML
    private void valider(ActionEvent event) {
        try{
        RapportMedical s = new RapportMedical();
        s.setAnimal_id(animalId);
            System.out.println("hedha id l'animal"+ animalId);
        s.setId(idsiege);
            System.out.println("hedha id el rapport "+ idsiege);
        String nomSiege = descriptionid.getText();

            s.setDescription(nomSiege);
            System.out.println("hedhi description" + nomSiege);
     
        
        System.out.println(s);
        ps.modifier(s);
        System.out.println(s);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Modification réussi");
            alert.setHeaderText("Modification de Rapport réussi");
            alert.setContentText("Le Rapport a été Modifier avec succès !");
            alert.showAndWait();
        System.out.println("Rapport modifié avec succes");
        } catch (SQLException ex) {
            System.out.println("error" + ex.getMessage());
        }
        try {
            
               BorderPane borderPane = new BorderPane();
            FXMLLoader loader1 = new FXMLLoader(getClass().getResource("AfficherListeAnimal.fxml"));
            Parent root2 = loader1.load();
           // AjouterSiegeController controller = loader.getController();
            HBox hbox = new HBox(new Pane(), root2);
            hbox.setSpacing(20);

            borderPane.setRight(hbox);

            

            borderPane.setPadding(new Insets(10, 10, 30, 10));
            descriptionid.getScene().setRoot(borderPane);
            
//            
//            
//            
//            
//            FXMLLoader loader = new FXMLLoader(getClass().getResource("afficherSiege.fxml"));
//            Parent root = loader.load();
//            AfficherSiegeController controller = loader.getController();        
//            nomsiegetf.getScene().setRoot(root);           
        } catch (IOException ex) {
            System.out.println("error" + ex.getMessage());
        }
    }
    
}
