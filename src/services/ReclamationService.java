/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;
import entities.Reclamation;
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
public class ReclamationService implements IService<Reclamation> {
Connection cnx;

    public ReclamationService() {
        cnx = MyDB.getInstance().getCnx();
    }
    @Override
    public void ajouter(Reclamation t) throws SQLException {
String req = "INSERT INTO reclamation (id, name, email,subject,message,etat) VALUES ('" + t.getId() + "', '" + t.getName() + "', '" + t.getEmail() + "', '" + t.getSubject()+"', '" + t.getMessage()+"', '" + t.getEtat() +"')";
         PreparedStatement st = cnx.prepareStatement(req);
        st.executeUpdate(req);
        System.out.println("Reclamation ajouté avec succés");    }

    @Override
    public void modifier(Reclamation t) throws SQLException {
        String req = "UPDATE reclamation SET name = ?,email = ?,subject = ?,message = ? where id = ?";
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setString(1, t.getName());
        ps.setString(2, t.getEmail());
        ps.setString(3, t.getSubject());
        ps.setString(4, t.getMessage());
        ps.setInt(5, t.getId());
        ps.executeUpdate();
        System.out.println("Reclamation modifié avec succés");
    }

    @Override
    public void supprimer(Reclamation t) throws SQLException {
        String req = "DELETE FROM reclamation where id = ?";
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setInt(1, t.getId());
        ps.executeUpdate();
        System.out.println("Reclamation supprimé avec succés");
    }

    @Override
    public List<Reclamation> recuperer() throws SQLException {
        List<Reclamation> rapport = new ArrayList<>();
    String req = "SELECT * FROM reclamation ";
        Statement stm = cnx.createStatement();
        ResultSet rs = stm.executeQuery(req);
    while (rs.next()) {
        Reclamation rm = new Reclamation();
        rm.setId(rs.getInt("id"));
        rm.setName(rs.getString("name"));
        rm.setEmail(rs.getString("email"));
        rm.setSubject(rs.getString("subject"));
        rm.setMessage(rs.getString("message"));
        rm.setEtat(rs.getString("etat"));
        rapport.add(rm);
    }
    return rapport;
    }
    
}
