package gui;
import entities.Produit;
import java.awt.event.MouseEvent;
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
    private Button suppbtn;
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
 ObservableList<Produit> data;
    private Produit p;
      private Produit Selected;
      ServiceProduit su = new ServiceProduit();
      
    /**
     * Initializes the controller class.
     */
    @Override
public void initialize(URL url, ResourceBundle rb) {
       setBtn();
      try {
          data = FXCollections.observableList(su.recuperer());
      } catch (SQLException ex) {
          Logger.getLogger(AfficherProduitController.class.getName()).log(Level.SEVERE, null, ex);
      }
       // Initialiser les colonnes du tableau
   coNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
    coDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
    coPrix.setCellValueFactory(new PropertyValueFactory<>("prix"));
    coStock.setCellValueFactory(new PropertyValueFactory<>("stock"));

        tableProduit.setItems(data);

        tableProduit.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                p = newSelection;
                setBtn();
            }

        });
   
}

  private void setBtn() {
        if (p == null) {
            suppbtn.setDisable(true);
            
        } else {
            suppbtn.setDisable(false);
           
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


      @FXML
    private void deletebtn(ActionEvent event) throws SQLException {
        ServiceProduit pc = new ServiceProduit();
        pc.supprimer(p);
        refreshList();
    }
    
    
        public void refreshList() throws SQLException {
       
        data = FXCollections.observableList(su.recuperer());

        coNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        coDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        coPrix.setCellValueFactory(new PropertyValueFactory<>("prix"));
        coStock.setCellValueFactory(new PropertyValueFactory<>("stock"));
        tableProduit.setItems(data);

    }

    @FXML
    private void clicked(MouseEvent event) {
        int myIndex =tableProduit.getSelectionModel().getSelectedIndex();
        Selected = tableProduit.getItems().get(myIndex);
        coNom.setText(Selected.getNom());
        coDescription.setText(Selected.getDescription());
        coStock.setText(String.valueOf(Selected.getStock()));
        coPrix.setText(String.valueOf(Selected.getPrix()));


    }

    @FXML
    private void supprimerAnimal(ActionEvent event) throws SQLException {

    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    alert.setTitle("Confirmation de suppression");
    alert.setHeaderText("Êtes-vous sûr de vouloir supprimer l'animal sélectionné ?");
    alert.setContentText("Cette action est irréversible.");

    Optional<ButtonType> result = alert.showAndWait();
    if (result.isPresent() && result.get() == ButtonType.OK) {
        try {
            su.supprimer(Selected);
        } catch (SQLException ex) {
            System.out.println("oupsie");
        }
       refreshList();
    }
}
    
    
    
   
}