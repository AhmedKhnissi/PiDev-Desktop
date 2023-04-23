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
import javafx.scene.Scene;
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        publicationService = new PublicationService();
        List<Publication> publications = null;
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
    vbox.setPadding(new Insets(250));
    vbox.setSpacing(10);

    // add the full content of the publication to the 
    Label titreLabel = new Label(publication.getTitre());
    titleLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 16pt;");
    vbox.getChildren().add(titleLabel);

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
    submitButton.setOnAction(event -> {
      
       
        Commentaire commentaire = new Commentaire();
        commentaire.setPublication(publication);
        commentaire.setContenu(commentaireField.getText());
        commentaire.setDatetime(sqlDate);

        try {
            commentaireService.ajouter(commentaire);
            System.out.println("Commentaire ajouté avec succès!");
        } catch (SQLException ex) {
            System.out.println("Erreur lors de l'ajout du commentaire: " + ex.getMessage());
        }

        // clear the text field after adding the comment
        commentaireField.clear(); 
        // get the commentaires associated with the publication



    });
    vbox.getChildren().add(submitButton);

    // add the vbox to the scene and show the window
    Scene scene = new Scene(vbox);
    stage.setScene(scene);
    stage.show();
});




        // create a HBox to hold the like and dislike buttons and the "Voir plus" button
        HBox buttonsBox = new HBox();
        buttonsBox.setAlignment(Pos.CENTER);
        buttonsBox.setSpacing(10);
        Button likeButton = new Button("Like");
        Button dislikeButton = new Button("Dislike");
        buttonsBox.getChildren().addAll(likeButton, dislikeButton, voirPlusButton);
        likeButton.setOnAction(e -> {
    int currentLikes = publication.getLikes();
    int currentDislikes = publication.getDislike();
    if(currentDislikes > 0){
        publication.setDislike(currentDislikes - 1); // decrement dislikes by 1 if it's greater than 0
    }
    publication.setLikes(currentLikes + 1); // increment likes by 1
});

dislikeButton.setOnAction(e -> {
    int currentLikes = publication.getLikes();
    int currentDislikes = publication.getDislike();
    if(currentLikes > 0){
        publication.setLikes(currentLikes - 1); // decrement likes by 1 if it's greater than 0
    }
    publication.setDislike(currentDislikes + 1); // increment dislikes by 1
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

}
