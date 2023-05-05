/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Adopt;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import utils.MyDB;

/**
 *
 * @author Soulaima_matmati
 */
public class AdoptService implements IService<Adopt> {
    Connection cnx;

    public AdoptService() {
         cnx = MyDB.getInstance().getCnx();
    }

    @Override
    public void ajouter(Adopt t) throws SQLException {
      
        
        String req = "INSERT INTO adopt (id, nom, gender, age, sterelisation, vaccination,image, informations) VALUES(?,?,?,?,?,?,?,?)";
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setInt(1, t.getId() );
        ps.setString(2, t.getNom());
        ps.setString(3, t.getGender());
        ps.setInt(4, t.getAge());
        ps.setBoolean(5,t.isSterelisation());
        ps.setBoolean(6, t.isVaccination());
        ps.setString(7, t.getImage());
        ps.setString(8, t.getInformations());
        ps.executeUpdate();
        
    }

    @Override
    public void modifier(Adopt t) throws SQLException {
         String req = "UPDATE adopt SET nom = ?,gender = ?,age = ?,sterelisation = ?,vaccination= ?,image=?,informations= ? where id = ?";
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setString(1, t.getNom());
        ps.setString(2, t.getGender());
        ps.setInt(3, t.getAge());
        ps.setBoolean(4, t.isSterelisation());
        ps.setBoolean(5, t.isVaccination());
        ps.setString(6, t.getImage());
        ps.setString(7, t.getInformations());
        ps.setInt(8,t.getId());
        ps.executeUpdate();
        System.out.println("Adoption modifié avec succés");
        
        
        
        
    }
    
    
    public void modifierx(Adopt t) throws SQLException {
         String req = "UPDATE adopt SET nom = ?,gender = ?,age = ?,sterelisation = ?,vaccination= ?,informations= ? where id = ?";
        PreparedStatement ps = cnx.prepareStatement(req);
        
        ps.setBoolean(4, true);
        
        ps.setInt(7,7);
        ps.executeUpdate();
        System.out.println("Adoption modifié avec succés");
        
        
        
        
    }

    

    @Override
    public void supprimer(Adopt t) throws SQLException {
         String req = "DELETE FROM adopt where id = ?";
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setInt(1, t.getId());
        ps.executeUpdate();
        System.out.println("Adoption supprimé avec succés");
    }

    @Override
    public List<Adopt> recuperer() throws SQLException {
        List<Adopt> rapport = new ArrayList<>();
    String req = "SELECT * FROM adopt ";
        Statement stm = cnx.createStatement();
        ResultSet rs = stm.executeQuery(req);
    while (rs.next()) {
        Adopt rm = new Adopt();
        rm.setId(rs.getInt("id"));
        rm.setNom(rs.getString("nom"));
        rm.setGender(rs.getString("gender"));
        rm.setAge(rs.getInt("age"));
        rm.setSterelisation(rs.getBoolean("sterelisation"));
        rm.setVaccination(rs.getBoolean("vaccination"));
        rm.setImage(rs.getString("image"));
        rm.setInformations(rs.getString("informations"));
        rapport.add(rm);
    }
    return rapport;
    }
    
}
