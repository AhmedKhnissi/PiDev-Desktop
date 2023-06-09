/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Publication;
import entities.UserSession;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import services.PublicationService;

/**
 * FXML Controller class
 *
 * @author Khalil
 */
public class AfficherPublicationController implements Initializable { 
    
    
    @FXML
    private TableView<Publication> publicationTableView;
   
    @FXML
    private TableColumn<Publication, String> auteurColumn;
    @FXML
    private TableColumn<Publication, String> titreColumn;
    @FXML
    private TableColumn<Publication, String> contenuColumn; 
    @FXML
    private TableColumn<Publication, String> imageColumn;
    @FXML
    private TableColumn<Publication, String> datepubColumn; 
    @FXML
    private TableColumn<Publication, Integer> likesColumn; 
    @FXML
    private TableColumn<Publication, Integer> dislikeColumn;  
    
    @FXML
    private TextField tfAuteur;
    @FXML
    private TextField tfTitre; 
    @FXML
    private TextArea taContenu;
    @FXML
    private TextField tfImage; 
    @FXML
    private Button suppbtn;
    @FXML
    private Button modbtn;
    @FXML
    private Button modbtn1;
    UserSession session = UserSession.getInstance(); 
    private int idloguser = session.getId();
    @FXML
    private Button modbtn2; 
    private String i; 
    byte [] post_image = null;
    private String imagePath; 
    private File file2;


    
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
       update();
     
    }
    public void update(){
        tfImage.setDisable(true);
        
      // Set up the table columns
        auteurColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getAuteur()));
        titreColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTitre()));
        contenuColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getContenu())); 
        imageColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getImage())); 
        datepubColumn.setCellValueFactory(cellData -> {
    Date datepub = cellData.getValue().getDatepub();
    if (datepub != null) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String dateStr = dateFormat.format(datepub);
        return new SimpleStringProperty(dateStr);
    } else {
        return new SimpleStringProperty("");
    }
});   
    TableColumn<Publication, Integer> signalColumn = new TableColumn<>("Nombre de Signals");
    signalColumn.setCellValueFactory(new PropertyValueFactory<>("nbsignal"));
    
    publicationTableView.getColumns().add(signalColumn);
        likesColumn.setCellValueFactory(new PropertyValueFactory<>("Likes")); 
        dislikeColumn.setCellValueFactory(new PropertyValueFactory<>("dislike")); 
        
        
        


      
        PublicationService publicationService = new PublicationService();
        try {
            List<Publication> publications = publicationService.recupererByIdUser(idloguser); 
          
            publicationTableView.getItems().setAll(publications);
        } catch (SQLException ex) {
            System.out.println("Error retrieving publications: " + ex.getMessage());
        }   
        
        
        
    }  
      @FXML
    private void clicked(javafx.scene.input.MouseEvent event) { 
        int myIndex =publicationTableView.getSelectionModel().getSelectedIndex();
        Publication selectedPub = publicationTableView.getItems().get(myIndex);  
            System.out.println("Selected publication: " + selectedPub);

        tfAuteur.setText(selectedPub.getAuteur()); 
    tfTitre.setText(selectedPub.getTitre()); 
    tfImage.setText(selectedPub.getContenu());
    taContenu.setText(selectedPub.getContenu()); 
    }

   
    @FXML
private void supprimerPublication(ActionEvent event) { 
    int selectedIndex = publicationTableView.getSelectionModel().getSelectedIndex();
    if (selectedIndex >= 0) { 
        Publication selectedPub = publicationTableView.getItems().get(selectedIndex); 
        
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Supprimer une publication");
        alert.setContentText("Êtes-vous sûr de vouloir supprimer cette publication ?"); 
        
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) { // Check if OK button is clicked
            PublicationService publicationService = new PublicationService();
            try {
                publicationService.supprimer(selectedPub);
                update(); 
                 Alert al = new Alert(Alert.AlertType.INFORMATION);
                al.setTitle("publication Supprimée");
                al.setContentText("La publication a été supprimée avec Succès !!");
                al.show();
            } catch (SQLException ex) {
                System.out.println("Erreur d'accès à la BD");
            }
        }
    } else {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Avertissement");
        alert.setHeaderText(null);
        alert.setContentText("Veuillez sélectionner une publication à supprimer.");
        alert.showAndWait();
    }
}   
 
     
   


   @FXML
private void modifier(ActionEvent event) {
    int selectedIndex = publicationTableView.getSelectionModel().getSelectedIndex();

    if (selectedIndex < 0) {
        // Display a warning dialog if no publication is selected
        Alert alert5 = new Alert(Alert.AlertType.WARNING);
        alert5.setTitle("Avertissement");
        alert5.setHeaderText(null);
        alert5.setContentText("Veuillez sélectionner une publication à modifier.");
        alert5.showAndWait();
    } else {
        Publication selectedPub = publicationTableView.getItems().get(selectedIndex);

        if (tfTitre.getText().isEmpty() || taContenu.getText().isEmpty() || tfAuteur.getText().isEmpty() || tfImage.getText().isEmpty()) {
            Alert al = new Alert(Alert.AlertType.WARNING);
            al.setTitle("Controle de saisie");
            al.setContentText("Veuillez remplir tous les champs !");
            al.show();
        } else {
            String auteur = tfAuteur.getText();
            String titre = tfTitre.getText();
            String contenu = taContenu.getText();
            String image = tfImage.getText();

            Publication p = new Publication(auteur, titre, contenu, image);
            p.setId(selectedPub.getId());

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation de modification");
            alert.setHeaderText("Êtes-vous sûr de vouloir modifier la publication sélectionnée ?");
            alert.setContentText("Cette action est irréversible.");

            Optional<ButtonType> result = alert.showAndWait();

            if (result.isPresent() && result.get() == ButtonType.OK) {
                PublicationService ps = new PublicationService();

                try {
                    ps.modifier(p);
                    update(); 
                    Alert al = new Alert(Alert.AlertType.INFORMATION);
                    al.setTitle("Publication modifiée");
                    al.setContentText("La publication a été modifiée avec succès !");
                    al.show();
                } catch (SQLException ex) {
                    Alert al = new Alert(Alert.AlertType.ERROR);
                    al.setTitle("Erreur");
                    al.setContentText("Impossible de modifier la publication : " + ex.getMessage());
                    al.show();
                }
            }
        }
    } 
    
}

@FXML
    private void retour(ActionEvent event) {
         try {
        Parent root = FXMLLoader.load(getClass().getResource("Sidebar_veterinaire.fxml"));
        Scene scene = ((Node) event.getSource()).getScene();
        scene.setRoot(root);
    } catch (IOException ex) {
        System.out.print(ex);
    }
    }

    @FXML
    private void ajout(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("AjouterPublication.fxml"));
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
        } catch (IOException ex) {
            System.out.print(ex);
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
                    System.out.println("errr");
                }
            }

        }
        tfImage.setText(String.valueOf(file2));
        p.setImage(String.valueOf(file2));
        
        return file2;
     
    }

    
    
    
    }
     
    
    

  
    
    
 

    
 

