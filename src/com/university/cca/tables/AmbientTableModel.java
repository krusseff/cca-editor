package com.university.cca.tables;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.university.cca.entities.Ambient;

/**
 * The table with all ambients that are created into the CCA Editor.
 * 
 * @author Konstantin Rusev
 * @version 1.0
 */
public class AmbientTableModel extends AbstractTableModel {
	
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(AmbientTableModel.class);
	
	public static final boolean IS_SORT_AVAILABLE = true;
	private static final boolean IS_CELL_EDITABLE = true;
	
	private static final int COLUMN_NAME_INDEX 		= 0;
    private static final int COLUMN_TYPE_INDEX 		= 1;
    private static final int COLUMN_LOCATION_INDEX 	= 2;
    private static final int COLUMN_LATITUDE_INDEX 	= 3;
    private static final int COLUMN_LONGITUDE_INDEX = 4;
    private static final int COLUMN_STATIC_INDEX 	= 5;
    private static final int COLUMN_PARENT_INDEX 	= 6;

	private final List<Ambient> ambients;
	
	private static final String[] columnNames = new String[] {
		"Name", 
		"Type", 
		"Location", 
		"Latitude", 
		"Longitude", 
		"Static", 
		"Parent"
    };
    
    public AmbientTableModel(List<Ambient> ambients) {
        this.ambients = ambients;
    }
    
    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }
    
    @Override
    public Class<?> getColumnClass(int columnIndex) {
        if (this.ambients.isEmpty()) {
            return Object.class;
        }
        
        return getValueAt(0, columnIndex).getClass();
    }
    
    @Override
    public int getColumnCount() {
        return columnNames.length;
    }
    
    @Override
    public int getRowCount() {
        return this.ambients.size();
    }
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
    	Object columnResult = null;
    	Ambient ambientRow = this.ambients.get(rowIndex);
         
        switch (columnIndex) {
	        case COLUMN_NAME_INDEX:
	            columnResult = ambientRow.getName();
	            break;
	        case COLUMN_TYPE_INDEX:
	            columnResult = ambientRow.getAmbientType();
	            break;
	        case COLUMN_LOCATION_INDEX:
	            columnResult = ambientRow.getLocation();
	            break;
	        case COLUMN_LATITUDE_INDEX:
	            columnResult = ambientRow.getLatitude();
	            break;
	        case COLUMN_LONGITUDE_INDEX:
	            columnResult = ambientRow.getLongitude();
	            break;
	        case COLUMN_STATIC_INDEX:
	            columnResult = ambientRow.isStaticAmbient();
	            break;
	        case COLUMN_PARENT_INDEX:
	            columnResult = ambientRow.getParentAmbient();
	            break;
	        default:
	        	columnResult = null;
	        	logger.error("Invalid value of column index: {} on show all ambients table.", columnIndex);
        }
         
        return columnResult;
    }
    
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return IS_CELL_EDITABLE;
    }
}
