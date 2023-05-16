/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import entities.Reclamation;
import java.awt.Desktop;
import java.io.File;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import services.ReclamationService;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;





/**
 * FXML Controller class
 *
 * @author Soulaima_matmati
 */
public class ReclamationController implements Initializable {

    @FXML
    private AnchorPane animalPane;
    @FXML
    private Button Annuler;
    @FXML
    private Label name;
    @FXML
    private Label email;
    @FXML
    private Label subject;
    @FXML
    private Label message;
    @FXML
    private Label etat;
    @FXML
    private Button Modifier;
    
    @FXML
    private Button upload ; 
    

    /**
     * Initializes the controller class.
     */
    
    
    ReclamationService rs = new ReclamationService();
        Reclamation pe = new Reclamation();
    
         public void setReclamation(Reclamation c) {
    
        
    name.setText(c.getName());
    email.setText(c.getEmail());
    subject.setText(c.getSubject());
    message.setText(c.getMessage());
    etat.setText(c.getEtat());
    
    pe.setId(c.getId());
             System.out.println("hedha l id taa reclamtion :" + pe.getId());
    pe.setName(c.getName());
    pe.setEmail(c.getEmail());
    pe.setSubject(c.getSubject());
    pe.setMessage(c.getMessage());
    pe.setEtat(c.getEtat());
}
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
    }    
    @FXML
    private void AnnulerReclamation(ActionEvent event) throws IOException {
              
//        try {
//            ps.supprimer(pe);
//            FXMLLoader loader = new FXMLLoader(getClass().getResource("afficherSiege.fxml"));
//            Parent root = loader.load();
//
//            siegesLignes.getScene().setRoot(root);
//
//        } catch (SQLException ex) {
//            System.out.println(ex.getMessage());
//        }
   
 

        System.out.println("etat " + pe.getEtat());
    if ("non_traitee".equals(pe.getEtat()) || "en_cours".equals(pe.getEtat())){
        try {
           
    
            rs.supprimer(pe);
                 
            BorderPane borderPane = new BorderPane();
            FXMLLoader loader1 = new FXMLLoader(getClass().getResource("AfficherListeReclamtion.fxml"));
            Parent root2 = loader1.load();
             // AfficherSiegeController controller = loader1.getController();

            //controller.setData(pe);
            HBox hbox = new HBox(new Pane(), root2);
            hbox.setSpacing(20);

            borderPane.setRight(hbox);

            

            borderPane.setPadding(new Insets(10, 10, 30, 10));
            email.getScene().setRoot(borderPane);
          
        } catch (SQLException ex) {
            
            System.out.println(ex.getMessage());
        
    }}
    else {
         Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
         alert.setTitle("Annulation Refusée !");
    alert.setHeaderText(null);
    alert.setContentText("Cette Reclamation ne peut pas être annulée !");
     Optional<ButtonType> result = alert.showAndWait();}
    }
 
    @FXML
    private void ModifierReclamation(ActionEvent event) {
        if ("non_traitee".equals(pe.getEtat()) || "en_cours".equals(pe.getEtat())){
        
             try {
             
            System.out.println("modif jawek behi");
                
            BorderPane borderPane = new BorderPane();
            FXMLLoader loader1 = new FXMLLoader(getClass().getResource("ModifierReclamation.fxml"));
            Parent root2 = loader1.load();
              GUI.ModifierReclamationController controller = loader1.getController();

            controller.setData(pe);
            
            HBox hbox = new HBox(new Pane(), root2);
            hbox.setSpacing(20);

            borderPane.setRight(hbox);

            

            borderPane.setPadding(new Insets(10, 10, 30, 10));
            email.getScene().setRoot(borderPane);
            
            
//            FXMLLoader loader = new FXMLLoader(getClass().getResource("modifierSiege.fxml"));
//            Parent root = loader.load();
//            ModifierSiegeController controller = loader.getController();
//
//            controller.setData(pe);
//
//            siegesLignes.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
             
}
             else{
              Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
         alert.setTitle("Modification Refusée !");
    alert.setHeaderText(null);
    alert.setContentText("Cette Reclamation ne peut pas être modifié !");
     Optional<ButtonType> result = alert.showAndWait();}
    }

    /*@FXML
    void telechargerFacture(ActionEvent event) {
        try {
            // Create a new PDF document
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream("reclamation.pdf"));
            document.open();

            // Add some content to the PDF document
            Paragraph heading = new Paragraph("Facture");
            heading.setAlignment(Element.ALIGN_CENTER);
            document.add(heading);

            // Add the order details to the PDF document
            Paragraph orderDetails = new Paragraph("Détails de la Reclamation:");
            document.add(orderDetails);
            for (Map.Entry<Integer, String> entry : Reclamation..entrySet()) {
                Reclamation reclamation = ReclamationService.getById(entry.getKey());
                Paragraph ReclamDetails = new Paragraph(reclamation.getName() + " - Quantité: " + entry.getValue());
                document.add(ReclamDetails);
            }

            // Add the total to the PDF document
            Paragraph total = new Paragraph("Objet: " + subject.getText() + "Message: " + message.getText()+ "Etat: " + etat.getText());
             
            document.add(total);

            // Close the PDF document
            document.close();

            // Show a success message to the user
            showAlert("Reclamation téléchargée", "La reclamation a été téléchargée avec succès.");

        } catch (FileNotFoundException | DocumentException e) {
            // Show an error message if an exception occurs
            showAlert("Erreur", "Une erreur s'est produite lors de la création de la reclamation.");
            e.printStackTrace();
        }
    }*/
     public void pdf(int id) throws SQLException{
         
       
        Reclamation l = new Reclamation();
       
        l = rs.getById(id);
        
         name.setText(l.getName());
         email.setText(l.getEmail());
        
        subject.setText(l.getSubject());
        message.setText(l.getMessage());
       
        etat.setText(l.getEtat());
         
     
     }

    @FXML
    private void Rectelecharger(MouseEvent event) {
        
        Document document = new Document(PageSize.A4);

        try {
            String filePath = "C:\\Users\\Soulaima_matmati\\Desktop\\Reclamation.pdf";
            //System.out.println("Working Directory = " + System.getProperty("C:\\xampp\\htdocs\\Highbrow"));
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(new File(filePath)));
            document.open();
             // Créer un Chunk avec le texte "Nom : " suivi du texte du Label nom
            Chunk nomChunk = new Chunk("Réclamation ");
            nomChunk.setFont(FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12));
            Paragraph nomParagraph = new Paragraph(nomChunk);
            nomParagraph.add(new Chunk(name.getText()));

            nomParagraph.setAlignment(Element.ALIGN_CENTER); // Ajouter cette ligne pour centrer le paragraphe

            

            
             Chunk prenomChunkk = new Chunk(" ");
            prenomChunkk.setFont(FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12));
            Paragraph prenomParagraphh = new Paragraph(prenomChunkk);
            
            prenomParagraphh.add(new Chunk(" "));
            
            
            PdfPTable table = new PdfPTable(2);
            table.setWidthPercentage(100);
            
            PdfPCell cell1 = new PdfPCell(new Phrase("Nom"));
            cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell1.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell1);

            PdfPCell cell2 = new PdfPCell(new Phrase(name.getText()));
            cell2.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cell2.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell2);
            
            PdfPCell cell3 = new PdfPCell(new Phrase("Email"));
            cell3.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell3.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell3);

            PdfPCell cell4 = new PdfPCell(new Phrase(email.getText()));
            cell4.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cell4.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell4);
            
            PdfPCell cell5 = new PdfPCell(new Phrase("Objet"));
            cell5.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell5.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell5);

            PdfPCell cell6 = new PdfPCell(new Phrase(subject.getText()));
            cell6.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cell6.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell6);
            
            
            PdfPCell cell7 = new PdfPCell(new Phrase("Message"));
            cell7.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell7.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell7);

            PdfPCell cell8 = new PdfPCell(new Phrase(message.getText()));
            cell8.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cell8.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell8);
            
            PdfPCell cell9 = new PdfPCell(new Phrase("etat"));
            cell9.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell9.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell9);

            PdfPCell cell10 = new PdfPCell(new Phrase(etat.getText()));
            cell10.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cell10.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell10);
            
            
            // Ajouter les Paragraphs  au Document
            document.add(nomParagraph);
            document.add(prenomParagraphh);
            document.add(table);
            
          
            
            

            document.close();
            writer.close();
            System.out.println("Mme Soulaima Matmati Your PDF has been created successfully !");
        } catch (DocumentException | IOException e) {
            e.printStackTrace();
        }
    }
  
}