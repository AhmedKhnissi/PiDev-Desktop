/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;
import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author user
 */
public class CrypterPassword {
 
    public String CrypterPassword(String password) {
    // Générer un salt avec un coût de 13
    String salt = BCrypt.gensalt(13);

    // Hacher le mot de passe avec le salt et le coût spécifié
    String hashedPassword = BCrypt.hashpw(password, salt);

    return hashedPassword;
  }
    
}
