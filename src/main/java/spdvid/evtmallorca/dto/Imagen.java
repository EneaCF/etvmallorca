/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spdvid.evtmallorca.dto;

import java.awt.Image;

/**
 *
 * @author Enea
 */
public class Imagen {
    private int id;
    private Image imagen;
    private String nomFitcherImatge;
    private String tipo;
    private String descripcion;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Image getImagen() {
        return imagen;
    }

    public void setImagen(Image imagen) {
        this.imagen = imagen;
    }

    

    public String getNomFitcherImatge() {
        return nomFitcherImatge;
    }

    public void setNomFitcherImatge(String nomFitcherImatge) {
        this.nomFitcherImatge = nomFitcherImatge;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    
    
}
