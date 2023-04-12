/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;


import entities.RendezVous;
import java.sql.SQLException;

import services.RapportMedicalService;
import services.RendezVousService;
import services.UserService;


/**
 *
 * @author Skander
 */
public class Test {
    
    
    public static void main(String[] args) {
       
        try {
            
            UserService ps = new UserService();
            RendezVousService rvs = new RendezVousService();
           // System.out.println(rvs.recupererRdvByVeto(1));
            //System.out.println(ps.recuperer());
            RendezVous t = new RendezVous(1, 1, "Zah", "22", "12", "fexli", "box");
            rvs.modifier(t);
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
}
