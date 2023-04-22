/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Animal;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import services.AnimalService;

/**
 * FXML Controller class
 *
 * @author Haamdi
 */
public class ModifierAnimalController implements Initializable {
    private Animal animal;
    @FXML
    private TextField ednom;
    @FXML
    private TextField edpoids;
    @FXML
    private TextField edage;

    private AnimalService animalService = new AnimalService();


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
       
    }  
    public void setAnimal(Animal animal) {
        this.animal = animal;
        ednom.setText(animal.getNom());
        edage.setText(Integer.toString(animal.getAge()));
        edpoids.setText(Float.toString(animal.getPoids()));
        
    }
    
    @FXML
    private void modifierAnimal() {
        String nom = ednom.getText();
        int age = Integer.parseInt(edage.getText());
        Float poids = Float.parseFloat(edpoids.getText());
        
        
        animal.setNom(nom);
        animal.setAge(age);
        animal.setPoids((float) poids);

        
        AnimalService serviceAnimal = new AnimalService();
        try {
            serviceAnimal.modifier(animal);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Succès");
            alert.setHeaderText("Le Animal a été modifié avec succès.");
            alert.showAndWait();
        } catch (SQLException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Une erreur s'est Animale lors de la modification du Animal.");
            alert.setContentText(ex.getMessage());
            alert.showAndWait();
        }
    }
    
}
