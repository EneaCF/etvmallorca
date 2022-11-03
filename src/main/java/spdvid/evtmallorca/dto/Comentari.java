package spdvid.evtmallorca.dto;

/**
 *
 * @author Miguel
 */
public class Comentari {
    private int id;
    private String text;
    private String dataihora;
    private String usuari;
    private int idAllotjament;
    private int idComentariPare;
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getDataihora() {
        return dataihora;
    }

    public void setDataihora(String dataihora) {
        this.dataihora = dataihora;
    }

    public String getUsuari() {
        return usuari;
    }

    public void setUsuari(String usuari) {
        this.usuari = usuari;
    }

    public int getIdAllotjament() {
        return idAllotjament;
    }

    public void setIdAllotjament(int idAllotjament) {
        this.idAllotjament = idAllotjament;
    }

    public int getIdComentariPare() {
        return idComentariPare;
    }

    public void setIdComentariPare(int idComentariPare) {
        this.idComentariPare = idComentariPare;
    }
}
