/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Animal;
import java.sql.SQLException;
import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import services.AnimalService;

public class AjouterAnimalController {
    @FXML
    private TextField tfnom;
    @FXML
    private TextField tfpoids;
    @FXML
    private TextField tfage;

    private AnimalService animalService = new AnimalService();

    @FXML
    private void ajouterAnimal(ActionEvent event) {
      
        String nom = tfnom.getText();
        int age = Integer.parseInt(tfage.getText());
        float poids = Float.parseFloat(tfpoids.getText());

         try {
            Animal animal = new Animal(nom,age,poids);
            animalService.ajouter(animal);
            showAlert(AlertType.INFORMATION, "Animal ajouté avec succès.");
            clearFields();
            
       } catch (SQLException e) {
            showAlert(AlertType.ERROR, "Erreur lors de l'ajout de l'animal.");
            e.printStackTrace();
        }
    }

    private void showAlert(AlertType alertType, String message) {
        Alert alert = new Alert(alertType);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void clearFields() {
        tfnom.setText("");
        tfpoids.setText("");
        tfage.setText("");
    }
}



