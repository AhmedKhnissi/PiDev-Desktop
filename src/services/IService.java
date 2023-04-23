/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package myvet.services;

import java.sql.SQLException;
import java.util.List;
import myvet.entities.UserSession;

/**
 *
 * @author user
 */
public interface Iservice<T> {
   public void ajouter(T t) throws SQLException;
    public void modifier(T t,String m) throws SQLException;
    public void supprimer(int id) throws SQLException;
    public List<T> recuperer_veterinaires() throws SQLException; 
    public List<T> recuperer_magasins() throws SQLException; 
    public List<T> recuperer_proprietaires() throws SQLException; 
    public List<T> recuperer_demande_acces() throws SQLException; 
   public void ajouter_prop(T t) throws SQLException;
   public void ajouter_magasin(T t) throws SQLException;

}
