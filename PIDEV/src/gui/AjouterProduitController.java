/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Produit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.SQLException;
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
import javafx.stage.FileChooser;
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
    private String imagePath;
    
@FXML   
private void uploadim(ActionEvent event) throws IOException {
   FileChooser chooser = new FileChooser();
   FileChooser.ExtensionFilter exxFilterJPG= new FileChooser.ExtensionFilter("JPG files (*.jpg)","*.JPG");
   FileChooser.ExtensionFilter exxFilterPNG= new FileChooser.ExtensionFilter("PNG files (*.png)","*.PNG");
    chooser.getExtensionFilters().addAll(exxFilterJPG,exxFilterPNG);
    File file=chooser.showOpenDialog(null);
    BufferedImage bufferedimg = ImageIO.read(file);
    Image image =SwingFXUtils.toFXImage(bufferedimg, null);
    img.setImage(image);
}

private String getFileExtension(String filename) {
    int dotIndex = filename.lastIndexOf(".");
    if (dotIndex > 0) {
        return filename.substring(dotIndex + 1);
    } else {
        return "";
    }
}
  @FXML
private void ajouterProduit(ActionEvent event) {
    if (tfNom.getText().isEmpty() || tfStock.getText().isEmpty() || tfPrix.getText().isEmpty() ) {
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