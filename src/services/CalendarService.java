/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Calendar;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import utils.MyDB;

/**
 *
 * @author heha
 */
public class CalendarService implements IService<Calendar>{
    Connection cnx;

    public CalendarService() {
        cnx = MyDB.getInstance().getCnx();
    }

    @Override
    public void ajouter(Calendar t) throws SQLException {
        String req = "INSERT INTO calendar (start,user_id,title,description) VALUES (?,?,?,?)";
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setTimestamp(1,new java.sql.Timestamp(t.getStart().getTime()));
        ps.setInt(2, t.getUser_id());
        ps.setString(3, t.getTitle());
        ps.setString(4, t.getDescription());
        ps.executeUpdate();
    }

    @Override
    public void modifier(Calendar t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void supprimer(Calendar t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Calendar> recuperer() throws SQLException {
       List<Calendar> rdv = new ArrayList<>();
        String req = "select * from calendar ";
        PreparedStatement ps = cnx.prepareStatement(req);
        ResultSet rs =  ps.executeQuery();
        while(rs.next()){
            Calendar e = new Calendar();
            e.setId(rs.getInt("id"));
            e.setStart(rs.getTimestamp("start"));
            e.setUser_id(rs.getInt("user_id"));
            e.setDescription(rs.getString("description"));
            e.setTitle(rs.getString("title"));
            rdv.add(e);
            
        }
        return rdv;
    }

    @Override
    public Calendar getById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
