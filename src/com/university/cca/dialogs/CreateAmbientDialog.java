package com.university.cca.dialogs;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.university.cca.buttons.CancelDialogButton;
import com.university.cca.entities.Ambient;
import com.university.cca.enums.AmbientType;
import com.university.cca.files.csv.AmbientCSVReader;
import com.university.cca.files.csv.AmbientCSVWriter;
import com.university.cca.util.CreateAmbientUtil;

public class CreateAmbientDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	private static final int GRID_ROWS = 0;
	private static final int GRID_COLS = 2;
	private static final int TEXT_FIELD_SIZE = 16;

	private JTextField ambientNameTextField;
	private JTextField ambientLocationTextField;
	private JTextField ambientGpsLatitudeTextField;
	private JTextField ambientGpsLongitudeTextField;
	private JCheckBox staticCheckBox;
	private JComboBox<String> parentAmbientsComboBox;
	
	public CreateAmbientDialog(JFrame parent, String title, String message, AmbientType ambientType) {
        super(parent, title, true);
        
        addDialogContent(message, ambientType);
        
        this.pack();
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        this.setVisible(true);
	}
	
	private void addDialogContent(String mainDialogMessage, AmbientType ambientType) {
        JPanel dialogPanel = new JPanel();
        dialogPanel.setLayout(getGridLayout());

        // first row of the dialog
        dialogPanel.add(new JLabel(mainDialogMessage));
        dialogPanel.add(new JLabel());
        
        // second row of the dialog
        ambientNameTextField = createTextField();
        dialogPanel.add(createLabel("Ambient Name:"));
        dialogPanel.add(ambientNameTextField);

        // third row of the dialog
        ambientLocationTextField = createTextField();
        dialogPanel.add(createLabel("Ambient Location:"));
        dialogPanel.add(ambientLocationTextField);
        
        // fourth row of the dialog
        ambientGpsLatitudeTextField = createTextField();
        ambientGpsLatitudeTextField.setToolTipText("Example: 42.135652");
        dialogPanel.add(createLabel("Ambient GPS Latitude:"));
        dialogPanel.add(ambientGpsLatitudeTextField);
        
        // fifth row of the dialog
        ambientGpsLongitudeTextField = createTextField();
        ambientGpsLongitudeTextField.setToolTipText("Example: 24.753942");
        dialogPanel.add(createLabel("Ambient GPS Longitude:"));
        dialogPanel.add(ambientGpsLongitudeTextField);

        // sixth row of the dialog
        staticCheckBox = new JCheckBox("", true);
        dialogPanel.add(createLabel("Static Ambient:"));
        dialogPanel.add(staticCheckBox);
        
        // seventh row of the dialog
        parentAmbientsComboBox = createComboBox();
        parentAmbientsComboBox.setSelectedIndex(-1); // set default empty value
        dialogPanel.add(createLabel("Parent Ambient Name:"));
        dialogPanel.add(parentAmbientsComboBox);
        
        // eight row of the dialog with the buttons      
        JButton createAmbientButton = CreateAmbientUtil.createAmbientButton();
        dialogPanel.add(createAmbientButton);
        dialogPanel.add(new CancelDialogButton(this));
        
        createAmbientButton.addActionListener(new ActionListener() {

        	@Override
			public void actionPerformed(ActionEvent e) {
				
				String name = ambientNameTextField.getText().trim();
				String location = ambientLocationTextField.getText().trim();
				String latitude = ambientGpsLatitudeTextField.getText().trim();
				String longitude = ambientGpsLongitudeTextField.getText().trim();
				boolean isStatic = staticCheckBox.isSelected();
				Object parentAmbient = parentAmbientsComboBox.getSelectedItem();
				
				if (CreateAmbientUtil.isValidAmbient(name, location, latitude, longitude, parentAmbient)) {
					Ambient ambient = CreateAmbientUtil.constructAmbient(name, location, latitude, longitude, isStatic, parentAmbient, ambientType);
					
					AmbientCSVWriter.writeAmbientToCsv(ambient);
					
					// TODO: Writing to CCA file here
					
					CreateAmbientUtil.createSuccessDialog(getCurrentDialog());
					getCurrentDialog().dispose();
				} else {
					CreateAmbientUtil.createErrorDialog(getCurrentDialog());
				}
			}
		});
        
        this.getContentPane().add(dialogPanel);
	}
	
	/**
	 * @return set up and return the combo box with all ambient names that are already created.
	 */
	private JComboBox<String> createComboBox() {
		String[] parentAmbientNames = AmbientCSVReader.getAmbientNames();
		
		return new JComboBox<>(parentAmbientNames);
	}
	
	/**
	 * @return set up and return the labels of the create ambient dialog.
	 */
	private JLabel createLabel(String labelName) {
		return new JLabel(labelName, JLabel.CENTER);
	}
	
	/**
	 * @return set up and return the input fields of the create ambient dialog.
	 */
	private JTextField createTextField() {
		return new JTextField(TEXT_FIELD_SIZE);
	}
	
	/**
	 * @return set up and return the layout of the create ambient dialog.
	 */
	private GridLayout getGridLayout() {
		GridLayout layout = new GridLayout(GRID_ROWS, GRID_COLS);
        layout.setHgap(10);
        layout.setVgap(10);
        
        return layout;
	}
	
	/**
	 * @return an object of the current instance.
	 */
	public CreateAmbientDialog getCurrentDialog() {
		return this;
	}
}
