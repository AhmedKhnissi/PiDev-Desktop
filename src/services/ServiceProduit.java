/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */package services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import entities.Produit;
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

    
    @Override
    public void ajouter(Produit p) throws SQLException {
        String req = "INSERT INTO `produit`(`nom`, `description`, `stock`,`prix`,`image`) "
                + "VALUES ('"+p.getNom()+"', '"+p.getDescription()+"', '"+p.getStock()+"','"+p.getPrix()+"','"+p.getImage()+"')";
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
    public void supprimer(Produit t) throws SQLException {
        String req = "DELETE FROM produit where id = ?";
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setInt(1, t.getId());
        ps.executeUpdate();
        System.out.println("publication supprimé avec succés");    }
    @Override
    public List<Produit> recuperer() throws SQLException {
        List<Produit> produits = new ArrayList<>();
        String requete = "SELECT * FROM `produit`";
        Statement stm = cnx.createStatement();
        ResultSet rs = stm.executeQuery(requete);
        while (rs.next()) {
            Produit p = new Produit();
           
            p.setNom(rs.getString("nom"));
            p.setDescription(rs.getString("description"));
            p.setStock(rs.getInt("stock"));
            p.setPrix(rs.getFloat("prix"));
            p.setImage(rs.getString("image"));
            produits.add(p);
        }
        return produits;
    }
}
