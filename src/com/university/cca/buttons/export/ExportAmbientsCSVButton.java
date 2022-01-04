package com.university.cca.buttons.export;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.university.cca.constants.Constants;
import com.university.cca.dialogs.menu.file.ExportDialog;
import com.university.cca.entities.Ambient;
import com.university.cca.files.csv.AmbientCSVReader;
import com.university.cca.files.csv.AmbientCSVWriter;
import com.university.cca.frames.AppMainFrame;
import com.university.cca.util.FilesUtil;
import com.university.cca.util.MouseCursorUtil;

/**
 * Button that exports the already created ambients in CSV format.
 * 
 * @author Konstantin Rusev
 * @version 1.0
 */
public class ExportAmbientsCSVButton extends JButton implements ActionListener {

	// System-related constants
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(ExportAmbientsCSVButton.class);

	// Constants related to the export Ambients CSV button
	private static final String BUTTON_NAME		= "Export Ambients";
	private static final String BUTTON_TOOL_TIP = "Export the Ambients in CSV Format";
	private static final boolean IS_FOCUSABLE 	= false;
	private static final boolean IS_ENABLED 	= false;
	
	// Constants related to the export Ambients CSV Save Us Dialog
	private static final String FILE_SAVE_US_DIALOG_TITLE = "Specify a file to export the ambients";
	private static final String FILE_SAVE_US_DIALOG_TOOLTIP = "File save us dialog";
	private static final String FILE_SAVE_US_DIALOG_APPROVED_TOOLTIP = "Click here to save into a file";
	private static final String EXPORT_CSV_FILE_NAME = "ambients.csv";
	
	private static final String AMBIENTS_NOT_FOUND = "There are no ambients created yet! \n Please, create ambients and try again! \n";

	private AppMainFrame parentFrame;
	private ExportDialog parentDialog;
	
    public ExportAmbientsCSVButton(AppMainFrame parentFrame, ExportDialog parentDialog) {
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
		List<Ambient> ambients = AmbientCSVReader.getAllAmbients();
		
		if (ambients.isEmpty()) {
			FilesUtil.createFileNotFoundDialog(getParentDialog(), AMBIENTS_NOT_FOUND);
			this.setEnabled(IS_ENABLED);
		} else {
			exportAmbients(ambients);
		}
	}
	
	private void exportAmbients(List<Ambient> ambients) {
		JFileChooser fileChooser = createFileSaveUsDialog();
		int userSelection = fileChooser.showSaveDialog(getParentDialog());
		
		if (userSelection == JFileChooser.APPROVE_OPTION) {
			File file = fileChooser.getSelectedFile();

			if (file.exists()) {
				FilesUtil.createFileExistsDialog(getParentDialog());
				logger.error("Export Ambients CSV file with that name already exists. File: {}", file.getPath());
			} else {
				AmbientCSVWriter.writeAmbientsToCsv(file.getPath(), ambients);
				FilesUtil.createFileSavedDialog(getParentDialog(), file.getPath());
				logger.info("The Ambients in CSV Format Exported! User selected option: {}. File: {}", userSelection, file.getPath());					
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
