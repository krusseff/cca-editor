package com.university.cca.tables;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.university.cca.entities.MessageStatistics;

/**
 * A table with different messages statistics.
 * 
 * @author Konstantin Rusev
 * @version 1.0
 */
public class MessageStatsTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(MessageStatsTableModel.class);
	
	private static final boolean IS_CELL_EDITABLE = false;
	
	private static final int COLUMN_AMBIENT_NAME_INDEX 	 = 0;
    private static final int COLUMN_SENT_COUNT_INDEX	 = 1;
    private static final int COLUMN_RECEIVED_COUNT_INDEX = 2;
    
    private final transient List<MessageStatistics> messageStatistics;
    
	private static final String[] columnNames = new String[] {
		"Ambient name", 
		"Sent messages", 
		"Received messages"
    };
    
    public MessageStatsTableModel(List<MessageStatistics> messageStatistics) {
        this.messageStatistics = messageStatistics;
    }
    
    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }
    
    @Override
    public Class<?> getColumnClass(int columnIndex) {
        if (this.messageStatistics.isEmpty()) {
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
        return this.messageStatistics.size();
    }
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
    	Object columnResult = null;
    	MessageStatistics messageStatsRow = this.messageStatistics.get(rowIndex);
         
        switch (columnIndex) {
	        case COLUMN_AMBIENT_NAME_INDEX:
	            columnResult = messageStatsRow.getAmbientName();
	            break;
	        case COLUMN_SENT_COUNT_INDEX:
	            columnResult = messageStatsRow.getSentCount();
	            break;
	        case COLUMN_RECEIVED_COUNT_INDEX:
	            columnResult = messageStatsRow.getReceivedCount();
	            break;
	        default:
	        	columnResult = null;
	        	logger.error("Invalid value of column index: {} on show messages statistics table.", columnIndex);
        }
         
        return columnResult;
    }
    
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return IS_CELL_EDITABLE;
    }
}
