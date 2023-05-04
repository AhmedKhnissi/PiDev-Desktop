/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entities.Reclamation;
import java.awt.Desktop;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.sql.SQLException;
import java.util.List;
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
    private Button excel_button ; 
    

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

    @FXML
    private void handleDownloadExcel(ActionEvent event) throws SQLException, IOException {
        // Generate the Excel file and save it to disk
        String filename = "produits.xlsx";
        Path filepath = Paths.get(System.getProperty("user.home"), filename);
        generateExcelFile(filepath.toString());

        // Download the file using the user's default browser
        Desktop.getDesktop().open(filepath.toFile());
    }

    private void generateExcelFile(String filename) throws SQLException {
        // Generate the Excel file and save it to disk using a library like Apache POI
        // Here's an example of how to generate a simple Excel file with three columns (ID, Name and Quantity):
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Produits");
        int rowNum = 0;
        Row headerRow = sheet.createRow(rowNum++);
        headerRow.createCell(0).setCellValue("id");
        headerRow.createCell(1).setCellValue("Name");
        headerRow.createCell(2).setCellValue("Email");
        headerRow.createCell(3).setCellValue("Subject");
        headerRow.createCell(4).setCellValue("Message");
headerRow.createCell(5).setCellValue("etat");


       // produitcrud produitService = new produitcrud();
        for (Reclamation reclamation : rs.recuperer()) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(reclamation.getId());
            row.createCell(1).setCellValue(reclamation.getName());
            row.createCell(2).setCellValue(reclamation.getEmail());
            row.createCell(3).setCellValue(reclamation.getSubject());
            row.createCell(4).setCellValue(reclamation.getMessage());
             row.createCell(5).setCellValue(reclamation.getEtat());
        }
        try {
            FileOutputStream outputStream = new FileOutputStream(filename);
            workbook.write(outputStream);
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

  
}