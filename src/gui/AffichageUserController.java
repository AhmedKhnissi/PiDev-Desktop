package gui;

import entities.Commentaire;
import entities.Publication;
import java.awt.Desktop;
import java.io.UnsupportedEncodingException;
import java.net.URI;
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
import java.net.URLEncoder;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
    
    
    
    
    final boolean[] signalPressed = { false }; 
    int badWordCount = 0; 
    int BAN_DURATION = 60;  
    private ScheduledExecutorService scheduler;
    
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
        VBox publicationBox = new VBox();
        publicationBox.setAlignment(Pos.CENTER);
        publicationBox.setSpacing(10);

        Label titleLabel = new Label(publication.getTitre());
        Label authorLabel = new Label("Auteur: " + publication.getAuteur());
        authorLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 13pt;");
        publicationBox.getChildren().addAll(titleLabel, authorLabel);

        Button voirPlusButton = new Button("Voir plus");
       voirPlusButton.setOnAction(e -> {
    Stage stage = new Stage();
    VBox vbox = new VBox();
    vbox.setPadding(new Insets(20));
    vbox.setSpacing(10);

    Label titreLabel = new Label(publication.getTitre());
    titleLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 25pt;");
    vbox.getChildren().add(titleLabel); 




    HBox infoBox = new HBox();
    infoBox.setSpacing(10);

    Label auteurLabel = new Label("Auteur: " + publication.getAuteur()); 
    titleLabel.setTextFill(Color.BLUE);
    Label dateLabel = new Label("Date de Publication: " + publication.getDatepub().toString()); 
    dateLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 13pt;");

    infoBox.getChildren().addAll(authorLabel, dateLabel);
    vbox.getChildren().add(infoBox);

    Label contentLabel = new Label(publication.getContenu()); 
   
    
    contentLabel.setWrapText(true);
    vbox.getChildren().add(contentLabel); 
    HBox likesDislikesBox = new HBox();
likesDislikesBox.setSpacing(10);



vbox.getChildren().add(likesDislikesBox);
    int  idpub= publication.getId();  
    List<Commentaire> commentaires = null; 
     CommentaireService commentaireService = new CommentaireService();
try {
    commentaires = commentaireService.getCommentaires(publication.getId());
} catch (SQLException ex) {
    System.out.println("Erreur lors de la récupération des commentaires: " + ex.getMessage());
}

Label commentairesLabel = new Label("Commentaires (" + commentaires.size() + "):");
commentairesLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 14pt;");
vbox.getChildren().add(commentairesLabel);

for (Commentaire com : commentaires) {
    Label commentaireLabel = new Label(com.getContenu());
    commentaireLabel.setWrapText(true);
    vbox.getChildren().add(commentaireLabel);
}
    

    TextField commentaireField = new TextField();
    commentaireField.setPromptText("Ajouter un commentaire");
    vbox.getChildren().add(commentaireField);
    

    Button submitButton = new Button("Ajouter un commentaire");   
    submitButton.setStyle("-fx-background-color: #4682B4; -fx-text-fill: white; -fx-font-weight: bold;");
    Button LikeButton = new Button("👍 " + publication.getLikes()); 
    LikeButton.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white; -fx-font-weight: bold;");
    
    Button DislikeButton = new Button("👎 " + publication.getDislike()); 
    DislikeButton.setStyle("-fx-background-color: #f44336; -fx-text-fill: white; -fx-font-weight: bold;"); 
    Button SignalButton = new Button("Signaler cette publication!"); 
    SignalButton.setStyle("-fx-background-color: #f44336; -fx-text-fill: white; -fx-font-weight: bold;"); 
    Button shareOnFacebookButton = new Button("Partager sur Facebook");
    shareOnFacebookButton.setStyle("-fx-background-color: #4682B4; -fx-text-fill: white; -fx-font-weight: bold;");
    
    submitButton.setOnAction(event -> { 
        Alert a9 = new Alert(Alert.AlertType.WARNING);
        if(commentaireField.getText().isEmpty()){ 
            
            a9.setTitle("Champ Vide!");
            a9.setContentText("Veuillez remplir le champs pour ajouter un commentaire!");
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
    badWordCount++;
     if (badWordCount >= 3) {
                submitButton.setDisable(true); 
                long banEndTime = System.currentTimeMillis() + BAN_DURATION * 1000;
                scheduler = Executors.newScheduledThreadPool(1);

                scheduler.scheduleAtFixedRate(() -> {
                    long remainingTime = banEndTime - System.currentTimeMillis();
                    if (remainingTime <= 0) {
                        Platform.runLater(() -> {
                            submitButton.setDisable(false);
                            submitButton.setText("Ajouter un commentaire");
                            
                            badWordCount = 0;
                        });
                        scheduler.shutdown();
                        return;
                    }
                    Platform.runLater(() -> {
                        submitButton.setText("banni pour " + remainingTime / 1000 + "s");
                        
                        
                    });
                }, 0, 1, TimeUnit.SECONDS);

                Alert banAlert = new Alert(Alert.AlertType.ERROR);
                banAlert.setTitle("Error");
                banAlert.setHeaderText("Vous avez été banni pour " + BAN_DURATION + " secondes");
                banAlert.showAndWait();
                return;
            }
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
        

        commentaireField.clear(); 
        



    }); 
    Button twitterBTN = new Button("share on twitter");
twitterBTN.setStyle("-fx-background-color: #008CBA; -fx-text-fill: white; -fx-font-weight: bold;");
    
    
    
    HBox buttonBox = new HBox(); 
buttonBox.setSpacing(10);
buttonBox.getChildren().addAll(submitButton, LikeButton, DislikeButton,SignalButton, twitterBTN );
vbox.getChildren().add(buttonBox); 

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
    DislikeButton.setText("👎 " + publication.getDislike()); 
    LikeButton.setText("👍 " + publication.getLikes());
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
    DislikeButton.setText("👎 " + publication.getDislike()); 
    LikeButton.setText("👍 " + publication.getLikes());
    
}); 
   
   
  final boolean[] signalPressed = { false };
SignalButton.setOnAction(r -> {
    if (!signalPressed[0]) {
        int currentSignal = publication.getNbsignal();
        publication.setNbsignal(currentSignal + 1);
        signalPressed[0] = true;
        try {
            publicationService.signaler(publication);
        } catch (SQLException ex) {
            Logger.getLogger(AffichageUserController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
});

 twitterBTN.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    // create the Twitter share link with pre-filled content
                    String encodedAuteur = URLEncoder.encode(publication.getAuteur(), "UTF-8");
                    String encodedTitle = URLEncoder.encode(publication.getTitre(), "UTF-8"); 
                    String encodedContent = URLEncoder.encode(publication.getContenu(), "UTF-8"); 
                    String encodedt = URLEncoder.encode(" a publié(e):\n", "UTF-8");
                    String shareLink = "https://twitter.com/intent/tweet?text="+encodedTitle+ "%0A"+encodedAuteur+encodedt+encodedContent;

                    
                    try {
                        // open the link in the default browser
                        Desktop.getDesktop().browse(new URI(shareLink));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } catch (UnsupportedEncodingException ex) {
                    Logger.getLogger(AffichageUserController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
});




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
    likeButton.setText("👍 " + publication.getLikes()); 
    dislikeButton.setText("👎 " + publication.getDislike());
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
    dislikeButton.setText("👎 " + publication.getDislike());
});



        publicationBox.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        publicationBox.setPadding(new Insets(10));

       

        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(publicationBox);
        borderPane.setPrefSize(400, 200);
        borderPane.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        borderPane.setPadding(new Insets(10));

        publicationGrid.add(borderPane, col, row);
        publicationGrid.add(buttonsBox, col, row + 1);

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
        displayPublications(publications);
    } else {
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
