/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package myvet.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import myvet.entities.User;
import myvet.utils.MaConnection;

/**
 *
 * @author user
 */
public class UserService implements Iservice<User> {
 Connection cnx;
    
    public UserService() {
        cnx = MaConnection.getInstance().getCnx();
    }
    @Override
    public void ajouter(User t) throws SQLException {
         String req = "INSERT INTO `user`(`nom`, `prenom`, `email`,`password`, `pays`, `gouvernorat`,`ville`, `rue`, `telephone`,`bloque`,`demande_acces`,`roles`) "
                + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement ps = cnx.prepareStatement(req);
        
        ps.setString(1, t.getNom());
        ps.setString(2, t.getPrenom());
        ps.setString(3, t.getEmail());
        ps.setString(4, t.getPassword());
        ps.setString(5, t.getPays());
        ps.setString(6, t.getGouvernorat());
        ps.setString(7, t.getVille());
        ps.setString(8, t.getRue());
        ps.setString(9, t.getTel());
        ps.setInt(10, 1);
        ps.setInt(11, 1);
        ps.setString(12, "[\"ROLE_VETERINAIRE\"]");

        
        ps.executeUpdate();
        
        System.out.println("veterinaire ajouté !");
    }

    @Override
    public void modifier(User t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void supprimer(int id) throws SQLException {
        System.out.println(" fil fct "+id);
      String req;
     req = "DELETE FROM user WHERE id =?";
             System.out.println(" teht delete ");

        PreparedStatement ps = cnx.prepareStatement(req);
                     System.out.println(" thet prepare ");

        ps.setInt(1, id);
                     System.out.println(" teht parametre ");

        ps.executeUpdate();
                             System.out.println(" teht exexxxx ");

        
    }

   

    @Override
    public void ajouter_prop(User t) throws SQLException {
             String req = "INSERT INTO `user`(`nom`, `prenom`, `email`,`password`, `pays`, `gouvernorat`,`ville`, `rue`, `telephone`,`roles`) "
                + "VALUES (?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement ps = cnx.prepareStatement(req);
        
        ps.setString(1, t.getNom());
        ps.setString(2, t.getPrenom());
        ps.setString(3, t.getEmail());
        ps.setString(4, t.getPassword());
        ps.setString(5, t.getPays());
        ps.setString(6, t.getGouvernorat());
        ps.setString(7, t.getVille());
        ps.setString(8, t.getRue());
        ps.setString(9, t.getTel());
       
        ps.setString(10, "[\"ROLE_PROPRIETAIRE\"]");

        
        ps.executeUpdate();
        
        System.out.println("prop ajouté !");
    }
    

    @Override
    public void ajouter_magasin(User t) throws SQLException {
         String req = "INSERT INTO `user`(`nom`,`email`,`password`, `pays`, `gouvernorat`,`ville`, `rue`, `telephone`,`bloque`,`demande_acces`,`roles`) "
                + "VALUES (?,?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement ps = cnx.prepareStatement(req);
        
        ps.setString(1, t.getNom());
        ps.setString(2, t.getEmail());
        ps.setString(3, t.getPassword());
        ps.setString(4, t.getPays());
        ps.setString(5, t.getGouvernorat());
        ps.setString(6, t.getVille());
        ps.setString(7, t.getRue());
        ps.setString(8, t.getTel());
        ps.setInt(9, 1);
        ps.setInt(10, 1);
        ps.setString(11, "[\"ROLE_MAGASIN\"]");

        
        ps.executeUpdate();
        
        System.out.println("magasin ajouté !");    }

    @Override
    public List<User> recuperer_veterinaires() throws SQLException {
   List<User> veterinaires = new ArrayList<>();
        String sql= "SELECT * FROM user where roles=?";
        try (PreparedStatement stmt = cnx.prepareStatement(sql)) {
                    stmt.setString(1, "[\"ROLE_VETERINAIRE\"]");

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String nom = rs.getString("nom");
                String prenom = rs.getString("prenom");
                String email = rs.getString("email");
                String tel = rs.getString("telephone");
                String pays = rs.getString("pays");
                String gouvernorat = rs.getString("gouvernorat");
                String ville = rs.getString("ville");
                String rue = rs.getString("rue");
                veterinaires.add(new User(id, nom, prenom, email, pays, gouvernorat, ville, rue,tel));
            }
        }
        return veterinaires;   
    }

    @Override
    public List<User> recuperer_magasins() throws SQLException {
List<User> veterinaires = new ArrayList<>();
        String sql= "SELECT * FROM user where roles=?";
        try (PreparedStatement stmt = cnx.prepareStatement(sql)) {
                    stmt.setString(1, "[\"ROLE_MAGASIN\"]");

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String nom = rs.getString("nom");
                String prenom = rs.getString("prenom");
                String email = rs.getString("email");
                String tel = rs.getString("telephone");
                String pays = rs.getString("pays");
                String gouvernorat = rs.getString("gouvernorat");
                String ville = rs.getString("ville");
                String rue = rs.getString("rue");
                veterinaires.add(new User(id, nom, prenom, email, pays, gouvernorat, ville, rue,tel));
            }
        }
        return veterinaires;   
    
    }    

    @Override
    public List<User> recuperer_proprietaires() throws SQLException {
   List<User> veterinaires = new ArrayList<>();
        String sql= "SELECT * FROM user where roles=?";
        try (PreparedStatement stmt = cnx.prepareStatement(sql)) {
                    stmt.setString(1, "[\"ROLE_PROPRIETAIRE\"]");

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String nom = rs.getString("nom");
                String prenom = rs.getString("prenom");
                String email = rs.getString("email");
                String tel = rs.getString("telephone");
                String pays = rs.getString("pays");
                String gouvernorat = rs.getString("gouvernorat");
                String ville = rs.getString("ville");
                String rue = rs.getString("rue");
                veterinaires.add(new User(id, nom, prenom, email, pays, gouvernorat, ville, rue,tel));
            }
        }
        return veterinaires;   
    
    }

    @Override
    public List<User> recuperer_demande_acces() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

   
    
}
