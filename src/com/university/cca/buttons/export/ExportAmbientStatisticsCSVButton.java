package com.university.cca.buttons.export;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.university.cca.constants.Constants;
import com.university.cca.dialogs.menu.file.ExportDialog;
import com.university.cca.entities.AmbientStatistics;
import com.university.cca.files.csv.AmbientCSVWriter;
import com.university.cca.frames.AppMainFrame;
import com.university.cca.services.AmbientStatisticsService;
import com.university.cca.util.FilesUtil;
import com.university.cca.util.MouseCursorUtil;

/**
 * Button that exports ambient statistics in CSV format if they're available.
 * 
 * @author Konstantin Rusev
 * @version 1.0
 */
public class ExportAmbientStatisticsCSVButton extends JButton implements ActionListener {

	// System-related constants
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(ExportAmbientStatisticsCSVButton.class);
	
	// Constants related to the export ambient statistics CSV button
	private static final String BUTTON_NAME		= "Export Ambient Statistics";
	private static final String BUTTON_TOOL_TIP = "Export the Ambient Statistics in CSV Format";
	private static final boolean IS_FOCUSABLE 	= false;
	private static final boolean IS_ENABLED 	= false;

	// Constants related to the export ambient statistics CSV Save Us Dialog
	private static final String FILE_SAVE_US_DIALOG_TITLE = "Specify a file to export the ambient statistics";
	private static final String FILE_SAVE_US_DIALOG_TOOLTIP = "File save us dialog";
	private static final String FILE_SAVE_US_DIALOG_APPROVED_TOOLTIP = "Click here to save into a file";
	private static final String EXPORT_CSV_FILE_NAME = "ambient_statistics.csv";
	
	private static final String STATISTICS_NOT_FOUND = "There are no ambient statistics available! \n Please, create ambients and try again! \n";
	
	private AppMainFrame parentFrame;
	private ExportDialog parentDialog;
	
    public ExportAmbientStatisticsCSVButton(AppMainFrame parentFrame, ExportDialog parentDialog) {
    	this.parentFrame  = parentFrame;
    	this.parentDialog = parentDialog;
    	
        this.setText(BUTTON_NAME);
        this.setIcon(new ImageIcon(Constants.EXPORT_BUTTON_ICON_PATH));
        
        this.setCursor(MouseCursorUtil.getMouseHand());
        this.setIconTextGap(Constants.ICON_GAP_SIZE);
        this.setToolTipText(BUTTON_TOOL_TIP);
        this.setFocusable(IS_FOCUSABLE);
        
        this.addActionListener(this);
    }
	
	@Override
	public void actionPerformed(ActionEvent event) {
		AmbientStatistics statistics = AmbientStatisticsService.getAmbientStatistics();

		if (!AmbientStatisticsService.hasStatistics(statistics)) {
			FilesUtil.createFileNotFoundDialog(getParentDialog(), STATISTICS_NOT_FOUND);
			this.setEnabled(IS_ENABLED);
		} else {
			exportAmbientStatistics(statistics);
		}
	}
	
	private void exportAmbientStatistics(AmbientStatistics statistics) {
		JFileChooser fileChooser = createFileSaveUsDialog();
		int userSelection = fileChooser.showSaveDialog(getParentDialog());

		if (userSelection == JFileChooser.APPROVE_OPTION) {
			File file = fileChooser.getSelectedFile();

			if (file.exists()) {				
				FilesUtil.createFileExistsDialog(getParentDialog());
				logger.error("Export Ambient Statistics CSV file with that name already exists. File: {}", file.getPath());
			} else {
				AmbientCSVWriter.writeAmbientStatisticsToCsv(statistics, file.getPath());
				FilesUtil.createFileSavedDialog(getParentDialog(), file.getPath());
				logger.info("The Ambient Statistics in CSV Format Exported! User selected option: {}. File: {}", userSelection, file.getPath());
			}
        }
	}
	
	private JFileChooser createFileSaveUsDialog() {
		JFileChooser fileChooser = new JFileChooser(Constants.USER_DEFAULT_DIR);
		fileChooser.setCursor(MouseCursorUtil.getMouseHand());

		fileChooser.setDialogTitle(FILE_SAVE_US_DIALOG_TITLE); 
		fileChooser.setToolTipText(FILE_SAVE_US_DIALOG_TOOLTIP);
		fileChooser.setApproveButtonToolTipText(FILE_SAVE_US_DIALOG_APPROVED_TOOLTIP);
		
		// predefine the file name of the file save us dialog
		fileChooser.setSelectedFile(new File(EXPORT_CSV_FILE_NAME));
		
		return fileChooser;
	}
	
	// Getters
	public AppMainFrame getParentFrame() {
		return this.parentFrame;
	}

	public ExportDialog getParentDialog() {
		return this.parentDialog;
	}
}
