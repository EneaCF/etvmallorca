package spdvid.evtmallorca.panels;

import java.awt.Image;
import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import spdvid.evtmallorca.Main;
import spdvid.evtmallorca.dataaccess.DataAccess;
import spdvid.evtmallorca.dto.Allotjament;
import spdvid.evtmallorca.dto.Comentari;
import spdvid.evtmallorca.dto.Municipi;
import spdvid.evtmallorca.dto.Servei;

/**
 *
 * @author Miguel
 */
public class PanelDetallAllotjament extends javax.swing.JPanel {

    private Allotjament allotjament = null;
    private DataAccess da = new DataAccess();
    private Main mainJFrame = null;

    /**
     * Creates new form PanelDetallAllotjament
     */
    public PanelDetallAllotjament(Allotjament allotjament, Main mainJPanel) {
        initComponents();
        this.allotjament = allotjament;
        this.mainJFrame = mainJPanel;
        setSize(780, 700);

        inicialitzaFields();
    }

    private void inicialitzaFields() {
        txtNom.setText(allotjament.getNom());
        txtAdresa.setText(allotjament.getAdresa());
        txaDescripcio.setText(allotjament.getDescripcio());
        initCmbMunicipis();
        spnNumPersones.setValue(allotjament.getNum_persones());
        txtPreuNit.setText(Float.toString(allotjament.getPreu_per_nit()));
        initServeis();
        initComentaris();
        txtValoracio.setText(Float.toString(da.getValoracioAllotjamentAvg(allotjament.getId())));
    }

    private void initCmbMunicipis() {
        var municipis = da.getMunicipis();
        DefaultComboBoxModel<String> cbm = new DefaultComboBoxModel<>();
        for (Municipi m : municipis) {
            cbm.addElement(m.getNom());
        }
        cmbMunicipi.setModel(cbm);
        cmbMunicipi.setSelectedItem(allotjament.getMunicipi());
    }

    private void initServeis() {
        var serveis = da.getServeisAllotjament(allotjament.getId());
        for (Servei s : serveis) {
            switch (s.getDescripcio()) {
                case "Piscina":
                    chkPiscina.setSelected(true);
                    break;
                case "Aire condicionat":
                    chkAireAcondicionat.setSelected(true);
                    break;
                case "Wifi":
                    chkWifi.setSelected(true);
                    break;
                case "Aparcament":
                    chkAparcament.setSelected(true);
                    break;
                case "Admet mascotes":
                    chkMascotes.setSelected(true);
                    break;
                case "Ascensor":
                    chkAscensor.setSelected(true);
                    break;
            }
        }
    }

    private void initComentaris() {
        var comentaris = da.getComentaris(allotjament.getId());
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        for (Comentari comentari : comentaris) {
            LocalDateTime dataihora = LocalDateTime.parse(comentari.getDataihora(), dtf);
            txaComentaris.append(comentari.getIdUsuari()
                    + " said:\n " + comentari.getText() + "\nOn "
                    + dataihora.toString() + "\n\n");
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtNom = new javax.swing.JTextField();
        txtAdresa = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        txaDescripcio = new javax.swing.JTextArea();
        cmbMunicipi = new javax.swing.JComboBox<>();
        spnNumPersones = new javax.swing.JSpinner();
        txtPreuNit = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        btnBack = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        pnlServeis = new javax.swing.JPanel();
        chkPiscina = new javax.swing.JCheckBox();
        chkMascotes = new javax.swing.JCheckBox();
        chkAscensor = new javax.swing.JCheckBox();
        chkAireAcondicionat = new javax.swing.JCheckBox();
        chkAparcament = new javax.swing.JCheckBox();
        chkWifi = new javax.swing.JCheckBox();
        jScrollPane2 = new javax.swing.JScrollPane();
        txaComentaris = new javax.swing.JTextArea();
        txtValoracio = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        btnPrevImage = new javax.swing.JButton();
        btnNextImage = new javax.swing.JButton();
        chkAutoMan = new javax.swing.JCheckBox();
        lblImageFileName = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        setPreferredSize(new java.awt.Dimension(780, 700));
        setLayout(null);

        txtNom.setText("nom");
        add(txtNom);
        txtNom.setBounds(60, 50, 231, 22);

        txtAdresa.setText("adresa");
        add(txtAdresa);
        txtAdresa.setBounds(60, 90, 230, 22);

        txaDescripcio.setColumns(20);
        txaDescripcio.setRows(5);
        txaDescripcio.setText("descripcio");
        jScrollPane1.setViewportView(txaDescripcio);

        add(jScrollPane1);
        jScrollPane1.setBounds(306, 42, 392, 80);

        cmbMunicipi.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Alaró", "Inca" }));
        add(cmbMunicipi);
        cmbMunicipi.setBounds(60, 140, 230, 22);
        add(spnNumPersones);
        spnNumPersones.setBounds(60, 200, 64, 22);

        txtPreuNit.setText("100.01");
        add(txtPreuNit);
        txtPreuNit.setBounds(170, 200, 64, 22);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Imatges"));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 310, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 247, Short.MAX_VALUE)
        );

        add(jPanel1);
        jPanel1.setBounds(50, 300, 320, 270);

        btnBack.setText("< Back");
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });
        add(btnBack);
        btnBack.setBounds(10, 10, 72, 23);

        btnUpdate.setText("Update");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });
        add(btnUpdate);
        btnUpdate.setBounds(630, 230, 72, 23);

        pnlServeis.setBorder(javax.swing.BorderFactory.createTitledBorder("Serveis"));

        chkPiscina.setText("Piscina");

        chkMascotes.setText("Admet mascotes");

        chkAscensor.setText("Ascensor");

        chkAireAcondicionat.setText("Aire aconcidionat");

        chkAparcament.setText("Aparcament");

        chkWifi.setText("Wifi");

        javax.swing.GroupLayout pnlServeisLayout = new javax.swing.GroupLayout(pnlServeis);
        pnlServeis.setLayout(pnlServeisLayout);
        pnlServeisLayout.setHorizontalGroup(
            pnlServeisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlServeisLayout.createSequentialGroup()
                .addGroup(pnlServeisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlServeisLayout.createSequentialGroup()
                        .addComponent(chkPiscina)
                        .addGap(68, 68, 68)
                        .addComponent(chkAireAcondicionat))
                    .addGroup(pnlServeisLayout.createSequentialGroup()
                        .addGroup(pnlServeisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(chkAscensor)
                            .addComponent(chkMascotes))
                        .addGap(18, 18, 18)
                        .addGroup(pnlServeisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(chkAparcament)
                            .addComponent(chkWifi))))
                .addGap(0, 46, Short.MAX_VALUE))
        );
        pnlServeisLayout.setVerticalGroup(
            pnlServeisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlServeisLayout.createSequentialGroup()
                .addGroup(pnlServeisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(chkPiscina)
                    .addComponent(chkAireAcondicionat))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlServeisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(chkAparcament)
                    .addComponent(chkMascotes))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlServeisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(chkWifi)
                    .addComponent(chkAscensor))
                .addGap(0, 15, Short.MAX_VALUE))
        );

        add(pnlServeis);
        pnlServeis.setBounds(310, 150, 300, 110);

        txaComentaris.setColumns(20);
        txaComentaris.setRows(5);
        txaComentaris.setText("Comentaris");
        jScrollPane2.setViewportView(txaComentaris);

        add(jScrollPane2);
        jScrollPane2.setBounds(420, 300, 300, 320);

        txtValoracio.setEditable(false);
        txtValoracio.setText("5");
        add(txtValoracio);
        txtValoracio.setBounds(50, 250, 30, 22);

        jLabel1.setText("stars");
        add(jLabel1);
        jLabel1.setBounds(90, 250, 60, 20);

        btnPrevImage.setText("<");
        add(btnPrevImage);
        btnPrevImage.setBounds(280, 590, 40, 23);

        btnNextImage.setText(">");
        add(btnNextImage);
        btnNextImage.setBounds(330, 590, 40, 23);

        chkAutoMan.setText("Auto");
        add(chkAutoMan);
        chkAutoMan.setBounds(70, 590, 85, 20);

        lblImageFileName.setText("Nom del fitxer de imatge i tamany en KB");
        lblImageFileName.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        add(lblImageFileName);
        lblImageFileName.setBounds(80, 630, 260, 30);

        jButton1.setText("Info Imagen");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        add(jButton1);
        jButton1.setBounds(190, 280, 94, 23);
    }// </editor-fold>//GEN-END:initComponents

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        mainJFrame.showPnlLlistaAllotjaments();
    }//GEN-LAST:event_btnBackActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        allotjament.setNom(txtNom.getText());
        allotjament.setAdresa(txtAdresa.getText());
        allotjament.setMunicipi(cmbMunicipi.getSelectedItem().toString());
        allotjament.setId_municipi(cmbMunicipi.getSelectedIndex() + 1);  // ERROR!!!
        allotjament.setDescripcio(txaDescripcio.getText());
        try {
            spnNumPersones.commitEdit();
        } catch (java.text.ParseException e) {
            e.printStackTrace();
        }
        allotjament.setNum_persones((Integer) spnNumPersones.getValue());
        float preuPerNit = Float.parseFloat(txtPreuNit.getText());
        allotjament.setPreu_per_nit(preuPerNit);

        da.updateAllojtament(allotjament);

        int[] serveis = new int[6];
        if (chkPiscina.isSelected()) {
            serveis[0] = 1;
        }
        if (chkMascotes.isSelected()) {
            serveis[1] = 1;
        }
        if (chkAireAcondicionat.isSelected()) {
            serveis[2] = 1;
        }
        if (chkAscensor.isSelected()) {
            serveis[3] = 1;
        }
        if (chkAparcament.isSelected()) {
            serveis[4] = 1;
        }
        if (chkWifi.isSelected()) {
            serveis[5] = 1;
        }
        da.updateServeisAllotjament(allotjament.getId(), serveis);

        JOptionPane.showMessageDialog(mainJFrame, "Allotjament updated correctly.");
        mainJFrame.showPnlLlistaAllotjaments();

    }//GEN-LAST:event_btnUpdateActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
       JOptionPane.showMessageDialog(this, da.getImagenes().get(0).getId());
       ImageIcon imageIcon = new ImageIcon(da.getImagenes().get(0).getImagen());
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnNextImage;
    private javax.swing.JButton btnPrevImage;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JCheckBox chkAireAcondicionat;
    private javax.swing.JCheckBox chkAparcament;
    private javax.swing.JCheckBox chkAscensor;
    private javax.swing.JCheckBox chkAutoMan;
    private javax.swing.JCheckBox chkMascotes;
    private javax.swing.JCheckBox chkPiscina;
    private javax.swing.JCheckBox chkWifi;
    private javax.swing.JComboBox<String> cmbMunicipi;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblImageFileName;
    private javax.swing.JPanel pnlServeis;
    private javax.swing.JSpinner spnNumPersones;
    private javax.swing.JTextArea txaComentaris;
    private javax.swing.JTextArea txaDescripcio;
    private javax.swing.JTextField txtAdresa;
    private javax.swing.JTextField txtNom;
    private javax.swing.JTextField txtPreuNit;
    private javax.swing.JTextField txtValoracio;
    // End of variables declaration//GEN-END:variables

}
