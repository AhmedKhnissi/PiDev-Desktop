/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import entities.Animal;
import utils.MyDB;


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
        String req = "INSERT INTO `animal`(`nom`, `poids`, `age`) "
            + "VALUES ('"+t.getNom()+"', '"+t.getPoids()+"', '"+t.getAge()+"')";
        Statement st = cnx.createStatement();
        st.executeUpdate(req);
        System.out.println("Animal ajouté avec succès !");
    }
    

    @Override
    public void modifier(Animal t) throws SQLException {
        String requete = "UPDATE animal SET nom = ?, poids = ?, age = ? WHERE id = ?";
        PreparedStatement pst = cnx.prepareStatement(requete);
        pst.setString(1, t.getNom());
        pst.setFloat(2, t.getPoids());
        pst.setInt(3, t.getAge());
        pst.setInt(4, t.getId());
        pst.executeUpdate();
        System.out.println("Animal modifié avec succès !");
    }

    @Override
    public void supprimer(Animal t) throws SQLException {
        String requete = "DELETE FROM animal WHERE id = ?";
        PreparedStatement pst = cnx.prepareStatement(requete);
        pst.setInt(1, t.getId());
        pst.executeUpdate();
        System.out.println("Animal supprimé avec succès !");
    }

  
    
    @Override
    public List<Animal> recuperer() throws SQLException {
        List<Animal> animals = new ArrayList<>();
        String requete = "SELECT * FROM animal";
        Statement stm = cnx.createStatement();
        ResultSet rs = stm.executeQuery(requete);
        while (rs.next()) {
            Animal t = new Animal();
            t.setId(rs.getInt("id"));
            t.setNom(rs.getString("nom"));
            t.setPoids(rs.getInt("poids"));
            t.setAge(rs.getInt("age"));
            animals.add(t);
        }
        return animals;
    }
    
    
    
    
}
    
    



   
    
    

