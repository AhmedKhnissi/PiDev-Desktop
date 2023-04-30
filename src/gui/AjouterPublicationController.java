/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Publication;
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
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.WritableImage;
import javax.imageio.ImageIO;


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
           if (tfTitre.getText().isEmpty() || taContenu.getText().isEmpty() || tfAuteur.getText().isEmpty()) {
            Alert al = new Alert(Alert.AlertType.WARNING);
            al.setTitle("Controle de saisie");
            al.setContentText("Veuillez remplir tout les champs !");
            al.show();
    } else{
               
              String auteur = tfAuteur.getText();
              String titre = tfTitre.getText();
              String contenu = taContenu.getText(); 
              String image = tfImage.getText(); 
            try {
                Publication p = new Publication(auteur, titre, contenu, image); 
                PublicationService ps = new PublicationService();
              
                p.setDatepub(sqlDate);
                p.setLikes(0); 
                p.setDislike(0); 
                p.setNbsignal(0);
                
                ps.ajouter(p);
                Alert al = new Alert(Alert.AlertType.INFORMATION);
                al.setTitle("publication ajouté");
                al.setContentText("La publication est ajoutée avec Succès !!");
                al.show();
                
            } catch (NumberFormatException e) {
                Alert al = new Alert(Alert.AlertType.ERROR);
                al.setTitle("Controle de saisie");
                al.setContentText("Le champ age doit etre numerique !!");
                al.show();
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
    private void uploadim(ActionEvent event) throws IOException {
        Publication p = new Publication();
        FileChooser fc = new FileChooser();
        fc.setTitle("Ajouter une Image");
        fc.getExtensionFilters().addAll(
            new FileChooser.ExtensionFilter("Images", "*.png", "*.jpg", "*.jpeg", "*.gif")
        );
        File f = fc.showOpenDialog(null);
        String DBPath = "C:\\xampp\\htdocs\\img" + f.getName();
        i = f.getName();
        p.setImage(i);
       tfImage.setText(i);
        System.out.println(p.getImage());
        if (f != null) {
            try {
                BufferedImage bufferedImage = ImageIO.read(f);
                WritableImage image = SwingFXUtils.toFXImage(bufferedImage,null);
                ImageIO.write(bufferedImage, "jpg", new File(DBPath));
                FileInputStream fin = new FileInputStream(f);
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                byte[] buf = new byte [1024];
                for (int readNum ;(readNum= fin.read(buf)) != -1 ;) {
                    bos.write(buf,0,readNum);
                    post_image = bos.toByteArray();
                }
            } catch (IOException ex) {
                // Traitement de l'exception
                ex.printStackTrace();
            }
        }      
    }
  
    
    
}
