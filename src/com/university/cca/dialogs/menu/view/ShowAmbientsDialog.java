package com.university.cca.dialogs.menu.view;

import java.awt.BorderLayout;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.JTableHeader;

import com.university.cca.entities.Ambient;
import com.university.cca.files.csv.AmbientCSVReader;
import com.university.cca.tables.AmbientTableModel;
import com.university.cca.tables.TableHeaderRenderer;
import com.university.cca.tables.TablesUtil;
import com.university.cca.util.CCAUtils;
import com.university.cca.util.MouseCursorUtil;

/**
 * The dialog that holds the information for the ambients into a table with an option to update their information.
 * 
 * @author Konstantin Rusev
 * @version 1.0
 */
public class ShowAmbientsDialog extends JDialog {
	
	private static final long serialVersionUID = 1L;

	private static final String TITLE = "Show All Ambients Table Dialog";
	private static final boolean IS_VISIBLE = true;
	private static final boolean IS_MODAL = true;
	
	private static final int HEIGHT_DIALOG = CCAUtils.getScreenSize().height - 100;
	private static final int WIDTH_DIALOG = CCAUtils.getScreenSize().width;
	
	private static final String TABLE_NAME = "Ambients Table";
	private static final int TABLE_ROWS_HEIGHT = 30;
	private static final double[] TABLE_COLUMNS_WIDTH = {17, 15, 15, 10, 10, 8, 17, 8};
	
	private JFrame parentFrame;

	public ShowAmbientsDialog(JFrame parentFrame) {
        super(parentFrame, TITLE, IS_MODAL);
        this.parentFrame = parentFrame;
        
        addDialogContent();
        
        this.setSize(WIDTH_DIALOG, HEIGHT_DIALOG);
        this.setLocationRelativeTo(parentFrame);
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        this.setVisible(IS_VISIBLE);
	}
	
	private void addDialogContent() {
		JPanel dialogPanel = new JPanel();
        dialogPanel.setLayout(new BoxLayout(dialogPanel, BoxLayout.Y_AXIS));
        
		dialogPanel.add(createAmbientsTable(), BorderLayout.CENTER);

        this.getContentPane().add(dialogPanel);
	}
	
	private JScrollPane createAmbientsTable() {
		List<Ambient> ambients = AmbientCSVReader.getAllAmbientsSortedByName();
		
		AmbientTableModel ambientTableModel = new AmbientTableModel(ambients);
		JTable ambientTable = new JTable(ambientTableModel);
		
		ambientTable.setName(TABLE_NAME);
		ambientTable.setCursor(MouseCursorUtil.getMouseHand());
		
		// set row and column size
		TablesUtil.setTableRowsHeight(ambientTable, TABLE_ROWS_HEIGHT);
		TablesUtil.setTableColumnsWidth(ambientTable, WIDTH_DIALOG, TABLE_COLUMNS_WIDTH);
		
		// set custom table header
		JTableHeader tableHeader = ambientTable.getTableHeader();
		ambientTable.getTableHeader().setDefaultRenderer(new TableHeaderRenderer(tableHeader.getDefaultRenderer()));
		
		// enable column sorting of the ambients table
		ambientTable.setAutoCreateRowSorter(AmbientTableModel.IS_SORT_AVAILABLE);
		
		return new JScrollPane(ambientTable);
	}

	// Getters
	public JFrame getParentFrame() {
		return this.parentFrame;
	}
}
