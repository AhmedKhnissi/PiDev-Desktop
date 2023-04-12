/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author heha
 */
public class RendezVous {
    private int id,user_id;
    private String date,heure,raeanimal,nomanimal,decision;

    public RendezVous() {
    }

    public RendezVous(int user_id, String decision, String date, String heure, String raeanimal, String nomanimal) {
        this.user_id = user_id;
        this.decision = decision;
        this.date = date;
        this.heure = heure;
        this.raeanimal = raeanimal;
        this.nomanimal = nomanimal;
    }

    public RendezVous(int id, int user_id, String decision, String date, String heure, String raeanimal, String nomanimal) {
        this.id = id;
        this.user_id = user_id;
        this.decision = decision;
        this.date = date;
        this.heure = heure;
        this.raeanimal = raeanimal;
        this.nomanimal = nomanimal;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getDecision() {
        return decision;
    }

    public void setDecision(String decision) {
        this.decision = decision;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getHeure() {
        return heure;
    }

    public void setHeure(String heure) {
        this.heure = heure;
    }

    public String getRaeanimal() {
        return raeanimal;
    }

    public void setRaeanimal(String raeanimal) {
        this.raeanimal = raeanimal;
    }

    public String getNomanimal() {
        return nomanimal;
    }

    public void setNomanimal(String nomanimal) {
        this.nomanimal = nomanimal;
    }

    @Override
    public String toString() {
        return "RendezVous{" + "id=" + id + ", user_id=" + user_id + ", decision=" + decision + ", date=" + date + ", heure=" + heure + ", raeanimal=" + raeanimal + ", nomanimal=" + nomanimal + '}';
    }
    
}
