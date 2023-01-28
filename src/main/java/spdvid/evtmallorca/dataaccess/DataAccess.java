package spdvid.evtmallorca.dataaccess;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import spdvid.evtmallorca.dto.Allotjament;
import spdvid.evtmallorca.dto.Comentari;
import spdvid.evtmallorca.dto.Municipi;
import spdvid.evtmallorca.dto.Servei;
import spdvid.garciajodar_tarea1.dto.Usuari;
import spdvid.evtmallorca.dto.Imagen;

/**
 *
 * @author Miguel
 */
public class DataAccess {

    private Connection getConnection() {
        Connection connection = null;
        Properties properties = new Properties();
        try {
            properties.load(DataAccess.class.getClassLoader().getResourceAsStream("properties/application.properties"));
            connection = DriverManager.getConnection(properties.getProperty("url"), properties);
//            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/etvmallorca", "root", "Pwd1234.");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }
    
    public ArrayList<Imagen> getImagenes(int idAlojamiento) {
	ArrayList <Imagen> lista = new ArrayList();
        
        //String sql = "select * from imatge where id < '10'";
        String sql = "SELECT imatge.* " +
                        "FROM allotjament " +
                        "JOIN imatge_allotjament ON allotjament.id = imatge_allotjament.id_allotjament " +
                        "JOIN imatge ON imatge_allotjament.id_imatge = imatge.id " +
                        "WHERE allotjament.id = ?";
        try ( Connection connection = getConnection();  PreparedStatement selectStatement = connection.prepareStatement(sql);) {
            selectStatement.setInt(1, idAlojamiento);
            ResultSet resultSet = selectStatement.executeQuery();
            while (resultSet.next()) {
                Imagen imagen = new Imagen();
                imagen.setId(resultSet.getInt("id"));
                Blob blob = resultSet.getBlob("imatge");
                byte[] data = blob.getBytes(1,(int)blob.length());
                BufferedImage img = null;
                try{
                    img = ImageIO.read(new ByteArrayInputStream(data));
                }catch(IOException e){
              
                }
                imagen.setImagen(img);
                imagen.setNomFitcherImatge(resultSet.getString("nom_fitxer_imatge"));
                lista.add(imagen);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(DataAccess.class.getName()).log(Level.SEVERE, null, ex);
        }
	return lista;
    }

    public ArrayList<Municipi> getMunicipis() {
        ArrayList<Municipi> municipis = new ArrayList<>();
        String sql = "SELECT * FROM municipi";
        try ( Connection connection = getConnection();  PreparedStatement selectStatement = connection.prepareStatement(sql);) {
            ResultSet resultSet = selectStatement.executeQuery();
            while (resultSet.next()) {
                Municipi municipi = new Municipi();
                municipi.setId(resultSet.getInt("id"));
                municipi.setNom(resultSet.getString("nom"));
                municipis.add(municipi);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return municipis;
    }

    public String findUserPassword(String email) {
        String passwordInDb = null;
        String sql = "SELECT password FROM usuaris WHERE email = ?";
        try ( Connection connection = getConnection();  PreparedStatement selectStatement = connection.prepareStatement(sql);) {
            selectStatement.setString(1, email);
            ResultSet resultSet = selectStatement.executeQuery();
            while (resultSet.next()) {
                passwordInDb = resultSet.getString("password");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return passwordInDb;
    }

    public int getUserId(String email) {
        int userId = 0;
        String sql = "SELECT id FROM usuaris WHERE email = ?";
        try ( Connection connection = getConnection();  PreparedStatement selectStatement = connection.prepareStatement(sql);) {
            selectStatement.setString(1, email);
            ResultSet resultSet = selectStatement.executeQuery();
            while (resultSet.next()) {
                userId = resultSet.getInt("id");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userId;
    }

    public Usuari getUser(String email) {
        Usuari user = null;
        String sql = "SELECT * FROM usuari WHERE email = ?";
        try ( Connection connection = getConnection();  PreparedStatement selectStatement = connection.prepareStatement(sql);) {
            selectStatement.setString(1, email);
            ResultSet resultSet = selectStatement.executeQuery();
            user = new Usuari();
            while (resultSet.next()) {
                user.setId(resultSet.getInt("id"));
                user.setNom(resultSet.getString("nom"));
                user.setLlinatges(resultSet.getString("llinatges"));
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("password"));
                user.setAdmin(resultSet.getBoolean("isAdmin"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public Usuari getUser(int id) {
        Usuari user = null;
        String sql = "SELECT * FROM usuari WHERE id = ?";
        try ( Connection connection = getConnection();  PreparedStatement selectStatement = connection.prepareStatement(sql);) {
            selectStatement.setInt(1, id);
            ResultSet resultSet = selectStatement.executeQuery();
            user = new Usuari();
            while (resultSet.next()) {
                user.setId(resultSet.getInt("id"));
                user.setNom(resultSet.getString("nom"));
                user.setLlinatges(resultSet.getString("llinatges"));
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("password"));
                user.setAdmin(resultSet.getBoolean("isAdmin"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public ArrayList<Allotjament> getAllotjaments(int userId) {
        ArrayList<Allotjament> allotjaments = new ArrayList<>();
        String sql = "SELECT allotjament.id AS id_allotjament, allotjament.nom AS nom_allotjament, descripcio, adresa,"
                + " municipi.nom AS nom_municipi, num_persones, preu_per_nit"
                + " FROM allotjament JOIN municipi ON allotjament.id_municipi=municipi.id"
                + " WHERE id_propietari=?";
        try ( Connection connection = getConnection();  PreparedStatement selectStatement = connection.prepareStatement(sql);) {
            selectStatement.setInt(1, userId);
            ResultSet resultSet = selectStatement.executeQuery();
            while (resultSet.next()) {
                Allotjament allotjament = new Allotjament();
                allotjament.setId(resultSet.getInt("id_allotjament"));
                allotjament.setNom(resultSet.getString("nom_allotjament"));
                allotjament.setDescripcio(resultSet.getString("descripcio"));
                allotjament.setAdresa(resultSet.getString("adresa"));
                allotjament.setMunicipi(resultSet.getString("nom_municipi"));
                allotjament.setNum_persones(resultSet.getInt("num_persones"));
                allotjament.setPreu_per_nit(resultSet.getFloat("preu_per_nit"));

                allotjaments.add(allotjament);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allotjaments;
    }

    public Allotjament getAllotjament(int id) {
        Allotjament allotjament = new Allotjament();
        String sql = "SELECT allotjament.id AS id_allotjament, allotjament.nom AS nom_allotjament, descripcio, adresa,"
                + " municipi.nom AS nom_municipi, num_persones, preu_per_nit"
                + " FROM allotjament JOIN municipi ON allotjament.id_municipi=municipi.id"
                + " WHERE allotjament.id=?";
        try ( Connection connection = getConnection();  PreparedStatement selectStatement = connection.prepareStatement(sql);) {
            selectStatement.setInt(1, id);
            ResultSet resultSet = selectStatement.executeQuery();
            while (resultSet.next()) {
                allotjament.setId(resultSet.getInt("id_allotjament"));
                allotjament.setNom(resultSet.getString("nom_allotjament"));
                allotjament.setDescripcio(resultSet.getString("descripcio"));
                allotjament.setAdresa(resultSet.getString("adresa"));
                allotjament.setMunicipi(resultSet.getString("nom_municipi"));
                allotjament.setNum_persones(resultSet.getInt("num_persones"));
                allotjament.setPreu_per_nit(resultSet.getFloat("preu_per_nit"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allotjament;
    }

    public int updateAllojtament(Allotjament allotjament) {
        int result = 0;
        String sql = "UPDATE allotjament SET nom = ?, descripcio = ?,"
                + " num_persones = ?, adresa = ?, id_municipi = ?,"
                + " destacat = ?, preu_per_nit = ?"
                + " WHERE id = ?";
        try ( Connection connection = getConnection();  PreparedStatement updateStatement = connection.prepareStatement(sql);) {
            updateStatement.setString(1, allotjament.getNom());
            updateStatement.setString(2, allotjament.getDescripcio());
            updateStatement.setInt(3, allotjament.getNum_persones());
            updateStatement.setString(4, allotjament.getAdresa());
            updateStatement.setInt(5, allotjament.getId_municipi());
            updateStatement.setBoolean(6, false);
            updateStatement.setFloat(7, allotjament.getPreu_per_nit());
            updateStatement.setInt(8, allotjament.getId());
            result = updateStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public int insertAllotjament(Allotjament allotjament) {
        int result = 0;
        String sql = "INSERT INTO allotjament (nom, descripcio, num_persones, adresa, "
                + "id_municipi, id_propietari, destacat, preu_per_nit) "
                + "VALUES (?,?,?,?,?,?,?,?)";
        try ( Connection connection = getConnection();  PreparedStatement insertStatement = connection.prepareStatement(sql);) {
            insertStatement.setString(1, allotjament.getNom());
            insertStatement.setString(2, allotjament.getDescripcio());
            insertStatement.setInt(3, allotjament.getNum_persones());
            insertStatement.setString(4, allotjament.getAdresa());
            insertStatement.setInt(5, allotjament.getId_municipi());
            insertStatement.setInt(6, allotjament.getId_propietari());
            insertStatement.setBoolean(7, false);
            insertStatement.setFloat(8, allotjament.getPreu_per_nit());

            result = insertStatement.executeUpdate();

            if (result > 0) {
                result = getIdLastInsertedAllotjament();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    private int getIdLastInsertedAllotjament() {
        int idAllotjament = 0;
        String sql = "SELECT MAX(id) AS last_id_allotjament FROM allotjament";
        try ( Connection connection = getConnection();  PreparedStatement selectStatement = connection.prepareStatement(sql);) {

            ResultSet resultSet = selectStatement.executeQuery();
            while (resultSet.next()) {
                idAllotjament = resultSet.getInt("last_id_allotjament");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return idAllotjament;
    }

    public ArrayList<Servei> getServeisAllotjament(int idAllotjament) {
        ArrayList<Servei> serveis = new ArrayList<>();
        String sql = "SELECT servei.id, servei.descripcio FROM servei JOIN servei_allotjament"
                + " ON servei.id=servei_allotjament.id_servei"
                + " WHERE servei_allotjament.id_allotjament=?";
        try ( Connection connection = getConnection();  PreparedStatement selectStatement = connection.prepareStatement(sql);) {
            selectStatement.setInt(1, idAllotjament);
            ResultSet resultSet = selectStatement.executeQuery();
            while (resultSet.next()) {
                Servei servei = new Servei();
                servei.setId(resultSet.getInt("id"));
                servei.setDescripcio(resultSet.getString("descripcio"));
                serveis.add(servei);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return serveis;
    }

    public int insertServei(int idServei, int idAllotjament) {
        int result = 0;
        String sql = "INSERT INTO servei_allotjament (id_servei, id_allotjament) VALUES (?,?)";
        try ( Connection connection = getConnection();  PreparedStatement insertStatement = connection.prepareStatement(sql);) {
            insertStatement.setInt(1, idServei);
            insertStatement.setInt(2, idAllotjament);
            result = insertStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public int updateServeisAllotjament(int idAllotjament, int[] serveis) {
        // Brute force
        int result = 0;
        String sql = "DELETE FROM servei_allotjament WHERE id_allotjament = ?";
        try ( Connection connection = getConnection();  PreparedStatement deleteStatement = connection.prepareStatement(sql);) {
            deleteStatement.setInt(1, idAllotjament);
            result = deleteStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < serveis.length; i++) {
            if (serveis[i] == 1) {
                insertServei(i + 1, idAllotjament);
            }
        }

        return result;
    }

    public ArrayList<Comentari> getComentaris(int idAllotjament) {
        ArrayList<Comentari> comentaris = new ArrayList<>();
        String sql = "SELECT comentari.id, comentari.text, comentari.dataihora,"
                + " comentari.id_usuari, comentari.id_allotjament, comentari.id_comentari_pare"
                + " FROM comentari WHERE id_allotjament = ?";
        try ( Connection connection = getConnection();  PreparedStatement selectStatement = connection.prepareStatement(sql);) {
            selectStatement.setInt(1, idAllotjament);
            ResultSet resultSet = selectStatement.executeQuery();
            while (resultSet.next()) {
                Comentari comentari = new Comentari();
                comentari.setId(resultSet.getInt("id"));
                comentari.setText(resultSet.getString("text"));
                comentari.setDataihora(resultSet.getString("dataihora"));
                comentari.setIdUsuari(resultSet.getInt("id_usuari"));
                comentari.setIdAllotjament(resultSet.getInt("id_allotjament"));
                comentari.setIdComentariPare(resultSet.getInt("id_comentari_pare"));
                comentaris.add(comentari);
                /*
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                LocalDateTime dataihora = LocalDateTime.parse(resultSet.getString("dataihora"), dtf);
                System.out.println(getUser(resultSet.getInt("id_usuari")).getNom()
                        + " said:\n " + resultSet.getString("text") + "\nOn "
                        + dataihora.toString());
                 */
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return comentaris;
    }

    public float getValoracioAllotjamentAvg(int idAllotjament) {
        float valoracioAvg = 0.0f;
        String sql = "SELECT AVG(num_estrelles) AS avg_valoracio FROM valoracio WHERE id_allotjament = ?";
        try ( Connection connection = getConnection();  PreparedStatement selectStatement = connection.prepareStatement(sql);) {
            selectStatement.setInt(1, idAllotjament);
            ResultSet resultSet = selectStatement.executeQuery();
            while (resultSet.next()) {
                valoracioAvg = resultSet.getFloat("avg_valoracio");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return valoracioAvg;
    }

    public int insertComentari(String text, int userId, int idAllotjament, int idComentariPare) {
        int result = 0;
        String sql = "INSERT INTO comentari (text, dataihora, id_usuari, id_allotjament"
                + ", id_comentari_pare) VALUES (?,?,?,?,?)";
        try ( Connection connection = getConnection();  PreparedStatement insertStatement = connection.prepareStatement(sql);) {
            insertStatement.setString(1, text);
            insertStatement.setString(2, LocalDateTime.now().toString());
            insertStatement.setInt(3, userId);
            insertStatement.setInt(4, idAllotjament);
            insertStatement.setInt(5, idComentariPare);
            result = insertStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}
