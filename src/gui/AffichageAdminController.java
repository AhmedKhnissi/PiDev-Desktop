/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Publication;
import entities.UserSession;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import services.CommentaireService;
import services.PublicationService;

/**
 * FXML Controller class
 *
 * @author Khalil
 */
public class AffichageAdminController implements Initializable {
    
    
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
    private Button suppbtn;
    UserSession session = UserSession.getInstance(); 
    private int idloguser = session.getId();
    @FXML
    private Button statbtn1; 
    private int numsig=0;
    @FXML
    private Button modbtn1;
    @FXML
    private Button details;

    /**
     * Initializes the controller class. 
     * 
     */ 
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
       update();
     
    }
    
    
    
    
    
    
    
    
    
    public void update(){ 
        
      // Set up the table columns
        auteurColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getAuteur()));
        titreColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTitre()));
        contenuColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getContenu())); 
        imageColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getImage())); 
        datepubColumn.setCellValueFactory(cellData -> {
    Date datepub = cellData.getValue().getDatepub();
    if (datepub != null) {
        // Convert the Date to a String representation, e.g. using SimpleDateFormat
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String dateStr = dateFormat.format(datepub);
        return new SimpleStringProperty(dateStr);
    } else {
        return new SimpleStringProperty("");
    }
});   
        // Create the new column
    TableColumn<Publication, Integer> signalColumn = new TableColumn<>("Nombre de Signals");
    signalColumn.setCellValueFactory(new PropertyValueFactory<>("nbsignal"));
    
    // Add the new column to the TableView
    publicationTableView.getColumns().add(signalColumn);
        likesColumn.setCellValueFactory(new PropertyValueFactory<>("Likes")); 
        dislikeColumn.setCellValueFactory(new PropertyValueFactory<>("dislike")); 
        
        
        


      
        // Retrieve publications from the service and populate the table
        PublicationService publicationService = new PublicationService();
        try {
            List<Publication> publications = publicationService.recuperer(); 
          
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
           
    }

   
    @FXML
private void supprimerPublication(ActionEvent event) { 
    // Get the selected publication from the table view
    int selectedIndex = publicationTableView.getSelectionModel().getSelectedIndex();
    if (selectedIndex >= 0) { // Check if an item is selected
        Publication selectedPub = publicationTableView.getItems().get(selectedIndex); 
        
        // Display a confirmation dialog
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Supprimer une publication");
        alert.setContentText("Êtes-vous sûr de vouloir supprimer cette publication ?"); 
        
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) { // Check if OK button is clicked
            PublicationService publicationService = new PublicationService();
            try {
                publicationService.supprimer(selectedPub);
                update(); // Call the update() method to refresh the table view 
                 Alert al = new Alert(Alert.AlertType.INFORMATION);
                al.setTitle("publication Supprimée");
                al.setContentText("La publication a été supprimée avec Succès !!");
                al.show();
            } catch (SQLException ex) {
                System.out.println("Erreur d'accès à la BD");
            }
        }
    } else {
        // Display a warning dialog if no publication is selected
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Avertissement");
        alert.setHeaderText(null);
        alert.setContentText("Veuillez sélectionner une publication à supprimer.");
        alert.showAndWait();
    }
}   
 
     
   


   

@FXML
    private void retour(ActionEvent event) {
         try {
        Parent root = FXMLLoader.load(getClass().getResource("NavbarAdmin.fxml"));
        Scene scene = ((Node) event.getSource()).getScene();
        scene.setRoot(root);
    } catch (IOException ex) {
        System.out.print(ex);
    }
    }

    @FXML
   private void stat(ActionEvent event) {
    try {
        stat_pie();
    } catch (Exception e) {
        e.printStackTrace();
    }
}
   
   public void stat_pie() { 
        

        try {
            PublicationService ps = new PublicationService();
            CommentaireService cs = new CommentaireService();
            
            
            List<Publication> publications = ps.recuperer(); 
            

            int numPublications = ps.recuperer().size();
            for (Publication publication : publications) {
            if(publication.getNbsignal()>0){ 
                numsig++; 
           
            }
            
            }
            
            ObservableList<PieChart.Data> pieChartData =
                    FXCollections.observableArrayList(
                            new PieChart.Data("Publications (" + numPublications + ")", numPublications),
                            new PieChart.Data("Publications signalées (" + numsig + ")", numsig));  

            
            PieChart pieChart = new PieChart(pieChartData);
            pieChart.setTitle("Nombre de Publications signalées");
            
            for (final PieChart.Data data : pieChart.getData()) {
                data.getNode().addEventHandler(MouseEvent.MOUSE_CLICKED,
                        e -> {
                            double percentage = (data.getPieValue() / (numPublications + numsig)) * 100.0;
                            
                            Tooltip tooltip = new Tooltip(String.format("%.2f%%", percentage));
                            Tooltip.install(data.getNode(), tooltip);
                        });
            }
            
            Scene scene = new Scene(new Group());
            ((Group) scene.getRoot()).getChildren().add(pieChart);
            
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Statistics");
            stage.show();
        } catch (SQLException ex) {
            Logger.getLogger(AffichageAdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
}

   @FXML
private void details(ActionEvent event) { 
    int selectedIndex = publicationTableView.getSelectionModel().getSelectedIndex();
    Publication selectedPub = publicationTableView.getItems().get(selectedIndex);
     if (selectedIndex >= 0) {
    


    Stage stage = new Stage(); 
    stage.setTitle("Publication: "+selectedPub.getTitre());
    VBox vbox = new VBox();
    vbox.setPadding(new Insets(150));
    vbox.setSpacing(10);

    Label auteurLabel = new Label("Utilisateur: " + selectedPub.getAuteur());
    auteurLabel.setStyle("-fx-font-weight: bold;");
     
    File file = new File(selectedPub.getImage());
    Image image1 = new Image(file.toURI().toString());
    ImageView imageView = new ImageView(image1);
    imageView.setFitHeight(120);
    imageView.setFitWidth(300);

    Label likesLabel = new Label("Likes: " + selectedPub.getLikes());
    likesLabel.setStyle("-fx-text-fill: green; -fx-font-weight: bold;");

    Label dislikesLabel = new Label("Dislikes: " + selectedPub.getDislike());
    dislikesLabel.setStyle("-fx-text-fill: orange; -fx-font-weight: bold;");

    Label signalsLabel = new Label("Signals: " + selectedPub.getNbsignal());
    signalsLabel.setStyle("-fx-text-fill: red; -fx-font-weight: bold;");
    vbox.getChildren().add(imageView);
    vbox.getChildren().add(auteurLabel);
    vbox.getChildren().addAll(likesLabel, dislikesLabel, signalsLabel);

    stage.setScene(new Scene(vbox));
    stage.show();
    } else {
        // Display a warning dialog if no publication is selected
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Avertissement");
        alert.setHeaderText(null);
        alert.setContentText("Veuillez sélectionner une publication.");
        alert.showAndWait();
    }
}


   

}
