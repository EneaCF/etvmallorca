package spdvid.evtmallorca.dto;

/**
 *
 * @author Miguel
 */
public class Allotjament {
    private int id;
    private String nom;
    private String descripcio;
    private int num_persones;
    private String adresa;
    private int id_municipi;
    private String municipi;
    private int id_propietari;
    private int destacat;
    private float preu_per_nit;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescripcio() {
        return descripcio;
    }

    public void setDescripcio(String descripcio) {
        this.descripcio = descripcio;
    }

    public int getNum_persones() {
        return num_persones;
    }

    public void setNum_persones(int num_persones) {
        this.num_persones = num_persones;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public int getId_municipi() {
        return id_municipi;
    }

    public void setId_municipi(int id_municipi) {
        this.id_municipi = id_municipi;
    }

    public int getId_propietari() {
        return id_propietari;
    }

    public void setId_propietari(int id_propietari) {
        this.id_propietari = id_propietari;
    }

    public int getDestacat() {
        return destacat;
    }

    public void setDestacat(int destacat) {
        this.destacat = destacat;
    }

    public float getPreu_per_nit() {
        return preu_per_nit;
    }

    public void setPreu_per_nit(float preu_per_nit) {
        this.preu_per_nit = preu_per_nit;
    }

    public String getMunicipi() {
        return municipi;
    }

    public void setMunicipi(String municipi) {
        this.municipi = municipi;
    }
    
    
}
