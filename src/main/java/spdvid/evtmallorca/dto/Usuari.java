package spdvid.garciajodar_tarea1.dto;

/**
 *
 * @author Miguel
 */
public class Usuari {

    private int id;
    private String nom;
    private String llinatges;
    private String email;
    private String password;
    private boolean admin = false;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return this.nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getLlinatges() {
        return this.llinatges;
    }

    public void setLlinatges(String llinatges) {
        this.llinatges = llinatges;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }
}
