/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;


import entities.Animal;
import entities.RendezVous;
import entities.User;
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
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import services.RapportMedicalService;
import services.RendezVousService;
import services.UserService;

/**
 * FXML Controller class
 *
 * @author heha
 */
public class VeterinaireController implements Initializable {

    @FXML
    private Button calendrier;
    @FXML
    private Button afficherRdv;
    @FXML
    private Label nomVeto;
    @FXML
    private Label prenomVeto;
    @FXML
    private Label paysVeto;
    @FXML
    private Label gouvernoratVeto;
    @FXML
    private Label villeVeto;
    @FXML
    private Label rueVeto;
    @FXML
    private Button ajouterRdv;

    /**
     * Initializes the controller class.
     */
     User pe = new User();
       RendezVousService aa = new RendezVousService();
    int ids;
    int iddd = UserSession.getInstance().getId();
    UserService ps = new UserService();
    public void setVeto(User c) {
        
        if ( !(UserSession.getInstance().getId() == c.getId())){calendrier.setVisible(false);}
        if ( UserSession.getInstance().getId() == c.getId()){ajouterRdv.setVisible(false);}
    ids = c.getId();
        System.out.println(ids);
    nomVeto.setText(c.getNom());
    prenomVeto.setText(c.getPrenom());
    paysVeto.setText(c.getPays());
    gouvernoratVeto.setText(c.getGouvernorat());
    villeVeto.setText(c.getVille());
    rueVeto.setText(c.getRue());
    
    pe.setId(c.getId());
    pe.setNom(c.getNom());
    pe.setPrenom(c.getPrenom());
    pe.setPays(c.getPays());
    pe.setGouvernorat(c.getGouvernorat());
    pe.setVille(c.getVille());
    pe.setRue(c.getRue());
    
    
}
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void afficherCalendrier(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Sidebar_veterinaire.fxml"));
        Parent root1 = loader.load();
        BorderPane borderPane = new BorderPane();
        FXMLLoader loader1 = new FXMLLoader(getClass().getResource("CalendrierE.fxml"));
        Parent root2 = loader1.load();
        gui.CalendrierEController controller= loader1.getController();
        controller.setData(pe);
        HBox hbox = new HBox(root1, new Pane(), root2);
        hbox.setSpacing(20);

        borderPane.setRight(hbox);

        borderPane.setLeft(root1);

        borderPane.setPadding(new Insets(10, 10, 30, 10));
        nomVeto.getScene().setRoot(borderPane);
    }

    @FXML
    private void afficherRdvVeto(ActionEvent event) throws SQLException, IOException  {
            // Récupération de l'ID de l'animal à partir de l'objet "pe" (supposons que "pe" soit un objet Animal)
        
        int vetoId = pe.getId();

        //System.err.println("1");
        System.out.println("hedha l id taa l veto doub ma tenzel aala consulter rdv " +vetoId);

        List<RendezVous> rdv = aa.recupererRdvByVeto(vetoId);
        System.out.println(rdv);

        if (rdv.isEmpty()) {
            // Aucun véhicule n'a été trouvé pour ce siège
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Aucun Rendez-Vous trouvé");
            alert.setHeaderText(null);
            alert.setContentText("Il n'y a aucun Rendez Vous associé à cet Animal.");
            alert.showAndWait();

        } else {
            
             User user = ps.veterinaireRole(iddd);
                                if (user.getRole().equals("[\"ROLE_VETERINAIRE\"]")){

                                       try {

                                           FXMLLoader loader = new FXMLLoader(getClass().getResource("Sidebar_veterinaire.fxml"));
                                     Parent root1 = loader.load();
                                              BorderPane borderPane = new BorderPane();  

                                           FXMLLoader loader1 = new FXMLLoader(getClass().getResource("AfficherRdvByVeto.fxml"));

                                           Parent root2 = loader1.load();

                                           gui.AfficherRdvByVetoController controller = loader1.getController();

                                            controller.dynamicinitialize(pe.getId());


                                           HBox hbox = new HBox(root1, new Pane(), root2);
                                    hbox.setSpacing(20);

                                    borderPane.setRight(hbox);
                                     borderPane.setLeft(root1);


                                    borderPane.setPadding(new Insets(10, 10, 30, 10));
                                    afficherRdv.getScene().setRoot(borderPane); 
                                    } catch (IOException ex) {
                                               System.out.println("+ zaabbb" );
                                           }
                                }
                    else if (user.getRole().equals("[\"ROLE_PROPRIETAIRE\"]")){
                         try {

                               FXMLLoader loader = new FXMLLoader(getClass().getResource("Sidebar_proprietaire.fxml"));
                         Parent root1 = loader.load();
                                  BorderPane borderPane = new BorderPane();  

                               FXMLLoader loader1 = new FXMLLoader(getClass().getResource("AfficherRdvByVeto.fxml"));

                               Parent root2 = loader1.load();

                               gui.AfficherRdvByVetoController controller = loader1.getController();

                                controller.dynamicinitialize(pe.getId());


                               HBox hbox = new HBox(root1, new Pane(), root2);
                        hbox.setSpacing(20);

                        borderPane.setRight(hbox);
                         borderPane.setLeft(root1);


                        borderPane.setPadding(new Insets(10, 10, 30, 10));
                        afficherRdv.getScene().setRoot(borderPane); 
                        } catch (IOException ex) {
                                   System.out.println("+ zaabbb" );
                               }
                    }
        }
               
               
               
    }
    

    

    @FXML
    private void ajouterRdvVeto(ActionEvent event) throws SQLException, IOException  {
               int animalid = pe.getId();

        //System.err.println("1");
       
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Sidebar_proprietaire.fxml"));
        Parent root1 = loader.load();
                      BorderPane borderPane = new BorderPane();   
                   FXMLLoader loader1 = new FXMLLoader(getClass().getResource("ReserverRdv.fxml"));
                   Parent root2 = loader1.load();
                    gui.ReserverRdvController controller = loader1.getController();
            controller.dynamicinitialize(pe.getId());
          
                   HBox hbox = new HBox(root1, new Pane(), root2);
            hbox.setSpacing(20);

            borderPane.setRight(hbox);
            borderPane.setLeft(root1);
            

            borderPane.setPadding(new Insets(10, 10, 30, 10));
            afficherRdv.getScene().setRoot(borderPane); 
 } catch (IOException ex) {
            System.out.println("error" + ex.getMessage());
        }   

    }
    
}
