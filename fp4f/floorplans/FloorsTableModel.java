/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fp4f.floorplans;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author river
 */
public class FloorsTableModel extends AbstractTableModel {

    public FloorsTableModel() {
        this.Data = new ArrayList<FloorItem>();
    }
    
    public ArrayList<FloorItem> Data;
    
    @Override
    public int getRowCount() {
        return Data.size();
    }

    @Override
    public int getColumnCount() {
        return 1;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return Data.get(rowIndex);
    }
}