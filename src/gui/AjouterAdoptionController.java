/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;


import entities.Adopt;
import entities.User;
import entities.UserSession;
import java.awt.AWTException;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import services.AdoptService;
import services.UserService;

/**
 * FXML Controller class
 *
 * @author Soulaima_matmati
 */
public class AjouterAdoptionController implements Initializable {
    private String path;
    @FXML
    private Button retourbtn;
    @FXML
    private ComboBox<String> genreid;
    @FXML
    private TextField nomid;
    @FXML
    private Button ajouterbtn;
    @FXML
    private TextField ageid;
    @FXML
    private TextField informationsid;
    @FXML
    private CheckBox sterilisation;
    @FXML
    private CheckBox vaccination;
    @FXML
    private ImageView image;
    File selectedFile;
    

    /**
     * Initializes the controller class.
     */
     AdoptService ps = new AdoptService();
     Adopt re = new Adopt();
      UserService us=new UserService();
    int idd = UserSession.getInstance().getId();
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
      
       
        ObservableList<String> listDecision = FXCollections.observableArrayList("Male","Female");
        genreid.setItems(listDecision);
    }    

    @FXML
    private void retour(ActionEvent event) throws IOException, SQLException {
        
           
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
            nomid.getScene().setRoot(borderPane);
     }
     else if (u.getRole().equals("[\"ROLE_PROPRIETAIRE\"]")){
     FXMLLoader loader = new FXMLLoader(getClass().getResource("Sidebar_proprietaire.fxml"));
            Parent root1 = loader.load();
            BorderPane borderPane = new BorderPane();
               
            FXMLLoader loader1 = new FXMLLoader(getClass().getResource("AfficherListeAdoption.fxml"));
            Parent root2 = loader1.load();
            HBox hbox = new HBox(root1,new Pane(), root2);
            hbox.setSpacing(20);

            borderPane.setRight(hbox);
            borderPane.setLeft(root1);
            
            borderPane.setPadding(new Insets(10, 10, 30, 10));
            nomid.getScene().setRoot(borderPane);
     }

    } catch (IOException ex) {
        System.out.print("err");
    }
        
       
        
        
    }

    @FXML
    private void ajouter(ActionEvent event) {
         try {
              Adopt s = new Adopt();
              
              String nom = nomid.getText();
            if(nom == null || nom.trim().isEmpty()){
                Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur de saisie");
            alert.setHeaderText("la case 'Nom' est vide ! ");
            alert.setContentText("Veuillez Entrer Un Nom Valide. ! ");
            alert.showAndWait();
            return;
        }
            String gender = genreid.getValue();
            if(gender == null || gender.trim().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur de saisie");
            alert.setHeaderText("la case 'genre' est vide ! ");
            alert.setContentText("Veuillez Entrer Un genre Valide. ! ");
            alert.showAndWait();
            return;
        }
            
            
              int age = Integer.parseInt(ageid.getText());
              if (ageid.getText().trim().length() == 2) {
            int nbChar = 0;
            for (int i = 1; i < ageid.getText().trim().length(); i++) {
                char ch = ageid.getText().charAt(i);
                if (Character.isLetter(ch)) {
                    nbChar++;
                }
            }
             Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setTitle("Erreur de saisie");
    alert.setHeaderText("La case 'age' est vide !");
    alert.setContentText("Age doit avoir 2 chiffres et ne doit pas contenir des caracteres \n");
    alert.showAndWait();
    return; 
              
              }
        
          
     
              
              String informations = informationsid.getText();
              if(informations == null || informations.trim().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur de saisie");
            alert.setHeaderText("la case 'informations' est vide ! ");
            alert.setContentText("Veuillez Entrer des informations Valides! ");
            alert.showAndWait();
            return;
        }
              
              
              
              
            s.setNom(nom);
            s.setGender(gender);
            s.setAge(age);
            s.setSterelisation(sterilisation.isSelected());
            s.setVaccination(vaccination.isSelected());
            
            s.setInformations(informations);
            
              
            ps.ajouter(s);
              
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Ajout réussi");
            alert.setHeaderText("Ajout de Adoption réussi");
            alert.setContentText("Votre Adoption a été ajouté avec succès !");
            alert.showAndWait();
            if (SystemTray.isSupported()) {
             String imagePath = "/images/full_up.png";
            URL imageURL = getClass().getResource(imagePath);
            Image img1 = new Image(imageURL.toString()); 
                BufferedImage awtImage = SwingFXUtils.fromFXImage(img1, null);
                TrayIcon trayIcon = new TrayIcon(awtImage, "Notification Title");
        SystemTray tray = SystemTray.getSystemTray();
        try {
            tray.add(trayIcon);
            trayIcon.displayMessage("Votre Adoption a bien été ajouté", "L'admin va Bientot Traitée votre Adoption", TrayIcon.MessageType.INFO);
        } catch (AWTException e) {
            System.err.println("Could not add TrayIcon to SystemTray");}
        
       } 
            
            
            else {
        System.err.println("SystemTray is not supported");
            System.out.println("Adoption ajouter avec succes");
        } 
            
         }
            catch (SQLException ex) {
            System.out.println("error" + ex.getMessage());
        }
         
    }
     @FXML
    private void changer(ActionEvent event) throws SQLException {
     if (sterilisation.isSelected()){
        re.setSterelisation(true);
        System.out.println("re"+re);
        ps.modifierx(re);      
     }
    }

    @FXML
    private void uploadimage(MouseEvent event) throws MalformedURLException {
        FileChooser fc = new FileChooser();
        fc.setInitialDirectory(new File(System.getProperty("user.home") + "\\Desktop"));
        fc.setTitle("Veuillez choisir l'image");
        fc.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image", "*.jpg", "*.png"),
                new FileChooser.ExtensionFilter("PNG", "*.png"),
                new FileChooser.ExtensionFilter("JPG", "*.jpg")
        );
        selectedFile = fc.showOpenDialog(null);

        if (selectedFile != null) {

            path = selectedFile.getName();
//                path = selectedFile.toURI().toURL().toExternalForm();
            image.setImage(new Image(selectedFile.toURI().toURL().toString()));
            image.setFitHeight(150);
            image.setFitWidth(250);
           

        }
    }
    
            public void handle(DragEvent event) {
                Dragboard db = event.getDragboard();
                boolean success = false;
                if (db.hasFiles()) {
                    success = true;
                    path = null;
                    for (File file : db.getFiles()) {
                        path = file.getName();
                        selectedFile = new File(file.getAbsolutePath());
                        System.out.println("Drag and drop file done and path=" + file.getAbsolutePath());//file.getAbsolutePath()="C:\Users\X\Desktop\ScreenShot.6.png"
                        image.setImage(new Image("file:" + file.getAbsolutePath()));
//                        screenshotView.setFitHeight(150);
//                        screenshotView.setFitWidth(250);
                        
                    }
                }
                event.setDropCompleted(success);
                event.consume();
            }
    
         /*FileChooser fileChooser = new FileChooser();
    fileChooser.setTitle("Choisir une image");
    fileChooser.getExtensionFilters().addAll(
            new FileChooser.ExtensionFilter("Images", "*.png", "*.jpg", "*.gif"));
    File selectedFile = fileChooser.showOpenDialog(null);
    if (selectedFile != null) {
        Image image = new Image(selectedFile.toURI().toString());
        image.setImage(image);
    }*/
}
