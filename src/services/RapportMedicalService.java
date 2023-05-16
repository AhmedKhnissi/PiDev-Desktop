/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;
import entities.RapportMedical;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import utils.MyDB;

/**
 *
 * @author heha
 */
public class RapportMedicalService implements IService<RapportMedical>,IRapportService<RapportMedical>{
    Connection cnx;

    public RapportMedicalService() {
        cnx = MyDB.getInstance().getCnx();
    }

    @Override
    public List<RapportMedical> recuperer() throws SQLException {
         List<RapportMedical> rapport = new ArrayList<>();
    String req = "SELECT description FROM Animal";
        Statement stm = cnx.createStatement();
        ResultSet rs = stm.executeQuery(req);
    while (rs.next()) {
        RapportMedical rm = new RapportMedical();
        rm.setDescription(rs.getString("description"));
        rapport.add(rm);
    }
    return rapport;
    }

    @Override
    public void ajouter(RapportMedical t) throws SQLException {
  String req = "INSERT INTO rapport_medical (id, animal_id, description) VALUES ('" + t.getId() + "', '" + t.getAnimal_id() + "', '" + t.getDescription() + "')";
         Statement st = cnx.createStatement();
        st.executeUpdate(req);
        System.out.println("rapport ajouté avec succés");
   }

    @Override
    public void modifier(RapportMedical t) throws SQLException {
String req = "UPDATE rapport_medical SET description = ?,animal_id = ? where id = ?";
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setString(1, t.getDescription());
        ps.setInt(2, t.getAnimal_id());
        ps.setInt(3, t.getId());
        ps.executeUpdate();
        System.out.println("Rapport modifié avec succés");    }

    @Override
    public void supprimer(RapportMedical t) throws SQLException {
String req = "DELETE FROM rapport_medical where id = ?";
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setInt(1, t.getId());
        ps.executeUpdate();
        System.out.println("Rapport supprimé avec succés");    }

    @Override
   public List<RapportMedical> recupererRapportByAnimal(int animalId) throws SQLException {
    List<RapportMedical> rapports = new ArrayList<>();
    String s = "select * from rapport_medical WHERE animal_id = ?";
    PreparedStatement ps = cnx.prepareStatement(s);
    ps.setInt(1, animalId);
    ResultSet rs = ps.executeQuery();
    while (rs.next()) {
        RapportMedical rapport = new RapportMedical();
        rapport.setId(rs.getInt("id"));
        rapport.setAnimal_id(rs.getInt("animal_id"));
        rapport.setDescription(rs.getString("description"));
        rapports.add(rapport);
    }
    return rapports;
}
   
   public RapportMedical recupererById(int t) throws SQLException {
        RapportMedical l = new RapportMedical();
        String req = "select * from rapport_medical where id = ? ";
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setInt(1, t);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            
            l.setId(rs.getInt("id"));
            l.setAnimal_id(rs.getInt("animal_id"));
            l.setDescription(rs.getString("description"));
            
        }
        
        return l;
    }

    @Override
    public RapportMedical getById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }



   
    
}
