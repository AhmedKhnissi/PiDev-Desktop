package gui;

import entities.Produit;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import services.ServiceProduit;

public class ModifierProduitController implements Initializable {
    
    @FXML
    private TextField txtNom;
    @FXML
    private TextField txtDescription;
    @FXML
    private TextField txtPrix;
    @FXML
    private TextField txtStock;
    @FXML
    private Button btnModifier;
    
    private Produit produit;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TmoODO
        modifierProduit();
    }
    
    public void setProduit(Produit produit) {
        this.produit = produit;
        txtNom.setText(produit.getNom());
        txtDescription.setText(produit.getDescription());
        txtPrix.setText(Float.toString(produit.getPrix()));
        txtStock.setText(Integer.toString(produit.getStock()));
    }
    
    @FXML
    private void modifierProduit() {
        String nom = txtNom.getText();
        String description = txtDescription.getText();
        Float prix = Float.parseFloat(txtPrix.getText());
        int stock = Integer.parseInt(txtStock.getText());
        
        produit.setNom(nom);
        produit.setDescription(description);
        produit.setPrix(prix);
        produit.setStock(stock);
        
        ServiceProduit serviceProduit = new ServiceProduit();
        try {
            serviceProduit.modifier(produit);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Succès");
            alert.setHeaderText("Le produit a été modifié avec succès.");
            alert.showAndWait();
        } catch (SQLException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Une erreur s'est produite lors de la modification du produit.");
            alert.setContentText(ex.getMessage());
            alert.showAndWait();
        }
    }
    
}