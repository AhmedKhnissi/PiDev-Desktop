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
public class Animal {
    private int id,age,poids;
    private String nom;
        private int    categories_animal_id,animals_id;

    public Animal() {
    }

    public Animal(int age, String nom, int poids, int categories_animal_id, int animals_id) {
        this.age = age;
        this.nom = nom;
        this.poids = poids;
        this.categories_animal_id = categories_animal_id;
        this.animals_id = animals_id;
    }

    public Animal(int id, int age, String nom, int poids, int categories_animal_id, int animals_id) {
        this.id = id;
        this.age = age;
        this.nom = nom;
        this.poids = poids;
        this.categories_animal_id = categories_animal_id;
        this.animals_id = animals_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getPoids() {
        return poids;
    }

    public void setPoids(int poids) {
        this.poids = poids;
    }

    public int getCategories_animal_id() {
        return categories_animal_id;
    }

    public void setCategories_animal_id(int categories_animal_id) {
        this.categories_animal_id = categories_animal_id;
    }

    public int getAnimals_id() {
        return animals_id;
    }

    public void setAnimals_id(int animals_id) {
        this.animals_id = animals_id;
    }

    @Override
    public String toString() {
        return "Animal{" + "id=" + id + ", age=" + age + ", nom=" + nom + ", poids=" + poids + ", categories_animal_id=" + categories_animal_id + ", animals_id=" + animals_id + '}';
    }
        
}
