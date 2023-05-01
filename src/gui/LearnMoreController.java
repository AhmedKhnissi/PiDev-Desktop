package gui;

import java.util.ResourceBundle;
import javafx.application.Platform;

import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import org.json.JSONException;
import org.json.JSONArray;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class LearnMoreController implements Initializable {

  
    @FXML
    private Label animalInfoLabel;
    @FXML
    private Button weatherButton;
    @FXML
    private Label animalInfoLabel1;
    @FXML
    private Button infoAnimalButton;
    @FXML
    private Button infoAnimalButton1;
    @FXML
    private Label catlabel;
    @FXML
    private TextField animalsearch;

    
    @FXML
    private ImageView catImage;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // your existing code here...

        catImage.setImage(new Image("file:/Users/Haamdi/Desktop/cat.png"));
    }

 

    @FXML
    private void weatherButtonClicked(ActionEvent event) throws JSONException {
    String apiKey = "c71834a2083f18f3ee5844ac1ea6c4f7";
    String city = "Ariana";
    String units = "metric"; // Add this line to specify temperature in Celsius
String urlString = "https://api.openweathermap.org/data/2.5/weather?q=" + city + "&units=" + units + "&appid=" + apiKey;
    

    try {
        URL url = new URL(urlString);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine;
        StringBuilder response = new StringBuilder();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }

        in.close();

        JSONObject json = new JSONObject(response.toString());
        JSONObject main = json.getJSONObject("main");
        Double temp = main.getDouble("temp");
        JSONArray weather = json.getJSONArray("weather");
        JSONObject weatherObject = weather.getJSONObject(0);
        String description = weatherObject.getString("description");

        String message = "Current temperature in " + city + " is " + temp + "°C with " + description;
        animalInfoLabel.setText(message);
    } catch (IOException e) {
        e.printStackTrace();
        animalInfoLabel.setText("Failed to retrieve weather data");
    }
}
    @FXML
private void infoAnimalButtonClicked(ActionEvent event) throws IOException {
    String apiUrl = "https://api.api-ninjas.com/v1/animals";
    String apiKey = "Bz5Nx7bVhhAP6ER4ZFCVuD8IN1Q2USlPfgUeHLya"; // Replace this with your actual API key
    String animalName = animalsearch.getText();

    try {
        URL url = new URL(apiUrl + "?name=" + animalName);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("accept", "application/json");
        connection.setRequestProperty("x-api-key", apiKey);

        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine;
        StringBuilder response = new StringBuilder();

        System.out.println(in.readLine());
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }

        in.close();

        JSONObject json = new JSONObject(response.toString());
        
        System.out.println(json.toString());
        JSONArray animals = json.getJSONArray("animals");
        System.out.println(animals.get(0));

        StringBuilder message = new StringBuilder();
        message.append("Results for: ").append(animalName).append("\n");
        

        for (int i = 0; i < animals.length(); i++) {
            JSONObject animal = animals.getJSONObject(i);
            String name = animal.getString("name");

            if (name.toLowerCase().equals(animalName.toLowerCase())) {
                JSONObject characteristics = animal.getJSONObject("characteristics");
                String location = characteristics.getString("location");
                String prey = characteristics.getString("prey");
                String diet = characteristics.getString("diet");
                message.append("- Location: ").append(location).append("\n");
                message.append("- Prey: ").append(prey).append("\n");
                message.append("- Diet: ").append(diet).append("\n");
            }
        }

        Platform.runLater(() -> animalInfoLabel1.setText(message.toString()));
    } catch (IOException | JSONException e) {
        Platform.runLater(() -> animalInfoLabel1.setText("Failed to retrieve animal data"));
        System.out.println(e.getMessage());
    }
}


   

   @FXML
private void getInfoCat(ActionEvent event) throws JSONException {
    String apiUrl = "https://api.thecatapi.com/v1/breeds";

    try {
        URL url = new URL(apiUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("x-api-key", "live_jyHNUK3kvHxmOUzKdzLOBghGPLJPTZLwWFiVS5evDaA8w2YRADOTnHZNJ8yKK2B8");

        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine;
        StringBuilder response = new StringBuilder();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }

        in.close();

        JSONArray jsonArray = new JSONArray(response.toString());
        int randomIndex = (int) Math.floor(Math.random() * jsonArray.length());
        JSONObject breedObject = jsonArray.getJSONObject(randomIndex);

        String breedName = breedObject.getString("name");
        String description = breedObject.getString("description");

        String message = "Random cat breed: " + breedName + "\nDescription: " + description;
        catlabel.setText(message);
    } catch (IOException e) {
        e.printStackTrace();
        catlabel.setText("Failed to retrieve cat data");
    }
}












}
