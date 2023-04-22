package gui;

import java.io.File;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import entities.Produit;
import java.sql.Connection;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Label;

import services.ServiceProduit;

import utils.MyDB;

public class DetailleProduitController implements Initializable {

    @FXML
    private GridPane gridPane;

    private final Connection cnx = MyDB.getInstance().getCnx();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ServiceProduit serviceProduit = new ServiceProduit();

        try {
            String query = "SELECT nom, prix, image FROM produit";
            PreparedStatement stmt = cnx.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();

            int row = 0;
            int col = 0;

            while (rs.next()) {
                // Récupérer les données de chaque produit
                String nom = rs.getString("nom");
                float prix = rs.getFloat("prix");
                String imagePath = rs.getString("image");

                // Créer un contrôle ImageView pour afficher l'image
                Image image = new Image(new File(imagePath).toURI().toString());
                ImageView imageView = new ImageView(image);
                imageView.setFitHeight(200);
                imageView.setFitWidth(200);

                // Créer un contrôle Label pour afficher le nom et le prix du produit
                Label nomLabel = new Label(nom);
                Label prixLabel = new Label(String.valueOf(prix));

                // Ajouter les contrôles à la grille
                gridPane.add(imageView, col, row);
                gridPane.add(nomLabel, col, row + 1);
                gridPane.add(prixLabel, col, row + 2);

                // Passer à la colonne suivante
                col++;
                if (col == 3) {
                    col = 0;
                    row += 3;
                }
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}