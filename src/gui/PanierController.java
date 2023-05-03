package gui;

import static com.itextpdf.text.pdf.PdfFileSpecification.url;
import entities.Produit;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class PanierController implements Initializable {

    @FXML
    private FlowPane flowPane;

    @FXML
    private Button viderPanierButton;

    @FXML
    private Button commanderButton;

    private ObservableList<Produit> produitsObservableList = FXCollections.observableArrayList();
    DetailleProduitController controller = new DetailleProduitController();


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Iterable<Produit> panierList = null;
        // Récupération des produits du panier
       
    // Récupération des produits du panier


        // Affichage des produits du panier
        for (Produit produit : panierList) {
            String nom = produit.getNom();
            float prix = produit.getPrix();
            String imagePath = produit.getImage();

            // Récupérer l'image à partir du chemin d'accès
            Image image = new Image(imagePath);
            ImageView imageView = new ImageView();
            imageView.setImage(image);
            imageView.setFitHeight(100);
            imageView.setPreserveRatio(true);

            Label labelNom = new Label(nom);
            Label labelPrix = new Label(String.format("%.2f €", prix));

            VBox vBox = new VBox();
            vBox.getChildren().addAll(imageView, labelNom, labelPrix);

            flowPane.getChildren().add(vBox);
        }
}


public void ajouterProduit(Produit produit) {
    produitsObservableList.add(produit);
 
}
@FXML
void viderPanier(ActionEvent event) {
    // Vider le panier
    produitsObservableList.clear();
    flowPane.getChildren().clear();
}
@FXML

public void envoyer(ActionEvent event) {
    
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
} catch (MessagingException e) {
throw new RuntimeException(e);
} }

}
     