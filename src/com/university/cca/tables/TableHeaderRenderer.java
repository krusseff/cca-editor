package com.university.cca.tables;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

/**
 * Custom table header for all of the tables created into the CCA Editor application.
 * 
 * @author Konstantin Rusev
 * @version 1.0
 */
public class TableHeaderRenderer implements TableCellRenderer {
	
	private static final boolean HAS_OPAQUE = true;
	private static final String CONSOLAS = "Consolas";
	
    private TableCellRenderer tableCellRenderer;
    
    public TableHeaderRenderer(TableCellRenderer tableCellRenderer) {
        this.tableCellRenderer = tableCellRenderer;
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, 
    											   boolean hasFocus, int row, int column) {
    	
        Component comp = this.tableCellRenderer.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
 
        if (comp instanceof JLabel) {
            JLabel headerLabel = (JLabel) comp;
            
            headerLabel.setOpaque(HAS_OPAQUE);
            headerLabel.setForeground(Color.WHITE);
            headerLabel.setBackground(Color.BLACK);
            headerLabel.setFont(new Font(CONSOLAS, Font.BOLD, 14));
            headerLabel.setBorder(BorderFactory.createEtchedBorder());
        }
         
        return comp;
    }

    // Getters
	public TableCellRenderer getTableCellRenderer() {
		return this.tableCellRenderer;
	}
}
