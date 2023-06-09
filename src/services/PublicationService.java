/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Publication;
import entities.User;
import entities.UserSession;
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
 * @author Khalil
 */
public class PublicationService  implements IService<Publication> { 
    Connection cnx; 
    UserSession session = UserSession.getInstance(); 
    private int idloguser = session.getId();

    public PublicationService() {
        cnx = MyDB.getInstance().getCnx();
    }

    @Override
    public void ajouter(Publication t) throws SQLException  {
        User user = new User(); 
        user.setId(idloguser);
        try {
            String requete = "INSERT INTO publication (id_user_id, auteur, titre, contenu, image, datepub, likes, dislike, nbsignal)"
                + " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement pst = cnx.prepareStatement(requete);
        pst.setInt(1, UserSession.getInstance().getId());
        pst.setString(2, t.getAuteur());
        pst.setString(3, t.getTitre());
        pst.setString(4, t.getContenu());
        pst.setString(5, t.getImage());
        pst.setDate(6, t.getDatepub());
        pst.setInt(7, t.getLikes());
        pst.setInt(8, t.getDislike());
        pst.setInt(9, t.getNbsignal());

        pst.executeUpdate();
        System.out.println("Publication ajoutée avec succès!");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    } 
    @Override
    public void modifier(Publication t) throws SQLException {
        String requete = "UPDATE publication SET auteur = ?, titre = ?, contenu = ?, image = ? WHERE id = ?";
        PreparedStatement pst = cnx.prepareStatement(requete);
        pst.setString(1, t.getAuteur());
        pst.setString(2, t.getTitre());
        pst.setString(3, t.getContenu()); 
        pst.setString(4, t.getImage());
        pst.setInt(5, t.getId());
        pst.executeUpdate();
        System.out.println("Publication modifié avec succès !");
    }

   
   
   


    @Override
    public void supprimer(Publication t) throws SQLException {
        String req = "DELETE FROM publication where id = ?";
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setInt(1, t.getId());
        ps.executeUpdate();
        System.out.println("publication supprimé avec succés");    }

    @Override
    public List<Publication> recuperer() throws SQLException {
List<Publication> Publications = new ArrayList<>();
    String s = "select * from publication";
    PreparedStatement ps = cnx.prepareStatement(s);
    ResultSet rs = ps.executeQuery();
    while (rs.next()) {
       Publication publication = new Publication();
       publication.setId(rs.getInt("id")); 
       publication.setAuteur(rs.getString("auteur")); 
       publication.setTitre(rs.getString("titre"));
       publication.setContenu(rs.getString("contenu"));
       publication.setImage(rs.getString("image"));
       publication.setDatepub(rs.getDate("datepub"));
       publication.setLikes(rs.getInt("likes")); 
       publication.setDislike(rs.getInt("dislike"));
       publication.setNbsignal(rs.getInt("nbsignal")); 
       Publications.add(publication); 
       
     }
    return Publications;
      
    }  
    
    public void like(Publication t) throws SQLException {
        String requete = "UPDATE publication SET likes = ? WHERE id = ?";
        PreparedStatement pst = cnx.prepareStatement(requete);
        pst.setInt(1, t.getLikes());
        pst.setInt(2, t.getId());
        pst.executeUpdate();
        System.out.println("like!");
    }
    
     public void dislike(Publication t) throws SQLException {
        String requete = "UPDATE publication SET dislike = ? WHERE id = ?";
        PreparedStatement pst = cnx.prepareStatement(requete);
        pst.setInt(1, t.getDislike());
        pst.setInt(2, t.getId());
        pst.executeUpdate();
        System.out.println("dislike!");
    } 
     public void signaler(Publication t) throws SQLException {
        String requete = "UPDATE publication SET nbsignal = ? WHERE id = ?";
        PreparedStatement pst = cnx.prepareStatement(requete);
        pst.setInt(1, t.getNbsignal());
        pst.setInt(2, t.getId());
        pst.executeUpdate();
        System.out.println("Publication signalée!");
    }
     
     
    public List<Publication> recupererByIdUser(int iduser) throws SQLException {
    List<Publication> Publications = new ArrayList<>();  
    User user = new User();
    String s = "select * from publication WHERE id_user_id = ?";
    PreparedStatement ps = cnx.prepareStatement(s);
    ps.setInt(1, iduser);
    ResultSet rs = ps.executeQuery(); 
    while (rs.next()) {
       Publication publication = new Publication();
       publication.setId(rs.getInt("id")); 
       publication.setAuteur(rs.getString("auteur")); 
       publication.setTitre(rs.getString("titre"));
       publication.setContenu(rs.getString("contenu"));
       publication.setImage(rs.getString("image"));
       publication.setDatepub(rs.getDate("datepub"));
       publication.setLikes(rs.getInt("likes")); 
       publication.setDislike(rs.getInt("dislike"));
       publication.setNbsignal(rs.getInt("nbsignal")); 
       user.setId(rs.getInt("id_user_id")); 
       publication.setUser(user);
       Publications.add(publication); 
       
     }
    return Publications;
      
    }  
  

    }

  
    

