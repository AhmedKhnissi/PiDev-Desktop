/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author Soulaima_matmati
 */
public class Reclamation {
    private int id;
    private String name,email,subject,message,etat;

    public Reclamation() {
    }

    public Reclamation(String name, String email, String subject, String message, String etat) {
        this.name = name;
        this.email = email;
        this.subject = subject;
        this.message = message;
        this.etat = etat;
    }

    public Reclamation(int id, String name, String email, String subject, String message, String etat) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.subject = subject;
        this.message = message;
        this.etat = etat;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    @Override
    public String toString() {
        return "Reclamation{" + "id=" + id + ", name=" + name + ", email=" + email + ", subject=" + subject + ", message=" + message + ", etat=" + etat + '}';
    }
    
    
    
}
