package com.university.cca.tables;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.university.cca.entities.Message;

/**
 * The table with all of the ambient messages that are created into the CCA Editor application.
 * 
 * @author Konstantin Rusev
 * @version 1.0
 */
public class MessageTableModel extends AbstractTableModel {
	
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(MessageTableModel.class);
	
	public static final boolean IS_SORT_AVAILABLE = true;
	private static final boolean IS_CELL_EDITABLE = true;
	
	// table column indexes
	private static final int COLUMN_SENDER_INDEX 	  = 0;
    private static final int COLUMN_RECIPIENT_INDEX   = 1;
    private static final int COLUMN_PASS_MSG_TO_INDEX = 2;
    private static final int COLUMN_RESPOND_TO_INDEX  = 3;
    private static final int COLUMN_MESSAGE_INDEX	  = 4;
	
	private final List<Message> ambientMessages;
	
	private static final String[] columnNames = new String[] {
		"Sender", 
		"Recipient", 
		"Pass message to", 
		"Respond to", 
		"Message"
    };
	
    public MessageTableModel(List<Message> ambientMessages) {
        this.ambientMessages = ambientMessages;
    }
    
    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }
    
    @Override
    public Class<?> getColumnClass(int columnIndex) {
        if (this.ambientMessages.isEmpty()) {
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
    	return this.ambientMessages.size();
    }
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
    	Object columnResult = null;
    	Message messageRow = this.ambientMessages.get(rowIndex);
         
        switch (columnIndex) {
	        case COLUMN_SENDER_INDEX:
	            columnResult = messageRow.getSenderAmbient();
	            break;
	        case COLUMN_RECIPIENT_INDEX:
	            columnResult = messageRow.getRecipientAmbient();
	            break;
	        case COLUMN_PASS_MSG_TO_INDEX:
	        	columnResult = messageRow.getPassMessageTo();
	        	break;
	        case COLUMN_RESPOND_TO_INDEX:
	            columnResult = messageRow.getRespondToMessage();
	            break;
	        case COLUMN_MESSAGE_INDEX:
	            columnResult = messageRow.getMessage();
	            break;
	        default:
	        	columnResult = null;
	        	logger.error("Invalid value of column index: {} on show all ambient messages table.", columnIndex);
        }
         
        return columnResult;
    }
    
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return IS_CELL_EDITABLE;
    }
}
