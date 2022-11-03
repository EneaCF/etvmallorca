package spdvid.evtmallorca.tablemodels;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;
import spdvid.evtmallorca.dto.Allotjament;

/**
 *
 * @author Miguel
 */
public class AllotjamentTableModel extends AbstractTableModel {

    private ArrayList<Allotjament> allotjaments = new ArrayList<>();
    private String[] headers = new String[]{"id", "nom", "adresa", "municipi"};

    public AllotjamentTableModel(ArrayList<Allotjament> allotjaments) {
        this.allotjaments = allotjaments;
    }

    @Override
    public int getRowCount() {
        return allotjaments.size();
    }

    @Override
    public int getColumnCount() {
        return headers.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0 -> {
                return allotjaments.get(rowIndex).getId();
            }
            case 1 -> {
                return allotjaments.get(rowIndex).getNom();
            }
            case 2 -> {
                return allotjaments.get(rowIndex).getAdresa();
            }
            case 3 -> {
                return allotjaments.get(rowIndex).getMunicipi();
            }
            default ->
                throw new AssertionError();
        }

    }
    
    public Allotjament getValueAtRow(int rowIndex) {
        return allotjaments.get(rowIndex);
    }

    @Override
    public String getColumnName(int column) {
        return headers[column];
    }

}
