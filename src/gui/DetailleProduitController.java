package gui;
import entities.Produit;
import java.net.URL;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javax.mail.Authenticator;
import services.ServiceProduit;
import utils.MyDB;

public class DetailleProduitController implements Initializable {
@FXML
private TextField prixMinTextField;

@FXML
private TextField prixMaxTextField;

@FXML
private Button searchByPriceButton;
@FXML
private GridPane gridPane;

@FXML
private BorderPane borderPane;

@FXML
private TextField searchField;

@FXML
private Button searchButton;

private final Connection cnx = MyDB.getInstance().getCnx();

private ObservableList<Produit> produitsObservableList = FXCollections.observableArrayList();
private FilteredList<Produit> produitsFiltresList = new FilteredList<>(produitsObservableList);

@Override
public void initialize(URL url, ResourceBundle rb) {

    // Récupération des produits depuis la base de données
    try {
        ServiceProduit serviceProduit = new ServiceProduit();
        produitsObservableList.setAll(serviceProduit.recuperer());
    } catch (SQLException ex) {
        Logger.getLogger(DetailleProduitController.class.getName()).log(Level.SEVERE, null, ex);
    }

    // Configuration de la grille d'affichage des produits
    gridPane.setHgap(100);
    gridPane.setVgap(100);
    gridPane.setPadding(new Insets(20));

    // Affichage des produits
    afficherProduits(produitsFiltresList);

    // Configuration de la zone de recherche
    searchField.textProperty().addListener((observable, oldValue, newValue) -> {
        produitsFiltresList.setPredicate(produit -> {
            if (newValue == null || newValue.isEmpty()) {
                return true;
            }

            String lowerCaseFilter = newValue.toLowerCase();

            if (produit.getNom().toLowerCase().contains(lowerCaseFilter)) {
                return true;
            }

            if (produit.getDescription().toLowerCase().contains(lowerCaseFilter)) {
                return true;
            }

            return false;
        });
        Platform.runLater(() -> afficherProduits(produitsFiltresList));
    });

    // Configuration du bouton de recherche
    searchButton.setOnAction(event -> {
        String searchTerm = searchField.getText().toLowerCase();
        produitsFiltresList.setPredicate(produit -> {
            if (searchTerm == null || searchTerm.isEmpty()) {
                return true;
            }

            String lowerCaseFilter = searchTerm.toLowerCase();

            if (produit.getNom().toLowerCase().contains(lowerCaseFilter)) {
                return true;
            }

            if (produit.getDescription().toLowerCase().contains(lowerCaseFilter)) {
                return true;
            }

            return false;
        });
        afficherProduits(produitsFiltresList);
    });
    // Configuration du bouton de recherche par prix
searchByPriceButton.setOnAction(event -> {
    String prixMinText = prixMinTextField.getText();
    String prixMaxText = prixMaxTextField.getText();

    float prixMin = prixMinText.isEmpty() ? 0 : Float.parseFloat(prixMinText);
    float prixMax = prixMaxText.isEmpty() ? Float.MAX_VALUE : Float.parseFloat(prixMaxText);

    produitsFiltresList.setPredicate(produit -> {
        float prix = produit.getPrix();
        return prix >= prixMin && prix <= prixMax;
    });
    afficherProduits(produitsFiltresList);
});
}

private void afficherProduits(List<Produit> produits) {
    // Effacer les anciens produits de la grille
    gridPane.getChildren().clear();

    int row = 0;
    int col = 0;

    // Ajouter chaque produit à la grille
    for (Produit produit : produits) {

        // Récupérer les données de chaque produit
        String nom = produit.getNom();
        float prix = produit.getPrix();
        String imagePath = produit.getImage();

        // Récupérer l'image à partir du chemin d'accès
        Image imagep = new Image("http://127.0.0.1/img/" + imagePath);
        ImageView iv2 = new ImageView();
        iv2.setImage(imagep);
    
        iv2.setFitHeight(100);
iv2.setPreserveRatio(true);


    // Créer les éléments graphiques pour chaque produit
    VBox vBox = new VBox();
    Label labelNom = new Label(nom);
    Label labelPrix = new Label(String.format("%.2f €", prix));
    Button commande=new Button("commander");
    commande.setOnAction(event -> envoyer());
    vBox.getChildren().addAll(iv2, labelNom, labelPrix,commande);
    vBox.setAlignment(Pos.CENTER);
    vBox.setSpacing(10);
    vBox.setPadding(new Insets(10));
    vBox.getStyleClass().add("produit");

    // Ajouter l'élément graphique à la grille
    gridPane.add(vBox, col, row);

    // Passer à la colonne suivante, ou à la première colonne de la ligne suivante si la ligne est pleine
    col++;
    if (col == 3) {
        col = 0;
        row++;
    }
}
}



public void envoyer() {
    
// Etape 1 : Création de la session
Properties props = new Properties();
props.put("mail.smtp.auth", "true");
props.put("mail.smtp.starttls.enable","true");
props.put("mail.smtp.host","smtp.gmail.com");
props.put("mail.smtp.port","587");
 Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("mohamedkhalil.khmiri@esprit.tn", "realfc2000B");
            }
        });
try {
// Etape 2 : Création de l'objet Message
Message message = new MimeMessage(session);
message.setFrom(new InternetAddress("mohamedkhalil.khmiri@esprit.tn"));
message.setRecipients(Message.RecipientType.TO,
InternetAddress.parse("khalilkhemiri681@gmail.com"));
message.setSubject("Commande");
message.setText("votre commande est en cours de traitement  ");
// Etape 3 : Envoyer le message
  Thread mailThread = new Thread(() -> {
           
    try {
        // send the message
        Transport.send(message);
    } catch (MessagingException ex) {
        Logger.getLogger(DetailleProduitController.class.getName()).log(Level.SEVERE, null, ex);
    }
            
               
            
        });
        mailThread.start();
             System.out.println("Message_envoye");
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Commande");
            alert.setHeaderText(null);
            alert.setContentText(" votre commande est en cours de traitement ");
            alert.showAndWait();
} catch (MessagingException e) {
throw new RuntimeException(e);
} }}
