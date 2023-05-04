/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.User;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.SwingNode;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import services.RendezVousService;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import services.UserService;

/**
 * FXML Controller class
 *
 * @author heha
 */
public class UserstatistiqueController implements Initializable {

    @FXML
    private AnchorPane chartPane;
    private JPanel chartPanel;
    
    private JFreeChart chart;
    
 


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
          UserService us=new UserService();

        // TODO
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        try {
            dataset.addValue(us.recuperer_veterinaires().size(),"vétérinaire","");
            dataset.addValue(us.recuperer_magasins().size(),"magasin","");
            dataset.addValue(us.recuperer_proprietaires().size(),"propriétaire","");

        } catch (SQLException ex) {
            Logger.getLogger(UserstatistiqueController.class.getName()).log(Level.SEVERE, null, ex);
        }


        chart = ChartFactory.createBarChart(
                "nombres des utilisateurs", //
                null, //
                null, //
                dataset //
                
        );
        chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(450, 400));
        SwingNode swingNode = new SwingNode();
        swingNode.setContent(chartPanel);
        chartPane.getChildren().add(swingNode);
          
        }

    @FXML
    private void retour_acceuil(ActionEvent event) {
                          try{
        Stage nouveauStage;
        Parent root = FXMLLoader.load(getClass().getResource("NavbarAdmin.fxml"));
        nouveauStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        nouveauStage.setScene(scene);
        }catch(IOException ex){
          System.out.println("nooooooooooooooooooooooooooooooooooooooooooonnnnnnnnnnn");
        }
    }
    
     
    }    
    

