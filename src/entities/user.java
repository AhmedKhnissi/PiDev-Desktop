/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package myvet.entities;

/**
 *
 * @author user
 */
public class User {
    
  

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
    public User() {
    }
    


    public User(int id,String nom, String prenom, String email, String pays, String gouvernorat, String ville, String rue, String tel) {
         this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.pays = pays;
        this.gouvernorat = gouvernorat;
        this.ville = ville;
        this.rue = rue;
        this.tel = tel;
        
    }

    public User(String nom, String prenom, String password, String email, String pays, String gouvernorat, String ville, String rue, String tel) {
        this.nom = nom;
        this.prenom = prenom;
        this.password = password;
        this.email = email;
        this.pays = pays;
        this.gouvernorat = gouvernorat;
        this.ville = ville;
        this.rue = rue;
        this.tel = tel;
    }

    public User(String nom, String password, String email, String pays, String gouvernorat, String ville, String rue, String tel, String permistravail, Integer bloque, Integer demande_acces) {
        this.nom = nom;
        this.password = password;
        this.email = email;
        this.pays = pays;
        this.gouvernorat = gouvernorat;
        this.ville = ville;
        this.rue = rue;
        this.tel = tel;
        this.permistravail = permistravail;
        this.bloque = bloque;
        this.demande_acces = demande_acces;
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

    public String getRole() {
        return role;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public void setRole(String role) {
        this.role = role;
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


    @Override
    public String toString() {
        return "User{" + "id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", password=" + password + ", email=" + email + ", pays=" + pays + ", gouvernorat=" + gouvernorat + ", ville=" + ville + ", rue=" + rue + ", tel=" + tel + ", permistravail=" + permistravail + ", bloque=" + bloque + ", demande_acces=" + demande_acces + '}';
    }  
}
