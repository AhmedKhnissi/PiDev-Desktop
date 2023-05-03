/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.sql.Date;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Khalil
 */
public class Commentaire { 
    private int id; 
    private Publication publication ;   
    private int pub_id ; 
    private User user ;  
    private int user_id; 
    private String contenu; 
    private Date datetime;  

    public Commentaire() {
    }

    public Commentaire(int id, Publication publication, User user, String contenu, Date datetime) {
        this.id = id;
        this.publication = publication;
        this.user = user;
        this.contenu = contenu;
        this.datetime = datetime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Publication getPublication() {
        return publication;
    }
    public int getPublicationID() {
        return publication.getId();
    } 

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public void setPublication(Publication publication) {
        this.publication = publication;
    }
 public void setPublicationId(int pub_id) {
    this.pub_id = pub_id;
}

    public User getUser() {
        return user;
    }
    

    public void setUser(User user) {
        this.user = user;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String conten) {
        this.contenu = conten;
    }

    public Date getDatetime() {
        return datetime;
    }

    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }
    

    @Override
    public String toString() {
        return "Commentaire{" + "id=" + id + ", publication=" + publication + ", user=" + user + ", conten=" + contenu + ", datetime=" + datetime + '}';
    }

    public Commentaire(int pub_id, String contenu, Date datetime) {
        this.pub_id = pub_id;
        this.contenu = contenu;
        this.datetime = datetime;
    }
    public Commentaire(Publication publication, String contenu, Date datetime) {
        this.publication = publication;
        this.contenu = contenu;
        this.datetime = datetime;
    }
     
 public boolean hasProfanity() {
    try {
        String encodedContenu = URLEncoder.encode(contenu, "UTF-8");
        URL url = new URL("https://www.purgomalum.com/service/json?text=" + encodedContenu);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection(); 
        connection.setRequestMethod("GET");
        connection.setRequestProperty("accept", "application/json");
        InputStream responseStream = connection.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(responseStream));
        StringBuilder responseBuilder = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            responseBuilder.append(line);
        }
        reader.close();
        String response = responseBuilder.toString();
        JSONObject jsonObject = new JSONObject(response);
        String result = jsonObject.getString("result");
        return result.contains("*");
    } catch (IOException | JSONException e) {
        e.printStackTrace();
        return false;
    }
} 
} 



