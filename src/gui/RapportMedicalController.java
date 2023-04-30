/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;



import entities.RapportMedical;
import java.io.IOException;

import java.sql.SQLException;
import java.util.Optional;

import javafx.event.ActionEvent;

import javafx.fxml.FXMLLoader;

import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Alert;

import javafx.scene.control.ButtonType;
import entities.Animal;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import services.RapportMedicalService;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import services.AnimalService;

/**
 * FXML Controller class
 *
 * @author heha
 */
public class RapportMedicalController implements Initializable {

    @FXML
    private AnchorPane rapportLignes;
    @FXML
    private ImageView img;
    @FXML
    private TextField descriptionlabel;
    @FXML
    private Button modifierbutton;
    @FXML
    private Button supprimerButton;

    /**
     * Initializes the controller class.
     */
    int animalId;
    private int idRapport;
RapportMedical loca = new RapportMedical();
    
       
    
       RapportMedical pe = new RapportMedical();
       RapportMedicalService aa = new RapportMedicalService();
       AnimalService vs = new AnimalService();
    
public void setRapport(RapportMedical c) throws SQLException {
    Animal v=new Animal();
    idRapport = c.getId();
    descriptionlabel.setText(c.getDescription());
    v= vs.recupererAnimalByid(c.getAnimal_id());
    pe.setAnimal_id(c.getAnimal_id());
    pe.setId(c.getId());
    pe.setDescription(c.getDescription());
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        try {
        BorderPane borderPane = new BorderPane();
            FXMLLoader loader1 = new FXMLLoader(getClass().getResource("ModifierRapport.fxml"));
            Parent root2 = loader1.load();
              gui.ModifierRapportController controller = loader1.getController();

            controller.AnimalId(pe.getAnimal_id());
        System.out.println("hedha clé etranger rapport "+pe.getAnimal_id());}
        catch (IOException ex) {
            System.err.println("chay");
        }
    }    

    @FXML
    private void afficherRapportDetail(MouseEvent event) throws IOException, SQLException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("DetailRapport.fxml"));
        Parent root1 = loader.load();
       gui.DetailRapportController pdfcontroller = loader.getController();
        pdfcontroller.pdf(pe.getAnimal_id() ,idRapport );
        BorderPane borderPane = new BorderPane();
         
        if (loca.getAnimal_id() != 0) {
            FXMLLoader loader1 = new FXMLLoader(getClass().getResource("DetailRapportProfil.fxml"));
            Parent root2 = loader1.load();
            gui.DetailRapportProfilController drapport = loader1.getController();
            drapport.setpdfDetail(loca.getAnimal_id());

            HBox hbox = new HBox(root1, new Pane(), root2);
            hbox.setSpacing(20);
            
            borderPane.setRight(hbox);
        } else {
            borderPane.setLeft(root1);
        }
        
        
        borderPane.setPadding(new Insets(10, 10, 30, 10));
        Scene scene = new Scene(borderPane);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.initModality(Modality.NONE);
        stage.initOwner(img.getScene().getWindow());
        stage.show();
    }

    @FXML
    private void modifierRapoort(ActionEvent event) {
             try {
             
            System.out.println("modifi rapport !");
                 FXMLLoader loader = new FXMLLoader(getClass().getResource("Sidebar_veterinaire.fxml"));
        Parent root1 = loader.load();
            BorderPane borderPane = new BorderPane();
            FXMLLoader loader1 = new FXMLLoader(getClass().getResource("ModifierRapport.fxml"));
            Parent root2 = loader1.load();
              gui.ModifierRapportController controller = loader1.getController();

            controller.setData(pe);
            
             HBox hbox = new HBox(root1, new Pane(), root2);
            hbox.setSpacing(20);

            borderPane.setRight(hbox);
            borderPane.setLeft(root1);
            

            borderPane.setPadding(new Insets(10, 10, 30, 10));
            rapportLignes.getScene().setRoot(borderPane);
            
          ;
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void supprimerRapport(ActionEvent event) throws IOException {
        

    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    alert.setTitle("Confirmation");
    alert.setHeaderText(null);
    alert.setContentText("Voulez-vous vraiment supprimer ce Rapport ?");

    Optional<ButtonType> result = alert.showAndWait();
    if (result.get() == ButtonType.OK){
        try {
            aa.supprimer(pe);
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
            rapportLignes.getScene().setRoot(borderPane);
          
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }


    }
    
}
