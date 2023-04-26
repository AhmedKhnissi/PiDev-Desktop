/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;
import entities.user;
import java.net.URL;
import java.sql.SQLException;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.SwingNode;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
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
public class StatistiquesController implements Initializable {

    @FXML
    private AnchorPane chartPane;
    private JPanel chartPanel;
    
    private JFreeChart chart;
    
    RendezVousService ls = new RendezVousService();
    UserService vs = new UserService();
    user v = new user();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        TreeMap<Integer,Integer> nbrVeterinaireByRdv;
        try {
            nbrVeterinaireByRdv = ls.nombreVetoParRdv();
            DefaultPieDataset dataset = new DefaultPieDataset();
             for (Map.Entry<Integer, Integer> entry : nbrVeterinaireByRdv.entrySet()) {
            Integer key = entry.getKey();
            v=vs.recupererUserByid(key);
            int value = entry.getValue();
           dataset.setValue(v.getGouvernorat(), value);
           
            JFreeChart chart = ChartFactory.createPieChart(
                    "Villes Avec Les Plus De Rendez-Vous", // 
                    dataset, // Dataset
                    true, // 
                    true, // 
                    false // 
            );
            
            chartPanel = new ChartPanel(chart);
            chartPanel.setPreferredSize(new java.awt.Dimension(450, 400));
            SwingNode swingNode = new SwingNode();

          
            swingNode.setContent(chartPanel);

            
            chartPane.getChildren().add(swingNode);
           
        }} catch (SQLException ex) {
            Logger.getLogger(StatistiquesController.class.getName()).log(Level.SEVERE, null, ex);
        }
          
        }
    
     
    }    
    

