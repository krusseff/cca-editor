package com.university.cca.dialogs;

import java.awt.GridLayout;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.university.cca.buttons.CancelDialogButton;
import com.university.cca.buttons.CreateAmbientButton;

public class CreateAmbientDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	private static final int GRID_ROWS = 0;
	private static final int GRID_COLS = 2;
	private static final int TEXT_FIELD_SIZE = 16;

	private JTextField ambientNameTextField;
	private JTextField ambientLocationTextField;
	
	// TODO: Value should be read from file with all existing ambients
	// CSV format or each parent name in new line
	String parentAmbients[] = {"Hotel 1", "Hospital 1", "Bus Station 1", "Port 1"};  
	
	public CreateAmbientDialog(JFrame parent, String title, String message) {
        super(parent, title, true);
        
        addDialogContent(message);
        
        this.pack();
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        this.setVisible(true);
	}
	
	private void addDialogContent(String mainDialogMessage) {
        JPanel dialogPanel = new JPanel();
        dialogPanel.setLayout(getGridLayout());

        // first row of the dialog
        dialogPanel.add(new JLabel(mainDialogMessage));
        dialogPanel.add(new JLabel());
        
        // second row of the dialog
        JLabel nameLabel = new JLabel("Ambient Name: ", JLabel.CENTER);
        ambientNameTextField = new JTextField(TEXT_FIELD_SIZE);
        ambientNameTextField.setToolTipText("Enter ambient name");
        dialogPanel.add(nameLabel);
        dialogPanel.add(ambientNameTextField);

        // third row of the dialog
        JLabel locationLabel = new JLabel("Ambient Location: ", JLabel.CENTER);
        ambientLocationTextField = new JTextField(TEXT_FIELD_SIZE);
        ambientLocationTextField.setToolTipText("Enter ambient location");
        dialogPanel.add(locationLabel);
        dialogPanel.add(ambientLocationTextField);

        // fourth row of the dialog
        JLabel staticLabel = new JLabel("Static Ambient: ", JLabel.CENTER);
        JCheckBox staticCheckBox = new JCheckBox("", true); // TODO: checkbox1.addItemListener(new ItemListener() {
        dialogPanel.add(staticLabel);
        dialogPanel.add(staticCheckBox);
        
        // fourth row of the dialog
        JLabel parentNameLabel = new JLabel("Parent Ambient Name: ", JLabel.CENTER);
        JComboBox<String> parentAmbientsComboBox = new JComboBox<String>(parentAmbients);
        parentAmbientsComboBox.setSelectedIndex(-1); // set default empty value
        dialogPanel.add(parentNameLabel);
        dialogPanel.add(parentAmbientsComboBox);
        
        // fifth row of the dialog with the buttons
        dialogPanel.add(createAmbientButton());
        dialogPanel.add(new CancelDialogButton(this));
        
        this.getContentPane().add(dialogPanel);
	}
	
	private CreateAmbientButton createAmbientButton() {
		// TODO: Added needed info to the constructor
		return new CreateAmbientButton(
			this
		);
	}
	
	private GridLayout getGridLayout() {
		GridLayout layout = new GridLayout(GRID_ROWS, GRID_COLS);
        layout.setHgap(10);
        layout.setVgap(10);
        
        return layout;
	}
}
