/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.user;
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
 * @author heha
 */
public class UserService implements IService<user>{
    Connection cnx;

    public UserService() {
        cnx = MyDB.getInstance().getCnx();
    }

    @Override
    public void ajouter(user t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void modifier(user t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void supprimer(user t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<user> recuperer() throws SQLException {
List<user> veterinaire = new ArrayList<>();
        String s = "select * from user where roles = ?";
        PreparedStatement st = cnx.prepareStatement(s);
        st.setString(1, "[\"ROLE_VETERINAIRE\"]");
        ResultSet rs =  st.executeQuery();
        user m = new user();
        while(rs.next()){
            
            m.setId(rs.getInt("id"));
            m.setTelephone(rs.getInt("telephone"));
            m.setNom(rs.getString("nom"));
            m.setPrenom(rs.getString("prenom"));
            m.setGouvernorat(rs.getString("gouvernorat"));
            m.setEMail(rs.getString("email"));
            m.setPays(rs.getString("pays"));
            m.setVille(rs.getString("ville"));
            m.setRue(rs.getString("rue"));
            m.setRoles(rs.getString("roles"));
            
            
            veterinaire.add(m);
            
        }
        return veterinaire;    }
    
       public user recupererUserByid(int id) throws SQLException {

        user p= new user();
        String req = "select * from user where id = ? ";
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {

           p.setEMail(rs.getString("email"));
            p.setNom(rs.getString("nom"));
            p.setPrenom(rs.getString("prenom"));
          
            p.setPays(rs.getString("pays"));
            
            p.setGouvernorat(rs.getString("gouvernorat"));
            
            p.setVille(rs.getString("ville"));
            p.setId(rs.getInt("id"));

        }
        return p;

    }
    
}
