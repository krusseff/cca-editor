package com.university.cca.tables;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.university.cca.entities.AmbientStatistics;

/**
 * A table with different ambients statistics available into the CCA Editor application.
 * 
 * @author Konstantin Rusev
 * @version 1.0
 */
public class AmbientStatsTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(AmbientStatsTableModel.class);
	
	private static final boolean IS_CELL_EDITABLE = false;
	
	// table column indexes
	private static final int COLUMN_TOTAL_INDEX 	 = 0;
    private static final int COLUMN_STATIC_INDEX	 = 1;
    private static final int COLUMN_NON_STATIC_INDEX = 2;
    private static final int COLUMN_ACTIVE_INDEX	 = 3;
    private static final int COLUMN_INACTIVE_INDEX 	 = 4;
    
    private final transient List<AmbientStatistics> ambientStatistics;
    
	private static final String[] columnNames = new String[] {
		"Total", 
		"Static", 
		"Non-static", 
		"Active", 
		"Inactive", 
    };
	
    public AmbientStatsTableModel(List<AmbientStatistics> ambientStatistics) {
        this.ambientStatistics = ambientStatistics;
    }
    
    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }
    
    @Override
    public Class<?> getColumnClass(int columnIndex) {
        if (this.ambientStatistics.isEmpty()) {
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
        return this.ambientStatistics.size();
    }
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
    	Object columnResult = null;
    	AmbientStatistics ambientStatsRow = this.ambientStatistics.get(rowIndex);
         
        switch (columnIndex) {
	        case COLUMN_TOTAL_INDEX:
	            columnResult = ambientStatsRow.getTotalCount();
	            break;
	        case COLUMN_STATIC_INDEX:
	            columnResult = ambientStatsRow.getStaticCount();
	            break;
	        case COLUMN_NON_STATIC_INDEX:
	            columnResult = ambientStatsRow.getNonStaticCount();
	            break;
	        case COLUMN_ACTIVE_INDEX:
	            columnResult = ambientStatsRow.getActiveCount();
	            break;
	        case COLUMN_INACTIVE_INDEX:
	            columnResult = ambientStatsRow.getInactiveCount();
	            break;
	        default:
	        	columnResult = null;
	        	logger.error("Invalid value of column index: {} on show ambients statistics table.", columnIndex);
        }
         
        return columnResult;
    }
    
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return IS_CELL_EDITABLE;
    }
}
