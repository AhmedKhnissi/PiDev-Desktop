/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */ 

/**
package gui;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.SwingNode;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javax.swing.SwingUtilities;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;



/**
 * FXML Controller class
 *
 * @author user
 */ 
/**
public class UserstatistiqueController implements Initializable {

    @FXML
    private AnchorPane chartpanepie;
    private static final String VET = "Vtrinaires";
    private static final String PROP = "Propritaires danimaux";
        private static final String MAG = "Magasin";

    /**
     * Initializes the controller class.
     */ 
/**
    @Override
    public void initialize(URL url, ResourceBundle rb) {
                // TODO
          try{
        // Create a SwingNode to host the JFreeChart panel
        SwingNode chartNode = new SwingNode();
          // Create the chart and dataset
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        //dataset.addValue(100,PROP,"");
       // dataset.addValue(60, MAG, "");
        //dataset.addValue(100, VET, "");
        JFreeChart chart = ChartFactory.createBarChart("Nombre", null, null, dataset);
        ChartPanel chartPanel = new ChartPanel(chart);
        
        // Add the JFreeChart panel to the SwingNode
        SwingUtilities.invokeLater(() -> {
        chartNode.setContent(chartPanel);
        });
        
        
        chartpanepie.getChildren().add(chartNode);
         } catch (Exception ex) {
             System.out.print(ex+"  errrrrrrreurrrrrrrrrrrrrrrrrrrr");
        }
        
    }    
    
}
*/