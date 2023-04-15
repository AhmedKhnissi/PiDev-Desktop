/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.RendezVous;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;
import utils.MyDB;

/**
 *
 * @author heha
 */
public class RendezVousService implements IService<RendezVous>{
Connection cnx;

    public RendezVousService() {
        cnx = MyDB.getInstance().getCnx();
    }

    @Override
    public void ajouter(RendezVous t) throws SQLException {
 String req = "INSERT INTO rendez_vous (id, user_id, date,heure,raceanimal,nomanimal,decision) VALUES ('" + t.getId() + "', '" + t.getUser_id() + "', '" + t.getDate() + "', '" + t.getHeure() + "', '" + t.getRaeanimal() + "', '" + t.getNomanimal() + "', '" + t.getDecision() +  "')";
         Statement st = cnx.createStatement();
        st.executeUpdate(req);
        System.out.println("rendez-vous ajouté avec succés");    }

    @Override
    public void modifier(RendezVous t) throws SQLException {
String req = "UPDATE rendez_vous SET user_id = ?,date = ?,heure = ?,raceanimal = ?,nomanimal = ?,decision = ? where id = ?";
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setInt(1, t.getUser_id());
        ps.setString(2, t.getDate());
        ps.setString(3, t.getHeure());
        ps.setString(4, t.getRaeanimal());
        ps.setString(5, t.getNomanimal());
        ps.setString(6,t.getDecision());
        ps.setInt(7, t.getId());
        ps.executeUpdate();
        System.out.println("Rdv modifié avec succés");     }

    @Override
    public void supprimer(RendezVous t) throws SQLException {
String req = "DELETE FROM rendez_vous where id = ?";
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setInt(1, t.getId());
        ps.executeUpdate();
        System.out.println("Rdv supprimé avec succés");     }

    @Override
    public List<RendezVous> recuperer() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
     public List<RendezVous> recupererRdvByVeto(int IdVeto) throws SQLException {
    List<RendezVous> rapports = new ArrayList<>();
    String s = "select * from rendez_vous WHERE user_id = ?";
    PreparedStatement ps = cnx.prepareStatement(s);
    ps.setInt(1, IdVeto);
    ResultSet rs = ps.executeQuery();
    while (rs.next()) {
        RendezVous rapport = new RendezVous();
        rapport.setId(rs.getInt("id"));
        rapport.setDate(rs.getString("date"));
        rapport.setHeure(rs.getString("heure"));
        rapport.setNomanimal(rs.getString("nomanimal"));
        rapport.setRaeanimal(rs.getString("raceanimal"));
        rapport.setUser_id(rs.getInt(IdVeto));
        rapport.setDecision(rs.getString("decision"));
        rapports.add(rapport);
    }
    return rapports;
}
     public TreeMap<Integer, Integer> nombreVetoParRdv() throws SQLException {
        
        TreeMap<Integer, Integer> Vetos = new TreeMap<Integer, Integer>();
        String req = "SELECT user.id, COUNT(rendez_vous.id) as nombre_de_vetos\n"
                + "FROM user\n"
                + "LEFT JOIN Rendez_vous ON user.id = rendez_vous.user_id\n"
                + "GROUP BY user.id;";
        PreparedStatement ps = cnx.prepareStatement(req);
        
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Vetos.put(rs.getInt("id"), rs.getInt("nombre_de_vetos"));
            
        }
        
        return Vetos;
    }
    
   
}
