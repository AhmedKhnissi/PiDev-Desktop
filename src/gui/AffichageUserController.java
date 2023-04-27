package gui;

import entities.Commentaire;
import entities.Publication;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import services.PublicationService;
import java.net.URL;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.stream.Collectors;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderStroke;
import javafx.stage.Stage;
import services.CommentaireService;


/**
 * FXML Controller class
 *
 * @author Khalil
 */
public class AffichageUserController implements Initializable {

    @FXML
    private GridPane publicationGrid;

    private PublicationService publicationService;
    @FXML
    private TextField searchField;
List<Publication> publications = null;
    @FXML
    private Button btnrechercher;
    @FXML
    private Button ActualiserBtn; 
    
    final boolean[] likePressed = { false };
    final boolean[] dislikePressed = { false }; 
    
    final boolean[] LikePressed = { false };
    final boolean[] DislikePressed = { false };
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        publicationService = new PublicationService();
        
        try {
            publications = publicationService.recuperer();
        } catch (SQLException ex) {
            Logger.getLogger(AffichageUserController.class.getName()).log(Level.SEVERE, null, ex);
        }
        displayPublications(publications); 
        
    }

    private void displayPublications(List<Publication> publications) {
    int row = 0;
    int col = 0; 
    LocalDate currentDate = LocalDate.now(); 
         Date sqlDate = Date.valueOf(currentDate); 
         publicationGrid.getChildren().clear();
    for (Publication publication : publications) {
        // create a VBox to hold the publication information
        VBox publicationBox = new VBox();
        publicationBox.setAlignment(Pos.CENTER);
        publicationBox.setSpacing(10);

        // add the publication title and author to the VBox
        Label titleLabel = new Label(publication.getTitre());
        Label authorLabel = new Label("Auteur: " + publication.getAuteur());
        publicationBox.getChildren().addAll(titleLabel, authorLabel);

        // create a button to display the full content of the publication
        Button voirPlusButton = new Button("Voir plus");
       voirPlusButton.setOnAction(e -> {
    // create a new window to display the full content
    Stage stage = new Stage();
    VBox vbox = new VBox();
    vbox.setPadding(new Insets(20));
    vbox.setSpacing(10);

    // add the full content of the publication to the 
    Label titreLabel = new Label(publication.getTitre());
    titleLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 16pt;");
    vbox.getChildren().add(titleLabel); 
    // create labels for displaying the number of likes and dislikes


// add the labels to the VBox


    // create an HBox to hold the author and date information
    HBox infoBox = new HBox();
    infoBox.setSpacing(10);

    Label auteurLabel = new Label("Auteur: " + publication.getAuteur()); 
    titleLabel.setTextFill(Color.BLUE);
    Label dateLabel = new Label("Date: " + publication.getDatepub().toString());

    infoBox.getChildren().addAll(authorLabel, dateLabel);
    vbox.getChildren().add(infoBox);

    Label contentLabel = new Label(publication.getContenu()); 
   
    
    contentLabel.setWrapText(true);
    vbox.getChildren().add(contentLabel); 
    HBox likesDislikesBox = new HBox();
likesDislikesBox.setSpacing(10);



// add the likes and dislikes HBox to the main VBox
vbox.getChildren().add(likesDislikesBox);
    int  idpub= publication.getId();  
    List<Commentaire> commentaires = null; 
     CommentaireService commentaireService = new CommentaireService();
try {
    commentaires = commentaireService.getCommentaires(publication.getId());
} catch (SQLException ex) {
    System.out.println("Erreur lors de la récupération des commentaires: " + ex.getMessage());
}

// create a label for the commentaires
Label commentairesLabel = new Label("Commentaires (" + commentaires.size() + "):");
commentairesLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 14pt;");
vbox.getChildren().add(commentairesLabel);

// display the commentaires
for (Commentaire com : commentaires) {
    Label commentaireLabel = new Label(com.getContenu());
    commentaireLabel.setWrapText(true);
    vbox.getChildren().add(commentaireLabel);
}
    

    // create a text field for adding a new comment
    TextField commentaireField = new TextField();
    commentaireField.setPromptText("Ajouter un commentaire");
    vbox.getChildren().add(commentaireField);
    

    // create a submit button for adding the new comment
    Button submitButton = new Button("Ajouter un commentaire");   
    Button LikeButton = new Button("👍 " + publication.getLikes()); 
    LikeButton.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white; -fx-font-weight: bold;");
    
    Button DislikeButton = new Button("👎 " + publication.getDislike()); 
    DislikeButton.setStyle("-fx-background-color: #f44336; -fx-text-fill: white; -fx-font-weight: bold;"); 
    
    
    submitButton.setOnAction(event -> { 
        Alert a9 = new Alert(Alert.AlertType.WARNING);
        if(commentaireField.getText().isEmpty()){ 
            
            a9.setTitle("Champ Vide!");
            a9.setContentText("Veuillez remplir le champs !");
            a9.show();
        }else{
        
        
        
        
        Commentaire commentaire = new Commentaire();
        commentaire.setPublication(publication);
        commentaire.setContenu(commentaireField.getText());
        commentaire.setDatetime(sqlDate);

        if (commentaire.hasProfanity()) {
    Alert alert = new Alert(Alert.AlertType.WARNING);
    alert.setTitle("Commentaire non autorisé");
    alert.setContentText("Votre commentaire contient un langage inapproprié.");
    alert.show();
} else {
    try {
        commentaireService.ajouter(commentaire);
        System.out.println("Commentaire ajouté avec succès!"); 
        Alert al = new Alert(Alert.AlertType.INFORMATION);
        al.setTitle("Commentaire ajouté");
        al.setContentText("Commentaire ajouté avec succès !");
        al.show();
    } catch (SQLException ex) {
        System.out.println("Erreur lors de l'ajout du commentaire: " + ex.getMessage());
    }
}

        }
        

        // clear the text field after adding the comment
        commentaireField.clear(); 
        // get the commentaires associated with the publication 
        



    }); 
    
    
    
    
    HBox buttonBox = new HBox(); 
buttonBox.setSpacing(10);
buttonBox.getChildren().addAll(submitButton, LikeButton, DislikeButton);
vbox.getChildren().add(buttonBox); 

    // add the vbox to the scene and show the window
    Scene scene = new Scene(vbox);
    stage.setScene(scene);
    stage.show(); 
 LikeButton.setOnAction(r -> {
    int currentLikes = publication.getLikes();
    int currentDislikes = publication.getDislike();
    if (publication.isDislikePressed2()) {
        publication.setDislike(currentDislikes - 1);
        publication.setDislikePressed2(false);
    }
    if (!publication.isLikePressed2()) {
        publication.setLikes(currentLikes + 1);
        publication.setLikePressed2(true);
        LikeButton.setText("👍 " + publication.getLikes());
        try {
            publicationService.like(publication);
        } catch (SQLException ex) {
            Logger.getLogger(AffichageUserController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
});

    
    
   DislikeButton.setOnAction(r -> {
    int currentLikes = publication.getLikes();
    int currentDislikes = publication.getDislike();
    if (publication.isLikePressed2()) {
        publication.setLikes(currentLikes - 1);
        publication.setLikePressed2(false);
    }
    if (!publication.isDislikePressed2()) {
        publication.setDislike(currentDislikes + 1);
        publication.setDislikePressed2(true);
        DislikeButton.setText("👎 " + publication.getDislike());
        try {
            publicationService.dislike(publication);
        } catch (SQLException ex) {
            Logger.getLogger(AffichageUserController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
});
});




        // create a HBox to hold the like and dislike buttons and the "Voir plus" button
        HBox buttonsBox = new HBox();
        buttonsBox.setAlignment(Pos.CENTER);
        buttonsBox.setSpacing(10); 
        Button likeButton =  new Button("👍 " + publication.getLikes());
         likeButton.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white; -fx-font-weight: bold;");
        Button dislikeButton = new Button("👎 " + publication.getDislike()); 
        dislikeButton.setStyle("-fx-background-color: #f44336; -fx-text-fill: white; -fx-font-weight: bold;");
        buttonsBox.getChildren().addAll(likeButton, dislikeButton, voirPlusButton);
likeButton.setOnAction(e -> {
    int currentLikes = publication.getLikes();
    int currentDislikes = publication.getDislike();
    if (publication.isDislikePressed()) {
        publication.setDislike(currentDislikes - 1);
        publication.setDislikePressed(false);
    }
    if (!publication.isLikePressed()) {
        publication.setLikes(currentLikes + 1);
        publication.setLikePressed(true);
        likeButton.setText("👍 " + publication.getLikes());
        try {
            publicationService.like(publication);
        } catch (SQLException ex) {
            Logger.getLogger(AffichageUserController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
});


dislikeButton.setOnAction(e -> {
    int currentLikes = publication.getLikes();
    int currentDislikes = publication.getDislike();
    if (publication.isLikePressed()) {
        publication.setLikes(currentLikes - 1);
        publication.setLikePressed(false);
    }
    if (!publication.isDislikePressed()) {
        publication.setDislike(currentDislikes + 1);
        publication.setDislikePressed(true);
        dislikeButton.setText("👎 " + publication.getDislike());
        try {
            publicationService.dislike(publication);
        } catch (SQLException ex) {
            Logger.getLogger(AffichageUserController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    likeButton.setText("👍 " + publication.getLikes());
});



        // add a border and spacing to the publicationBox
        publicationBox.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        publicationBox.setPadding(new Insets(10));

       

        // create a BorderPane to hold the rectangle and publicationBox
        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(publicationBox);
        borderPane.setPrefSize(400, 200);
        borderPane.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        borderPane.setPadding(new Insets(10));

        // add the BorderPane and HBox to the grid pane
        publicationGrid.add(borderPane, col, row);
        publicationGrid.add(buttonsBox, col, row + 1);

        // increment the column index, wrap to the next row if necessary
        col++;
        if (col == 3) {
            col = 0;
            row += 2;
        }
    }
} 
    @FXML
private void handleSearch(ActionEvent event) { 
    String searchTerm = searchField.getText();
    if (searchTerm == null || searchTerm.trim().isEmpty()) {
        // No search term entered, show all publications
        displayPublications(publications);
    } else {
        // Filter publications based on search term
        List<Publication> filteredPublications = publications.stream()
                .filter(p -> p.getTitre().contains(searchTerm) || p.getContenu().contains(searchTerm))
                .collect(Collectors.toList()); 
        publicationGrid.getChildren().clear();
        displayPublications(filteredPublications);
    }
}

    @FXML
    private void Actualiser(ActionEvent event) { 
        displayPublications(publications);
    } 
    
    
    


}
