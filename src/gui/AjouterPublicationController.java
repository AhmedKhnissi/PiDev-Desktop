/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Publication;
import entities.User;
import entities.UserSession;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import services.PublicationService;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.WritableImage;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;


/**
 * FXML Controller class
 *
 * @author Khalil
 */
public class AjouterPublicationController implements Initializable {
    
    @FXML
    private TextField tfAuteur;
    @FXML
    private TextField tfTitre; 
    @FXML
    private TextArea taContenu;
    @FXML
    private TextField tfImage; 
    @FXML
    private Button ajouterbtn;  
    
    PublicationService ps = new PublicationService();
    @FXML
    private Button chooseImageButton; 
    private String i; 
    byte [] post_image = null;
    private String imagePath; 
    UserSession session = UserSession.getInstance(); 
    private int idloguser = session.getId(); 
    private File file2;
   
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
     @FXML
    private void ajouterPublication(ActionEvent event) { 
         LocalDate currentDate = LocalDate.now(); 
         Date sqlDate = Date.valueOf(currentDate);
         User user = new User(); 
         user.setId(idloguser);
           if (tfTitre.getText().isEmpty() || taContenu.getText().isEmpty() || tfAuteur.getText().isEmpty()) {
            Alert al = new Alert(Alert.AlertType.WARNING);
            al.setTitle("Controle de saisie");
            al.setContentText("Veuillez remplir tout les champs !");
            al.show();
    } else{
               
             try {
                 
                 try {
                     String auteur = tfAuteur.getText();
                     String titre = tfTitre.getText();
                     String contenu = taContenu.getText();
                     String image = tfImage.getText();
                     Publication p = new Publication(auteur, titre, contenu, image);
                     PublicationService ps = new PublicationService();
                     p.setDatepub(sqlDate);
                     p.setLikes(0);
                     p.setDislike(0);
                     p.setNbsignal(0);
                     p.setUser(user);
                     
                     ps.ajouter(p);
                     
                     
                 } catch (SQLException ex) {
                     Logger.getLogger(AjouterPublicationController.class.getName()).log(Level.SEVERE, null, ex);
                     System.out.println(ex.getMessage());
                 }
                 Alert al = new Alert(Alert.AlertType.INFORMATION);
                 al.setTitle("publication ajouté");
                 al.setContentText("La publication est ajoutée avec Succès !!");
                 al.show();
                 Stage nouveauStage;
                 Parent root = FXMLLoader.load(getClass().getResource("AfficherPublication.fxml"));
                 nouveauStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                 Scene scene = new Scene(root);
                 nouveauStage.setScene(scene);
                 
                 
                 
             } catch (IOException ex) {
                 Logger.getLogger(AjouterPublicationController.class.getName()).log(Level.SEVERE, null, ex);
             }
            
            
    
} 

    } 

    private void chooseImage(ActionEvent event) { 
         FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Image");
        fileChooser.getExtensionFilters().addAll(
            new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif")
        );
        File selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile != null) {
            tfImage.setText(selectedFile.getAbsolutePath());
        }
    }
@FXML   
    private File uploadim(ActionEvent event) throws IOException {
        Publication p = new Publication();
        Path to1 = null;
        String m = null;
        String path = "C:\\xampp\\htdocs\\img";
        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "JPG & PNG Images", "jpg", "jpeg", "PNG");
        chooser.setFileFilter(filter);
        int returnVal = chooser.showOpenDialog(null);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            m = chooser.getSelectedFile().getAbsolutePath();

            file2 = chooser.getSelectedFile();
            String fileName = file2.getName();

            if (chooser.getSelectedFile() != null) {

                try {
                    Path from = Paths.get(chooser.getSelectedFile().toURI());
                    to1 = Paths.get(path + "\\" + fileName);
                    //to2 =Paths.get("src\\"+path+"\\"+file.getName()+".png");

                    CopyOption[] options = new CopyOption[]{
                        StandardCopyOption.REPLACE_EXISTING,
                        StandardCopyOption.COPY_ATTRIBUTES
                    };
                    Files.copy(from, to1, options);
                    System.out.println("added");
                    System.out.println(file2);

                } catch (IOException ex) {
                    System.out.println();
                }
            }

        }
        tfImage.setText(String.valueOf(file2));
        p.setImage(String.valueOf(file2));
        
        return file2;
     
    }
  
    
    
}
