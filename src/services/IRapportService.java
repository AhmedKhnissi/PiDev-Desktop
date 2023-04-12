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
public interface IRapportService<T> {
    public List<T> recupererRapportByAnimal(int x) throws SQLException;
}
