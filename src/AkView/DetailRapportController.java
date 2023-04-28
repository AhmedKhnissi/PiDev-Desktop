/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AkView;
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
import entities.Animal;
import entities.RapportMedical;
import entities.user;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import services.AnimalService;
import services.RapportMedicalService;
import services.UserService;

/**
 * FXML Controller class
 *
 * @author heha
 */
public class DetailRapportController implements Initializable {

    @FXML
    private Label nom;
    @FXML
    private Label nomanimal;
    @FXML
    private Label age;
    @FXML
    private Label poids;
    @FXML
    private Label email;
    @FXML
    private Label nomveto;
    @FXML
    private Label prenomveto;
    @FXML
    private ImageView rapport;

    /**
     * Initializes the controller class.
     */
    int idveto;
    AnimalService vs = new AnimalService();
    RapportMedicalService ls = new RapportMedicalService();
    UserService us = new UserService();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
     public void pdf(int idv, int idl) throws SQLException{
         
        Animal v = new Animal();
        user u = new user();
        RapportMedical l = new RapportMedical();
        
         System.out.println("clé etranger"+idv);
         System.out.println("clé primaire"+idl);
        v = vs.recupererAnimalByid(idv);
        l = ls.recupererById(idl);
        
        idveto = v.getAnimals_id();
         System.out.println("hedha id l'véto "+ idveto);
         u = us.recupererUserByid(idveto);
         System.out.println("hedha l user "+ u);
         nomveto.setText(u.getNom());
         prenomveto.setText(u.getPrenom());
        
        nomanimal.setText(v.getNom());
        poids.setText(String.valueOf(v.getPoids()));
       
        age.setText(String.valueOf(v.getAge()));
         System.out.println("age"+v.getAge());
        email.setText(l.getDescription());
     
     }

    @FXML
    private void Rapportlecharger(MouseEvent event) {
        
        Document document = new Document(PageSize.A4);

        try {
            String filePath = "C:\\XAMPP\\htdocs\\img\\RapportMedicale.pdf";
            //System.out.println("Working Directory = " + System.getProperty("C:\\xampp\\htdocs\\Highbrow"));
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(new File(filePath)));
            document.open();
             // Créer un Chunk avec le texte "Nom : " suivi du texte du Label nom
            Chunk nomChunk = new Chunk("Rapport Médicale Appartennant à L'Animal ");
            nomChunk.setFont(FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12));
            Paragraph nomParagraph = new Paragraph(nomChunk);
            nomParagraph.add(new Chunk(nomanimal.getText()));

            nomParagraph.setAlignment(Element.ALIGN_CENTER); // Ajouter cette ligne pour centrer le paragraphe

            

            
             Chunk prenomChunkk = new Chunk(" ");
            prenomChunkk.setFont(FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12));
            Paragraph prenomParagraphh = new Paragraph(prenomChunkk);
            
            prenomParagraphh.add(new Chunk(" "));
            
            
            PdfPTable table = new PdfPTable(2);
            table.setWidthPercentage(100);
            
            PdfPCell cell1 = new PdfPCell(new Phrase("Nom Animal"));
            cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell1.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell1);

            PdfPCell cell2 = new PdfPCell(new Phrase(nomanimal.getText()));
            cell2.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cell2.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell2);
            
            PdfPCell cell3 = new PdfPCell(new Phrase("Poids Animal"));
            cell3.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell3.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell3);

            PdfPCell cell4 = new PdfPCell(new Phrase(poids.getText()));
            cell4.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cell4.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell4);
            
            PdfPCell cell5 = new PdfPCell(new Phrase("Age Animal"));
            cell5.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell5.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell5);

            PdfPCell cell6 = new PdfPCell(new Phrase(age.getText()));
            cell6.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cell6.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell6);
            
            
            PdfPCell cell7 = new PdfPCell(new Phrase("Description Sur Animal"));
            cell7.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell7.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell7);

            PdfPCell cell8 = new PdfPCell(new Phrase(email.getText()));
            cell8.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cell8.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell8);
            
            PdfPCell cell9 = new PdfPCell(new Phrase("Prénom Du Propriétaire Animal"));
            cell9.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell9.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell9);

            PdfPCell cell10 = new PdfPCell(new Phrase(nomveto.getText()));
            cell10.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cell10.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell10);
            
            PdfPCell cell11 = new PdfPCell(new Phrase("Nom Du Propriétaire Animal"));
            cell11.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell11.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell11);

            PdfPCell cell12 = new PdfPCell(new Phrase(prenomveto.getText()));
            cell12.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cell12.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell12);



           

            // Ajouter les Paragraphs  au Document
            document.add(nomParagraph);
            document.add(prenomParagraphh);
            document.add(table);
            
          
            
            

            document.close();
            writer.close();
            System.out.println("Mr Ahmed Khnissi Your PDF has been created successfully !");
        } catch (DocumentException | IOException e) {
            e.printStackTrace();
        }
    }
    
}

    

