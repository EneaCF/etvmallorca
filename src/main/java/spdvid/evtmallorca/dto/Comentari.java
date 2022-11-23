package spdvid.evtmallorca.dto;

/**
 *
 * @author Miguel
 */
public class Comentari {
    private int id;
    private String text;
    private String dataihora;
    private int idUsuari;
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

    public int getIdUsuari() {
        return idUsuari;
    }

    public void setIdUsuari(int idUsuari) {
        this.idUsuari = idUsuari;
    }
}
