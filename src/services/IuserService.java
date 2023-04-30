/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;
import java.sql.SQLException;
import java.util.List;
/**
 *
 * @author heha
 */
public interface IuserService<T> {
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
