package gui;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Phrase;
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
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import com.itextpdf.text.pdf.PdfWriter;
import entities.UserSession;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import services.ServiceProduit;
import utils.PDFMaker;

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
    private TableColumn<Produit, String> coCategorie;
     @FXML
    private TextField availableB_search;
    @FXML
    private TableColumn<Produit, String> coStock;
 ObservableList<Produit> data;
    private Produit p;
      private Produit Selected;
      ServiceProduit su = new ServiceProduit();
        UserSession session = UserSession.getInstance(); 
    private int idloguser = session.getId(); 
    @FXML
    private Button suppbtn1;
    @FXML
    private Button suppbtn2;
    /**
     * Initializes the controller class.
     */
    @Override
public void initialize(URL url, ResourceBundle rb) {
       setBtn();
      try {
          data = FXCollections.observableList(su.recupByIdUser(idloguser));
      } catch (SQLException ex) {
          Logger.getLogger(AfficherProduitController.class.getName()).log(Level.SEVERE, null, ex);
      }
       // Initialiser les colonnes du tableau
   coNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
    coDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
    coPrix.setCellValueFactory(new PropertyValueFactory<>("prix"));
    coStock.setCellValueFactory(new PropertyValueFactory<>("stock"));
    coCategorie.setCellValueFactory(new PropertyValueFactory<>("categories_id"));
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


    private void deletebtn(ActionEvent event) throws SQLException {
        ServiceProduit pc = new ServiceProduit();
        pc.supprimer(p);
        refreshList();
    }
    
    
        public void refreshList() throws SQLException {
       
        data = FXCollections.observableList(su.recupByIdUser(idloguser));

        coNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        coDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        coPrix.setCellValueFactory(new PropertyValueFactory<>("prix"));
        coStock.setCellValueFactory(new PropertyValueFactory<>("stock"));
        tableProduit.setItems(data);

    }

    private void clicked(MouseEvent event) {
        int myIndex =tableProduit.getSelectionModel().getSelectedIndex();
        Selected = tableProduit.getItems().get(myIndex);
        coNom.setText(Selected.getNom());
        coDescription.setText(Selected.getDescription());
        coStock.setText(String.valueOf(Selected.getStock()));
        coPrix.setText(String.valueOf(Selected.getPrix()));


    }
@FXML
    private void supprimerPublication(ActionEvent event) throws SQLException { 
        int myIndex =tableProduit.getSelectionModel().getSelectedIndex();
        Produit selectedPub = tableProduit.getItems().get(myIndex); 
        ServiceProduit publicationService = new ServiceProduit();

               try {
          publicationService.supprimer(selectedPub);
        } catch (SQLException ex) {
            System.out.println("Erreur d'accées a la BD");
        } 
               refreshList();  

    }
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
@FXML
private void pdf(ActionEvent event) throws FileNotFoundException, DocumentException, SQLException {
    PDFMaker pdf = new PDFMaker("listeabonnement.pdf");
    PdfWriter writer = pdf.getWriter();
    Document doc = pdf.getDocument();
    PdfWriter.getInstance(doc, new FileOutputStream("C:\\Users\\admin\\OneDrive\\Bureau\\khalil2.pdf"));
    doc.open();

    PdfPTable table = new PdfPTable(5);
    PdfPCell header1 = new PdfPCell(new Phrase("nom"));
    table.addCell(header1);
    PdfPCell header2 = new PdfPCell(new Phrase("description"));
    table.addCell(header2);
    PdfPCell header3 = new PdfPCell(new Phrase("prix"));
    table.addCell(header3);
    PdfPCell header4 = new PdfPCell(new Phrase("stock"));
    table.addCell(header4);

    ServiceProduit pc = new ServiceProduit();
    List<Produit> prod = pc.recupByIdUser(idloguser);
    prod.stream().map(entry -> {
        PdfPCell cell1 = new PdfPCell(new Phrase(entry.getNom().toString()));
        table.addCell(cell1);
        PdfPCell cell2 = new PdfPCell(new Phrase(entry.getDescription().toString()));
        table.addCell(cell2);
        PdfPCell cell3 = new PdfPCell(new Phrase(String.valueOf(entry.getStock())));
        table.addCell(cell3);
        PdfPCell cell4 = new PdfPCell(new Phrase(String.valueOf(entry.getPrix())));

        return cell4;
    }).forEachOrdered(cell2 -> {
        table.addCell(cell2);
    });

    PdfContentByte cb = writer.getDirectContent();
    cb.moveTo(doc.leftMargin(), doc.bottomMargin());
    cb.lineTo(doc.right() - doc.rightMargin(), doc.bottomMargin());
    cb.stroke();

    Phrase footer = new Phrase("Abonnement information", new Font(Font.FontFamily.HELVETICA, 10, Font.NORMAL));
    ColumnText.showTextAligned(cb, Element.ALIGN_CENTER, footer, (doc.right() - doc.left()) / 2 + doc.leftMargin(), doc.bottom() - 10, 0);

    try {
        doc.add(table);
    } catch (DocumentException ex) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("An error occurred");
        alert.setContentText(ex.toString());
        alert.showAndWait();
    }
    doc.close();
    pdf.closePDF();
}

@FXML
    public void availableSearch() throws SQLException{
        data = FXCollections.observableList(su.recupByIdUser(idloguser));
        FilteredList<Produit> filter = new FilteredList<>(data, e-> true);
        
        availableB_search.textProperty().addListener((Observable, oldValue, newValue) ->{
            
            filter.setPredicate(predicateabonDataBusData ->{
                
                if(newValue.isEmpty() || newValue == null){
                    return true;
                }
                
                String searchKey = newValue.toLowerCase();
//                NOTHING? THEN WE NEED TO DO THIS FIRST
                if(predicateabonDataBusData.getNom().toString().contains(searchKey)){
                    return true;
                }else if(predicateabonDataBusData.getDescription().contains(searchKey)){
                    return true;
                
                }else return false;
                
            });
        });
        
        SortedList<Produit> sortList = new SortedList<>(filter);
        
        sortList.comparatorProperty().bind(tableProduit.comparatorProperty());
        tableProduit.setItems(sortList);
    }


}