package com.university.cca.dialogs.menu.view;

import java.io.File;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.university.cca.buttons.ExportToCSVButton;
import com.university.cca.charts.AmbientStatsPieChart;
import com.university.cca.entities.AmbientStatistics;
import com.university.cca.files.csv.AmbientCSVWriter;
import com.university.cca.frames.AppMainFrame;
import com.university.cca.services.AmbientStatisticsService;
import com.university.cca.tables.AmbientStatsTableModel;
import com.university.cca.tables.TableHeaderRenderer;
import com.university.cca.tables.TablesUtil;
import com.university.cca.util.CCAUtils;
import com.university.cca.util.FilesUtil;
import com.university.cca.util.MouseCursorUtil;

/**
 * Holds an information for the ambients statistics into a table without an option to update.
 * 
 * @author Konstantin Rusev
 * @version 1.0
 */
public class ShowAmbientsStatisticsDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(ShowAmbientsStatisticsDialog.class);

	private static final String EXPORT_CSV_FILE_NAME = "export.csv";
	private static final String TITLE = "Show Ambients Statistics Dialog";
	private static final boolean IS_VISIBLE 		 = true;
	private static final boolean IS_MODAL 			 = true;
	private static final boolean IS_VIEWPORT_ENABLED = true;
	
	private static final int HEIGHT_DIALOG = (int) (CCAUtils.getScreenSize().height / 1.2);
	private static final int WIDTH_DIALOG  = (int) (CCAUtils.getScreenSize().width / 1.4);
	
	private static final String TABLE_NAME = "Ambients Statistics Table";
	private static final int TABLE_ROWS_HEIGHT = 75;
	private static final double[] TABLE_COLUMNS_WIDTH = {20, 20, 20, 20, 20};
	
	private static final int BORDER = 3;
	
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
		
		JPanel buttonPanel = createPanel();
		buttonPanel.add(createExportToCSVButton());
        
        JPanel tablePanel = createPanel();
        tablePanel.add(createStatisticsTable());
        
        JPanel diagramPanel = createPanel();
        diagramPanel.add(createStatisticsDiagram());
        
        dialogPanel.add(buttonPanel);
        dialogPanel.add(tablePanel);
        dialogPanel.add(diagramPanel);

        this.getContentPane().add(dialogPanel);
	}
	
	private JButton createExportToCSVButton() {
		ExportToCSVButton exportButton = new ExportToCSVButton(getParentFrame());
		AmbientStatistics ambientStats = AmbientStatisticsService.getAmbientStatistics();
		
		// If the total count is zero, then there are no statistics to export
		if (!AmbientStatisticsService.hasStatistics(ambientStats)) {
			exportButton.setEnabled(ExportToCSVButton.BUTTON_DISABLED);
			exportButton.setToolTipText(ExportToCSVButton.BUTTON_TOOL_TIP_DISABLED);
		}

		exportButton.addActionListener(event -> {
			JFileChooser fileChooser = createFileSaveUsDialog();
			int userSelection = fileChooser.showSaveDialog(this);
			
			if (userSelection == JFileChooser.APPROVE_OPTION) {
				File file = fileChooser.getSelectedFile();

				if (file.exists()) {					
					FilesUtil.createFileExistsDialog(this);
					logger.error("Export ambient statistics file with that name already exists. File: {}", file.getPath());
				} else {
					AmbientCSVWriter.writeAmbientStatisticsToCsv(ambientStats, file.getPath());
					FilesUtil.createFileSavedDialog(this, file.getPath());
					logger.info("Ambient Statistics Exported! User selected option: {}. File: {}", userSelection, file.getPath());					
				}
	        } else {
	        	logger.info("Ambient Statistics File Save Us Dialog is closed without saving a file. User selected option: {}", userSelection);
	        }
		});
		
		return exportButton;
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

		// set the preferred scrollable size of the table
		ambientStatsTable.setPreferredScrollableViewportSize(ambientStatsTable.getPreferredSize());
		ambientStatsTable.setFillsViewportHeight(IS_VIEWPORT_ENABLED);
		
		// create and customize the scroll panel of the table
		JScrollPane ambientStatsScrollPane = new JScrollPane(ambientStatsTable);
		ambientStatsScrollPane.setPreferredSize(ambientStatsTable.getPreferredSize());
		
		return ambientStatsScrollPane;
	}

	private JPanel createStatisticsDiagram() {
		AmbientStatistics ambientStats = AmbientStatisticsService.getAmbientStatistics();

		JFreeChart ambientStatsChart = AmbientStatsPieChart.createChart(ambientStats);
		
		return new ChartPanel(ambientStatsChart); 
	}
	
	private JPanel createPanel() {
		JPanel panel = new JPanel();

        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(BORDER, BORDER, BORDER, BORDER));
        panel.add(Box.createHorizontalGlue());
        
        return panel;
	}
	
	private JFileChooser createFileSaveUsDialog() {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setCursor(MouseCursorUtil.getMouseHand());

		fileChooser.setDialogTitle("Specify a file to save the ambient statistics"); 
		fileChooser.setApproveButtonToolTipText("Click here to save the file");
		fileChooser.setToolTipText("File save us dialog");
		
		// predefine the file name of the file save us dialog
		fileChooser.setSelectedFile(new File(EXPORT_CSV_FILE_NAME));
		
		return fileChooser;
	}
	
	// Getters
	public AppMainFrame getParentFrame() {
		return this.parentFrame;
	}
}
