/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Produit;
import entities.User;
import entities.UserSession;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.SQLException;
import java.util.Base64;
import java.util.ResourceBundle;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import services.ServiceProduit;

/**
 * FXML Controller class
 *
 * @author admin
 */
public class AjouterProduitController implements Initializable {

    @FXML
    private TextField tfNom;

    @FXML
    private TextField tfDescription;

    @FXML
    private TextField tfStock;

    @FXML
    private TextField tfPrix;
     @FXML
    private TextField tfimag;
     @FXML
    private ImageView img;
     byte [] post_image = null;
    private String imagePath;
            private String i;
 private Produit p = new Produit();
 UserSession session = UserSession.getInstance(); 
    private int idloguser = session.getId(); 
       private static final String UPLOAD_DIR = "uploads/";
    @FXML
    private AnchorPane tfimage;
 @FXML   
    private void uploadim(ActionEvent event) throws IOException {
        FileChooser fc = new FileChooser();
        fc.setTitle("Ajouter une Image");
        fc.getExtensionFilters().addAll(
            new FileChooser.ExtensionFilter("Images", "*.png", "*.jpg", "*.jpeg", "*.gif")
        );
        File f = fc.showOpenDialog(null);
        String DBPath = "C:\\xampp\\htdocs\\img" + f.getName();
        i = f.getName();
        p.setImage(i);
       tfimag.setText(i);
        System.out.println(p.getImage());
        if (f != null) {
            try {
                BufferedImage bufferedImage = ImageIO.read(f);
                WritableImage image = SwingFXUtils.toFXImage(bufferedImage,null);
                ImageIO.write(bufferedImage, "jpg", new File(DBPath));
                img.setImage(image);
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
  @FXML
private void ajouterProduit(ActionEvent event) {
       User user = new User(); 
         user.setId(idloguser);
    if (tfNom.getText().isEmpty() || tfStock.getText().isEmpty() || tfPrix.getText().isEmpty() || tfDescription.getText().isEmpty() ) {
        Alert al = new Alert(Alert.AlertType.WARNING);
        al.setTitle("Contrôle de saisie");
        al.setContentText("Veuillez remplir tous les champs !!");
        al.show();
    } else {
        try {
            String nom = tfNom.getText();
            int stock = Integer.parseInt(tfStock.getText());
            float prix = Float.parseFloat(tfPrix.getText());
            String description = tfDescription.getText();
            String image= tfimag.getText();
            Produit p = new Produit(nom, description,stock,prix,image);
            p.setUser(user);
            ServiceProduit serviceProduit = new ServiceProduit();
            serviceProduit.ajouter(p);
            
            Alert al = new Alert(Alert.AlertType.CONFIRMATION);
            al.setTitle("Produit ajouté");
            al.setContentText("Le produit est ajouté avec succès !!");
            al.show();
            
        } catch (NumberFormatException e) {
            Alert al = new Alert(Alert.AlertType.ERROR);
            al.setTitle("Contrôle de saisie");
            al.setContentText("Le champ stock et le champ prix doivent être numériques !!");
            al.show();
        } catch (SQLException ex) {
            Alert al = new Alert(Alert.AlertType.ERROR);
            al.setTitle("Erreur SQL");
            al.setContentText(ex.getMessage());
            al.show();
        }
    }
}

    @Override
    public void initialize(URL location, ResourceBundle resources) {
     //To change body of generated methods, choose Tools | Templates.
    }
    @FXML
    private void reset(ActionEvent event) {
        tfNom.setText("");
        tfDescription.setText("");
        tfStock.setText("");
        tfPrix.setText("");
    }
    
}