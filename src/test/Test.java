/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;


import entities.Publication;
import entities.RendezVous;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;
import services.PublicationService;

import services.RapportMedicalService;
import services.RendezVousService;
import services.UserService;


/**
 *
 * @author Skander
 */
public class Test {
    
    
    public static void main(String[] args) {
       
       
        LocalDate currentDate = LocalDate.now();
        Date sqlDate = Date.valueOf(currentDate);
        Publication p = new Publication("khalil", "titre11", "contenu1","image");
        p.setDatepub(sqlDate);
        p.setLikes(0);
        p.setDislike(0);
        p.setNbsignal(0);
        PublicationService ps = new PublicationService(); 
        
       
        try {
            System.out.println(ps.recuperer());
        } catch (SQLException ex) {
            Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
        }
        
       
        
    }
    
}
