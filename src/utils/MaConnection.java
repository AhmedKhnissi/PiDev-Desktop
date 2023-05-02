/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author user
 */
public class MaConnection {
     private final String url = "jdbc:mysql://localhost:3306/pii";
    private final String login = "root";
    private final String password ="";
    
    private Connection cnx;

    //2 STEP
    private static MaConnection instance;
    
    //1 STEP
    private MaConnection() {
        try {
            cnx = DriverManager.getConnection(url, login, password);
            System.out.println("Connexion etablie !");
        } catch (SQLException ex) {
            System.err.println("nooo cnnxxxxxxxxxxxxxxxxxxx");
        }
    }
    
    //3 STEP
    public static MaConnection getInstance(){
        if (instance == null) {
            instance = new MaConnection();
        }
        return instance;
    }

    public Connection getCnx() {
        return cnx;
    }
    
}
