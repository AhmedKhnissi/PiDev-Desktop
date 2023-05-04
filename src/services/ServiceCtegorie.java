/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;
import entities.Categorie;
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
/**
 *
 * @author admin
 */
public class ServiceCtegorie  implements IService<Categorie> {
     private final Connection cnx = MyDB.getInstance().getCnx();

    @Override
    public void ajouter(Categorie t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void modifier(Categorie t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void supprimer(Categorie t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Categorie> recuperer() throws SQLException {
       List<Categorie> produits = new ArrayList<>();
        String requete = "SELECT * FROM `categorie` ";
        Statement stm = cnx.createStatement();
        ResultSet rs = stm.executeQuery(requete);
 
        while (rs.next()) {
            Categorie p = new Categorie();
            p.setId(rs.getInt("id"));
            p.setNom(rs.getString("nom"));
           
            produits.add(p);
        }
        return produits;
    }
    public Categorie getCategorieById(int id) throws SQLException {
    String requete = "SELECT * FROM categorie WHERE id = ?";
    PreparedStatement pst = cnx.prepareStatement(requete);
    pst.setInt(1, id);
    ResultSet rs = pst.executeQuery();

    Categorie c = null;
    if (rs.next()) {
        c = new Categorie();
        
        c.setNom(rs.getString("nom"));
    }
    return c;
}
}
