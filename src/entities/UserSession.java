/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

/**
 *
 * @author user
 */
public class UserSession {
   private Integer id;
   private String nom;
   private String prenom;
   private String password;
   private String email;
   private String pays;
   private String gouvernorat;
   private String ville;
   private String rue;
   private String tel;
   private String permistravail;
   private Integer bloque;
   private Integer demande_acces;
   private String role;
   private static UserSession instance; 
    private boolean isLoggedIn;
    
    
    public void setId(Integer id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPays(String pays) {
        this.pays = pays;
    }

    public void setGouvernorat(String gouvernorat) {
        this.gouvernorat = gouvernorat;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public void setRue(String rue) {
        this.rue = rue;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public void setPermistravail(String permistravail) {
        this.permistravail = permistravail;
    }

    public void setBloque(Integer bloque) {
        this.bloque = bloque;
    }

    public void setDemande_acces(Integer demande_acces) {
        this.demande_acces = demande_acces;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public static void setInstance(UserSession instance) {
        UserSession.instance = instance;
    }

    public void setIsLoggedIn(boolean isLoggedIn) {
        this.isLoggedIn = isLoggedIn;
    }

    public Integer getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getPays() {
        return pays;
    }

    public String getGouvernorat() {
        return gouvernorat;
    }

    public String getVille() {
        return ville;
    }

    public String getRue() {
        return rue;
    }

    public String getTel() {
        return tel;
    }

    public String getPermistravail() {
        return permistravail;
    }

    public Integer getBloque() {
        return bloque;
    }

    public Integer getDemande_acces() {
        return demande_acces;
    }

    public String getRole() {
        return role;
    }

    public boolean isIsLoggedIn() {
        return isLoggedIn;
    }


    private UserSession() {
        // Empêcher l'instanciation directe de la classe
    }

    public static synchronized UserSession getInstance() {
        if (instance == null) {
            instance = new UserSession();
        }
        return instance;
    }

    

   

    
}
