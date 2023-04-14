package gui;
import entities.Produit;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import services.ServiceProduit;

public class AfficherProduitController implements Initializable {

    @FXML
    private TableView<Produit> tableProduit;
    @FXML
    private TableColumn<Produit, String> coNom;
    @FXML
    private TableColumn<Produit, String> coDescription;
    @FXML
    private TableColumn<Produit, String> coPrix;
    @FXML
    private TableColumn<Produit, String> coStock;

    /**
     * Initializes the controller class.
     */
    @Override
public void initialize(URL url, ResourceBundle rb) {
        
    // Initialiser les colonnes du tableau
    coNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
    coDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
    coPrix.setCellValueFactory(new PropertyValueFactory<>("prix"));
    coStock.setCellValueFactory(new PropertyValueFactory<>("stock"));

    // Ajouter les boutons Modifier et Supprimer
    TableColumn<Produit, Void> colAction = new TableColumn<>("Action");
    Callback<TableColumn<Produit, Void>, TableCell<Produit, Void>> cellFactory = new Callback<TableColumn<Produit, Void>, TableCell<Produit, Void>>() {
        @Override
        public TableCell<Produit, Void> call(final TableColumn<Produit, Void> param) {
            final TableCell<Produit, Void> cell = new TableCell<Produit, Void>() {
                private final Button btnModifier = new Button("Modifier");
                private final Button btnSupprimer = new Button("Supprimer");

                {
                    // Action pour le bouton Modifier
                    btnModifier.setOnAction((ActionEvent event) -> {
                        Produit produit = getTableView().getItems().get(getIndex());
                        // TODO: Code pour afficher la vue de modification du produit
                         afficherVueModificationProduit(produit);
                    });
                    
                    // Action pour le bouton Supprimer
                    btnSupprimer.setOnAction((ActionEvent event) -> {
    Produit produit = getTableView().getItems().get(getIndex());
    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    alert.setTitle("Confirmation de suppression");
    alert.setHeaderText("Supprimer le produit " + produit.getNom() + " ?");
    alert.setContentText("Êtes-vous sûr de vouloir supprimer ce produit ?");

    Optional<ButtonType> result = alert.showAndWait();
    if (result.get() == ButtonType.OK) {
        ServiceProduit serviceProduit = new ServiceProduit();
        try {
            serviceProduit.supprimer(produit);
            refreshTable();
        } catch (SQLException ex) {
            Alert error = new Alert(Alert.AlertType.ERROR);
            error.setTitle("Erreur");
            error.setHeaderText("Une erreur s'est produite lors de la suppression du produit.");
            error.setContentText(ex.getMessage());
            error.showAndWait();
        }
    }
});
                }

                @Override
                public void updateItem(Void item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty) {
                        setGraphic(null);
                    } else {
                        setGraphic(new HBox(btnModifier, btnSupprimer));
                    }
                }
            };
            return cell;
        }
    };
    
    colAction.setCellFactory(cellFactory);

    // Ajouter la colonne Action au tableau
    tableProduit.getColumns().add(colAction);

    // Récupérer les produits de la base de données
    ServiceProduit serviceProduit = new ServiceProduit();
    List<Produit> produits = new ArrayList<>();
    try {
        produits = serviceProduit.recuperer();
        ObservableList<Produit> observableProduits = FXCollections.observableArrayList(produits);
        tableProduit.setItems(observableProduits);
        refreshTable();
    } catch (SQLException ex) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText("Une erreur s'est produite lors de la récupération des produits.");
        alert.setContentText(ex.getMessage());
        alert.showAndWait();
    }
}

    
    @FXML
    private void getAddView(ActionEvent event) {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("../gui/AjouterProduit.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(AfficherProduitController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
private void afficherVueModificationProduit(Produit produit) {
    try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/ModifierProduit.fxml"));
        Parent parent = loader.load();  
        ModifierProduitController cont = loader.getController();
        cont.setProduit(produit); 
        Scene scene = new Scene(parent);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.initStyle(StageStyle.UTILITY);
        stage.show();
    } catch (IOException ex) {
        Logger.getLogger(AfficherProduitController.class.getName()).log(Level.SEVERE, null, ex);
    }
}
private void refreshTable() {
    ServiceProduit serviceProduit = new ServiceProduit();
    List<Produit> produits = new ArrayList<>();
    try {
        produits = serviceProduit.recuperer();
        ObservableList<Produit> observableProduits = FXCollections.observableArrayList(produits);
        tableProduit.setItems(observableProduits);
    } catch (SQLException ex) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText("Une erreur s'est produite lors de la récupération des produits.");
        alert.setContentText(ex.getMessage());
        alert.showAndWait();
    }
}

@FXML
    private void supprimerPublication(ActionEvent event) { 
        int myIndex =tableProduit.getSelectionModel().getSelectedIndex();
        Produit selectedProd = tableProduit.getItems().get(myIndex); 
        coNom.setText(selectedProd.getNom());
        coDescription.setText(String.valueOf(selectedProd.getDescription()));
        coPrix.setText(String.valueOf(selectedProd.getPrix()));
        coStock.setText(String.valueOf(selectedProd.getStock()));
        ServiceProduit produitService = new ServiceProduit();

               try {
          produitService.supprimer(selectedProd);
        } catch (SQLException ex) {
            System.out.println("Erreur d'accées a la BD");
        } 
               update(); 
                
    }
    public void update()
    { 

      // Set up the table columns
        coNom.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNom()));
        coDescription.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDescription()));
        coPrix.setCellValueFactory(new PropertyValueFactory<>("Prix"));
        coStock.setCellValueFactory(new PropertyValueFactory<>("Stock"));
     
        // Retrieve publications from the service and populate the table
        ServiceProduit produitService = new ServiceProduit();
        try {
            List<Produit> produits = produitService.recuperer(); 

            tableProduit.getItems().setAll(produits);
        } catch (SQLException ex) {
            System.out.println("Error retrieving publications: " + ex.getMessage());
        }
}
}