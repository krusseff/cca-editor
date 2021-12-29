package com.university.cca.tables;

import javax.swing.JTable;
import javax.swing.table.TableColumn;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Utility methods related to the UI tables of the CCA Editor application.
 * 
 * @author Konstantin Rusev
 * @version 1.0
 */
public class TablesUtil {
	
	private static final Logger logger = LoggerFactory.getLogger(TablesUtil.class);
	
	private TablesUtil() {
		// Prevent to create an object from this class
	}
	
	/**
	 * Method that sets the preffered row height of each row of a specific table.
	 */
	public static void setTableRowsHeight(JTable table, int tablePreferredHeight) {
		table.setRowHeight(tablePreferredHeight);
	}

	/**
	 * Method that sets the preffered column width of each column of a specific table.
	 */
	public static void setTableColumnsWidth(JTable table, 
											int tablePreferredWidth,
											double... percentages) {
	    double total = 0;
	    
	    for (int i = 0; i < table.getColumnModel().getColumnCount(); i++) {
	        total += percentages[i];
	    }
	 
	    for (int columnIndex = 0; columnIndex < table.getColumnModel().getColumnCount(); columnIndex++) {

	    	if (total != 0) {
	    		int columnWidth = (int)(tablePreferredWidth * (percentages[columnIndex] / total));
		    	
		    	TableColumn column = table.getColumnModel().getColumn(columnIndex);
		        column.setPreferredWidth(columnWidth);
	    	} else {
	    		logger.error(
    				"Not able to set width of a column: {} of a table: {}", 
    				columnIndex, 
    				table.getName()
				);
	    	}
	    }
	}
}
