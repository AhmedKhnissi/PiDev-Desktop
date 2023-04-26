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
public class RapportMedical {
    private int id,animal_id;
    private String description;

    public RapportMedical() {
    }

    public RapportMedical(int animal_id, String description) {
        this.animal_id = animal_id;
        this.description = description;
    }

    public RapportMedical(int id, int animal_id, String description) {
        this.id = id;
        this.animal_id = animal_id;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAnimal_id() {
        return animal_id;
    }

    public void setAnimal_id(int animal_id) {
        this.animal_id = animal_id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "RapportMedical{" + "id=" + id + ", animal_id=" + animal_id + ", description=" + description + '}';
    }
    
    
}
