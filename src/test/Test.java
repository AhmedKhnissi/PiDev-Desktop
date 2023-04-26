/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;


import entities.Reclamation;
import java.sql.SQLException;

import services.RapportMedicalService;
import services.ReclamationService;
import services.RendezVousService;
import services.UserService;


/**
 *
 * @author Skander
 */
public class Test {
    
    
    public static void main(String[] args) {
       
        try {
            
            ReclamationService ps = new ReclamationService();
           // System.out.println(rvs.recupererRdvByVeto(1));
            //System.out.println(ps.recuperer());
            Reclamation  t = new Reclamation(22,"mohsen", "jame@erlgj", "sekopu", "toujaa", "non traité");
           // rvs.modifier(t);
           ps.modifier(t);
           System.out.println(); 
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
}


