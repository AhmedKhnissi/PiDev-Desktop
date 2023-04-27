/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.Alert;
import entities.User;
import entities.UserSession;
import utils.MaConnection;

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
         String req = "INSERT INTO `user`(`nom`, `prenom`, `email`,`password`, `pays`, `gouvernorat`,`ville`, `rue`, `telephone`,`bloque`,`demande_acces`,`roles`,`permistravail`) "
                + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";
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
        ps.setInt(10, 0);
        ps.setInt(11, 1);
        ps.setString(12, "[\"ROLE_VETERINAIRE\"]");
        ps.setString(13, t.getPermistravail());

        
        ps.executeUpdate();
        
        System.out.println("veterinaire ajouté !");
    }

    @Override
    public void modifier(User u,String email) throws SQLException {
            String req = "UPDATE user SET email=?,nom=?,prenom=? where email = ?";
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setString(1, u.getEmail());
        ps.setString(2, u.getNom());
        ps.setString(3, u.getPrenom());
        ps.setString(4, email);

  
        ps.executeUpdate();
        
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
             String req = "INSERT INTO `user`(`nom`, `prenom`, `email`,`password`, `pays`, `gouvernorat`,`ville`, `rue`, `telephone`,`roles`,`bloque`,`demande_acces`) "
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
       
        ps.setString(10, "[\"ROLE_PROPRIETAIRE\"]");
        ps.setInt(11, 0);
        ps.setInt(12, 0);

        
        ps.executeUpdate();
        
        System.out.println("prop ajouté !");
    }
    

    @Override
    public void ajouter_magasin(User t) throws SQLException {
         String req = "INSERT INTO `user`(`nom`,`email`,`password`, `pays`, `gouvernorat`,`ville`, `rue`, `telephone`,`bloque`,`demande_acces`,`roles`,`permistravail`) "
                + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement ps = cnx.prepareStatement(req);
        
        ps.setString(1, t.getNom());
        ps.setString(2, t.getEmail());
        ps.setString(3, t.getPassword());
        ps.setString(4, t.getPays());
        ps.setString(5, t.getGouvernorat());
        ps.setString(6, t.getVille());
        ps.setString(7, t.getRue());
        ps.setString(8, t.getTel());
        ps.setInt(9, 0);
        ps.setInt(10, 1);
        ps.setString(11, "[\"ROLE_MAGASIN\"]");
        ps.setString(12, t.getPermistravail());

        
        ps.executeUpdate();
        
        System.out.println("magasin ajouté !");    }

    @Override
    public List<User> recuperer_veterinaires() throws SQLException {
   List<User> veterinaires = new ArrayList<>();
        String sql= "SELECT * FROM user where roles=? AND demande_acces=?";
        try (PreparedStatement stmt = cnx.prepareStatement(sql)) {
                    stmt.setString(1, "[\"ROLE_VETERINAIRE\"]");
                    stmt.setInt(2, 0);

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
                int bloque = rs.getInt("bloque");

                veterinaires.add(new User(id, nom, prenom, email, pays, gouvernorat, ville, rue,tel,bloque));
            }
        }
        return veterinaires;   
    }

    @Override
    public List<User> recuperer_magasins() throws SQLException {
    List<User> veterinaires = new ArrayList<>();
        String sql= "SELECT * FROM user where roles=? AND demande_acces=?";
        try (PreparedStatement stmt = cnx.prepareStatement(sql)) {
                    stmt.setString(1, "[\"ROLE_MAGASIN\"]");
                    stmt.setInt(2, 0);


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
                int bloque = rs.getInt("bloque");

                veterinaires.add(new User(id, nom, prenom, email, pays, gouvernorat, ville, rue,tel,bloque));
            }
        }
        return veterinaires;   
    
    }    

    @Override
    public List<User> recuperer_proprietaires() throws SQLException {
    List<User> veterinaires = new ArrayList<>();
        String sql= "SELECT * FROM user where roles=? AND demande_acces=?";
        try (PreparedStatement stmt = cnx.prepareStatement(sql)) {
                    stmt.setString(1, "[\"ROLE_PROPRIETAIRE\"]");
                    stmt.setInt(2, 0);


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
                int bloque = rs.getInt("bloque");

                veterinaires.add(new User(id, nom, prenom, email, pays, gouvernorat, ville, rue,tel,bloque));
            }
        }
        return veterinaires;   
    
    }

    @Override
    public List<User> recuperer_demande_acces() throws SQLException {
  List<User> demandes = new ArrayList<>();
        String sql= "SELECT * FROM user where demande_acces=?";
        try (PreparedStatement stmt = cnx.prepareStatement(sql)) {
                    stmt.setInt(1, 1);

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
                int bloque = rs.getInt("bloque");

                demandes.add(new User(id, nom, prenom, email, pays, gouvernorat, ville, rue,tel,bloque));
            }
        }
        return demandes;   
    }
    
    
    public User authenticate(String mail, String password) {
        User u = new User();
        u.setId(0);
        try {

            String sql = "SELECT * FROM user WHERE email = ? AND password = ?";
            PreparedStatement ps = cnx.prepareStatement(sql);
            ps.setString(1, mail);
            ps.setString(2, password);
            
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                  
                u.setId(rs.getInt("id"));
                u.setNom(rs.getString("nom"));
                u.setPrenom(rs.getString("prenom"));
                u.setEmail(rs.getString("email"));
                u.setTel(rs.getString("telephone"));
                u.setPassword(rs.getString("password"));
                u.setRole(rs.getString("roles"));
                u.setPays(rs.getString("pays"));
                u.setGouvernorat(rs.getString("gouvernorat"));
                u.setVille(rs.getString("ville"));
                u.setRue(rs.getString("rue"));
                u.setBloque(rs.getInt("bloque"));
                u.setDemande_acces(rs.getInt("demande_acces"));
                u.setPermistravail(rs.getString("permistravail"));


            } 

        } catch (SQLException e) {
            System.out.print("erreur authentification");

        }
        return u;
    }
    
    
    
    
 public void modifier_vet_et_prop(User u,String email) throws SQLException {
            String req = "UPDATE user SET email=?,nom=?,prenom=?,telephone=?,pays=?,gouvernorat=?,ville=?,rue=?,password=? where email = ?";
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setString(1, u.getEmail());
        ps.setString(2, u.getNom());
        ps.setString(3, u.getPrenom());
        ps.setString(4, u.getTel());
        ps.setString(5, u.getPays());
        ps.setString(6, u.getGouvernorat());
        ps.setString(7, u.getVille());
        ps.setString(8, u.getRue());
        ps.setString(9, u.getPassword());

        ps.setString(10, email);

  
        ps.executeUpdate();
        
    }

   public void accepter(int id) throws SQLException{
   
       String req = "UPDATE user SET demande_acces=? where id = ?";
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setInt(1, 0);
        ps.setInt(2, id);
      

  
        ps.executeUpdate();
   }
   
      public void bloquer(int id) throws SQLException{
   
       String req = "UPDATE user SET bloque=? where id = ?";
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setInt(1, 1);
        ps.setInt(2, id);
      

  
        ps.executeUpdate();
   }
      
         public void debloquer(int id) throws SQLException{
   
        String req = "UPDATE user SET bloque=? where id = ?";
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setInt(1, 0);
        ps.setInt(2, id);
      

  
        ps.executeUpdate();
   }
         
         
         
     public void ModifierMdp(String email, String pwd) {
     try{
        String req = "UPDATE user SET password=? WHERE email=?";
        PreparedStatement pst = cnx.prepareStatement(req);
            
            pst.setString(1, pwd);
            pst.setString(2, email);
            pst.executeUpdate();
            System.out.println("The password was updated successfully!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }  
    }
     
     
       public Integer rechercheEmail(String email){
                    Integer exist = 0;
                    PreparedStatement pst;
                    ResultSet rs = null;
                    try {
                        pst = cnx.prepareStatement("SELECT COUNT(*) AS count FROM user WHERE email=? ");
                        pst.setString(1, email);
                      
                        rs = pst.executeQuery();
                        if (rs.next()) {
                             exist=rs.getInt("count"); 
                        }
                    } catch (SQLException ex) {
                        System.out.println(ex.getMessage());
                    }
                    return exist;
    }
    
}
