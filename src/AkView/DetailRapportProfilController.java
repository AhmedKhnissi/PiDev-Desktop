/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */





package AkView;
import entities.Animal;
import entities.user;
import java.io.File;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import services.AnimalService;
import services.UserService;

/**
 * FXML Controller class
 *
 * @author heha
 */
public class DetailRapportProfilController implements Initializable {

    @FXML
    private ImageView profileImage;
    @FXML
    private Label nom;
    @FXML
    private Label age;
    @FXML
    private Label poids;
    @FXML
    private Label description;
    @FXML
    private Label nomveto;
    @FXML
    private Label prenomveto;
AnimalService ch = new AnimalService();
    UserService us = new UserService();
    int idVeto;
    /**
     * Initializes the controller class.
     */
    
    public void setpdfDetail(int id) throws SQLException {
        
        Animal c;
        user u;
        try {
        c = ch.recupererAnimalByid(id);
        idVeto = c.getAnimals_id();
        u = us.recupererUserByid(idVeto);
        nom.setText(c.getNom());
        age.setText(String.valueOf(c.getAge()));
        poids.setText(String.valueOf(c.getPoids()));
        nomveto.setText(u.getNom());
        prenomveto.setText(u.getPrenom());
        description.setText("ok");
} catch (SQLException ex) {
            System.out.println("chay ");
    }}

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
 
}
