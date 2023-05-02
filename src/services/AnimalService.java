/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Animal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import entities.User;
import entities.UserSession;
import utils.MyDB;


/**
 *
 * @author heha
 */

public class AnimalService implements IService<Animal>{
    UserSession session = UserSession.getInstance(); 
    private int idloguser = session.getId();
    Connection cnx;

    public AnimalService() {
        cnx = MyDB.getInstance().getCnx();
    }

    @Override
    public void ajouter(Animal t) throws SQLException {
        String req = "INSERT INTO `animal`(`nom`, `poids`, `age`,`animals_id`) "
            + "VALUES ('"+t.getNom()+"', '"+t.getPoids()+"', '"+t.getAge()+"','"+UserSession.getInstance().getId()+"')";
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
    
    public List<Animal> getanimalsbyIdUser(int iduser) throws SQLException {
        List<Animal> animals = new ArrayList<>();
        User user = new User();
        String requete = "SELECT * FROM animal where animals_id = ? ";
        PreparedStatement stm = cnx.prepareStatement(requete);
        stm.setInt(1, iduser);
        ResultSet rs = stm.executeQuery();
        while (rs.next()) {
            Animal t = new Animal();
            t.setId(rs.getInt("id"));
            t.setNom(rs.getString("nom"));
            t.setPoids(rs.getInt("poids"));
            t.setAge(rs.getInt("age"));
            user.setId(rs.getInt("animals_id"));  
            t.setUser(user);
            animals.add(t);
        }
        return animals;
    }
    
    
    
    
}
    
    



   
    
    

