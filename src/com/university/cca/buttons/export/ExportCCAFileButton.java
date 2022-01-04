package com.university.cca.buttons.export;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.university.cca.constants.Constants;
import com.university.cca.dialogs.menu.file.ExportDialog;
import com.university.cca.exceptions.FileReadFailedException;
import com.university.cca.files.cca.AmbientCCAReader;
import com.university.cca.files.cca.AmbientCCAWriter;
import com.university.cca.frames.AppMainFrame;
import com.university.cca.util.FilesUtil;
import com.university.cca.util.MouseCursorUtil;

/**
 * Button that exports the already generated CCA file and scenario.
 * 
 * @author Konstantin Rusev
 * @version 1.0
 */
public class ExportCCAFileButton extends JButton implements ActionListener {
	
	// System-related constants
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(ExportCCAFileButton.class);
	
	// Constants related to the export CCA button
	private static final String BUTTON_NAME		= "Export CCA File";
	private static final String BUTTON_TOOL_TIP = "Export the CCA Scenario";
	private static final boolean IS_FOCUSABLE 	= false;
	private static final boolean IS_ENABLED 	= false;
	
	// Constants related to the export CCA File Save Us Dialog
	private static final String CCA_FILE_SAVE_US_DIALOG_TITLE = "Specify a file to export the CCA scenario";
	private static final String CCA_FILE_SAVE_US_DIALOG_TOOLTIP = "File save us dialog";
	private static final String CCA_FILE_SAVE_US_DIALOG_APPROVED_TOOLTIP = "Click here to save into a file";
	private static final String EXPORT_CCA_FILE_NAME = "scenario.cca";
	
	private static final String CCA_SCENARIO_NOT_FOUND = "The CCA scenario is not generated! \n Please, generate the CCA scenario and try again! \n";
	
	private AppMainFrame parentFrame;
	private ExportDialog parentDialog;
	
    public ExportCCAFileButton(AppMainFrame parentFrame, ExportDialog parentDialog) {
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
		
		try {
			exportCCAScenario(AmbientCCAReader.read());
		} catch (FileNotFoundException fileNotFoundEx) {
			FilesUtil.createFileNotFoundDialog(getParentDialog(), CCA_SCENARIO_NOT_FOUND);
			this.setEnabled(IS_ENABLED);
		} catch (FileReadFailedException fileReadFailedEx) {
			FilesUtil.createUnexpectedErrorDialog(getParentDialog());
			this.setEnabled(IS_ENABLED);
		}
	}
	
	private void exportCCAScenario(String dataToExport) {
		JFileChooser fileChooser = createFileSaveUsDialog();
		int userSelection = fileChooser.showSaveDialog(getParentDialog());
		
		if (userSelection == JFileChooser.APPROVE_OPTION) {
			File file = fileChooser.getSelectedFile();

			if (file.exists()) {
				FilesUtil.createFileExistsDialog(getParentDialog());
				logger.error("Export CCA file with that name already exists. File: {}", file.getPath());
			} else {
				AmbientCCAWriter.write(file.getPath(), dataToExport);
				FilesUtil.createFileSavedDialog(getParentDialog(), file.getPath());
				logger.info("CCA Scenario Exported! User selected option: {}. File: {}", userSelection, file.getPath());					
			}
        }
	}
	
	private JFileChooser createFileSaveUsDialog() {
		JFileChooser fileChooser = new JFileChooser(Constants.USER_DEFAULT_DIR);
		fileChooser.setCursor(MouseCursorUtil.getMouseHand());

		fileChooser.setDialogTitle(CCA_FILE_SAVE_US_DIALOG_TITLE); 
		fileChooser.setToolTipText(CCA_FILE_SAVE_US_DIALOG_TOOLTIP);
		fileChooser.setApproveButtonToolTipText(CCA_FILE_SAVE_US_DIALOG_APPROVED_TOOLTIP);
		
		// predefine the file name of the file save us dialog
		fileChooser.setSelectedFile(new File(EXPORT_CCA_FILE_NAME));
		
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
