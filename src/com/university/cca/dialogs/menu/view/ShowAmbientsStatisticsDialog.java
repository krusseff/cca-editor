package com.university.cca.dialogs.menu.view;

import java.awt.BorderLayout;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;

import com.university.cca.charts.AmbientStatsPieChart;
import com.university.cca.entities.AmbientStatistics;
import com.university.cca.frames.AppMainFrame;
import com.university.cca.repositories.AmbientStatisticsRepository;
import com.university.cca.services.AmbientStatisticsService;
import com.university.cca.tables.AmbientStatsTableModel;
import com.university.cca.tables.TableHeaderRenderer;
import com.university.cca.tables.TablesUtil;
import com.university.cca.util.CCAUtils;
import com.university.cca.util.MouseCursorUtil;

/**
 * Holds an information for the ambients statistics into a table without an option to update.
 * 
 * @author Konstantin Rusev
 * @version 1.0
 */
public class ShowAmbientsStatisticsDialog extends JDialog {

	private static final long serialVersionUID = 1L;

	private static final String TITLE = "Show Ambients Statistics Dialog";
	private static final boolean IS_VISIBLE = true;
	private static final boolean IS_MODAL = true;
	
	private static final int HEIGHT_DIALOG = (int) (CCAUtils.getScreenSize().height / 1.2);
	private static final int WIDTH_DIALOG = (int) (CCAUtils.getScreenSize().width / 1.4);
	
	private static final String TABLE_NAME = "Ambients Statistics Table";
	private static final int TABLE_ROWS_HEIGHT = 35;
	private static final double[] TABLE_COLUMNS_WIDTH = {20, 20, 20, 20, 20};
	
	private AppMainFrame parentFrame;
	
	public ShowAmbientsStatisticsDialog(AppMainFrame parentFrame) {
        super(parentFrame, TITLE, IS_MODAL);
        this.parentFrame = parentFrame;
        
        addDialogContent();
        
        this.setSize(WIDTH_DIALOG, HEIGHT_DIALOG);
        this.setLocationRelativeTo(parentFrame);
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        this.setVisible(IS_VISIBLE);
	}
	
	private void addDialogContent() {
		JPanel dialogPanel = createPanel();
        
        JPanel tablePanel = createPanel();
        // TODO: tablePanel.add(new JButton("Export to CSV"), BorderLayout.LINE_END);
        tablePanel.add(createStatisticsTable(), BorderLayout.CENTER);
        
        JPanel diagramPanel = createPanel();
        diagramPanel.add(createStatisticsDiagram(), BorderLayout.CENTER);
        
        dialogPanel.add(tablePanel, BorderLayout.CENTER);
        dialogPanel.add(diagramPanel, BorderLayout.CENTER);

        this.getContentPane().add(dialogPanel);
	}
	
	private JScrollPane createStatisticsTable() {
		List<AmbientStatistics> ambientStats = AmbientStatisticsService.getAllAmbientStatistics();
		
		AmbientStatsTableModel ambientStatsTableModel = new AmbientStatsTableModel(ambientStats);
		JTable ambientStatsTable = new JTable(ambientStatsTableModel);
		
		ambientStatsTable.setName(TABLE_NAME);
		ambientStatsTable.setCursor(MouseCursorUtil.getMouseHand());
		
		// set row and column size
		TablesUtil.setTableRowsHeight(ambientStatsTable, TABLE_ROWS_HEIGHT);
		TablesUtil.setTableColumnsWidth(ambientStatsTable, WIDTH_DIALOG, TABLE_COLUMNS_WIDTH);
		
		// set custom table header
		JTableHeader tableHeader = ambientStatsTable.getTableHeader();
		ambientStatsTable.getTableHeader().setDefaultRenderer(new TableHeaderRenderer(tableHeader.getDefaultRenderer()));
		
		// set center alignment for all of the columns of the table	
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(JLabel.CENTER);
		ambientStatsTable.setDefaultRenderer(Integer.class, centerRenderer);
		
		return new JScrollPane(ambientStatsTable);
	}

	private JPanel createStatisticsDiagram() {
		AmbientStatistics ambientStats = AmbientStatisticsRepository.getAmbientStatistics();

		JFreeChart ambientStatsChart = AmbientStatsPieChart.createChart(ambientStats);
		
		return new ChartPanel(ambientStatsChart); 
	}
	
	private JPanel createPanel() {
		JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        
        return panel;
	}
	
	// Getters
	public AppMainFrame getParentFrame() {
		return this.parentFrame;
	}
}
