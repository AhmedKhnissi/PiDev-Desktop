/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.sql.Connection;
import utils.MyDB;
import entities.Animal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import services.IService;

/**
 *
 * @author heha
 */
public class AnimalService implements IService<Animal>{
    Connection cnx;

    public AnimalService() {
        cnx = MyDB.getInstance().getCnx();
    }

    @Override
    public void ajouter(Animal t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void modifier(Animal t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void supprimer(Animal t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Animal> recuperer() throws SQLException {
        List<Animal> animaux = new ArrayList<>();
    String req = "SELECT * FROM Animal";
        Statement stm = cnx.createStatement();
        ResultSet rs = stm.executeQuery(req);
    while (rs.next()) {
        Animal animal = new Animal();
        animal.setId(rs.getInt("id"));
        animal.setNom(rs.getString("nom"));
        animaux.add(animal);
    }
    return animaux;
    }
    
    public Animal recupererAnimalByid(int id) throws SQLException {

        Animal p= new Animal();
        String req = "select * from animal where id = ? ";
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {

           p.setNom(rs.getString("nom"));
            p.setAge(rs.getInt("age"));
            p.setPoids(rs.getInt("poids"));
            p.setAnimals_id(rs.getInt("animals_id"));
            

        }
        return p;

    }

    @Override
    public Animal getById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    

   
    
    
}
