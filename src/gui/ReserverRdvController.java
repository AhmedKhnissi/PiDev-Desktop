/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;
import entities.user;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import entities.RendezVous;
import java.awt.AWTException;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import services.RendezVousService;
import services.UserService;

/**
 * FXML Controller class
 *
 * @author heha
 */
public class ReserverRdvController implements Initializable {

    private ComboBox<String> decisiontf;
    private TextField dateid;
    private ComboBox<String> heureid;
    
    private TextField nomanimalid;
    @FXML
    private Button ajouterbtn;
    int id;
    RendezVousService ps = new RendezVousService();
    @FXML
    private TextField subjectid;
    @FXML
    private TextField nomid;
    @FXML
    private AnchorPane messageid;
    @FXML
    private TextField emaimid;
    
    
    
     
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ObservableList<String> listCategorie = FXCollections.observableArrayList("Chien","Chat","Lapin","Oiseau","Singe","Agneau","Vache");
        ObservableList<String> listHeure = FXCollections.observableArrayList("09:00","10:00","11:00","12:00","14:00","15:00","16:00","17:00");
        decisiontf.setItems(listCategorie);
        heureid.setItems(listHeure);
    }    
     public void dynamicinitialize(int id) {
         this.id = id;
     }

    @FXML
    private void ajouter(ActionEvent event) throws SQLException {
        try {
              RendezVous s = new RendezVous();
              
              
              String date = dateid.getText();
                if(date == null || date.trim().isEmpty()){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Erreur de saisie");
                    alert.setHeaderText("Le champ 'date' est vide !");
                    alert.setContentText("Veuillez entrer une date valide sous la forme JJ/MM/YYYY.");
                    alert.showAndWait();
                    return;
                }
                if(!date.matches("^(0[1-9]|[12][0-9]|3[01])/(0[1-9]|1[0-2])/[0-9]{4}$")){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Erreur de saisie");
                    alert.setHeaderText("Format de date invalide !");
                    alert.setContentText("La date doit être sous la forme JJ/MM/YYYY avec JJ entre 1 et 31, MM entre 1 et 12 et YYYY étant une année à 4 chiffres.");
                    alert.showAndWait();
                    return;
                }

            s.setDate(date);
            
            
            String heure = heureid.getValue();
            if (heure == null || heure.trim().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur de saisie");
                alert.setHeaderText("Le champ 'heure' est vide !");
                alert.setContentText("Veuillez entrer une heure valide sous la forme HH:MM.");
                alert.showAndWait();
                return;
            }

            

            s.setHeure(heure);
            
            String raceanimal = decisiontf.getValue();
            if (raceanimal == null || raceanimal.trim().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur de saisie");
                alert.setHeaderText("Le champ 'Race Animal' est vide !");
                alert.setContentText("Veuillez Choisir la Catégorie de Votre Animal");
                alert.showAndWait();
                return;
            }
            s.setRaeanimal(raceanimal);
            
            String nomanimal = nomanimalid.getText();
            if (nomanimal == null || nomanimal.trim().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur de saisie");
                alert.setHeaderText("Le champ 'Nom Animal' est vide !");
                alert.setContentText("Veuillez Ecrivez le Nom de Votre Animal");
                alert.showAndWait();
                return;
            }
            s.setNomanimal(nomanimal);
            s.setDecision("Pas Prise Encore");
            s.setUser_id(id);
              System.out.println(id);
            ps.ajouter(s);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Ajout réussi");
            alert.setHeaderText("Ajout de Rendez-Vous réussi");
            alert.setContentText("Le Rendez-Vous a été ajouté avec succès !");
            alert.showAndWait();
            System.out.println("Rendez-Vous ajouter avec succes");
        } catch (SQLException ex) {
            System.out.println("error" + ex.getMessage());
        }
        
        if (SystemTray.isSupported()) {
//         String img = "C:\\xampp\\htdocs\\pidev\\Highbrow\\src\\media\\full_down.png";
//            File file = new File(img);
//            Image img1 = new Image(file.toURI().toString());
             String imagePath = "/images/full_up.png";
            URL imageURL = getClass().getResource(imagePath);
            Image img1 = new Image(imageURL.toString()); 
            BufferedImage awtImage = SwingFXUtils.fromFXImage(img1, null);
        TrayIcon trayIcon = new TrayIcon(awtImage, "Notification Title");
        SystemTray tray = SystemTray.getSystemTray();
        try {
            tray.add(trayIcon);
            trayIcon.displayMessage("Votre Rendez-Vous a bien été ajouté", "Le Vétérinaire Va Bientot Traitée Votre Rendez-Vous", TrayIcon.MessageType.INFO);
        } catch (AWTException e) {
            System.err.println("Could not add TrayIcon to SystemTray");}
        
       } else {
        System.err.println("SystemTray is not supported");
      

    }
       
        
    }
    
}
