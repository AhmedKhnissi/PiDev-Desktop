/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Commentaire;
import entities.Publication;
import entities.User;
import entities.UserSession;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import utils.MyDB;

/**
 *
 * @author Khalil
 */
public class CommentaireService  implements IService<Commentaire>{ 
    Connection cnx; 
    UserSession session = UserSession.getInstance(); 
    private int idloguser = session.getId();

    public CommentaireService() {
        cnx = MyDB.getInstance().getCnx();
    }

    @Override
    public void ajouter(Commentaire t) throws SQLException { 
        try {
            String requete="INSERT INTO commentaire (id_user_id,pub_id,contenu,datetime)"
                    + "VALUES (?,?,?,?)";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, UserSession.getInstance().getId());
            pst.setInt(2, t.getPublication().getId());
            pst.setString(3, t.getContenu());
            pst.setDate(4, t.getDatetime());
            pst.executeUpdate();
            System.out.println("Success!");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

       
    }

    @Override
    public void modifier(Commentaire t) throws SQLException { 
        String requete = "UPDATE commentaire SET Contenu = ?";
        PreparedStatement pst = cnx.prepareStatement(requete);
        pst.setString(1, t.getContenu());
        pst.executeUpdate();
        System.out.println("Publication modifié avec succès !");
        
    }

    @Override
    public void supprimer(Commentaire t) throws SQLException {
        String req = "DELETE FROM commentaire where id = ?";
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setInt(1, t.getId());
        ps.executeUpdate();
        System.out.println("commentaire supprimé avec succés");
        
    }

    @Override
    public List<Commentaire> recuperer() throws SQLException {
        List<Commentaire> Commentaires = new ArrayList<>(); 
        
    String s = "select * from commentaire";
    PreparedStatement ps = cnx.prepareStatement(s);
    ResultSet rs = ps.executeQuery();
    while (rs.next()) {
       Commentaire com = new Commentaire();
       com.setId(rs.getInt("id"));  
       com.setContenu(rs.getString("contenu"));
       com.setPublicationId(rs.getInt("pub_id"));
       com.setDatetime(rs.getDate("datetime"));
       Commentaires.add(com); 
       
    }
    return Commentaires;
    }
    
   public List<Commentaire> getCommentaires(int idPublication) throws SQLException {
    List<Commentaire> commentaires = new ArrayList<>();
    String query = "SELECT c.*, u.id AS user_id, u.nom AS user_nom " +
                   "FROM commentaire c LEFT JOIN user u ON c.id_user_id = u.id " +
                   "WHERE c.pub_id = ?";
    PreparedStatement preparedStatement = cnx.prepareStatement(query);
    preparedStatement.setInt(1, idPublication);
    ResultSet resultSet = preparedStatement.executeQuery();
    while (resultSet.next()) {
        Commentaire commentaire = new Commentaire();
        commentaire.setId(resultSet.getInt("id"));
        commentaire.setContenu(resultSet.getString("contenu"));
        commentaire.setDatetime(resultSet.getDate("datetime"));
        // set the publication for the comment
        Publication publication = new Publication();
        publication.setId(resultSet.getInt("pub_id"));
        commentaire.setPublication(publication);
        // set the user for the comment
        int userId = resultSet.getInt("user_id");
        if (!resultSet.wasNull()) {
            User user = new User();
            user.setId(userId);
            user.setNom(resultSet.getString("user_nom"));
            commentaire.setUser(user);
        }
        commentaires.add(commentaire);
    }
    return commentaires;
}

        
        
              
    

    
}
