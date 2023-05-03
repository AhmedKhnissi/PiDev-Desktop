/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */package services;

import entities.Categorie;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import entities.Produit;
import entities.Publication;
import entities.User;
import entities.UserSession;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import utils.MyDB;

public class ServiceProduit implements IService<Produit> {
    
 private final Connection cnx = MyDB.getInstance().getCnx();

      UserSession session = UserSession.getInstance(); 
    private int idloguser = session.getId();
    @Override
    public void ajouter(Produit p) throws SQLException {
        String req = "INSERT INTO `produit`(`nom`, `description`, `stock`,`prix`,`image`,`id_user_id`) "
                + "VALUES ('"+p.getNom()+"', '"+p.getDescription()+"', '"+p.getStock()+"','"+p.getPrix()+"','"+p.getImage()+"','"+UserSession.getInstance().getId()+"')";
        Statement st = cnx.createStatement();
        st.executeUpdate(req);
        System.out.println("Produit ajouté avec succès !");
    }

    @Override
    public void modifier(Produit p) throws SQLException {
        String requete = "UPDATE produit SET nom = ?, description = ?, stock = ?, prix = ? WHERE id = ?";
        PreparedStatement pst = cnx.prepareStatement(requete);
        pst.setString(1, p.getNom());
        pst.setString(2, p.getDescription());
        pst.setInt(3, p.getStock());
        pst.setDouble(4, p.getPrix());
        pst.setInt(5, p.getId());
        pst.executeUpdate();
        System.out.println("Produit modifié avec succès !");
    }

   @Override
    public void supprimer(Produit p) throws SQLException {
        String req = "DELETE FROM produit WHERE stock = ?";
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setInt(1,p.getStock());
        ps.executeUpdate();
        System.out.println("produit supprimé avec succés");    }
    @Override
    public List<Produit> recuperer() throws SQLException {
        List<Produit> produits = new ArrayList<>();
        String requete = "SELECT * FROM `produit` ";
        Statement stm = cnx.createStatement();
        ResultSet rs = stm.executeQuery(requete);
        while (rs.next()) {
            Produit p = new Produit();
            p.setNom(rs.getString("nom"));
            p.setDescription(rs.getString("description"));
            p.setStock(rs.getInt("stock"));
            p.setPrix(rs.getFloat("prix"));
            p.setImage(rs.getString("image"));
             // récupérer la catégorie correspondante
   
    

            produits.add(p);
        }
        return produits;
    }
    
    
  public List<Produit> recupByIdUser(int iduser) throws SQLException {
    List<Produit> produits = new ArrayList<>();  
    User user = new User();
    String s = "select * from produit WHERE id_user_id = ?";
    PreparedStatement ps = cnx.prepareStatement(s);
    ps.setInt(1, iduser);
    ResultSet rs = ps.executeQuery(); 
    while (rs.next()) {
 Produit p = new Produit();
            p.setNom(rs.getString("nom"));
            p.setDescription(rs.getString("description"));
            p.setStock(rs.getInt("stock"));
            p.setPrix(rs.getFloat("prix"));
            p.setImage(rs.getString("image"));
            p.setUser(user);
            produits.add(p); 
       
        System.out.println(produits);
     }
    return produits;
      
    }  
    
    
}
