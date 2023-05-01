package gui;

import entities.Animal;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import services.AnimalService;
import javafx.fxml.FXML;
import javafx.scene.control.Label;








public class AfficherListeAnimalController implements Initializable {
    
    AnimalService animalService = new AnimalService();
    @FXML
    private TableView<Animal> animalTable;
    @FXML
    private TableColumn<Animal, String> nomColumn;
    @FXML
    private TableColumn<Animal, Integer> ageColumn;
    @FXML
    private TableColumn<Animal, Float> poidsColumn;

    private ObservableList<Animal> observableList;
    @FXML
    private TextField tfnom;
    @FXML
    private TextField tfpoids;
    @FXML
    private TextField tfage;
    @FXML
    private Button supprimerBtn;
    @FXML
    private TableColumn<?, ?> idColumn;
    
    private Animal selectedAnimal;
    @FXML
    private Button updateBtn;
    @FXML
    private Button learnmorebtn;
    
    List<Animal> animals = null;


    


private TableView<Animal> tableView;

private ObservableList<Animal> animalList;
private FilteredList<Animal> filteredList;
    @FXML
    private TextField searchfield;

     

@FXML
void learnMoreClicked(ActionEvent event) throws IOException  {
    
    Stage nouveauStage;
        Parent root = FXMLLoader.load(getClass().getResource("LearnMore.fxml"));
        nouveauStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        nouveauStage.setScene(scene);
}

    
    
   public void update(){
       
        try {
            animals = animalService.recuperer();
            observableList = FXCollections.observableArrayList(animals);
            animalTable.setItems(observableList);
        } catch (SQLException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Une erreur s'est Animale lors de la récupération des animaux.");
            alert.setContentText(ex.getMessage());
            alert.showAndWait();
        }
       
        nomColumn.setCellValueFactory(new PropertyValueFactory<>("nom"));
        ageColumn.setCellValueFactory(new PropertyValueFactory<>("age"));
        poidsColumn.setCellValueFactory(new PropertyValueFactory<>("poids"));
        
   }

    public void initialize(URL url, ResourceBundle rb) {
        
        update();
        
         
                }

    
    @FXML
   private void ajouterAnimal(ActionEvent event) {
    
    String nom = tfnom.getText();
    String ageText = tfage.getText();
    String poidsText = tfpoids.getText();

    
    if (nom.trim().isEmpty() || ageText.trim().isEmpty() || poidsText.trim().isEmpty()) {
        showAlert(Alert.AlertType.ERROR, "Veuillez remplir tous les champs.");
        return;
    }

    
    if (!nom.matches("[a-zA-Z]+")) {
        showAlert(Alert.AlertType.ERROR, "Le nom ne peut contenir que des lettres.");
        return;
    }

  
    int age = 0;
    float poids = 0.0f;
    try {
        age = Integer.parseInt(ageText);
        poids = Float.parseFloat(poidsText);
    } catch (NumberFormatException e) {
        showAlert(Alert.AlertType.ERROR, "Veuillez entrer des valeurs numériques valides pour l'âge et le poids.");
        return;
    }

    
    if (age < 0 || poids < 0.0f) {
        showAlert(Alert.AlertType.ERROR, "L'âge et le poids ne peuvent pas être négatifs.");
        return;
    }

    // Add the animal to the database
    try {
        Animal animal = new Animal(nom, age, poids);
        animalService.ajouter(animal);
        showAlert(Alert.AlertType.INFORMATION, "Animal ajouté avec succès.");
        clearFields();
        update();
    } catch (SQLException e) {
        showAlert(Alert.AlertType.ERROR, "Erreur lors de l'ajout de l'animal.");
        e.printStackTrace();
    }
}



    private void showAlert(Alert.AlertType alertType, String message) {
        Alert alert = new Alert(alertType);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void clearFields() {
        tfnom.setText("");
        tfpoids.setText("");
        tfage.setText("");
    }

    @FXML
    private void clicked(MouseEvent event) {
        int myIndex =animalTable.getSelectionModel().getSelectedIndex();
        selectedAnimal = animalTable.getItems().get(myIndex);
        tfnom.setText(selectedAnimal.getNom());
        tfage.setText(String.valueOf(selectedAnimal.getAge()));
        tfpoids.setText(String.valueOf(selectedAnimal.getPoids()));
        
        
    }

    @FXML
    private void supprimerAnimal(ActionEvent event) {
    
    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    alert.setTitle("Confirmation de suppression");
    alert.setHeaderText("Êtes-vous sûr de vouloir supprimer l'animal sélectionné ?");
    alert.setContentText("Cette action est irréversible.");

    Optional<ButtonType> result = alert.showAndWait();
    if (result.isPresent() && result.get() == ButtonType.OK) {
        try {
            animalService.supprimer(selectedAnimal);
        } catch (SQLException ex) {
            System.out.println("oupsie");
        }
        update();
        clearFields();
    }
}


    @FXML
    private void updateClicked(ActionEvent event) {
    
    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    alert.setTitle("Confirmation de modification");
    alert.setHeaderText("Êtes-vous sûr de vouloir modifier l'animal sélectionné ?");
    alert.setContentText("Cette action est irréversible.");

    Optional<ButtonType> result = alert.showAndWait();
    if (result.isPresent() && result.get() == ButtonType.OK) {
        Animal an = new Animal(Integer.valueOf(tfage.getText()), tfnom.getText(), Float.parseFloat(tfpoids.getText()));
        an.setId(selectedAnimal.getId());
        try {
            animalService.modifier(an);
        } catch (SQLException ex) {
            Logger.getLogger(AfficherListeAnimalController.class.getName()).log(Level.SEVERE, null, ex);
        }
        update();
        clearFields();
    }
}


@FXML
private void handleSearch(ActionEvent event) {
    String searchTerm = searchfield.getText();
    if (searchTerm == null || searchTerm.trim().isEmpty()) {
        // No search term entered, show all animals
        update();
    } else {
        // Filter animals based on search term
        List<Animal> filteredAnimals = animals.stream()
                .filter(a -> a.getNom().contains(searchTerm))
                .collect(Collectors.toList()); 
        animalTable.setItems(FXCollections.observableArrayList(filteredAnimals));
    }
}


   

}



